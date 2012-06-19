/**
 *
 */
/**
 *
 * Copyright (c) 2012 <copyright Aruba spa>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 *
 */
package whitelabel.cloud.wsclient;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Date;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.codec.Base64;

import whitelabel.cloud.adapter.DateFormatterExt;
import whitelabel.cloud.exception.WsAuthenticationException;

/**
 * @author luca
 *
 */
public class WebServiceAuthenticator {

	private static final Logger LOG = Logger.getLogger(WebServiceAuthenticator.class);
	private final DateFormatterExt dfe = new DateFormatterExt("yyyy-MM-dd'T'HH:mm:ss'Z'");

	private MessageDigest digest = null;
	private SecureRandom random = null;



	public void authenticateBasic(final Dispatch<SOAPMessage> dispatch, final String username, final String password) throws WsAuthenticationException {

		if (dispatch == null) {
			LOG.error(" SoapMessage dispatcher not defined");
			throw new WsAuthenticationException("SOAP_DISPATCHER_NOT_DEFINED");
		}
		if (username == null || password == null || username.trim().length() == 0 || password.trim().length() == 0) {
			LOG.error("Username: " + username + " password: " + password + " - invalid parameters");
			throw new WsAuthenticationException("INVALID_PARAMETERS");
		}
		dispatch.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, username);
		dispatch.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
	}


	public void authenticateInClear(final SOAPMessage request, final String username, final String password) throws WsAuthenticationException {

		if (request == null) {
			LOG.error(" SoapMessage request not defined");
			throw new WsAuthenticationException("SOAP_REQUEST_NOT_DEFINED");
		}
		if (username == null || password == null || username.trim().length() == 0 || password.trim().length() == 0) {
			LOG.error("Username: " + username + " password: " + password + " - invalid parameters");
			throw new WsAuthenticationException("INVALID_PARAMETERS");
		}

		String nonceValue = generateNonceBase64(16);
		String createdValue = dfe.print(new Date());
		String userValue = username;
		String pwdValue = password;
		String pwdType = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText";

		QName securityQName = new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd","Security","wsse");
		QName usernameTokenQName = new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd","UsernameToken","wsse");
		QName usernameQName = new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd","Username","wsse");
		QName PasswordQName = new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd","Password","wsse");
		QName PasswordTypeQName = new QName("Type");
		QName nonceQName = new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd","Nonce","wsse");
		QName createdQName = new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd","Created", "wsu");

		try {
			SOAPElement securitySoap = request.getSOAPHeader().addChildElement(securityQName);
			SOAPElement usernameTokenSoap = securitySoap.addChildElement(usernameTokenQName);
			SOAPElement usernameSoap = usernameTokenSoap.addChildElement(usernameQName);
			usernameSoap.addTextNode(userValue);
			SOAPElement passwordSoap = usernameTokenSoap.addChildElement(PasswordQName);
			passwordSoap.addTextNode(pwdValue);
			passwordSoap.addAttribute(PasswordTypeQName, pwdType);
			SOAPElement nonceSoap =usernameTokenSoap.addChildElement(nonceQName);
			nonceSoap.addTextNode(nonceValue);
			SOAPElement creadedSoap = usernameTokenSoap.addChildElement(createdQName);
			creadedSoap.addTextNode(createdValue);
		}
		catch (SOAPException se) {
			LOG.error(se);
			throw new WsAuthenticationException("SOAPHEADER_CREATION", se);
		}
	}

	public void authenticateWithDigest(final SOAPMessage request, final String username, final String password) throws WsAuthenticationException {

		if (request == null) {
			LOG.error(" SoapMessage request not defined");
			throw new WsAuthenticationException("SOAP_REQUEST_NOT_DEFINED");
		}
		if (username == null || password == null || username.trim().length() == 0 || password.trim().length() == 0) {
			LOG.error("Username: " + username + " password: " + password + " - invalid parameters");
			throw new WsAuthenticationException("INVALID_PARAMETERS");
		}

		String nonceValue = generateNonceBase64(16);
		String createdValue = dfe.print(new Date());
		String userValue = username;
		String pwdValue = crypthPassword(nonceValue, createdValue, password);

		String pwdType = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordDigest";

		QName securityQName = new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd","Security","wsse");
		QName usernameTokenQName = new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd","UsernameToken","wsse");
		QName usernameQName = new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd","Username","wsse");
		QName PasswordQName = new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd","Password","wsse");
		QName PasswordTypeQName = new QName("Type");
		QName nonceQName = new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd","Nonce","wsse");
		QName createdQName = new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd","Created", "wsu");

		SOAPElement securitySoap;
		try {
			securitySoap = request.getSOAPHeader().addChildElement(securityQName);
			SOAPElement usernameTokenSoap = securitySoap.addChildElement(usernameTokenQName);
			SOAPElement usernameSoap = usernameTokenSoap.addChildElement(usernameQName);
			usernameSoap.addTextNode(userValue);
			SOAPElement passwordSoap = usernameTokenSoap.addChildElement(PasswordQName);
			passwordSoap.addTextNode(pwdValue);
			passwordSoap.addAttribute(PasswordTypeQName, pwdType);
			SOAPElement nonceSoap =usernameTokenSoap.addChildElement(nonceQName);
			nonceSoap.addTextNode(nonceValue);
			SOAPElement creadedSoap = usernameTokenSoap.addChildElement(createdQName);
			creadedSoap.addTextNode(createdValue);
		}
		catch (SOAPException se) {
			LOG.error(se);
			throw new WsAuthenticationException("SOAPHEADER_CREATION", se);
		}
	}


	private final String crypthPassword(String nonce, String created, String password) {
		String crypto = null;
		try {
			byte[] b1 = nonce != null ? Base64.decode(nonce.getBytes("UTF-8")) : new byte[0];
			byte[] b2 = created != null ? created.getBytes("UTF-8") : new byte[0];
			byte[] b3 = password.getBytes("UTF-8");
			byte[] b4 = new byte[b1.length + b2.length + b3.length];

			int offset = 0;
			System.arraycopy(b1, 0, b4, offset, b1.length);
			offset += b1.length;

			System.arraycopy(b2, 0, b4, offset, b2.length);
			offset += b2.length;

			System.arraycopy(b3, 0, b4, offset, b3.length);

			byte[] digestBytes = generateDigest(b4);

			crypto = new String(Base64.encode(digestBytes));
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		return crypto;
	}

	/**
     * Generate a (SHA1) digest of the input bytes. The MessageDigest instance that backs this
     * method is cached for efficiency.
     * @param inputBytes the bytes to digest
     * @return the digest of the input bytes
     * @throws WSSecurityException
     */
    private final byte[] generateDigest(byte[] inputBytes) {
        try {
            if (digest == null) {
                digest = MessageDigest.getInstance("SHA-1");
            }
            return digest.digest(inputBytes);
        }
        catch (Exception e) {
            throw new RuntimeException("Error in generating digest", e);
        }
    }

	/**
     * Generate a nonce of the given length using the SHA1PRNG algorithm.
     * The SecureRandom instance that backs this method is cached for efficiency.
     *
     * @return a nonce of the given length
     * @throws WSSecurityException
     */
    private final String generateNonceBase64(int length) {
        try {
            if (random == null) {
                random = SecureRandom.getInstance("SHA1PRNG");
                random.setSeed(System.currentTimeMillis());
            }
            byte[] temp = new byte[length];
            random.nextBytes(temp);

            //return temp;
            return new String(Base64.encode(temp));
        }
        catch (Exception ex) {
            throw new RuntimeException("Error in generating nonce of length " + length, ex);
        }
    }
}
