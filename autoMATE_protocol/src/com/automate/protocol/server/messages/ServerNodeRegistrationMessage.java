package com.automate.protocol.server.messages;

import com.automate.protocol.Message;
import com.automate.protocol.server.ServerProtocolParameters;
import com.automate.util.xml.Attribute;
import com.automate.util.xml.XmlFormatException;

public class ServerNodeRegistrationMessage extends Message<ServerProtocolParameters> {

	public final long nodeId;
	public final String password;
	
	public ServerNodeRegistrationMessage(ServerProtocolParameters parameters, long nodeId, String password) {
		super(parameters);
		if(nodeId < 0) {
			throw new IllegalArgumentException("nodeId was invalid.");
		} else if(password == null) {
			throw new NullPointerException("password was null.");
		}
		this.nodeId = nodeId;
		this.password = password;
	}

	@Override
	protected void addContent() throws XmlFormatException {
		addElement("register-node", true, new Attribute("node-id", String.valueOf(nodeId))
										, new Attribute("password", password));
	}

	@Override
	public com.automate.protocol.Message.MessageType getMessageType() {
		return MessageType.REGISTER_NODE;
	}
	
	
	
}
