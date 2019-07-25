package ru.rd.rest;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.math.BigInteger;

import static org.testng.AssertJUnit.assertEquals;

public class ExampleTests extends TestBase {

    @BeforeMethod
    public void before() throws IOException {
        BigInteger issueId = new BigInteger("1589");
        skipIfNotFixed(issueId);
    }

    @Test
    public void testExample() {
        assertEquals(2, 2);
    }
}
