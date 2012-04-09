package com.mikemunhall.scraper;

import java.io.IOException;
import java.text.ParseException;
import java.io.FileInputStream;
import java.util.Properties;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.Iterator;

public class Scraper {

    public static void main(String[] args) throws ParseException, IOException {

        String scraperConfigPath = System.getProperty("configPath", "./config/config.properties");

        Properties props = new Properties();
        props.load(new FileInputStream(scraperConfigPath));

        ScraperService service = new ScraperService(props.getProperty("scrapeUrl"));
        service.readAndSave();
    }
}
