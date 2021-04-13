package com.xtransformers.chapter8.strategy;

public class Main {
    public static void main(String[] args) {
        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        System.out.println(lowerCaseValidator.validate("Aabcd"));

        Validator numericValidator = new Validator(new IsNumeric());
        System.out.println(numericValidator.validate("123"));

        // 使用 Lambda 表达式
        lowerCaseValidator = new Validator(s -> s.matches("[a-z]+"));
        System.out.println(lowerCaseValidator.validate("aaa"));

        numericValidator = new Validator(s -> s.matches("\\d+"));
        System.out.println(numericValidator.validate("123a"));
    }
}
