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
package whitelabel.cloud.webapp.security.spring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import whitelabel.cloud.webapp.security.Authenticable;

public class UserDetailsImpl implements UserDetails {


	private static final long serialVersionUID = -1744892594500617223L;

	private Authenticable user;

	private List<GrantedAuthority> userAuthorities = null;


	public UserDetailsImpl(Authenticable user) {
		this.user = user;
	}

	public Collection<GrantedAuthority> getAuthorities() {

		if (userAuthorities == null || userAuthorities.isEmpty()) {

			userAuthorities = new ArrayList<GrantedAuthority>();

			GrantedAuthority user_default = new GrantedAuthorityImpl("ROLE_AUTHENTICATED");
			userAuthorities.add(user_default);
			/**
			 * you can add here all addititional Authorities for the specific user
			 */

		}

		return userAuthorities;
	}

	public boolean haveRole(String ruolo) {
		for (GrantedAuthority a : getAuthorities()) {
			if (a.getAuthority().equals(ruolo)) {
				return true;
			}
		}
		return false;
	}

	public String getPassword() {
		return null;
	}

	public String getUsername() {
		return user.getUsername();
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public Authenticable getUser() {
		return user;
	}

	public boolean isEnabled() {
		return this.user.isEnabled();
	}

}
