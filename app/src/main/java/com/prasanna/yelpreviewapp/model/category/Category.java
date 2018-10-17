package com.prasanna.yelpreviewapp.model.category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Category {

    private String alias;
    private String title;
    private List<String> parentAliases = null;
    private List<String> countryWhitelist = null;
    private List<Object> countryBlacklist = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

    public List<String> getCountryWhitelist() {
        return countryWhitelist;
    }

    public void setCountryWhitelist(List<String> countryWhitelist) {
        this.countryWhitelist = countryWhitelist;
    }

    public List<Object> getCountryBlacklist() {
        return countryBlacklist;
    }

    public void setCountryBlacklist(List<Object> countryBlacklist) {
        this.countryBlacklist = countryBlacklist;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
