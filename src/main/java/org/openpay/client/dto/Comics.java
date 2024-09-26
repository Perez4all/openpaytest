package org.openpay.client.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Comics{
    private String available;
    private String returned;
    private String collectionURI;
    private ArrayList<Item> items;
}
