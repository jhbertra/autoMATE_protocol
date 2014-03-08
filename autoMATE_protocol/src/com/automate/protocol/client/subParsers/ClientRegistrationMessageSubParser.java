package com.automate.protocol.client.subParsers;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.automate.protocol.MessageFormatException;
import com.automate.protocol.client.messages.ClientRegistrationMessage;
import com.automate.util.xml.XmlFormatException;

public class ClientRegistrationMessageSubParser extends ClientMessageSubParser<ClientRegistrationMessage> {

	private String username;
	private String password;
	private String name;
	private String email;
	
	/* (non-Javadoc)
	 * @see com.automate.protocol.server.ServerMessageSubParser#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if(qName.equals("content")) {
			this.message = new ClientRegistrationMessage(parameters, username, password, email, name);
		} else {
			super.endElement(uri, localName, qName);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.automate.protocol.client.subParsers.ClientMessageSubParser#parseXml(java.lang.String)
	 */
	@Override
	public ClientRegistrationMessage parseXml(String xml)
			throws XmlFormatException, IOException, MessageFormatException,
			SAXException, ParserConfigurationException {
		username = null;
		password = null;
		name = null;
		email = null;
		return super.parseXml(xml);
	}

	/* (non-Javadoc)
	 * @see com.automate.protocol.server.ServerMessageSubParser#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if(qName.equals("registration")) {
			username = attributes.getValue("username");
			password = attributes.getValue("password");
			name = attributes.getValue("name");
			email = attributes.getValue("email");
			if(username == null || username.isEmpty()) {
				throw new SAXException("username was null.");
			} else if(password == null || password.isEmpty()) {
				throw new SAXException("password was null.");
			} else if(name == null || name.isEmpty()) {
				throw new SAXException("name was null.");
			} else if(email == null || email.isEmpty()) {
				throw new SAXException("email was null.");
			}
		} else {
			super.startElement(uri, localName, qName, attributes);
		}
	}
}
