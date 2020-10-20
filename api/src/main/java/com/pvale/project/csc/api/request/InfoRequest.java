package com.pvale.project.csc.api.request;

import java.util.Objects;

public class InfoRequest {

    private String lang;

    public InfoRequest() {
        super();
    }

    public InfoRequest(String lang) {
        this.lang = lang;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InfoRequest)) return false;
        InfoRequest that = (InfoRequest) o;
        return Objects.equals(getLang(), that.getLang());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLang());
    }

    @Override
    public String toString() {
        return "InfoRequest{" +
                "lang='" + lang + '\'' +
                '}';
    }
}