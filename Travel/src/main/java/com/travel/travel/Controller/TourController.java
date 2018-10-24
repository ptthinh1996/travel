package com.travel.travel.Controller;

import com.travel.travel.Model.Tour;
import com.travel.travel.Service.CrawlerService;
import com.travel.travel.Service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/tours")
public class TourController {

    @Autowired
    TourService tourService;

    @Autowired
    CrawlerService crawlerService;

    @PostMapping(value = "/crawl_data")
    public void crawlData() {
        Set<String> datVietLinks = crawlerService.getPageLinks("https://datviettour.com.vn/du-lich-trong-nuoc","https://datviettour.com.vn/du-lich-mien-bac.html/page/");
        Set<String> saiGonLinks = crawlerService.getPageLinks("https://www.saigontourist.net/vi/tour/","https://www.saigontourist.net/vi/tour/page/");
        Set<String> kiemLienLinks = crawlerService.getPageLinks("http://kimlientourist.com.vn","http://kimlientourist.com.vn/page/");
        crawlerService.crawlDataDatViet(datVietLinks);
        crawlerService.crawDataSaigon(saiGonLinks);
        crawlerService.crawlDataKimLien(kiemLienLinks);
    }

    @GetMapping(value = "/show_all")
    public ResponseEntity<List<Tour>> tours() {

        List<Tour> tours =tourService.getAllTours();

        return new ResponseEntity<List<Tour>>(tours, HttpStatus.OK);
    }
}
