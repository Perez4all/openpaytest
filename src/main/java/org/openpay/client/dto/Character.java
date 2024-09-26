package org.openpay.client.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Character {
    private String id;
    private String name;
    private String description;
    private String modified;
    private String resourceURI;
    private ArrayList<Url> urls;
    private Thumbnail thumbnail;
    private Comics comics;
    private Stories stories;
    private Events events;
    private Series series;
}