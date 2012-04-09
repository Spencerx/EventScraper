package com.mikemunhall.eventscraper.model;

import java.util.HashMap;
import java.util.Iterator;

public class ScrapedEvent {

    private HashMap<String, String> map = new HashMap<String,String>();

    public ScrapedEvent() {
        this.map = new HashMap<String, String>();
    }

    public ScrapedEvent(HashMap<String, String> map) {
        set(map);
    }

    public void set(HashMap<String, String> map) {
        this.map = map;
    }

    public void add(String key, String value) {
        this.map.put(key, value);
    }

    public Iterator iterator() {
        return map.entrySet().iterator();
    }
}