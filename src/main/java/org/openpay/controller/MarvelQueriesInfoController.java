package org.openpay.controller;

import org.openpay.client.dto.MarvelQueriesInfo;
import org.openpay.mapper.MarvelQueriesInfoMapper;
import org.openpay.service.MarvelQueriesInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/marvelqueriesinfo")
public class MarvelQueriesInfoController {

    private final MarvelQueriesInfoService marvelQueriesInfoService;

    private final MarvelQueriesInfoMapper marvelQueriesInfoMapper;

    @Autowired
    public MarvelQueriesInfoController(MarvelQueriesInfoService marvelQueriesInfoService,
                                       MarvelQueriesInfoMapper marvelQueriesInfoMapper){
        this.marvelQueriesInfoService = marvelQueriesInfoService;
        this.marvelQueriesInfoMapper = marvelQueriesInfoMapper;
    }

    @GetMapping
    public ResponseEntity<List<MarvelQueriesInfo>> getQueries(){
        List<MarvelQueriesInfo> marvelQueriesInfo = marvelQueriesInfoMapper.toDto(marvelQueriesInfoService.getMarvelQueriesInfo());
        return ResponseEntity.ok(marvelQueriesInfo);
    }
}
