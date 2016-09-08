/* Copyright 2006-2012 SpringSource.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package grails.plugins.springsecurity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.codec.Hex;
import org.springframework.util.Assert;

/**
 * Workaround for the limitation in Digest authentication where you must store passwords in
 * the database in cleartext so the encrypted password use for comparison with what the
 * browser sends will agree. This password encoder uses the same algorithm as the browser
 * and does a good job with encryption (it effectively uses the username and the Realm
 * name as a salt) but is completely unconfigurable.
 * <p/>
 * Uses code from package org.springframework.security.web.authentication.www.DigestAuthUtils
 * which is unfortunately package-default.
 *
 * @author <a href='mailto:burt@burtbeckwith.com'>Burt Beckwith</a>
 */
public class DigestAuthPasswordEncoder implements PasswordEncoder, InitializingBean {

	private String _realm;

	/**
	 * {@inheritDoc}
	 * @see org.springframework.security.authentication.encoding.PasswordEncoder#encodePassword(
	 * 	java.lang.String, java.lang.Object)
	 */
	public String encodePassword(final String rawPass, final Object salt) {
		Assert.notNull(salt, "Salt is required and must be the username");
		String username = salt.toString();
		return md5Hex(username + ":" + _realm + ":" + rawPass);
	}

	/**
	 * {@inheritDoc}
	 * @see org.springframework.security.authentication.encoding.PasswordEncoder#isPasswordValid(
	 * 	java.lang.String, java.lang.String, java.lang.Object)
	 */
	public boolean isPasswordValid(final String encPass, final String rawPass, final Object salt) {
		// the 'raw' password will already be encrypted, so compare directly
		return encPass != null && rawPass != null ? rawPass.equals(encPass) : false;
	}

	/**
	 * Dependency injection for the realm name.
	 *
	 * @param realm the name
	 */
	public void setRealm(final String realm) {
		_realm = realm;
	}

	/**
	 * {@inheritDoc}
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() {
		Assert.hasLength(_realm, "realm is required");
	}

	private String md5Hex(final String s) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
		}
		catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("No MD5 algorithm available!");
		}

		return new String(Hex.encode(digest.digest(s.getBytes())));
	}
}
