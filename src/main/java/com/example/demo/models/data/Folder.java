package com.example.demo.models.data;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;


public class Folder {
    @JsonProperty("id")
    private String id;
    @JsonProperty("assets")
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    private List<Asset> assets;

    public Folder() {
    }

    public Folder(String id, List<Asset> assets) {
        this.id = id;
        this.assets = assets;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "id='" + id + '\'' +
                ", assets=" + assets +
                '}';
    }
}
