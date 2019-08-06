package com.polarising.PortalNet.Utilities;

import org.springframework.stereotype.Component;


@Component
public class ClientNumberGenerator {

	public String generateClientNumber()
	{		
		Long currentMillis = (System.currentTimeMillis() /1000);
		
		return currentMillis.toString();
	}
}
