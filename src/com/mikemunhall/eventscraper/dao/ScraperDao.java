package com.mikemunhall.eventscraper.dao;

import com.mikemunhall.eventscraper.model.ScrapedEvent;
import com.mongodb.BasicDBObject;
import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import java.net.UnknownHostException;
import java.util.Iterator;

public class ScraperDao {

    Mongo m = null;
    DB db = null;

    public ScraperDao(String host, Integer port, String dbName) throws UnknownHostException {
        this.m = new Mongo(host, port);
        this.db = m.getDB(dbName);
    }

    public boolean save(ScrapedEvent event) {
        DBCollection eventsCollection = db.getCollection("events");

        BasicDBObject doc = new BasicDBObject();

        Iterator it = event.iterator();

        while (it.hasNext()) {
            doc.put(it.next().key(), it.next().val());
        }

        eventsCollection.insert(doc);

        return true;
    }
}
