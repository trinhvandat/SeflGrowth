package com.selfgrowth.model.finance.income;

import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "income")
public class Income implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "earnincome", nullable = false)
    private double earnIncome;

    @Column(name = "passiveincome", nullable = false)
    private double passiveIncome;

    public Income() {
    }

    public Income(Builder builder) {
        this.earnIncome = builder.earnIncome;
        this.passiveIncome = builder.passiveIncome;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setPassiveIncome(double passiveIncome) {
        this.passiveIncome = passiveIncome;
    }

    public static class Builder {
        private double earnIncome;
        private double passiveIncome;

        private Builder() {
        }

        public Builder earnIncome(double earnIncome) {
            this.earnIncome = earnIncome;
            return this;
        }

        public Builder passiveIncome(double passiveIncome) {
            this.passiveIncome = passiveIncome;
            return this;
        }


        public Income build() {
            Income build = new Income(this);
            return build;
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Income other = (Income) o;

        return  Objects.equals(id, other.id) &&
                Objects.equals(earnIncome, other.earnIncome) &&
                Objects.equals(passiveIncome, other.passiveIncome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                earnIncome,
                passiveIncome);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("earnIncome", earnIncome)
                .add("passiveIncome", passiveIncome)
                .toString();
    }
}
