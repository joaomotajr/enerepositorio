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

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionSignUp;

import br.com.eneeyes.archetype.model.acl.User;
import br.com.eneeyes.archetype.services.identity.IdentityService;

/**
 * Simple little {@link org.springframework.social.connect.ConnectionSignUp} command that allocates new userIds in memory.
 * Doesn't bother storing a user record in any local database, since this quickstart just stores the user id in a cookie.
 * @author Keith Donald
 */
public final class SimpleConnectionSignUp implements ConnectionSignUp {

	private IdentityService ids;
	
	private final AtomicLong userIdSequence = new AtomicLong();
	
	public SimpleConnectionSignUp(IdentityService ids) {
		this.ids = ids;
	}
	
	public String execute(Connection<?> connection) {
		ConnectionKey key = connection.getKey();
		ConnectionData data = connection.createData();
		
		User user = new User();
		user.setLogin(key.getProviderUserId());
		user.setProviderUserId(key.getProviderUserId());
		user.setProviderId(key.getProviderId());
		user.setNickname(data.getDisplayName());
		user.setDisplayName(data.getDisplayName());
		user.setProfileUrl(data.getProfileUrl());
		user.setImageUrl(data.getImageUrl());
		user.setAccessToken(data.getAccessToken());
		user.setSecret(data.getSecret());
		user.setRefreshToken(data.getRefreshToken());
		user.setExpireTime(data.getExpireTime());
		
		ids.save(user);
		
		return Long.toString(userIdSequence.incrementAndGet());
	}

}
