package com.alodia.bitbash.models;

/**
 * Created by Alaina Traxler on 3/14/2017.
 */

public class Record {
    public String createdOn;
    public String playerName;
    public String playerId;
    public String gameName;
    public String gameId;
    public String pushId;
    public String type;

    public Record(String playerName, String playerId, String gameName, String gameId) {
        this.playerName = playerName;
        this.playerId = playerId;
        this.gameName = gameName;
        this.gameId = gameId;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
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

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
