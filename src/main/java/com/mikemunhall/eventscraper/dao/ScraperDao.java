package com.mikemunhall.eventscraper.dao;

import com.mikemunhall.eventscraper.model.ScrapedEvent;
import com.mongodb.BasicDBObject;
import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map;

public class ScraperDao {

    private Mongo m = null;
    private DB db = null;

    public ScraperDao(String host, Integer port, String dbName) throws UnknownHostException {
        this.m = new Mongo(host, port);
        this.db = m.getDB(dbName);
    }

    /**
     * Persists an event
     * @param event
     * @return Number of keys saved for event
     */
    public int save(ScrapedEvent event) {
        DBCollection eventsCollection = db.getCollection("events");

        BasicDBObject doc = new BasicDBObject();

        Iterator it = event.iterator();

        int count = 0;
        while (it.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            doc.put(entry.getKey(), entry.getValue());
            count++;
        }

        eventsCollection.insert(doc);

        return count;
    }
}
