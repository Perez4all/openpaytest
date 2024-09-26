package org.openpay.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarvelQueriesInfo {

    private Integer statusCode;

    private String requestType;

    private String url;

    private String hash;

    private String info;

    private String requestDateTime;

    private String responseDateTime;

    private String result;

    private String responseTime;

}
