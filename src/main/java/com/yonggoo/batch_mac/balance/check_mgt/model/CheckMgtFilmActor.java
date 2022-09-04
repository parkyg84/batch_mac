package com.yonggoo.batch_mac.balance.check_mgt.model;

import java.time.LocalDate;

public class CheckMgtFilmActor {


    private int actorId;
    private int filmId;
    private LocalDate lastUpdate;


    public CheckMgtFilmActor() {}

    public CheckMgtFilmActor(int actorId, int filmId, LocalDate lastUpdate) {
        this.actorId = actorId;
        this.filmId = filmId;
        this.lastUpdate = lastUpdate;
    }

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }


}
