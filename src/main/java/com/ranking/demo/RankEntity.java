package com.ranking.demo;


import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class RankEntity {
    public RankEntity(String login, String date, long points) {
        this.login = login;
        this.date = date;
        this.points = points;
    }
    public RankEntity() {

    }

    @Id
    @GeneratedValue
    private Long id;

    private String login;
    private String date;
    private long points;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }
}
