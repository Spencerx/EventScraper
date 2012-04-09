package com.mikemunhall.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class scraper {

    private static String scrapeUrl = "http://nexgen.cpr.org/playlist.html";
    private static int scrapeInterval = 120;

    public static void main(String[] args) throws ParseException, IOException {
        Calendar playDate = Calendar.getInstance();
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
                    String extractedDate = ((Element) rowIterator.next()).select("big big").text();
                    SimpleDateFormat playDateFormatter = new SimpleDateFormat("MMMMM dd, yyyy");
                    playDate = playDateFormatter.parse(extractedDate);
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
                        SimpleDateFormat eventDateFormatter = new SimpleDateFormat("MMMMM dd, yyyy h:mm a");
                        String val = ((Element) eventCellIterator.next()).text();
                        if (labels[j] == 'Start') {
                            // This is the start time. Merge value with playDate


                            val = eventDateFormatter(playDate.getYear(), playDate)
                        }
                        event.add(val);
                        j++;
                    }
                    events.add(event);
                    break;
            }
            i++;
        }
    }
}
