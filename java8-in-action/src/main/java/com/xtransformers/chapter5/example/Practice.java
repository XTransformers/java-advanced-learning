package com.xtransformers.chapter5.example;

import com.xtransformers.chapter5.example.domain.Trader;
import com.xtransformers.chapter5.example.domain.Transaction;

import java.util.*;
import java.util.stream.Collectors;

public class Practice {

    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");
    List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950));

    //（1）找出2011年发生的所有交易，并按交易额排序（从低到高）。
    public void test1() {
        List<Transaction> transactions = this.transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        // [{Trad er:Brian in Cambridge, year: 2011, value:300}, {Trad er:Raoul in Cambridge, year: 2011, value:400}]
        System.out.println("transactions = " + transactions);
    }

    //（2）交易员都在哪些不同的城市工作过？
    public void test2() {
        List<String> citys = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        Set<String> citySet = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .collect(Collectors.toSet());
    }

    //（3）查找所有来自于剑桥的交易员，并按姓名排序。
    public void test3() {
        List<Trader> traders = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
    }

    //（4）返回所有交易员的姓名字符串，按字母顺序排序。
    public void test4() {
        List<String> traderNames = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        // 字符串拼接，效率不高
        String traderNameStr = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .sorted()
                .distinct()
                .reduce("", (a, b) -> a + b);
        traderNameStr = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining());
    }

    //（5）有没有交易员是在米兰工作的？
    public void test5() {
        boolean isAnyTraderInMilan = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
    }

    //（6）打印生活在剑桥的交易员的所有交易额。
    public void test6() {
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    //（7）所有交易中，最高的交易额是多少？
    public void test7() {
        Optional<Integer> highestValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
    }

    //（8）找到交易额最小的交易。
    public void test8() {
        Optional<Integer> lowestValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min);
        Optional<Transaction> lowest = transactions.stream()
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
        lowest = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));
    }

}
