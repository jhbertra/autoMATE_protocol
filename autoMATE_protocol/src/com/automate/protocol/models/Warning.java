package com.automate.protocol.models;

/**
 * Encapsulates a warning object.
 * @author jamie.bertram
 *
 */
public class Warning {

	/**
	 * The id of the warning, as sent by the server.
	 */
	public final long warningId;
	
	/**
	 * The messsage the warning contains.
	 */
	public final String message;

	/**
	 * Creates a new {@link Warning}
	 * @param warningId the id of the warning, as sent by the server.
	 * @param message the message the warning contains.
	 */
	public Warning(long warningId, String message) {
		this.warningId = warningId;
		this.message = message;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Warning) {
			return this.warningId == ((Warning) obj).warningId
					&& this.message.equals(((Warning) obj).message);
		} else {
			return false;
		}
	}
	
}
