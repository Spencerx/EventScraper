package com.mikemunhall.eventscraper.service;

import com.mikemunhall.eventscraper.dao.IScraperDao;
import com.mikemunhall.eventscraper.model.ScrapedEvent;
import com.mikemunhall.eventscraper.parser.IScraperParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ScraperService {

    private IScraperDao dao = null;
    private IScraperParser parser = null;

    public ScraperService() { }

    public ArrayList<ScrapedEvent> parse(String scrapeUrl) throws IOException{
        return parser.parse(scrapeUrl);
    }

    public void persist(ArrayList<ScrapedEvent> events) {
        Iterator it = events.iterator();
        while (it.hasNext()) {
            this.dao.save((ScrapedEvent) it.next());
        }
    }

    public void setDao(IScraperDao dao) {
        this.dao = dao;
    }

    public IScraperDao getDao() {
        return this.dao;
    }

    public void setParser(IScraperParser parser) {
        this.parser = parser;
    }

    public IScraperParser getParser() {
        return this.parser;
    }


}
