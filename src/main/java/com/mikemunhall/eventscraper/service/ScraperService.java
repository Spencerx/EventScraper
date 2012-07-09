package com.mikemunhall.eventscraper.service;

import com.mikemunhall.eventscraper.dao.ScraperDao;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.mikemunhall.eventscraper.model.ScrapedEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ScraperService {

    private ScraperDao dao = null;
    // private ScraperParser parser = null;

    public ScraperService() { }

    public ArrayList<ScrapedEvent> parse(String scrapeUrl) throws IOException {
        String eventDate = "";
        ArrayList<String> labels = new ArrayList<String>();
        ArrayList<ScrapedEvent> events = new ArrayList<ScrapedEvent>();

        Document doc = Jsoup.connect(scrapeUrl).get();

        Elements rows = doc.select("tr");
        Iterator rowIterator = rows.iterator();

        int i = 0;
        while (rowIterator.hasNext()) {
            switch (i) {
                case 0: // We don't care about this row.
                    rowIterator.next();
                    break;
                case 1: // eventDate
                    eventDate = ((Element) rowIterator.next()).select("big big").text();
                    break;
                case 2: // labels
                    Elements labelCells = ((Element) rowIterator.next()).select("td");
                    Iterator labelCellIterator = labelCells.iterator();
                    while (labelCellIterator.hasNext()) {
                        labels.add(((Element) labelCellIterator.next()).text());
                    }
                default: // an event
                    ScrapedEvent event = new ScrapedEvent();
                    Elements eventCells = ((Element) rowIterator.next()).select("td");
                    Iterator eventCellIterator = eventCells.iterator();

                    int j=0;
                    while (eventCellIterator.hasNext()) {
                        String key = labels.get(j);
                        String val = ((Element) eventCellIterator.next()).text();

                        if (key.equals("Start")) {
                            // This cell is the start time. Concat with eventDate. We could also create an instance of
                            // java.util.Date or java.util.Calendar, but since we're just going to cast back to a
                            // string, there doesn't seem to be much point.
                            val = eventDate + " " + String.valueOf(val);
                        }

                        event.put(labels.get(j), val);
                        j++;
                    }

                    events.add(event);
                    break;
            }
            i++;
        }

        return events;
    }

    public void persist(ArrayList<ScrapedEvent> events) {
        Iterator it = events.iterator();
        while (it.hasNext()) {
            this.dao.save((ScrapedEvent) it.next());
        }
    }

    public void setDao(ScraperDao dao) {
        this.dao = dao;
    }

    public ScraperDao getDao() {
        return this.dao;
    }

    /*public ScraperParser getParser() {
        return parser;
    }

    public void setParser(ScraperParser parser) {
        this.parser = parser;
    }*/
}
