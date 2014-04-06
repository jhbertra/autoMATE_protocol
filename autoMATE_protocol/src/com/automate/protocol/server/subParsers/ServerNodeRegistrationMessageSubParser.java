package com.automate.protocol.server.subParsers;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.automate.protocol.MessageFormatException;
import com.automate.protocol.server.messages.ServerNodeRegistrationMessage;
import com.automate.util.xml.XmlFormatException;

public class ServerNodeRegistrationMessageSubParser extends ServerMessageSubParser<ServerNodeRegistrationMessage> {

	private long nodeId;
	private String password;

	/* (non-Javadoc)
	 * @see com.automate.protocol.server.subParsers.ServerMessageSubParser#parseXml(java.lang.String)
	 */
	@Override
	public ServerNodeRegistrationMessage parseXml(String xml)
			throws XmlFormatException, IOException, MessageFormatException,
			SAXException, ParserConfigurationException {
		this.nodeId = -1;
		this.password = null;
		return super.parseXml(xml);
	}

	/* (non-Javadoc)
	 * @see com.automate.protocol.server.subParsers.ServerMessageSubParser#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if(qName.equals("content")) {
			this.message = new ServerNodeRegistrationMessage(parameters, nodeId, password);
		} else {			
			super.endElement(uri, localName, qName);
		}
	}

	/* (non-Javadoc)
	 * @see com.automate.protocol.server.subParsers.ServerMessageSubParser#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if(qName.equals("register-node")) {
			password = attributes.getValue("password");
			try {
				nodeId = Long.parseLong(attributes.getValue("node-id"));
			} catch (RuntimeException e) {
				throw new SAXException(e);
			}
			if(password == null) {
				throw new SAXException("Node registration password was null in xml.");
			}
		} else {
			super.startElement(uri, localName, qName, attributes);
		}
	}

	
	
}
