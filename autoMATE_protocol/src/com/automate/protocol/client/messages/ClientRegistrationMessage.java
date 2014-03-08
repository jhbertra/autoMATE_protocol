package com.automate.protocol.client.messages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.automate.protocol.Message;
import com.automate.protocol.client.ClientProtocolParameters;
import com.automate.util.xml.Attribute;
import com.automate.util.xml.XmlFormatException;

/**
 * Encapsulates a client message as sent by the client.
 * @author jamie.bertram
 *
 */
public class ClientRegistrationMessage extends Message<ClientProtocolParameters> {

	/**
	 * The username of the account to be created.
	 */
	public final String username;
	
	/**
	 * The password for the account to be created.
	 */
	public final String password;
	
	/**
	 * The email address of the user.
	 */
	public final String email;
	
	/**
	 * The name of the user.
	 */
	public final String name;

	/**
	 * Creates a new ClientRegistrationMessage
	 * 
	 * @param parameters The protocol parameters sent by the client.  It is expected that the session key be empty.
	 * @param username The username of the account to be created.
	 * @param password The password for the account to be created.
	 * @param email The email address of the user.
	 * @param nameThe name of the user.
	 * 
	 * @throws NullPointerException if parameters is null
	 * @throws NullPointerException if username is null
	 * @throws NullPointerException if password is null
	 * @throws NullPointerException if email is null
	 * @throws NullPointerException if name is null
	 * @throws IllegalArgumentException if usrename is invalid
	 * @throws IllegalArgumentException if password is invalid
	 */
	public ClientRegistrationMessage(ClientProtocolParameters parameters, String username, String password, String email, String name) {
		super(parameters);
		if(username == null) {
			throw new NullPointerException("username was null.");
		}
		if(password == null) {
			throw new NullPointerException("password was null.");
		}
		if(email == null) {
			throw new NullPointerException("email was null.");
		}
		if(name == null) {
			throw new NullPointerException("name was null.");
		}
		Matcher useranmeMatcher = Pattern.compile("[a-zA-Z0-9._-]{6,}").matcher(username);
		if(!useranmeMatcher.matches()) {
			throw new IllegalArgumentException("Username " + username + " is invalid.");
		}
		if(password.length() < 6) {
			throw new IllegalArgumentException("Password " + password + " is too short.");
		}
		Matcher passwordMatcher1 = Pattern.compile("[a-zA-Z]").matcher(password);
		if(!passwordMatcher1.find()) {
			throw new IllegalArgumentException("Password " + password + " is invalid.  It must contain a letter.");
		}
		Matcher passwordMatcher2 = Pattern.compile("[0-9]").matcher(password);
		if(!passwordMatcher2.find()) {
			throw new IllegalArgumentException("Password " + password + " is invalid.  It must contain a number.");
		}
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
	}

	@Override
	protected void addContent() throws XmlFormatException {
		addElement("registration", true, 
				new Attribute("username", username),
				new Attribute("password", password),
				new Attribute("name", name),
				new Attribute("email", email));
	}

	@Override
	public com.automate.protocol.Message.MessageType getMessageType() {
		return MessageType.REGISTRATION;
	}
	
}
