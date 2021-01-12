package com.example.demo.models.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Asset {
    @JsonProperty("attributes")
    private List<Attribute> attributes;
    @JsonProperty("base")
    private Base base;
    @JsonProperty("mobId")
    private String mobId;
    @JsonProperty("common")
    private Common common;
    @JsonProperty("media-items")
    private List<MediaItem> mediaItems;

    public Asset() {
    }

    public Asset(List<Attribute> attributes, Base base, String mobId, Common common, List<MediaItem> mediaItems) {
        this.attributes = attributes;
        this.base = base;
        this.mobId = mobId;
        this.common = common;
        this.mediaItems = mediaItems;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public String getMobId() {
        return mobId;
    }

    public void setMobId(String mobId) {
        this.mobId = mobId;
    }

    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
        this.common = common;
    }

    public List<MediaItem> getMediaItems() {
        return mediaItems;
    }

    public void setMediaItems(List<MediaItem> mediaItems) {
        this.mediaItems = mediaItems;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "attributes=" + attributes +
                ", base=" + base +
                ", mobId='" + mobId + '\'' +
                ", common=" + common +
                ", mediaItems=" + mediaItems +
                '}';
    }
}
