package com.mikemunhall.eventscraper;

import com.mikemunhall.eventscraper.service.ScraperService;
import com.mikemunhall.eventscraper.model.ScrapedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.IOException;
import java.util.ArrayList;

public class Scraper {

    public static void main(String[] args) throws IOException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("resources.xml");
        ScraperService scraperService = (ScraperService) ctx.getBean("scraperService");
        ArrayList<ScrapedEvent> events = scraperService.parse("http://nexgen.cpr.org/playlist.html");
        scraperService.persist(events);
    }
}
