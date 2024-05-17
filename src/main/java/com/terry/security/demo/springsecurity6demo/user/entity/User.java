package com.terry.security.demo.springsecurity6demo.user.entity;

public record User(int id, String name, String mailAddress) {

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private int id;
		private String name;
		private String mailAddress;

		private Builder() {
		}

		private Builder builder() {
			return new Builder();
		}

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

		public User build() {
			return new User(id, name, mailAddress);
		}

	}
}
