package com.example.screentestapp.entity;

import java.io.Serializable;

public class NationData implements Serializable {
    String id;
    String nation;
    int idYear;
    String year;
    int population;
    String slugNation;

    public NationData() {
    }

    public NationData(String id, String nation, int idYear, String year, int population, String slugNation) {
        this.id = id;
        this.nation = nation;
        this.idYear = idYear;
        this.year = year;
        this.population = population;
        this.slugNation = slugNation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public int getIdYear() {
        return idYear;
    }

    public void setIdYear(int idYear) {
        this.idYear = idYear;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getSlugNation() {
        return slugNation;
    }

    public void setSlugNation(String slugNation) {
        this.slugNation = slugNation;
    }

    @Override
    public String toString() {
        return "NationData{" +
                "id='" + id + '\'' +
                ", nation='" + nation + '\'' +
                ", idYear=" + idYear +
                ", year='" + year + '\'' +
                ", population=" + population +
                ", slugNation='" + slugNation + '\'' +
                '}';
    }
}
