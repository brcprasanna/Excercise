package com.prasanna.yelpreviewapp.model;

import java.util.List;

public class Category {

    private String alias;
    private String title;
    private List<String> parentAliases = null;
    private List<Object> countryWhitelist = null;
    private List<Object> countryBlacklist = null;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getParentAliases() {
        return parentAliases;
    }

    public void setParentAliases(List<String> parentAliases) {
        this.parentAliases = parentAliases;
    }

    public List<Object> getCountryWhitelist() {
        return countryWhitelist;
    }

    public void setCountryWhitelist(List<Object> countryWhitelist) {
        this.countryWhitelist = countryWhitelist;
    }

    public List<Object> getCountryBlacklist() {
        return countryBlacklist;
    }

    public void setCountryBlacklist(List<Object> countryBlacklist) {
        this.countryBlacklist = countryBlacklist;
    }

}