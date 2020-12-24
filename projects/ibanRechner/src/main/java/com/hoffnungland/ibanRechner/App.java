package com.hoffnungland.ibanRechner;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.ibanrechner.ssl.IBANValResStruct;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final Logger logger = LogManager.getLogger(App.class);
	public static void main( String[] args )
    {
		logger.traceEntry();
		
		String username = args[0];
	    String password = args[1];
	    String usernameAndPassword = username + ":" + password;
	    String authorizationHeaderValue = "Basic " + java.util.Base64.getEncoder().encodeToString( usernameAndPassword.getBytes() );
		
		Client client = ClientBuilder.newClient();
		
		WebTarget webTarget = client.target("https://rest.sepatools.eu").path("validate_iban_dummy").path("{iban}").resolveTemplate("iban", "IT80F0347501605CC0010161847");
		
		//IBANValResStruct ibanValResStruct = webTarget.request(MediaType.APPLICATION_JSON).header("Authorization", authorizationHeaderValue).get(IBANValResStruct.class);
		//logger.debug(ibanValResStruct.getIban());
		String response = webTarget.request(MediaType.APPLICATION_JSON).header("Authorization", authorizationHeaderValue).get(String.class);
		logger.debug(response);
		client.close();
		
		JsonReader jsonReader = Json.createReader(new StringReader(response));
		JsonObject object = (JsonObject)jsonReader.read();
		logger.debug(object.getString("result"));
		logger.debug(object.getInt("return_code"));
		logger.debug(object.getString("iban"));
		for(JsonValue curBidCandidate : object.getJsonArray("bic_candidates")) {
			logger.debug(((JsonObject) curBidCandidate).getString("bic"));
		}
		logger.traceExit();
    }
}
