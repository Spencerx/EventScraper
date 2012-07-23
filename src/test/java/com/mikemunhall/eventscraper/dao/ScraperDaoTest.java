package com.mikemunhall.eventscraper.dao;

import com.mikemunhall.eventscraper.model.ScrapedEvent;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.Mongo;
import de.flapdoodle.embedmongo.MongoDBRuntime;
import de.flapdoodle.embedmongo.MongodExecutable;
import de.flapdoodle.embedmongo.MongodProcess;
import de.flapdoodle.embedmongo.config.MongodConfig;
import de.flapdoodle.embedmongo.distribution.Version;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;

import static org.testng.Assert.*;

public class ScraperDaoTest {

    private String dbName = "dbunittests";
    private MongodExecutable mongoDExe;
    private MongodProcess mongoD;
    private Mongo mongo;
    private DB db;
    private ScraperDao dao = new ScraperDao();

    @BeforeClass
    public void startMongo() throws IOException {
        MongoDBRuntime runtime = MongoDBRuntime.getDefaultInstance();
        mongoDExe = runtime.prepare(new MongodConfig(Version.V2_0, 2306, false));
        mongoD = mongoDExe.start();
        mongo = new Mongo("localhost", 2306);
    }

    @AfterClass
    public void stopMongo() {
        mongoD.stop();
        mongoDExe.cleanup();
    }

    @BeforeMethod
    public void setUp() throws UnknownHostException{
        db = mongo.getDB(dbName);
        dao.setDatabase(db);
    }

    @AfterMethod
    public void tearDown() {
        db.getCollection("events").remove(new BasicDBObject());
    }

    @Test
    public void testInsertEmptyDocument_Returns0AndInsertsNoRecords() {
        HashMap<String, String> eventItems = new HashMap<String, String>();
        ScrapedEvent event1 = new ScrapedEvent(eventItems);
        assertEquals(0, dao.save(event1));
        assertEquals(0, db.getCollection("events").getCount());
    }

    @Test
    public void testInsertDocumentWithEvent_Returns3AndInsertsTwoRecords() {
        HashMap<String, String> eventItems = new HashMap<String, String>();
        eventItems.put("date", "19730212");
        eventItems.put("artist", "Hagfish");
        eventItems.put("title", "Eat It While I Work");
        ScrapedEvent event1 = new ScrapedEvent(eventItems);

        eventItems = new HashMap<String, String>();
        eventItems.put("date", "20080405");
        eventItems.put("artist", "Gauntlet Hair");
        eventItems.put("title", "Top Bunk");
        ScrapedEvent event2 = new ScrapedEvent(eventItems);

        assertEquals(3, dao.save(event1));
        assertEquals(3, dao.save(event2));
        assertEquals(2, db.getCollection("events").getCount());
    }

    @Test
    public void testDuplicatesIgnored() {
        HashMap<String, String> eventItems = new HashMap<String, String>();
        eventItems.put("date", "19730212");
        eventItems.put("artist", "Hagfish");
        eventItems.put("title", "Eat It While I Work");
        ScrapedEvent event1 = new ScrapedEvent(eventItems);

        eventItems = new HashMap<String, String>();
        eventItems.put("date", "19730212");
        eventItems.put("artist", "Hagfish");
        eventItems.put("title", "Eat It While I Work");
        ScrapedEvent event2 = new ScrapedEvent(eventItems);

        assertEquals(3, dao.save(event1));
        assertEquals(0, dao.save(event2));
        assertEquals(1, db.getCollection("events").getCount());
    }
}