package com.xtransformers.chapter7;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CustomSpliterator {

    // 1.迭代式字数统计
    public static int countWordsIteratively(String words) {
        int result = 0;
        boolean lastSpace = true;
        for (char c : words.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) {
                    result++;
                }
                lastSpace = false;
            }
        }
        return result;
    }

    static final String SENTENCE = " Nel   mezzo del cammin  di nostra  vita "
            + "mi  ritrovai in una  selva oscura"
            + " ché la  dritta via era   smarrita ";

    public static void main(String[] args) {
        // Found 19 words
//        System.out.println("Found " + countWordsIteratively(SENTENCE) + " words");

        Stream<Character> stream = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);

        // Found 19 words
        System.out.println("Found " + new CustomSpliterator().countWords(stream) + " words");

        stream = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);

        // Found 25 words
        System.out.println("Found " + new CustomSpliterator().countWords(stream.parallel()) + " words");

        Spliterator<Character> spliterator = new WordCountSpliterator(SENTENCE);
        Stream<Character> parallelStream = StreamSupport.stream(spliterator, true);
        System.out.println("Found " + new CustomSpliterator().countWords(parallelStream) + " words");

    }

    // 2.函数式风格改写

    public int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);
        return wordCounter.getCounter();
    }

    class WordCounter {
        private int counter;
        private boolean lastSpace;

        public WordCounter(int counter, boolean lastSpace) {
            this.counter = counter;
            this.lastSpace = lastSpace;
        }

        public WordCounter accumulate(Character c) {
            if (Character.isWhitespace(c)) {
                return lastSpace ?
                        this :
                        new WordCounter(counter, true);
            } else {
                return lastSpace ?
                        new WordCounter(counter + 1, false) :
                        this;
            }
        }

        public WordCounter combine(WordCounter wordCounter) {
            return new WordCounter(counter + wordCounter.counter,
                    wordCounter.lastSpace);
        }

        public int getCounter() {
            return counter;
        }
    }

    // 自定义 Spliterator
    static class WordCountSpliterator implements Spliterator<Character> {

        private final String string;

        private int currentChar = 0;

        public WordCountSpliterator(String string) {
            this.string = string;
        }

        @Override
        public boolean tryAdvance(Consumer<? super Character> action) {
            // 处理当前字符
            action.accept(string.charAt(currentChar++));

            // 如果还有字符要处理，返回 true
            return currentChar < string.length();
        }

        @Override
        public Spliterator<Character> trySplit() {
            int currentSize = string.length() - currentChar;
            if (currentSize < 10) {
                return null;
            }
            // 将拆分位置设置为字符串中间
            for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {
                // 让拆分位置前进到下一个空格
                if (Character.isWhitespace(string.charAt(splitPos))) {
                    Spliterator spliterator = new WordCountSpliterator(string.substring(currentChar, splitPos));
                    currentChar = splitPos;
                    return spliterator;
                }
            }
            return null;
        }

        @Override
        public long estimateSize() {
            return string.length() - currentChar;
        }

        @Override
        public int characteristics() {
            return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
        }
    }

}
