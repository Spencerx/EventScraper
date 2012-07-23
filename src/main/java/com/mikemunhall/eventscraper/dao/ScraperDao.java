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

        if (count > 0 && isUnique(doc)) {
            eventsCollection.insert(doc);
            return count;
        } else {
            return 0;
        }
    }

    private boolean isUnique(BasicDBObject doc) {
        return database.getCollection("events").find(doc).count() == 0;
    }

    public void setDatabase(DB database) {
        this.database = database;
    }

    public DB getDatabase() {
        return this.database;
    }
}
