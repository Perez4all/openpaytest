package org.openpay.controller;

import org.openpay.service.MarvelCharacterServiceImpl;
import org.openpay.client.dto.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/character")
public class CharacterController {

    private final MarvelCharacterServiceImpl marvelCharacterServiceImpl;

    @Autowired
    public CharacterController(MarvelCharacterServiceImpl marvelCharacterServiceImpl){
        this.marvelCharacterServiceImpl = marvelCharacterServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<Character>> getCharacters(@RequestParam Map<String, String> params){;
        return ResponseEntity.ok(marvelCharacterServiceImpl.getCharacters(params));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Character> getCharacterById(@PathVariable Long id){
        return ResponseEntity.ok(marvelCharacterServiceImpl.getCharacterById(id));
    }
}
