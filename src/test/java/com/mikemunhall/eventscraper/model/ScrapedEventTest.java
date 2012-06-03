package com.mikemunhall.eventscraper.model;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ScrapedEventTest {

    @Test
    public void objectContainsAnEmptyHashMapAfterInitializationUsingNoArgConstructor() {
        ScrapedEvent se = new ScrapedEvent();
        Iterator it = se.iterator();
        assertFalse(it.hasNext());
    }

    @Test
    public void objectContainsProvidedHashMapAfterInitializationUsingAlternateConstructor() {
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("One", "Foo");
        hm.put("Two", "Bar");
        ScrapedEvent se = new ScrapedEvent(hm);
        Iterator it = se.iterator();
        HashMap<String, String> hm2 = new HashMap<String, String>();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            hm2.put(entry.getKey(), entry.getValue());
        }
        assertTrue(hm.equals(hm2));
    }

    @Test
    public void objectContainsProvidedHashMapAfterCallingPutAll() {
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("One", "Foo");
        hm.put("Two", "Bar");
        ScrapedEvent se = new ScrapedEvent(hm);
        Iterator it = se.iterator();
        HashMap<String, String> hm2 = new HashMap<String, String>();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            hm2.put(entry.getKey(), entry.getValue());
        }
        assertTrue(hm.equals(hm2));
    }

    @Test
    public void objectContainsProvidedEntryAfterCallingPut() {
        ScrapedEvent se = new ScrapedEvent();
        se.put("Foo", "Bar");
        Iterator it = se.iterator();
        HashMap<String, String> hm2 = new HashMap<String, String>();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            hm2.put(entry.getKey(), entry.getValue());
        }
        assertEquals(1, hm2.size());
        assertTrue(hm2.containsKey("Foo"));
        assertTrue(hm2.containsValue("Bar"));
    }

    @Test
    public void objectReturnsIteratorFromIteratorMethod() {
        ScrapedEvent se = new ScrapedEvent();
        Iterator it = se.iterator();
        assertTrue(it instanceof Iterator);
    }
}