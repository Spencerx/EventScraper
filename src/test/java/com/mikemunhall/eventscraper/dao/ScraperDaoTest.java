package com.mikemunhall.eventscraper.dao;

import org.testng.annotations.*;

public class ScraperDaoTest {

    @BeforeMethod
    public void setUp() {
        System.out.println("setUp");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("tearDown");
    }

    @Test
    public void testApp1() {
        assert(true);
    }

    @Test
    public void testApp2() {
        assert(true);
    }
}