package com.terry.security.demo.springsecurity6demo.user.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Collection;
import java.util.stream.Stream;

@JsonDeserialize(builder = User.Builder.class)
public class User {
	@JsonProperty
	private int id;
	@JsonProperty
	private String name;
	@JsonProperty
	private String mailAddress;
	@JsonProperty
	private String username;
	@JsonProperty
	private String password;
	private Collection<String> authorities;

	public User(int id, String name, String mailAddress, String username, String password, Collection<String> authorities) {
		this.id = id;
		this.name = name;
		this.mailAddress = mailAddress;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public int id() {
		return id;
	}

	public String name() {
		return name;
	}

	public String password() {
		return password;
	}

	public String username() {
		return username;
	}

	public Collection<String> authorities() {
		return authorities;
	}

	public String mailAddress() {
		return mailAddress;
	}

	public static Builder builder() {
		return new Builder();
	}

	@JsonPOJOBuilder(withPrefix = "")
	public static class Builder {
		private int id;
		private String name;
		private String mailAddress;
		private String username;
		private String password;
		private Collection<String> authorities;

		public Builder id(int id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder mailAddress(String mailAddress) {
			this.mailAddress = mailAddress;
			return this;
		}

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public Builder authorities(String... authorities) {
			this.authorities = Stream.of(authorities).toList();
			return this;
		}

		public User build() {
			return new User(id, name, mailAddress, username, password, authorities);
		}
	}
}
