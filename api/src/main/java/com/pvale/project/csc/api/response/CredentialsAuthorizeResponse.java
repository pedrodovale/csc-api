package com.pvale.project.csc.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class CredentialsAuthorizeResponse {

	@JsonProperty("SAD")
	private String sad;
	private Integer expiresIn;

	public CredentialsAuthorizeResponse() {
        super();
	}

	public CredentialsAuthorizeResponse(String sad, Integer expiresIn) {
		this.sad = sad;
		this.expiresIn = expiresIn;
	}

	public String getSad() {
		return sad;
	}

	public void setSad(String sad) {
		this.sad = sad;
	}

	public Integer getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CredentialsAuthorizeResponse)) return false;
		CredentialsAuthorizeResponse that = (CredentialsAuthorizeResponse) o;
		return Objects.equals(getSad(), that.getSad()) &&
				Objects.equals(getExpiresIn(), that.getExpiresIn());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getSad(), getExpiresIn());
	}

	@Override
	public String toString() {
		return "CredentialsAuthorizeResponse{" +
				"sad='" + sad + '\'' +
				", expiresIn=" + expiresIn +
				'}';
	}
}