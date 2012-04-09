package com.mikemunhall.eventscraper;

import com.mikemunhall.eventscraper.service.ScraperService;
import java.io.IOException;
import java.text.ParseException;
import java.io.FileInputStream;
import java.util.Properties;

public class Scraper {

    public static void main(String[] args) throws ParseException, IOException {

        String scraperConfigPath = System.getProperty("configPath", "./config/config.properties");

        Properties props = new Properties();
        props.load(new FileInputStream(scraperConfigPath));

        ScraperService service = new ScraperService(props.getProperty("scrapeUrl"));
        service.readAndSave();
    }
}
