package com.alodia.bitbash.models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Alaina Traxler on 3/14/2017.
 */

public class Bash {
    public String name;
    public String description;
    public String createdBy;
    public int currentSeason = 1;
    public String pushId;
    public HashMap<String, Boolean> players;
    public ArrayList<ArrayList<HighScoreTable>> seasons = new ArrayList<>();



    public Bash(String name, String description, String createdBy, HashMap<String, Boolean> players, ArrayList<ArrayList<HighScoreTable>> seasons) {
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
        this.players = players;
        this.seasons = seasons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public int getCurrentSeason() {
        return currentSeason;
    }

    public void setCurrentSeason(int currentSeason) {
        this.currentSeason = currentSeason;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public HashMap<String, Boolean> getPlayers() {
        return players;
    }

    public void setPlayers(HashMap<String, Boolean> players) {
        this.players = players;
    }

    public ArrayList<ArrayList<HighScoreTable>> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<ArrayList<HighScoreTable>> seasons) {
        this.seasons = seasons;
    }
}
