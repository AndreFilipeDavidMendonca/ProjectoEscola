package com.polarising.PortalNet.Utilities.XMLParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParseBodyXML {
	
	public ArrayList<Map<String, String>> parseResponseXML( String response, String[] standardVars, String[] expectedVars) {
		
		ArrayList<Map<String, String>> mapList = new ArrayList<Map<String,String>>();

		String[] aux1 = response.split("<SOAP-ENV:Body>");
		String[] aux2 = aux1[1].split("</SOAP-ENV:Body>");
			
		String[] lines = aux2[0].split("<");
		
		mapList.add( getExpectedVarsByRow(response, standardVars) );
		
		for (String line : lines) {
			if( getExpectedVarsByRow(line, expectedVars) != null) {
				mapList.add( getExpectedVarsByRow(line, expectedVars ) );
			}
		}
					
		return mapList;
	}
	
	private static Map<String, String> getExpectedVarsByRow(String line, String[] expectedVars) {
		
		boolean isEmpty = true;
		Map<String, String> map = new HashMap<String, String>();	

		for (String var : expectedVars) {
			if(line.contains( var + "=" )) {
				isEmpty = false;
				String[] aux1 = line.split( var + "=\"");
				String[] aux2 = aux1[1].split("\"");
				
				map.put(var, aux2[0]);
			}
		}	
		if(isEmpty) {
			return null;
		}
		return map;
	}
}
