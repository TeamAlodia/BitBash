package com.alodia.bitbash.models;

import java.util.HashMap;

/**
 * Created by Alaina Traxler on 3/14/2017.
 */

public class HighScoreTable {
    public String gameName;
    public String gameId;
    public String type;
    public String description;
    public boolean locked = false;
    public HashMap<String, Boolean> records = new HashMap<>();

    public HighScoreTable(){}

    public HighScoreTable(String gameName, String gameId, String type) {
        this.gameName = gameName;
        this.gameId = gameId;
        this.type = type;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public HashMap<String, Boolean> getRecords() {
        return records;
    }

    public void setRecords(HashMap<String, Boolean> records) {
        this.records = records;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
