package org.openpay.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.openpay.client.MarvelClient;
import org.openpay.client.dto.Character;
import org.openpay.client.dto.CharactersResponse;
import org.openpay.client.model.MarvelQueriesInfo;
import org.openpay.client.repository.MarvelQueriesInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Log
@Service
public class MarvelCharacterServiceImpl implements MarvelCharacterService {

    @Value("${marvel.private.key}")
    private String MARVEL_PRIVATE_KEY;

    @Value("${marvel.public.key}")
    private String MARVEL_PUBLIC_KEY;

    private final MarvelClient marvelClient;
    private final MarvelQueriesInfoRepository marvelQueriesInfoRepository;

    private final ObjectMapper objectMapper;

    @Autowired
    public MarvelCharacterServiceImpl(MarvelClient marvelClient,
                                      MarvelQueriesInfoRepository marvelQueriesInfoRepository,
                                      ObjectMapper objectMapper){
        this.marvelClient = marvelClient;
        this.marvelQueriesInfoRepository = marvelQueriesInfoRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Character> getCharacters(Map<String, String> params) {

        Map<String, String> requestAuthParams = createRequestAuthParams(params);
        ResponseEntity<CharactersResponse> characterResponse = marvelClient.getCharacters(requestAuthParams);

        if(characterResponse.getStatusCode() == HttpStatus.OK){
            CharactersResponse body = characterResponse.getBody();
            if(body != null && body.getData() != null && !body.getData().getResults().isEmpty()){
                try {
                    saveResponse(objectMapper.writeValueAsString(body), requestAuthParams.get("hash"));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                return body.getData().getResults();
            }
        }
        return new ArrayList<>();
    }

    @Override

    public Character getCharacterById(Long id) {
        Map<String, String> requestAuthParams = createRequestAuthParams(null);
        ResponseEntity<CharactersResponse> characterResponse = marvelClient.getCharacterById(requestAuthParams, id);
        if (characterResponse.getStatusCode() == HttpStatus.OK) {
            CharactersResponse body = characterResponse.getBody();
            if (body != null && body.getData() != null && !body.getData().getResults().isEmpty()) {
                try {
                    saveResponse(objectMapper.writeValueAsString(body), requestAuthParams.get("hash"));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                return body.getData().getResults().get(0);
            }
        }
        return null;
    }

    private Map<String, String> createRequestAuthParams(Map<String, String> params){
        if(params == null || params.isEmpty()){
            params = new HashMap<>();
        }
        String hash;
        long ts = new Date().getTime();
        try {
            String stringToHash = ts
                    + MARVEL_PRIVATE_KEY
                    + MARVEL_PUBLIC_KEY;
            byte[] md5hash = MessageDigest.getInstance("MD5")
                    .digest(stringToHash.getBytes());
            hash = byteToString(md5hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        params.put("ts", String.valueOf(ts));
        params.put("apikey", MARVEL_PUBLIC_KEY);
        params.put("hash", hash);

        return params;
    }

    private String byteToString(byte[] bytes){
        StringBuffer sb = new StringBuffer();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    private void saveResponse(String body, String hash) {
        MarvelQueriesInfo marvelQueriesInfo = marvelQueriesInfoRepository.findByHash(hash);
        marvelQueriesInfo.setResult(body.getBytes());
        marvelQueriesInfoRepository.save(marvelQueriesInfo);
    }
}
