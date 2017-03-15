package com.alodia.bitbash.models;

/**
 * Created by Alaina Traxler on 3/14/2017.
 */

public class Gamelet {
    public String name;
    public String system;
    public String gameId;

    public Gamelet(){}

    public Gamelet(String name, String gameId) {
        this.name = name;
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}
