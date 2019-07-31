package com.polarising.PortalNet.Controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
import com.polarising.PortalNet.Utilities.XMLParser.ParseBodyXML;
import com.polarising.PortalNet.jwt.JwtCreator;
import com.polarising.PortalNet.jwt.JwtResponse;
import com.polarising.PortalNet.model.Client;
import com.polarising.PortalNet.model.Workers;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
	
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
	
	//User login
	@PostMapping(path = "/home")
	public ResponseEntity<?> login(@RequestBody LoginCredentials user)
	{
		try{
			
			String requestLogin = String.format("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:wsdl=\"http://www.tibco.com/schemas/Portalnet_Beta_v1.0/Resources/Schemas/WSDL.xsd\">"
					+ "<soapenv:Header/>"
					+ "<soapenv:Body>"
					+ "<wsdl:Login_In email=\"%s\" password=\"%s\"/>"
					+ "</soapenv:Body>"
					+ "</soapenv:Envelope>", user.getEmail(),user.getPassword());			
			
			String soapAction = "/WSDL%2520Services/Aplication/Service.serviceagent/LoginEndpoint1/Verification";
			String response = portalNetHttpRequest.postToTibco("/WSDL%20Services/Aplication/Login%20Service.serviceagent/LoginEndpoint", requestLogin, soapAction, 9012);
			
			String[] loginVars = { "id" , "role" };
			String[] expectedVars = { "message" , "error"};
			ParseBodyXML parseBodyXML = new ParseBodyXML(); 
			
			ArrayList<Map<String, String>> mapList = parseBodyXML.parseResponseXML(response, expectedVars, loginVars);	
			for (Map<String, String> map : mapList) {
				for ( Map.Entry<String, String> entry : map.entrySet() ) {
					System.out.println(entry.getKey() + "=" + entry.getValue() );
				}				
			}
			
			if (!mapList.get(0).get("message").equals("SUCCESS"))
			{
				throw new AuthenticationCredentialsNotFoundException(mapList.get(0).get("message") + "--> TIBCO action: " + mapList.get(0).get("error"));
			}
			
			String id = mapList.get(1).get("id");
			
			String role = mapList.get(1).get("role");
			
			
			String requestUser = String.format("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:wsdl=\"http://www.tibco.com/schemas/Portalnet_Beta_v1.0/Resources/Schemas/WSDL.xsd\">"
						+  "<soapenv:Header/>"
						+ "<soapenv:Body>"
						+ "<wsdl:Standard_In_MSG id=\"%s\" role=\"%s\"/>"
				        + "<wsdl:Request_Body id=\"%s\"/>"
				        + "</soapenv:Body>"
				        + "</soapenv:Envelope>", id, role, id);
			
//			if (role.equalsIgnoreCase("client"))
//			{
//				soapAction = "/WSDL%2520Services/Aplication/Client%2520Service.serviceagent/ClientGettersEndpoint/GetPersonalInfo";
//				response = portalNetHttpRequest.postToTibco("/WSDL%20Services/Aplication/Login%20Service.serviceagent/LoginEndpoint", requestLogin, soapAction, 9010);
//				String[] clientVars = {"clientNumber", "clientName", "email", "gender", "birthDate", "nif", "mobileNumber", "phoneNumber", "address", "postalCode", "locality", "accessionDate"};	
//				mapList = parseBodyXML.parseResponseXML(response, expectedVars, clientVars);
//				clientRepository.save(new Client())
//			}
//			else
//			{
				soapAction = "/WSDL%2520Services/Aplication/Worker%2520Service.serviceagent/WorkerGettersEndpoint/GetWorkerInfo";
				response = portalNetHttpRequest.postToTibco("/WSDL%20Services/Aplication/Worker%20Service.serviceagent/WorkerGettersEndpoint", requestUser, soapAction, 9013);
				String[] workerVars = {"workerNumber", "workerName", "email", "role"};
				mapList = parseBodyXML.parseResponseXML(response, expectedVars, workerVars);
				workersRepository.save(new Workers(Integer.parseInt(mapList.get(1).get("workerNumber")), mapList.get(1).get("workerName"), mapList.get(1).get("email"), mapList.get(1).get("role"), passwordEncoder.encode("password")));
//			}
			
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(id, "password"));
			
			//Storing the details of the currently authenticated user (changing, if there was already one)
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		
			String jwt;
		
			String message = "Login bem sucedido.";
			
			if (userPrincipal.getAuthorities().toString().equals("[CLIENT]"))
				{
					String clientName = clientRepository.findByEmail(userPrincipal.getUsername()).get(0).getName();
					int clientId = clientRepository.findByEmail(userPrincipal.getUsername()).get(0).getClientId();
					jwt = new JwtCreator().createJWT(authentication, clientId, clientName);
					return new ResponseEntity<>(new JwtResponse(jwt, message, userPrincipal.getAuthorities(), clientId, clientName), HttpStatus.OK);
				}
				else if (userPrincipal.getAuthorities().toString().equals("[EMPLOYEE]"))
				{
					String employeeName = workersRepository.findByEmail(userPrincipal.getUsername()).get(0).getName();
					int employeeId = workersRepository.findByEmail(userPrincipal.getUsername()).get(0).getEmployeeId();
					jwt = new JwtCreator().createJWT(authentication, employeeId, employeeName);
					return new ResponseEntity<>(new JwtResponse(jwt, message, userPrincipal.getAuthorities(), employeeId, employeeName), HttpStatus.OK);
				}
				else if (userPrincipal.getAuthorities().toString().equals("[ADMIN]"))
				{
					String adminName = workersRepository.findByEmail(userPrincipal.getUsername()).get(0).getName();
					int adminId = workersRepository.findByEmail(userPrincipal.getUsername()).get(0).getEmployeeId();
					jwt = new JwtCreator().createJWT(authentication, adminId, adminName);
					return new ResponseEntity<>(new JwtResponse(jwt, message, userPrincipal.getAuthorities(), adminId, adminName), HttpStatus.OK);
				}
				else {
					throw new AuthenticationCredentialsNotFoundException("Não foi possível encontrar o role do user.");
				}		
		}
		catch(AuthenticationException e)
		{
			String message = "Password ou email errados.";
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		catch (IndexOutOfBoundsException e) {
			String message = "Erro a verificar email ou a criar token.";
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}
}
