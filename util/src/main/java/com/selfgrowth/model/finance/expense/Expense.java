package com.selfgrowth.model.finance.expense;

import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "expense")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "eat", nullable = false)
    private double eat;

    @Column(name = "travelexpense", nullable = false)
    private double travelExpense;

    @Column(name = "health", nullable = false)
    private double health;

    @Column(name = "entertainment", nullable = false)
    private double entertainment;

    @Column(name = "other", nullable = false)
    private double other;

    public Expense(){
    }

    public Expense(Builder builder) {
        this.eat = builder.eat;
        this.travelExpense = builder.travelExpense;
        this.health = builder.health;
        this.entertainment = builder.entertainment;
        this.other = builder.other;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getEat() {
        return eat;
    }

    public void setEat(double eat) {
        this.eat = eat;
    }

    public double getTravelExpense() {
        return travelExpense;
    }

    public void setTravelExpense(double travelExpense) {
        this.travelExpense = travelExpense;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getEntertainment() {
        return entertainment;
    }

    public void setEntertainment(double entertainment) {
        this.entertainment = entertainment;
    }

    public double getOther() {
        return other;
    }

    public void setOther(double other) {
        this.other = other;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private double eat;
        private double travelExpense;
        private double health;
        private double entertainment;
        private double other;

        private Builder() {
        }

        public Builder eat(double eat) {
            this.eat = eat;
            return this;
        }

        public Builder travelExpense(double travelExpense) {
            this.travelExpense = travelExpense;
            return this;
        }

        public Builder health(double health) {
            this.health = health;
            return this;
        }

        public Builder entertainment(double entertainment) {
            this.entertainment = entertainment;
            return this;
        }

        public Builder other(double other) {
            this.other = other;
            return this;
        }

        public Expense build() {
            Expense build = new Expense(this);
            return build;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return  true;
        }
        if (o == null || (o.getClass() != this.getClass())) return false;
        Expense other = (Expense) o;
        return Objects.equals(other.eat, this.eat) &&
                Objects.equals(other.travelExpense, this.travelExpense) &&
                Objects.equals(other.health, this.health) &&
                Objects.equals(other.entertainment, this.entertainment) &&
                Objects.equals(other.other, this.other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                eat,
                travelExpense,
                health,
                entertainment,
                other);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("eat", eat)
                .add("travelExpense", travelExpense)
                .add("health", health)
                .add("entertainment", entertainment)
                .toString();
    }
}
