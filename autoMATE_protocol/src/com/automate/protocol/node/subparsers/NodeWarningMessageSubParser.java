package com.automate.protocol.node.subparsers;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.automate.protocol.MessageFormatException;
import com.automate.protocol.client.subParsers.ClientMessageSubParser;
import com.automate.protocol.node.messages.NodeWarningMessage;
import com.automate.protocol.server.messages.ServerClientWarningMessage;
import com.automate.util.xml.XmlFormatException;

public class NodeWarningMessageSubParser extends ClientMessageSubParser<NodeWarningMessage> {

	private String warningMessage;
	
	/* (non-Javadoc)
	 * @see com.automate.protocol.server.ServerMessageSubParser#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if(qName.equals("content")) {
			this.message = new NodeWarningMessage(parameters, warningMessage);
		} else {
			super.endElement(uri, localName, qName);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.automate.protocol.server.subParsers.ServerMessageSubParser#parseXml(java.lang.String)
	 */
	@Override
	public NodeWarningMessage parseXml(String xml) throws XmlFormatException,
			IOException, MessageFormatException, SAXException,
			ParserConfigurationException {
		warningMessage = null;
		return super.parseXml(xml);
	}

	/* (non-Javadoc)
	 * @see com.automate.protocol.server.ServerMessageSubParser#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if(qName.equals("warning")) {
			warningMessage = attributes.getValue("message");
			if(warningMessage == null) {
				throw new SAXException("warning message was null.");
			}
		} else {
			super.startElement(uri, localName, qName, attributes);
		}
	}
	
}
