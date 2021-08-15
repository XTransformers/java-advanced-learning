package com.xtransformers.designpattern.interpreter;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressionInterpreterTest {

    @Test
    public void testInterpreter() {
        ExpressionInterpreter expressionInterpreter = new ExpressionInterpreter();
        long result = expressionInterpreter.interpret("8 3 2 4 - + *");
        assertEquals(28, result);
    }

    @Test(expected = RuntimeException.class)
    public void testExp() {
        ExpressionInterpreter expressionInterpreter = new ExpressionInterpreter();
        long result = expressionInterpreter.interpret("8 3 2 0 - + /");
        assertEquals(28, result);
    }
}