package com.hoffnungland.checkTinService;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.europa.ec.taxud.tin.services.checktin.CheckTinPortType;
import eu.europa.ec.taxud.tin.services.checktin.CheckTinService;

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
		CheckTinService checkTinService = new CheckTinService();
		logger.debug(checkTinService.getWSDLDocumentLocation());
		CheckTinPortType checkTinPort = checkTinService.getCheckTinPort();
		
		String endpointURL = "https://ec.europa.eu/taxation_customs/tin/services/checkTinService";
		BindingProvider bp = (BindingProvider)checkTinPort;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);
		
		Holder<String> countryCode = new Holder<String>();
		countryCode.value = "IT";
		Holder<String> tinNumber = new Holder<String>();
		tinNumber.value = "SPRMLM84L18F205D";
		Holder<XMLGregorianCalendar> requestDate = new Holder<XMLGregorianCalendar>();
		Holder<Boolean> validStructure = new Holder<Boolean>(); 
		Holder<Boolean> validSyntax = new Holder<Boolean>();
		checkTinPort.checkTin(countryCode, tinNumber, requestDate, validStructure, validSyntax);
		
		logger.debug(validStructure.value + " " + validSyntax.value);
		
		logger.traceExit();
    }
}
