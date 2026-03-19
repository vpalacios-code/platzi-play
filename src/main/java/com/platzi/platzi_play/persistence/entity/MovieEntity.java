package com.platzi.platzi_play.persistence.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "movies")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 150,unique = true)
    private String title;

    @Column(nullable = false,precision = 3)
    private Integer duration;

    @Column(nullable = false,length = 150)
    private String gender;

    @Column(name ="date_release")
    private LocalDate dateRelease;

    @Column(precision = 3,scale = 2)
    private BigDecimal classification;

    @Column(nullable = false, length = 1)
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateRelease() {
        return dateRelease;
    }

    public void setDateRelease(LocalDate dateRelease) {
        this.dateRelease = dateRelease;
    }

    public BigDecimal getClassification() {
        return classification;
    }

    public void setClassification(BigDecimal classification) {
        this.classification = classification;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
