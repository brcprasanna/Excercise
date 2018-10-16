
package com.prasanna.yelpreviewapp.model.category;

import com.prasanna.yelpreviewapp.model.category.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryResponse {

    private List<Category> categories = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
