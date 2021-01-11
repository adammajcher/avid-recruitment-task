package com.example.demo.diffrentObjects;

import com.example.demo.jsonObjects.Asset;

import java.util.List;

public class PaddingList {
    private List<Result> results;
    private List<Asset> assets;

    public PaddingList(List<Result> results, List<Asset> assets) {
        this.results = results;
        this.assets = assets;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }
}
