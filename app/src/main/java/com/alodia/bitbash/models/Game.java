package com.alodia.bitbash.models;

import java.util.ArrayList;

/**
 * Created by Alaina Traxler on 3/14/2017.
 */

public class Game {
    public String name = "???";
    public String gameId;
    public ArrayList<String> genres = new ArrayList<>();
    public String numberOfPlayers = "???";
    public String publisher = "???";
    public String developer = "???";
    public String hasCoop = "???";
    public String releaseDate = "???";
    public String overview = "No description available.";
    public String boxArt;

    public Game(){}

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

    public String getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(String numberOfPlayers) {
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
