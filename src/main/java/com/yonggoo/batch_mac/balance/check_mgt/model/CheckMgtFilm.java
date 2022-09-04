package com.yonggoo.batch_mac.balance.check_mgt.model;

import java.text.DecimalFormat;
import java.time.LocalDate;

public class CheckMgtFilm {


    public CheckMgtFilm(int filmId, String title, String description, String releaseYear) {
        this.filmId = filmId;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
    }

    private int filmId;
    private String title;
    private String description;
    private String releaseYear;

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRelease_year() {
        return releaseYear;
    }

    public void setRelease_year(String releaseYear) {
        this.releaseYear = releaseYear;
    }


//    private int language_id;
//    private int original_language_id;
//    private int rental_duration;
//    private DecimalFormat rental_rate;
//    private int length;
//    private DecimalFormat replacement_cost;
    //private Enum enum;
    //private String special_features;
    //private LocalDate last_update;





    //release_year year(4) DEFAULT NULL,
    //language_id tinyint(3) unsigned NOT NULL,
    //original_language_id tinyint(3) unsigned DEFAULT NULL,
    //rental_duration tinyint(3) unsigned NOT NULL DEFAULT 3,
    //rental_rate decimal(4,2) NOT NULL DEFAULT 4.99,
    //length smallint(5) unsigned DEFAULT NULL,
    //replacement_cost decimal(5,2) NOT NULL DEFAULT 19.99,
    //rating enum('G','PG','PG-13','R','NC-17') DEFAULT 'G',
    //special_features set('Trailers','Commentaries','Deleted Scenes','Behind the Scenes') DEFAULT NULL,
    //last_update timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
}
