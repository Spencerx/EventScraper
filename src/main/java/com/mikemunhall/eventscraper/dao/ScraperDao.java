package com.mikemunhall.eventscraper.dao;

import com.mikemunhall.eventscraper.model.ScrapedEvent;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import java.util.Iterator;
import java.util.Map;

public class ScraperDao implements IScraperDao {

    private DB database = null;

    public ScraperDao() { }

    public int save(ScrapedEvent event) {
        DBCollection eventsCollection = database.getCollection("events");

        BasicDBObject doc = new BasicDBObject();

        Iterator it = event.iterator();

        int count = 0;
        while (it.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            doc.put(entry.getKey(), entry.getValue());
            count++;
        }

        eventsCollection.insert(doc);
        System.out.println(doc);
        return count;
    }

    public void setDatabase(DB database) {
        this.database = database;
    }

    public DB getDatabase() {
        return this.database;
    }
}
