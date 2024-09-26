package org.openpay.client;

import org.openpay.client.dto.CharactersResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "characters", url = "http://gateway.marvel.com:80")
public interface MarvelClient {

    @GetMapping("/v1/public/characters")
    ResponseEntity<CharactersResponse> getCharacters(@RequestParam Map<String, String> params);

    @GetMapping("/v1/public/characters/{id}")
    ResponseEntity<CharactersResponse> getCharacterById(@RequestParam Map<String, String> params,
                                                        @PathVariable Long id);
}
