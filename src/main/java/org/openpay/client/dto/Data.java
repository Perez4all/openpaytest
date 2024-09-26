package org.openpay.client.dto;

import java.util.ArrayList;
import java.util.List;

@lombok.Data
public class Data{
    private String offset;
    private String limit;
    private String total;
    private String count;
    private List<Character> results = new ArrayList<>();

}
