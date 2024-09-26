package org.openpay.client.dto;

@lombok.Data
public class CharactersResponse {
    private String code;
    private String status;
    private String copyright;
    private String attributionText;
    private String attributionHTML;
    private Data data;
    private String etag;

}
