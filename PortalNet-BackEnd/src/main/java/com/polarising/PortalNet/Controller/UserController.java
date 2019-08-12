package com.polarising.PortalNet.Controller;


import java.util.ArrayList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.polarising.PortalNet.Forms.LoginCredentials;
import com.polarising.PortalNet.Repository.ClientRepository;
import com.polarising.PortalNet.Repository.WorkersRepository;
import com.polarising.PortalNet.Security.UserDetailsService;
import com.polarising.PortalNet.Security.UserPrincipal;
import com.polarising.PortalNet.Utilities.PortalNetHttpRequest;
import com.polarising.PortalNet.Utilities.TibcoService;
import com.polarising.PortalNet.Utilities.XMLParser.ParseBodyXML;
import com.polarising.PortalNet.jwt.JwtCreator;
import com.polarising.PortalNet.jwt.JwtResponse;
import com.polarising.PortalNet.model.Client;
import com.polarising.PortalNet.model.Workers;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	WorkersRepository workersRepository;
	
	@Autowired
	PortalNetHttpRequest portalNetHttpRequest;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	ParseBodyXML parseBodyXML;
	
	@Autowired
	TibcoService tibcoService;
	
	@Value("${portalnet.tibco.login.verification.soapAction}")
	private String loginSoapAction;
	
	@Value("${portalnet.tibco.login.verification.subPath}")
	private String loginSubPath;
	
	@Value("${portalnet.tibco.login.verification.specificVars}")
	private String[] getLoginVars;
	
	@Value("${portalnet.tibco.login.verification.soapRequestBody}")
	private String getLoginSoapRequestBody;

	@Value("${portalnet.tibco.client.getPersonalInfo.soapAction}")
	private String getClientSoapAction;
	
	@Value("${portalnet.tibco.client.getPersonalInfo.subPath}")
	private String getClientSubPath;
	
	@Value("${portalnet.tibco.client.getPersonalInfo.specificVars}")
	private String[] getClientInfoSpecificVars;
	
	@Value("${portalnet.tibco.client.getPersonalInfo.soapRequestBody}")
	private String getClientInfoSoapRequestBody;
	
	@Value("${portalnet.tibco.worker.getWorkerInfo.soapAction}")
	private String getWorkerSoapAction;
	
	@Value("${portalnet.tibco.worker.getWorkerInfo.subPath}")
	private String getWorkerSubPath;
	
	@Value("${portalnet.tibco.worker.getWorkerInfo.specificVars}")
	private String[] getWorkerInfoSpecificVars;
	
	@Value("${portalnet.tibco.login.port}")
	private int getLoginPort;
	
	//User login
	@PostMapping(path = "/home")
	public ResponseEntity<?> login(@RequestBody LoginCredentials user)
	{
		try{	
			String response;
			ArrayList<Map<String, String>> mapList;

			String[] credentials = tibcoService.login(user.getEmail(), user.getPassword());
			
			String requestUserSoapRequestBody = String.format(getClientInfoSoapRequestBody, credentials[0], credentials[1], credentials[0]);
			
			if (credentials[1].equalsIgnoreCase("client"))
			{
				response = portalNetHttpRequest.postToTibco(getClientSubPath, requestUserSoapRequestBody, getClientSoapAction, 9010);
				mapList = parseBodyXML.parseResponseXML(response, getClientInfoSpecificVars);
				Client client = new Client();
				client.setName(mapList.get(1).get("clientName"));
				client.setClientId(Integer.parseInt(credentials[0]));
				client.setPassword(passwordEncoder.encode(user.getPassword()));
				clientRepository.save(client);
			}
			else
			{
				response = portalNetHttpRequest.postToTibco(getWorkerSubPath, requestUserSoapRequestBody, getWorkerSoapAction, 9013);
				mapList = parseBodyXML.parseResponseXML(response, getWorkerInfoSpecificVars);
				workersRepository.save(new Workers(Integer.parseInt(mapList.get(1).get("workerNumber")), mapList.get(1).get("workerName"), mapList.get(1).get("email"), mapList.get(1).get("role"), passwordEncoder.encode(user.getPassword())));
			}
				
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials[0], user.getPassword()));
			
			//Storing the details of the currently authenticated user (changing, if there was already one)
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
			String jwt;
			String message = "Login bem sucedido.";
			
			if (userPrincipal.getAuthorities().toString().equals("[CLIENT]"))
				{
					String clientName = clientRepository.findByClientId(Integer.parseInt(userPrincipal.getUsername())).get(0).getName();
					int clientId = clientRepository.findByClientId(Integer.parseInt(userPrincipal.getUsername())).get(0).getClientId();
					jwt = new JwtCreator().createJWT(authentication, clientId, clientName);
					return new ResponseEntity<>(new JwtResponse(jwt, message, userPrincipal.getAuthorities(), clientId, clientName), HttpStatus.OK);
				}
				else if (userPrincipal.getAuthorities().toString().equals("[EMPLOYEE]") || userPrincipal.getAuthorities().toString().equals("[ADMIN]"))
				{
					String employeeName = workersRepository.findByEmployeeId(Integer.parseInt(userPrincipal.getUsername())).get(0).getName();
					int employeeId = workersRepository.findByEmployeeId(Integer.parseInt(userPrincipal.getUsername())).get(0).getEmployeeId();
					jwt = new JwtCreator().createJWT(authentication, employeeId, employeeName);
					return new ResponseEntity<>(new JwtResponse(jwt, message, userPrincipal.getAuthorities(), employeeId, employeeName), HttpStatus.OK);
				}
				else {
					throw new AuthenticationCredentialsNotFoundException("Não foi possível encontrar o role do user.");
				}		
		}
		catch(AuthenticationException e)
		{
			logger.error(e.getMessage());
			String message = "Password ou email errados.";
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		catch (IndexOutOfBoundsException e) {
			logger.error(e.getMessage());
			String message = "Erro a verificar email ou a criar token.";
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}
}
