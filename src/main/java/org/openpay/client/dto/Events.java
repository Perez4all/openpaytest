package org.openpay.client.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Events{
    private String available;
    private String returned;
    private String collectionURI;
    private ArrayList<Item> items;
}
