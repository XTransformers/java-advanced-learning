package com.xtransformers.chapter14.train;

import org.junit.Test;

/**
 * @author daniel
 * @date 2021-05-05
 */
public class Client {

    // 传统命令式
    public static TrainJourney link(TrainJourney a, TrainJourney b) {
        if (a == null) {
            return b;
        }

        TrainJourney onward = a;
        while (onward.getOnward() != null) {
            onward = onward.getOnward();
        }

        onward.setOnward(b);
        return a;
    }

    // 函数式编程方式
    public static TrainJourney append(TrainJourney a, TrainJourney b) {
        return a == null ? b : new TrainJourney(a.getPrice(), append(a.getOnward(), b));
    }

    @Test
    public void test1() {
        TrainJourney a2 = new TrainJourney(6, null);
        TrainJourney a = new TrainJourney(3, a2);
        TrainJourney b = new TrainJourney(9, null);

        System.out.println(a);
        System.out.println(b);

        System.out.println(link(a, b));

        System.out.println(a);

        /**
         * TrainJourney{price=3.0, onward=TrainJourney{price=6.0, onward=null}}
         * TrainJourney{price=9.0, onward=null}
         * TrainJourney{price=3.0, onward=TrainJourney{price=6.0, onward=TrainJourney{price=9.0, onward=null}}}
         * TrainJourney{price=3.0, onward=TrainJourney{price=6.0, onward=TrainJourney{price=9.0, onward=null}}}
         */
    }

    @Test
    public void test2() {
        TrainJourney a2 = new TrainJourney(6, null);
        TrainJourney a = new TrainJourney(3, a2);
        TrainJourney b = new TrainJourney(9, null);

        System.out.println(a);
        System.out.println(b);

        System.out.println(append(a, b));

        System.out.println(a);
        /**
         * TrainJourney{price=3.0, onward=TrainJourney{price=6.0, onward=null}}
         * TrainJourney{price=9.0, onward=null}
         * TrainJourney{price=3.0, onward=TrainJourney{price=6.0, onward=TrainJourney{price=9.0, onward=null}}}
         * TrainJourney{price=3.0, onward=TrainJourney{price=6.0, onward=null}}
         */
    }

}
