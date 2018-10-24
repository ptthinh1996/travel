package com.travel.travel.Service;

import java.util.Set;

public interface CrawlerService {

    Set<String> getPageLinks(String URL,String link);

    void crawlDataDatViet(Set<String> links);

    void crawDataSaigon(Set<String> links);

    void crawlDataKimLien(Set<String> links);
}
