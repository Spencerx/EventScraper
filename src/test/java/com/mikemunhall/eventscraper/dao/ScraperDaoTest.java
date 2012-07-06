package com.mikemunhall.eventscraper.dao;

import com.mikemunhall.eventscraper.dao.ScraperDao;
import com.mikemunhall.eventscraper.model.ScrapedEvent;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.DB;
import org.testng.annotations.*;
import java.net.UnknownHostException;
import java.util.HashMap;
import org.testng.annotations.Test;

import static org.mockito.Mockito.spy;
import static org.testng.Assert.*;

public class ScraperDaoTest {

    private ScraperDao dao;

    @BeforeMethod
    public void setUp() throws UnknownHostException{
        dao = new ScraperDao("localhost", 27129, "unittests");
        System.out.println("setUp");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("tearDown");
    }

    @Test
    public void testInsertEmptyDocument_ReturnsZer0() {
        assertEquals(0, dao.save(new ScrapedEvent()));
    }

    @Test
    public void testInsertDocumentWithEvent_Returns2() {
        DBCollection coll = spy(DB.);



        HashMap<String, String> record = new HashMap<String, String>();
        record.put("One", "_1_");
        record.put("Two", "_2_");
        ScrapedEvent event = new ScrapedEvent(record);
        assertEquals(2, dao.save(event));
    }
}