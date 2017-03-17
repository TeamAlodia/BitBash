package com.alodia.bitbash.models;

import java.util.HashMap;

/**
 * Created by Alaina Traxler on 3/14/2017.
 */

public class Player {
    public String name;
    public String initials;
    public String height = "???";
    public String weight = "???";
    public String dateOfBirth = "???";
    public String bloodType = "???";
    public String origin = "???";
    public String bio = "A mysterious challenger.";
    public String pushId;
    public HashMap<String, Boolean> records = new HashMap<>();
    public HashMap<String, Boolean> friends = new HashMap<>();
    public HashMap<String, Boolean> bashes = new HashMap<>();

    public Player(String name, String initials) {
        this.name = name;
        this.initials = initials;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public HashMap<String, Boolean> getRecords() {
        return records;
    }

    public void setRecords(HashMap<String, Boolean> records) {
        this.records = records;
    }

    public HashMap<String, Boolean> getFriends() {
        return friends;
    }

    public void setFriends(HashMap<String, Boolean> friends) {
        this.friends = friends;
    }

    public HashMap<String, Boolean> getBashes() {
        return bashes;
    }

    public void setBashes(HashMap<String, Boolean> bashes) {
        this.bashes = bashes;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}