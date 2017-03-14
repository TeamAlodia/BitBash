package com.alodia.bitbash.models;

import java.util.HashMap;

/**
 * Created by Alaina Traxler on 3/14/2017.
 */

public class Player {
    public String name;
    public String initials;
    public String height;
    public String weight;
    public String dateOfBirth;
    public String bloodType;
    public String bio;
    public String pushId;
    public HashMap<String, Boolean> records = new HashMap<>();
    public HashMap<String, Boolean> friends = new HashMap<>();
    public HashMap<String, Boolean> competitions = new HashMap<>();

    public Player(String name, String initials, String height, String weight, String dateOfBirth, String bloodType, String bio) {
        this.name = name;
        this.initials = initials;
        this.height = height;
        this.weight = weight;
        this.dateOfBirth = dateOfBirth;
        this.bloodType = bloodType;
        this.bio = bio;
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

    public HashMap<String, Boolean> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(HashMap<String, Boolean> competitions) {
        this.competitions = competitions;
    }
}