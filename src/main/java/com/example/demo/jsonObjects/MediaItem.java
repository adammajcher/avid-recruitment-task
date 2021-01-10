package com.example.demo.jsonObjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MediaItem {
    @JsonProperty("essenceType")
    private String essenceType;
    @JsonProperty("track")
    private String track;
    @JsonProperty("start")
    private String start;
    @JsonProperty("length")
    private String length;
    @JsonProperty("mobId")
    private String mobId;
    @JsonProperty("online")
    private String online;
    @JsonProperty("type")
    private String type;
    @JsonProperty("filePath")
    private String filePath;

    public MediaItem() {
    }

    public MediaItem(String essenceType, String track, String start, String length, String mobId, String online, String type, String filePath) {
        this.essenceType = essenceType;
        this.track = track;
        this.start = start;
        this.length = length;
        this.mobId = mobId;
        this.online = online;
        this.type = type;
        this.filePath = filePath;
    }

    public String getEssenceType() {
        return essenceType;
    }

    public void setEssenceType(String essenceType) {
        this.essenceType = essenceType;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getMobId() {
        return mobId;
    }

    public void setMobId(String mobId) {
        this.mobId = mobId;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


}
