package com.alodia.bitbash.models;

import java.util.ArrayList;

/**
 * Created by Alaina Traxler on 3/14/2017.
 */

public class Game {
    public String name = "???";
    public String gameId;
    public ArrayList<String> genres = new ArrayList<>();
    public int numberOfPlayers = 1;
    public String publisher = "???";
    public String developer = "???";
    public ArrayList<String> screenshots = new ArrayList<>();
    public String hasCoop = "???";
    public String overview = "No description available.";
    public String boxArt;

    public Game(String name, String gameId) {
        this.name = name;
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public ArrayList<String> getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(ArrayList<String> screenshots) {
        this.screenshots = screenshots;
    }

    public String getHasCoop() {
        return hasCoop;
    }

    public void setHasCoop(String hasCoop) {
        this.hasCoop = hasCoop;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBoxArt() {
        return boxArt;
    }

    public void setBoxArt(String boxArt) {
        this.boxArt = boxArt;
    }
}