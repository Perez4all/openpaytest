package org.openpay.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openpay.client.model.MarvelQueriesInfo;
import org.openpay.client.repository.MarvelQueriesInfoRepository;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MarvelQueriesInfoServiceTest {

    @Mock
    private MarvelQueriesInfoRepository marvelQueriesInfoRepository;

    private MarvelQueriesInfoService marvelQueriesInfoService;

    @BeforeEach
    public void setup(){
        marvelQueriesInfoService = new MarvelQueriesInfoServiceImpl(marvelQueriesInfoRepository);
    }

    @Test
    void getMarvelQueriesInfo(){
        MarvelQueriesInfo marvelQueriesInfo = new MarvelQueriesInfo();
        marvelQueriesInfo.setHash("!gf4554352)=)");
        marvelQueriesInfo.setRequestType("GET");
        marvelQueriesInfo.setStatusCode(200);

        when(marvelQueriesInfoRepository.findAll()).thenReturn(Collections.singletonList(marvelQueriesInfo));

        List<MarvelQueriesInfo> result = marvelQueriesInfoService.getMarvelQueriesInfo();

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(marvelQueriesInfo, result.get(0));

    }
}
