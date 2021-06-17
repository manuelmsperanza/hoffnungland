package com.hoffnungland.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.hoffnungland.xpath.XmlExtractor;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import net.sf.saxon.s9api.SaxonApiException;

@WebServlet(urlPatterns={"/passthrough"})
public class Passthrough extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7562234306130866835L;
	private static final Logger logger = Logger.getLogger("Passthrough");

	private Properties props;
	private XmlExtractor xmlExtractor;
	private DocumentBuilder builder;
	private Transformer transformer;

	@Override
	public void init() throws ServletException {
		super.init();
		logger.entering("Passthrough", "init");
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			this.builder = factory.newDocumentBuilder();
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			this.transformer = transformerFactory.newTransformer();
			
			this.props = new Properties();
			this.xmlExtractor = new XmlExtractor();
			this.props.load(this.getClass().getResourceAsStream("/users.properties"));
		} catch (IOException | ParserConfigurationException | TransformerConfigurationException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		logger.exiting("Passthrough", "init");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		logger.entering("Passthrough", "service");
		
		try {
			
			StringBuffer content = new StringBuffer();
			try (BufferedReader in = req.getReader();) {
				String line = null;
				while((line = in.readLine()) != null) {
					content.append(line.trim());
				}
			}
			logger.info("content " + content);
			
			String wsseNs = "xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\"";
			this.xmlExtractor.init(content.toString());
			
			String userName = this.xmlExtractor.extractString("//wsse:Security/wsse:UsernameToken/wsse:Username", wsseNs);
			logger.info("userName: " + userName);
			String password = this.xmlExtractor.extractString("//wsse:Security/wsse:UsernameToken/wsse:Password", wsseNs);
			//logger.info("password: " + password);
			String passwordType = this.xmlExtractor.extractString("//wsse:Security/wsse:UsernameToken/wsse:Password/@Type", wsseNs);
			logger.info("passwordType: " + passwordType);
			if("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordDigest".equals(passwordType)) {
				String nonce = this.xmlExtractor.extractString("//wsse:Security/wsse:UsernameToken/wsse:Nonce", wsseNs);
				logger.info("nonce: " + nonce);
				if(nonce != null && !"".equals(nonce)) {
					String nonceEncodingType = this.xmlExtractor.extractString("//wsse:Security/wsse:UsernameToken/wsse:Nonce/@EncodingType", wsseNs);
					logger.info("nonceEncodingType: " + nonceEncodingType);
					
					String createdDate = this.xmlExtractor.extractString("//wsse:Security/wsse:UsernameToken/wsu:Created", wsseNs);
					logger.info("createdDate: " + createdDate);
					String userPasswd = this.props.getProperty(userName);
					
					String digestCheck = GeneratePasswordDigest.buildPasswordDigest(userPasswd, nonce, nonceEncodingType, createdDate);
					if(!digestCheck.equals(password)) {
						throw new ServletException("Authentication failed");
					} else {
						logger.info(userName + " authenticated");
					}
				}
				
			} else {
				throw new ServletException("Invalid authentication");
			}
			
			String contentToSend = content.toString();
			StringReader stringReader = new StringReader(contentToSend);
			Document doc = builder.parse(new InputSource(stringReader));
			Element root = doc.getDocumentElement();
			NodeList soapHeaderNodes = root.getElementsByTagNameNS(root.getNamespaceURI(), "Header");
			if(soapHeaderNodes.getLength() > 0) {
				root.removeChild(soapHeaderNodes.item(0));
				
				DOMSource source = new DOMSource(doc);
				
				try(StringWriter writer = new StringWriter()){
					
					StreamResult result = new StreamResult(writer);
					transformer.transform(source, result);
					contentToSend = writer.toString();
					logger.info("contentToSend " + contentToSend);
				}
			}

			try(PrintWriter writer = resp.getWriter();){			
				writer.write(getResponseFromBackend(contentToSend, "https://ec.europa.eu/taxation_customs/tin/services/checkTinService", req));
			}
			
		} catch (SaxonApiException | IOException | SAXException | TransformerException | NoSuchAlgorithmException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		
		
		logger.exiting("Passthrough", "service");
	}

	private String getResponseFromBackend(String content, String addr, HttpServletRequest req) throws HttpException, IOException {

		logger.entering("Passthrough", "getResponseFromBackend");
		HttpClient client = new HttpClient();

		HttpMethod method = null;
		switch(req.getMethod()) {
		case "GET":
			method = new GetMethod(addr);
		case "POST":
			PostMethod postMethod = new PostMethod(addr);
			RequestEntity entity = new StringRequestEntity(content.toString(), "text/xml", "UTF-8");
			postMethod.setRequestEntity(entity);
			method = postMethod;
		}

		client.executeMethod(method);
		String body=method.getResponseBodyAsString();

		logger.exiting("Passthrough", "getResponseFromBackend", body);
		return body;

	}

}
