package org.openpay.service;

import org.openpay.client.model.MarvelQueriesInfo;
import org.openpay.client.repository.MarvelQueriesInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarvelQueriesInfoServiceImpl implements MarvelQueriesInfoService{

    private final MarvelQueriesInfoRepository marvelQueriesInfoRepository;

    @Autowired
    public MarvelQueriesInfoServiceImpl(MarvelQueriesInfoRepository marvelQueriesInfoRepository){
        this.marvelQueriesInfoRepository = marvelQueriesInfoRepository;
    }

    @Override
    public List<MarvelQueriesInfo> getMarvelQueriesInfo() {
        return marvelQueriesInfoRepository.findAll();
    }
}
