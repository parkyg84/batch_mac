package com.yonggoo.batch_mac.balance.check_mgt.model;

import java.time.LocalDate;

public class CheckMgtActor {


    private int actorId;
    private String firstName;
    private String lastName;
    private LocalDate lastUpdate;


    public CheckMgtActor() {}

    public CheckMgtActor(int actorId, String firstName, String lastName, LocalDate lastUpdate) {
        this.actorId = actorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastUpdate = lastUpdate;
    }

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }



}
