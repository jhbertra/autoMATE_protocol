package com.automate.protocol.server.messages;

import com.automate.protocol.Message;
import com.automate.protocol.models.Warning;
import com.automate.protocol.server.ServerProtocolParameters;
import com.automate.util.xml.Attribute;
import com.automate.util.xml.XmlFormatException;

/**
 * Represents a warning message sent from the server to the clinet
 * @author jamie.bertram
 *
 */
public class ServerClientWarningMessage extends Message<ServerProtocolParameters> {

	/***
	 * The warning.
	 */
	public final Warning warning;
	
	/**
	 * The uid of the node that created the warning.
	 */
	public final long nodeId;
	
	/**
	 * Creates a new {@link ServerClientWarningMessage}
	 * @param parameters the protocol parameters from the server
	 * @param warningId the warning's uid
	 * @param nodeId the uid of the node
	 * @param message the warning message.
	 * @throws NullPointerException if message is null
	 * @throws IllegalArgumentException if warningId < 0
	 * @throws IllegalArgumentException if nodeId < 0
	 */
	public ServerClientWarningMessage(ServerProtocolParameters parameters, long warningId, long nodeId, String message) {
		super(parameters);
		if(message == null) {
			throw new NullPointerException("message was null in ServerWarningMessage");
		}
		if(nodeId < 0) {
			throw new IllegalArgumentException("nodeId invalid: " + nodeId);
		}
		if(warningId < 0) {
			throw new IllegalArgumentException("warningId invalid: " + warningId);
		}
		this.warning = new Warning(warningId, message);
		this.nodeId = nodeId;
	}

	@Override
	protected void addContent() throws XmlFormatException {
		addElement("warning", true
				, new Attribute("warning-id", String.valueOf(warning.warningId))
				, new Attribute("node-id", String.valueOf(nodeId))
				, new Attribute("message", warning.message));
	}

	@Override
	public com.automate.protocol.Message.MessageType getMessageType() {
		return MessageType.WARNING_NODE;
	}

	/* (non-Javadoc)
	 * @see com.automate.protocol.Message#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(super.equals(obj)) {
			return 	this.nodeId == ((ServerClientWarningMessage)obj).nodeId
					&& this.warning.equals(((ServerClientWarningMessage)obj).warning);
		} else return false;
	}

	@Override
	public String toString() {
		return super.toString() + "\nServerClientWarningMessage:\nnodeId: " + nodeId + "\nwarningId: " + warning.warningId 
				+ "\nmessage: " + warning.message;
	}

}
