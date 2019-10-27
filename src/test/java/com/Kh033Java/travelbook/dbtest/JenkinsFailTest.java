package com.Kh033Java.travelbook.dbtest;

import org.junit.Assert;
import org.junit.Test;

public class JenkinsFailTest {

    @Test
    public void failTest() {
        Assert.assertEquals("true", "false");
    }

    @Test
    public void failTest2() {
        Assert.assertEquals("", "10");
    }
}
