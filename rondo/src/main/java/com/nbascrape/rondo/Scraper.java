package com.nbascrape.rondo;

import java.io.IOException;

import org.jsoup.Jsoup;

import org.jsoup.select.Elements;

public class Scraper {

    void fetchThings(String url) {
    }

    
    public static void main(String[] args) {
        final String url = "https://stats.nba.com/players/bio/?Season=2019-20&SeasonType=Regular%20Season&sort=PLAYER_NAME&dir=1";

        System.out.println("printing");
        try {
            var doc = Jsoup.connect(url).get();
            String title = doc.title();
            Elements nameTds = doc.getElementsByTag("tbody");
            System.out.println(title);
            System.out.println(nameTds.size());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}