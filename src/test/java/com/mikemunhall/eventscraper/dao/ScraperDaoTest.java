package com.mikemunhall.eventscraper.dao;

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

import static org.testng.Assert.*;

public class ScraperDaoTest {

    private MongodExecutable mongoDExe;
    private MongodProcess mongoD;
    private Mongo mongo;

    @BeforeClass
    public void startMongo() throws IOException {
        MongoDBRuntime runtime = MongoDBRuntime.getDefaultInstance();
        mongoDExe = runtime.prepare(new MongodConfig(Version.V2_0, 2306, false));
        mongoD = mongoDExe.start();
        mongo = new Mongo("localhost", 2307);
    }

    @AfterClass
    public void stopMongo() {
        mongoD.stop();
        mongoDExe.cleanup();
    }

    @BeforeMethod
    public void setUp() throws UnknownHostException{
        System.out.println("setUp");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("tearDown");
    }

    @Test
    public void testInsertEmptyDocument_Returns0AndInsertsNoRecords() {
        System.out.println("test1");
    }

    @Test
    public void testInsertDocumentWithEvent_Returns2AndInsertsTwoRecords() {
        System.out.println("test2");
    }
}