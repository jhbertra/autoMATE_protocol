package com.automate.protocol.client.subParsers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.automate.protocol.client.messages.ClientNodeRegistrationMessage;

public class ClientNodeRegistrationMessageSubParser extends ClientMessageSubParser<ClientNodeRegistrationMessage> {

	private long modelId;
	private int maxVersionMajor;
	private int maxVersionMinor;
	private String name;

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName.equals("content")) {
			this.message = new ClientNodeRegistrationMessage(parameters, modelId, name, maxVersionMajor, maxVersionMinor);
		} else {
			super.endElement(uri, localName, qName);
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if(qName.equals("register-node")) {
			String modelIdString = attributes.getValue("model-id");
			String maxVersionString = attributes.getValue("max-version");
			name = attributes.getValue("name");
			if(modelIdString == null) {
				throw new SAXException("model-id was null.");
			} else if(maxVersionString == null) {
				throw new SAXException("max-version was null.");
			}
			try {
				modelId = Long.parseLong(modelIdString);
			} catch(NumberFormatException e) {
				throw new SAXException("Error parsing model-id", e);
			}
			try {
				maxVersionMajor = Integer.parseInt(maxVersionString.substring(0, maxVersionString.indexOf(".")));
				maxVersionMinor = Integer.parseInt(maxVersionString.substring(maxVersionString.indexOf(".") + 1));
			} catch(RuntimeException e) {
				throw new SAXException("Error parsing maxversion", e);
			}
		} else {
			super.startElement(uri, localName, qName, attributes);
		}
	}
	
}
