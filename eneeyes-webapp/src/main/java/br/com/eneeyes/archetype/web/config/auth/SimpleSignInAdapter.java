/*
 * Copyright 2013 the original author or authors.
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
package br.com.eneeyes.archetype.web.config.auth;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.NativeWebRequest;

import br.com.eneeyes.archetype.model.acl.Role;
import br.com.eneeyes.archetype.model.acl.User;
import br.com.eneeyes.archetype.model.acl.UserStatus;
import br.com.eneeyes.archetype.result.UserResult;
import br.com.eneeyes.archetype.services.identity.IdentityService;

public final class SimpleSignInAdapter implements SignInAdapter {

	private IdentityService ids;
	
	private final UserCookieGenerator userCookieGenerator = new UserCookieGenerator();
	
	public SimpleSignInAdapter(IdentityService ids) {
		this.ids = ids;
	}
	
	@Transactional
	public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
		ConnectionData data = connection.createData();
		
		UserResult userResult = ids.findByLogin(data.getProviderUserId());
		User user = userResult.getUser();
		
		if(user == null) {
			user = new User();
			user.setLogin(data.getProviderUserId());
			user.setNickname(data.getDisplayName());
			user.setStatus(UserStatus.ACTIVE);
			
			user.setRoles(new HashSet<Role>());
			user.getRoles().add(new Role(4l));
		}
		
		user.setDisplayName(data.getDisplayName());
		user.setImageUrl(data.getImageUrl());
//		user.setAccessToken(data.getAccessToken());
//		user.setSecret(data.getSecret());
//		user.setRefreshToken(data.getRefreshToken());
		user.setExpireTime(data.getExpireTime());
		
		ids.save(user);
		
		List<GrantedAuthority> grants = new ArrayList<GrantedAuthority>();
		
		for(Role role : user.getRoles()) {
			final String roleName = role.getName();
			grants.add(new GrantedAuthority() {
				
				private static final long serialVersionUID = 4490745121463633506L;

				public String getAuthority() {
					return roleName;
				}
			});				
		}
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, user.getHash(), grants);
		SecurityContextHolder.getContext().setAuthentication(token);
		
		SecurityContext.setCurrentUser(userResult.getUser());
		userCookieGenerator.addCookie(userId, request.getNativeResponse(HttpServletResponse.class));
		return null;
	}

}
