package com.pvale.project.csc.api.response;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class InfoResponse {

	private String specs;
	private String name;
	private String logo;
	private String region;
	private String lang;
	private String description;
	private Set<String> authType;
	private String oauth2;
	private Set<String> methods;

	public InfoResponse() {
		super();
	}

	public InfoResponse(String specs, String name, String logo, String region, String lang, String description, Set<String> authType, String oauth2, Set<String> methods) {
		this.specs = specs;
		this.name = name;
		this.logo = logo;
		this.region = region;
		this.lang = lang;
		this.description = description;
		this.authType = authType;
		this.oauth2 = oauth2;
		this.methods = methods;
	}

	public String getSpecs() {
		return specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<String> getAuthType() {
		return authType;
	}

	public void setAuthType(Set<String> authType) {
		this.authType = authType;
	}

	public String getOauth2() {
		return oauth2;
	}

	public void setOauth2(String oauth2) {
		this.oauth2 = oauth2;
	}

	public Set<String> getMethods() {
		return methods;
	}

	public void setMethods(Set<String> methods) {
		this.methods = methods;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof InfoResponse)) return false;
		InfoResponse that = (InfoResponse) o;
		return Objects.equals(getSpecs(), that.getSpecs()) &&
				Objects.equals(getName(), that.getName()) &&
				Objects.equals(getLogo(), that.getLogo()) &&
				Objects.equals(getRegion(), that.getRegion()) &&
				Objects.equals(getLang(), that.getLang()) &&
				Objects.equals(getDescription(), that.getDescription()) &&
				Objects.equals(getAuthType(), that.getAuthType()) &&
				Objects.equals(getOauth2(), that.getOauth2()) &&
				Objects.equals(getMethods(), that.getMethods());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getSpecs(), getName(), getLogo(), getRegion(), getLang(), getDescription(), getAuthType(), getOauth2(), getMethods());
	}

	@Override
	public String toString() {
		return "InfoResponse{" +
				"specs='" + specs + '\'' +
				", name='" + name + '\'' +
				", logo='" + logo + '\'' +
				", region='" + region + '\'' +
				", lang='" + lang + '\'' +
				", description='" + description + '\'' +
				", authType=" + authType +
				", oauth2='" + oauth2 + '\'' +
				", methods=" + methods +
				'}';
	}
}