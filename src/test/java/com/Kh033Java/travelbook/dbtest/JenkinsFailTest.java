package com.Kh033Java.travelbook.dbtest;

import org.junit.Assert;
import org.junit.Test;

public class JenkinsFailTest {

    @Test
    public void failTest() {
        Assert.assertEquals("true", "false");
    }
}
