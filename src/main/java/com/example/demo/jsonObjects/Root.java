package com.example.demo.jsonObjects;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

public class Root {
    @JsonIgnore
    private Map<String, Folder> rootMap = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Folder> getRootMap() {
        return rootMap;
    }

    @JsonAnySetter
    public void setRootMap(String name, Folder value) {
        this.rootMap.put(name, value);
    }


}
