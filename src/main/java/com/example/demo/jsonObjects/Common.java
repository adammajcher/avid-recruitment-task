package com.example.demo.jsonObjects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Common {
    @JsonProperty("created")
    private Date created;
    @JsonProperty("modified")
    private Date modified;
    @JsonProperty("name")
    private String name;

    public Common() {
    }

    public Common(Date created, Date modified, String name) {
        this.created = created;
        this.modified = modified;
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
