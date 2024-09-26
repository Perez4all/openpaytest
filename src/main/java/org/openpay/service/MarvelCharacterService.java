package org.openpay.service;

import org.openpay.client.dto.Character;

import java.util.List;
import java.util.Map;

public interface MarvelCharacterService {
    List<Character> getCharacters(Map<String, String> params);

    Character getCharacterById(Long id);
}
