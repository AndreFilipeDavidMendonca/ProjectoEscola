package com.polarising.PortalNet.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.polarising.PortalNet.Repository.ClientRepository;
import com.polarising.PortalNet.Repository.WorkersRepository;
import com.polarising.PortalNet.Utilities.PortalNetHttpRequest;
import com.polarising.PortalNet.model.Client;
import com.polarising.PortalNet.model.Workers;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private WorkersRepository workersRepository;
	
	@Autowired
	PortalNetHttpRequest portalNetHttpRequest;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
			
		try{			
			
			Integer Id_int = Integer.parseInt(id);
			
			if (clientRepository.existsByClientId(Id_int))
			{
				Client client = clientRepository.findByClientId(Id_int).get(0);
				
				if (client == null)
				{
					throw new UsernameNotFoundException(id);
				}
				
				return new UserPrincipal(client);
			}
			else if (workersRepository.existsByEmployeeId(Id_int))
			{
				Workers worker = workersRepository.findByEmployeeId(Id_int).get(0);
				
				if (worker == null)
				{
					throw new UsernameNotFoundException(id);
				}
				
				return new UserPrincipal(worker);
			}
			else
			{
				throw new UsernameNotFoundException("Username not found.");
			}
		}
		catch(UsernameNotFoundException e)
		{
			throw new UsernameNotFoundException("Username not found.");
		}
	}
}
