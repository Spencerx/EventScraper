package com.mikemunhall.eventscraper.dao;

import com.mikemunhall.eventscraper.model.ScrapedEvent;
import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import java.net.UnknownHostException;
import java.util.Set;

public class ScraperDao {

    Mongo m = null;
    DB conn = null;

    public ScraperDao(String host, Integer port, String dbName) throws UnknownHostException {
        this.m = new Mongo(host, port);
        this.conn = m.getDB(dbName);
    }

    public boolean save(ScrapedEvent event) {
        Set<String> colls = conn.getCollectionNames();

        for (String s : colls) {
            System.out.println(s);
        }
System.out.println("done");
        return true;
    }
}
