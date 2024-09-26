package org.openpay.client.interceptor;

import feign.RequestTemplate;
import org.openpay.model.MarvelQueriesInfo;
import org.openpay.repository.MarvelQueriesInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Component
public class RequestInterceptor implements feign.RequestInterceptor {

    private final MarvelQueriesInfoRepository marvelQueriesInfoRepository;

    @Autowired
    public RequestInterceptor(MarvelQueriesInfoRepository marvelQueriesInfoRepository){
        this.marvelQueriesInfoRepository = marvelQueriesInfoRepository;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        if(Objects.equals(requestTemplate.feignTarget().name(), "characters")) {
            List<String> hash = (List<String>) requestTemplate.queries().get("hash");
            marvelQueriesInfoRepository.save(MarvelQueriesInfo.builder()
                    .requestType(requestTemplate.method())
                    .requestDateTime(LocalDateTime.now())
                    .hash(hash.get(0))
                    .url(requestTemplate.url())
                    .build());
        }
    }

}
