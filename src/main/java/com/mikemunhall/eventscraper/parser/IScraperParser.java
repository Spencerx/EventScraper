package com.mikemunhall.eventscraper.parser;

import com.mikemunhall.eventscraper.model.ScrapedEvent;
import java.io.IOException;
import java.util.ArrayList;

public interface IScraperParser {

    public ArrayList<ScrapedEvent> parse(String url) throws IOException;

}
