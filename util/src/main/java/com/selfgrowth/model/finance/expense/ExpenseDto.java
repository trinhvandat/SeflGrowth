package com.selfgrowth.model.finance.expense;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.io.Serializable;
import java.util.Objects;


@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ExpenseDto implements Serializable {

    private int id;
    @JsonProperty("eat")
    private double eat;
    @JsonProperty("travel_expense")
    private double travelExpense;
    @JsonProperty("health")
    private double health;
    @JsonProperty("entertainment")
    private double entertainment;
    @JsonProperty("other")
    private double other;

    public ExpenseDto() {
    }

    public ExpenseDto(int id, double eat, double travelExpense, double health, double entertainment, double other) {
        this.id = id;
        this.eat = eat;
        this.travelExpense = travelExpense;
        this.health = health;
        this.entertainment = entertainment;
        this.other = other;
    }

    public ExpenseDto(Builder builder){
        this.eat = builder.eat;
        this.travelExpense = builder.travelExpense;
        this.health = builder.health;
        this.entertainment = builder.entertainment;
        this.other = builder.other;
    }

    public static Builder getBuilder (){
        return new Builder();
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

    public static class Builder{

        private double eat;
        private double travelExpense;
        private double health;
        private double entertainment;
        private double other;

        public Builder eat(double eat){
            this.eat = eat;
            return this;
        }

        public Builder travelExpense(double travelExpense){
            this.travelExpense = travelExpense;
            return this;
        }

        public Builder health(double health){
            this.health = health;
            return  this;
        }

        public Builder entertainment(double entertainment){
            this.entertainment = entertainment;
            return this;
        }

        public Builder other(double other){
            this.other = other;
            return this;
        }

        public ExpenseDto build(){
            ExpenseDto build = new ExpenseDto(this);
            return build;
        }
    }

    @Override
    public boolean equals(final Object obj) {

        if(this == obj){
            return true;
        }

        if(obj == null || (obj.getClass() != this.getClass())){
            return  false;
        }

        ExpenseDto other = (ExpenseDto) obj;

        return Objects.equals(eat, other.eat) &&
                Objects.equals(health, other.health) &&
                Objects.equals(travelExpense, other.travelExpense) &&
                Objects.equals(entertainment, other.entertainment) &&
                Objects.equals(other, other.other);
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
                .add("other", other)
                .toString();
    }
}
