package com.mikemunhall.eventscraper.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ScraperService {

    private String scrapeUrl = "";

    public ScraperService(String scrapeUrl) {
        this.scrapeUrl = scrapeUrl;
    }

    public ArrayList<ArrayList<String>> readAndSave() throws IOException {
        ArrayList<ArrayList<String>> results = parse();
        persist();
        return results;
    }

    public ArrayList<ArrayList<String>> parse() throws IOException{
        String playDate = "";
        ArrayList<String> labels = new ArrayList<String>();
        ArrayList<ArrayList<String>> events = new ArrayList<ArrayList<String>>();

        Document doc = Jsoup.connect(scrapeUrl).get();

        Elements rows = doc.select("tr");
        Iterator rowIterator = rows.iterator();

        int i = 0;
        while (rowIterator.hasNext()) {
            switch (i) {
                case 0: // We don't care about this row.
                    rowIterator.next();
                    break;
                case 1: // playDate
                    playDate = ((Element) rowIterator.next()).select("big big").text();
                    break;
                case 2: // labels
                    Elements labelCells = ((Element) rowIterator.next()).select("td");
                    Iterator labelCellIterator = labelCells.iterator();
                    while (labelCellIterator.hasNext()) {
                        labels.add(((Element) labelCellIterator.next()).text());
                    }
                default: // an event
                    ArrayList<String> event = new ArrayList<String>();
                    Elements eventCells = ((Element) rowIterator.next()).select("td");
                    Iterator eventCellIterator = eventCells.iterator();

                    int j=0;
                    while (eventCellIterator.hasNext()) {
                        String val = ((Element) eventCellIterator.next()).text();
                        if (labels.get(j).equals("Start")) {
                            // This cell is the start time. Concat with playDate. We could also create an instance of
                            // java.util.Date or java.util.Calendar, but since we're just going to cast back to a
                            // string, there doesn't seem to be much point.
                            val = playDate + " " + String.valueOf(val);
                        }
                        event.add(val);
                        j++;
                    }
                    events.add(event);
                    break;
            }
            i++;
        }

        ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
        results.add(labels);
        results.add(events);

        return results;
    }

    private void persist() {

    }
}
