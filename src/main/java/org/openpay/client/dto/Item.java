package org.openpay.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class Item{
    private String resourceURI;
    private String name;
    @JsonInclude(JsonInclude. Include. NON_NULL)
    private String type;
}
