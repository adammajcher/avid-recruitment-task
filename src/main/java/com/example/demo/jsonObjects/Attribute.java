package com.example.demo.jsonObjects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Attribute {
    @JsonProperty("group")
    private String group;
    @JsonProperty("name")
    private String name;
    @JsonProperty("value")
    private String value;

    public Attribute() {
    }

    public Attribute(String group, String name, String value) {
        this.group = group;
        this.name = name;
        this.value = value;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
