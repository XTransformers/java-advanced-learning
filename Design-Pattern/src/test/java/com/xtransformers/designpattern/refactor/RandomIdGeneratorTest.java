package com.xtransformers.designpattern.refactor;

import com.google.common.collect.Sets;
import com.xtransformers.designpattern.refactor.exception.IdGenerationFailureException;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class RandomIdGeneratorTest {

    @Test
    public void generate() throws IdGenerationFailureException {
        RandomIdGenerator idGenerator = new RandomIdGenerator();
        String actualRandomStr;
        Set<String> set = Sets.newHashSet();
        int count = 10_000;
        for (int i = 0; i < count; i++) {
            actualRandomStr = idGenerator.generate();
            set.add(actualRandomStr);
        }
        assertEquals(count, set.size());
    }

    @Test
    public void getLastSubstrSplittedByDot() {
        RandomIdGenerator idGenerator = new RandomIdGenerator();
        String actualSubstr = idGenerator.getLastSubstrSplittedByDot("field1.field2.field3");
        assertEquals("field3", actualSubstr);

        actualSubstr = idGenerator.getLastSubstrSplittedByDot("field1");
        assertEquals("field1", actualSubstr);

        actualSubstr = idGenerator.getLastSubstrSplittedByDot("field1#field2#field3");
        assertEquals("field1#field2#field3", actualSubstr);
    }

    @Test
    public void getLastSubstrSplittedByDot_nullOrEmpty() {
        RandomIdGenerator idGenerator = new RandomIdGenerator();
        String actualSubstr = idGenerator.getLastSubstrSplittedByDot(null);
        assertNull(actualSubstr);

        actualSubstr = idGenerator.getLastSubstrSplittedByDot("");
        assertEquals("", actualSubstr);
    }

    @Test
    public void generateRandomAlphameric() {
        RandomIdGenerator idGenerator = new RandomIdGenerator();
        String actualRandomStr = idGenerator.generateRandomAlphameric(6);
        assertNotNull(actualRandomStr);
        assertEquals(6, actualRandomStr.length());
//        for (char c : actualRandomStr.toCharArray()) {
//            assertTrue((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'));
//        }
        actualRandomStr.chars()
                .mapToObj(c -> (char) c)
                .forEach(c -> assertTrue((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')));
    }

    @Test
    public void generateRandomAlphameric_lengthEqualsOrLessThanZero() {
        RandomIdGenerator idGenerator = new RandomIdGenerator();
        String actualRandomStr = idGenerator.generateRandomAlphameric(0);
        assertEquals("", actualRandomStr);

        actualRandomStr = idGenerator.generateRandomAlphameric(-1);
        assertNull(actualRandomStr);
    }

}