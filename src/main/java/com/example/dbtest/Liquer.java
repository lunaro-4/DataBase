package com.example.dbtest;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Liquer {
    SimpleStringProperty label;
    SimpleStringProperty country;
    SimpleIntegerProperty exposure;
    SimpleFloatProperty strength;
    SimpleIntegerProperty sugar;
    AlcoCategory category;

    public String getLabel() {
        return label.get();
    }

    public SimpleStringProperty labelProperty() {
        return label;
    }

    public String getCountry() {
        return country.get();
    }

    public SimpleStringProperty countryProperty() {
        return country;
    }

    public int getExposure() {
        return exposure.get();
    }

    public SimpleIntegerProperty exposureProperty() {
        return exposure;
    }

    public float getStrength() {
        return strength.get();
    }

    public SimpleFloatProperty strengthProperty() {
        return strength;
    }

    public int getSugar() {
        return sugar.get();
    }

    public SimpleIntegerProperty sugarProperty() {
        return sugar;
    }

    public AlcoCategory getCategory() {
        return category;
    }

    public Liquer(String label,
                  String country,
                  String category,
                  int exposure,
                  float strength,
                  int sugar) {
        this.label= new SimpleStringProperty(label);
        this.country= new SimpleStringProperty(country);
        this.category = AlcoCategory.valueOf(category);
        this.exposure= new SimpleIntegerProperty(exposure);
        this.strength= new SimpleFloatProperty(strength);
        this.sugar= new SimpleIntegerProperty(sugar);
    }
}
