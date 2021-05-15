package com.xtransformers.chapter14.train;

/**
 * @author daniel
 * @date 2021-05-05
 */
public class TrainJourney {

    private double price;

    private TrainJourney onward;

    public TrainJourney(double price, TrainJourney onward) {
        this.price = price;
        this.onward = onward;
    }

    public double getPrice() {
        return price;
    }

    public TrainJourney getOnward() {
        return onward;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setOnward(TrainJourney onward) {
        this.onward = onward;
    }

    @Override
    public String toString() {
        return "TrainJourney{" +
                "price=" + price +
                ", onward=" + onward +
                '}';
    }
}
