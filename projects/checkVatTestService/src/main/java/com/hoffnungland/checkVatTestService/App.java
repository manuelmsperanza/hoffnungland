package com.hoffnungland.checkVatTestService;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;

import eu.europa.ec.taxud.vies.services.checkvat.CheckVatPortType;
import eu.europa.ec.taxud.vies.services.checkvat.CheckVatTestService;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main( String[] args )
	{
		CheckVatTestService checkVatTestService = new CheckVatTestService();
		CheckVatPortType checkVatPort = checkVatTestService.getCheckVatPort();
		Holder<String> countryCode = new Holder<String>();
		countryCode.value = "IT";
		Holder<String> vatNumber = new Holder<String>();
		vatNumber.value = "06250920482";
		Holder<XMLGregorianCalendar> requestDate = null;
		Holder<Boolean> valid = null;
		Holder<String> name = null;
		Holder<String> address = null;
		checkVatPort.checkVat(countryCode, vatNumber, requestDate, valid, name, address);
		System.out.println(name);
		

	}
}
