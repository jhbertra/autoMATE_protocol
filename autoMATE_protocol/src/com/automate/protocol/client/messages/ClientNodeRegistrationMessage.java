package com.automate.protocol.client.messages;

import com.automate.protocol.Message;
import com.automate.protocol.client.ClientProtocolParameters;
import com.automate.util.xml.Attribute;
import com.automate.util.xml.XmlFormatException;

public class ClientNodeRegistrationMessage extends Message<ClientProtocolParameters> {

	public final long modelId;
	public final String name;
	public final String maxVersion;
	
	/**
	 * Creates a new {@link ClientNodeRegistrationMessage}
	 * @param parameters the protocol parameters from the client
	 * @param modelId the id of the model
	 * @param name the name of the node
	 * @param maxVersionMajor the major version of the maximum protocol version the node supports
	 * @param maxVersionMinor the minor version of the maximum protocol version the node supports
	 */
	public ClientNodeRegistrationMessage(ClientProtocolParameters parameters, 
			long modelId, String name, int maxVersionMajor, int maxVersionMinor) {
		super(parameters);
		if(modelId < 0) {
			throw new IllegalArgumentException("modelId invalid " + modelId);
		}
		if(maxVersionMajor < 0) {
			throw new IllegalArgumentException("maxVersionMajor invalid " + modelId);
		}
		if(maxVersionMinor < 0) {
			throw new IllegalArgumentException("maxVersionMinor invalid " + modelId);
		}
		this.modelId = modelId;
		this.name = name;
		this.maxVersion = maxVersionMajor + "." + maxVersionMinor;
	}

	@Override
	protected void addContent() throws XmlFormatException {
		addElement("register-node", true
				, new Attribute("model-id", String.valueOf(modelId))
				, new Attribute("name", name)
				, new Attribute("max-version", maxVersion));
	}

	@Override
	public com.automate.protocol.Message.MessageType getMessageType() {
		return MessageType.REGISTER_NODE;
	}

}
