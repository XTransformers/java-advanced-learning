package com.xtransformers.chapter8.observer;

public class Main {
    public static void main(String[] args) {
        Subject subject = new Feed();
        subject.registerObserver(new NYTimes());
        subject.registerObserver(new Guardian());
        subject.registerObserver(new LeMond());
        subject.notifyObservers("The queen said her favourite book is Java 8 in Action!");

        // Lambda
        Feed feed = new Feed();
        feed.registerObserver(tweet -> {
            if (tweet != null && tweet.contains("money")) {
                System.out.println("Breaking news in NY! " + tweet);
            }
        });
        feed.registerObserver(tweet -> {
            if (tweet != null && tweet.contains("queen")) {
                System.out.println("Yet another news in London... " + tweet);
            }
        });
    }
}
