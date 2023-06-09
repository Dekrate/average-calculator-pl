package pl.diakowski.averagecalculatorpl;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Grade {
    private DoubleProperty grade;
    private DoubleProperty weight;

    public Grade(Double grade, Double weight) {
        this.grade = new SimpleDoubleProperty(grade);
        this.weight = new SimpleDoubleProperty(weight);
    }

    public Grade() {

    }

    public Double getGrade() {
        return grade.get();
    }

    public void setGrade(Double grade) {
        this.grade.set(grade);
    }

    public Double getWeight() {
        return weight.get();
    }

    public void setWeight(Double weight) {
        this.weight.set(weight);
    }
}
