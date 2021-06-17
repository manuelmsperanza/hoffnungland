package com.hoffnungland.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.codec.binary.Base64;

public class GeneratePasswordDigest {

	public static void main(String[] args) {
		String username = "manuel.m.speranza";
		String password = "`[G:jg2qVlGe";

		try {

			java.security.SecureRandom random = java.security.SecureRandom.getInstance("SHA1PRNG");
			random.setSeed(System.currentTimeMillis()); 
			byte[] nonceBytes = new byte[16]; 
			random.nextBytes(nonceBytes); 
			String nonce = new String(org.apache.commons.codec.binary.Base64.encodeBase64(nonceBytes), "UTF-8");


			System.out.println("Nonce: " + nonce);
			
			Instant instant = Instant.now();
			String created = DateTimeFormatter.ISO_INSTANT.format(instant);

			System.out.println("created: " + created);
			String nonceEncodingType = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary";
			System.out.println("digestString: " + buildPasswordDigest(password, nonce, nonceEncodingType, created));
		} catch (NoSuchAlgorithmException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public static String buildPasswordDigest(String password, String nonce, String nonceEncodingType, String created) throws IOException, NoSuchAlgorithmException{
		String digestString = null;
		
		byte[] nonceBytes = null;
		if("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary".equals(nonceEncodingType)) {
			nonceBytes = org.apache.commons.codec.binary.Base64.decodeBase64(nonce.getBytes());
		} else {
			nonceBytes = nonce.getBytes();
		}
		
		byte[] createdBytes = created.getBytes("UTF-8");
		byte[] passwordBytes = password.getBytes("UTF-8");
		try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream();){

			outputStream.write(nonceBytes);
			outputStream.write(createdBytes);
			outputStream.write(passwordBytes);
			byte[] concatenatedBytes = outputStream.toByteArray();
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(concatenatedBytes, 0, concatenatedBytes.length);
			byte[] digestBytes = digest.digest();
			digestString = new String(Base64.encodeBase64(digestBytes));
		}

		return digestString;
	}
}
