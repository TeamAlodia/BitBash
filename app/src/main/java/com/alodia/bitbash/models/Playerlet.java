package com.alodia.bitbash.models;

/**
 * Created by Alaina Traxler on 3/19/2017.
 */

public class Playerlet {
    public String name;
    public String playerId;
    public String icon;

    public Playerlet() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
