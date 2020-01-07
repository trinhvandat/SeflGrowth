package com.selfgrowth.model.finance.income;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.Objects;

@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IncomeDto implements Serializable {


    private int id;

    @JsonProperty("earn_Income")
    private double earnIncome;

    @JsonProperty("passive_Income")
    private double passiveIncome;

    public IncomeDto() {
    }

    public IncomeDto(int id, double earnIncome, double passiveIncome) {
        this.id = id;
        this.earnIncome = earnIncome;
        this.passiveIncome = passiveIncome;
    }

    public IncomeDto(Builder builder) {
        this.earnIncome = builder.earnIncome;
        this.passiveIncome = builder.passiveIncome;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public double getEarnIncome() {
        return earnIncome;
    }

    public void setEarnIncome(double earnIncome) {
        this.earnIncome = earnIncome;
    }

    public double getPassiveIncome() {
        return passiveIncome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassiveIncome(double passiveIncome) {
        this.passiveIncome = passiveIncome;
    }

    public static class Builder {
        private double earnIncome;
        private double passiveIncome;

        public Builder() {
        }

        public Builder earnIncome(double earnIncome) {
            this.earnIncome = earnIncome;
            return this;
        }

        public Builder passiveIncome(double passiveIncome) {
            this.passiveIncome = passiveIncome;
            return this;
        }

        public IncomeDto build() {
            IncomeDto build = new IncomeDto(this);
            return build;
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || (o.getClass() != this.getClass())) {
            return false;
        }

        IncomeDto other = (IncomeDto) o;

        return
                Objects.equals(earnIncome, other.earnIncome) &&
                        Objects.equals(passiveIncome, other.passiveIncome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(

                earnIncome,
                passiveIncome);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("earnIncome", earnIncome)
                .add("passiveIncome", passiveIncome)
                .toString();
    }
}
