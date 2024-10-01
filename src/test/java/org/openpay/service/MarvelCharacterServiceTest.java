package org.openpay.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openpay.client.MarvelClient;
import org.openpay.client.dto.Character;
import org.openpay.client.dto.CharactersResponse;
import org.openpay.client.dto.Data;
import org.openpay.client.model.MarvelQueriesInfo;
import org.openpay.client.repository.MarvelQueriesInfoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MarvelCharacterServiceTest {

    private MarvelCharacterService marvelCharacterService;

    @Mock
    private MarvelQueriesInfoRepository marvelQueriesInfoRepository;

    @Mock
    private MarvelClient marvelClient;

    private static final String HASH = "2948394134JASD#$";

    @BeforeEach
    public void setup(){
        this.marvelCharacterService = new MarvelCharacterServiceImpl(
                marvelClient, marvelQueriesInfoRepository, new ObjectMapper());
        ReflectionTestUtils.setField(marvelCharacterService, "MARVEL_PRIVATE_KEY", "privateKey");
        ReflectionTestUtils.setField(marvelCharacterService, "MARVEL_PUBLIC_KEY", "publicKey");
    }

    @Test
    void testGetCharacters(){
        CharactersResponse charactersResponse = new CharactersResponse();
        Character character = new Character();
        character.setName("Andres");
        Character secondCharacter = new Character();
        secondCharacter.setName("openPay");

        List<Character> characters = List.of(character, secondCharacter);

        Data data = new Data();
        data.setResults(characters);
        charactersResponse.setData(data);

        MarvelQueriesInfo marvelQueriesInfo = new MarvelQueriesInfo();
        marvelQueriesInfo.setHash(HASH);
        marvelQueriesInfo.setRequestType("GET");
        marvelQueriesInfo.setStatusCode(200);

        when(marvelClient.getCharacters(anyMap())).thenReturn(ResponseEntity.ok(charactersResponse));
        when(marvelQueriesInfoRepository.findByHash(anyString()))
                .thenReturn(marvelQueriesInfo);
        when(marvelQueriesInfoRepository.save(marvelQueriesInfo))
                .thenReturn(marvelQueriesInfo);

        List<Character> result = marvelCharacterService.getCharacters(new HashMap<>());

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(result.size(), 2);
        Assertions.assertEquals(result.get(0).getName(), "Andres");
        Assertions.assertEquals(result.get(1).getName(), "openPay");
    }

    @Test
    void testGetCharactersById(){
        CharactersResponse charactersResponse = new CharactersResponse();
        Character character = new Character();
        character.setName("Andres");
        character.setId("1");

        List<Character> characters = List.of(character);

        Data data = new Data();
        data.setResults(characters);
        charactersResponse.setData(data);

        MarvelQueriesInfo marvelQueriesInfo = new MarvelQueriesInfo();
        marvelQueriesInfo.setHash(HASH);
        marvelQueriesInfo.setRequestType("GET");
        marvelQueriesInfo.setStatusCode(200);

        when(marvelClient.getCharacterById(anyMap(), eq(1L))).thenReturn(ResponseEntity.ok(charactersResponse));
        when(marvelQueriesInfoRepository.findByHash(anyString()))
                .thenReturn(marvelQueriesInfo);
        when(marvelQueriesInfoRepository.save(marvelQueriesInfo))
                .thenReturn(marvelQueriesInfo);

        Character result = marvelCharacterService.getCharacterById(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getName(), "Andres");
    }

}
