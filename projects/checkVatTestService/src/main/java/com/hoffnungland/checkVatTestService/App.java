package com.hoffnungland.checkVatTestService;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceFeature;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.europa.ec.taxud.vies.services.checkvat.CheckVatPortType;
import eu.europa.ec.taxud.vies.services.checkvat.CheckVatTestService;

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
		CheckVatTestService checkVatTestService = new CheckVatTestService();
		System.out.println(checkVatTestService.getWSDLDocumentLocation());
		CheckVatPortType checkVatPort = checkVatTestService.getCheckVatPort();
		
		String endpointURL = "http://ec.europa.eu/taxation_customs/vies/services/checkVatService";
		BindingProvider bp = (BindingProvider)checkVatPort;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);
		
		Holder<String> countryCode = new Holder<String>();
		countryCode.value = "IT";
		Holder<String> vatNumber = new Holder<String>();
		vatNumber.value = "06250920482";
		Holder<XMLGregorianCalendar> requestDate = new Holder<XMLGregorianCalendar>();
		Holder<Boolean> valid = new Holder<Boolean>();
		Holder<String> name = new Holder<String>();
		Holder<String> address = new Holder<String>();
		checkVatPort.checkVat(countryCode, vatNumber, requestDate, valid, name, address);

		logger.debug(valid.value ? name.value : valid.value);
		
		logger.traceExit();
		

	}
}
