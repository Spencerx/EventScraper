package com.mikemunhall.eventscraper.dao;

import com.mikemunhall.eventscraper.model.ScrapedEvent;

public interface IScraperDao {
    public int save(ScrapedEvent event);
}
