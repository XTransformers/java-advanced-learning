package com.xtransformers.designpattern.interpreter.refactor;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressionInterpreterTest {

    @Test
    public void test() {
        ExpressionInterpreter expressionInterpreter = new ExpressionInterpreter();
        long result = expressionInterpreter.interpret("8 3 2 4 - + *");
        assertEquals(28, result);
    }

}