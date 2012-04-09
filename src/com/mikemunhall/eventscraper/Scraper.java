package com.mikemunhall.eventscraper;

import com.mikemunhall.eventscraper.service.ScraperService;
import com.mikemunhall.eventscraper.model.ScrapedEvent;
import java.io.IOException;
import java.text.ParseException;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

public class Scraper {

    public static void main(String[] args) throws ParseException, IOException {

        String scraperConfigPath = System.getProperty("configPath", "./config/config.properties");

        Properties props = new Properties();
        props.load(new FileInputStream(scraperConfigPath));

        String scrapeUrl = props.getProperty("scrapeUrl");

        ArrayList<ScrapedEvent> events = ScraperService.parse(scrapeUrl);
        ScraperService.persist(events);
    }
}
