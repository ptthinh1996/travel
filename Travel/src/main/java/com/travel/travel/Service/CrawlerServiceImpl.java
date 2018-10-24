package com.travel.travel.Service;

import com.travel.travel.Model.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Service
public class CrawlerServiceImpl implements CrawlerService {

    @Autowired
    TourService tourService;

    @Override
    public Set<String> getPageLinks(String URL,String link) {
        Set<String> links = new HashSet<>();

        if (!links.contains(URL)) {
            try {
                Document document = Jsoup.connect(URL).get();
                Elements otherLinks = document.select("a[href^=\"" + link + "\"]");
                for (Element page : otherLinks) {
                    if (links.add(URL)) {
                        System.out.println(URL);
                    }
                    getPageLinks(page.attr("abs:href"), link);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        return links;
    }

    @Override
    public void crawlDataDatViet(Set<String> links) {

        for (String url: links) {
            Document doc;
            try {
                doc = Jsoup.connect(url).get();
                Elements ahref = doc.getElementsByTag("a");
                for (Element href : ahref) {
                    String link = href.absUrl("href");
                    String c = href.className();
                    if (link.contains("tour-du-lich") && c.equals("more-detail1")) {
                        Element x = href.select("div.name1.clearfix > div.name2 > h3 > b").first();
                        Element y = href.select("div.name1.clearfix > div.price-list.clearfix > cite").first();
                        System.out.println(x.text() + "\nGia: " + y.text() + "\nLink: " + link + "\n----------");
                        String name = x.text();
                        String price = y.text();
                        price = price.replaceAll("[^0-9]","");
                        Double value = Double.parseDouble(price);
                        Tour tour = new Tour();
                        tour.setName(name);
                        tour.setUrl(link);
                        tour.setPrice(value);

                        tourService.addTour(tour);
                    }
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @Override
    public void crawDataSaigon(Set<String> links) {
        for (String url : links) {
            try {
                Document doc = Jsoup.connect(url).get();
                Elements tours = doc.select("div.media.packagesList");
                for (Element element : tours) {
                    Element x = element.select(">div.media-body >div.bodyLeft >h4 >a").first();
                    Element y = element.select("div.bookingDetails >h2").first();
                    if (y != null) {
                        String name = x.text();
                        String price = y.text();
                        String link = x.absUrl("href");
                        price = price.replaceAll("[^0-9]","");
                        Double value = Double.parseDouble(price);
                        Tour tour = new Tour();
                        tour.setName(name);
                        tour.setUrl(link);
                        tour.setPrice(value);

                        tourService.addTour(tour);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void crawlDataKimLien(Set<String> links) {
        for (String url : links) {
            try {
                Document doc = Jsoup.connect(url).get();
                Elements tours = doc.select("div.sanpham_cell");
                for (Element element : tours) {
                    Element x = element.select(">h2 >a").first();
                    Element y = element.select(">div.sanpham_gia_dathang >div.giavnd_cot >span").first();
                    if (y != null) {
                        String name = x.text();
                        String price = y.text();
                        String link = x.absUrl("href");
                        price = price.replaceAll("[^0-9]", "");
                        Double value = Double.parseDouble(price);

                        Tour tour = new Tour();
                        tour.setName(name);
                        tour.setUrl(link);
                        tour.setPrice(value);

                        tourService.addTour(tour);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
