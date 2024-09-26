package org.openpay.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.openpay.dto.MarvelQueriesInfo;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MarvelQueriesInfoMapper {

    @Mapping(source = "result" , target = "result", qualifiedByName = "byteToString")
    MarvelQueriesInfo toDto(org.openpay.model.MarvelQueriesInfo marvelQueriesInfo);

    List<MarvelQueriesInfo> toDto(List<org.openpay.model.MarvelQueriesInfo> marvelQueriesInfo);

    @Named("byteToString")
    default String byteToString(byte[] bytes){
        return new String(bytes, StandardCharsets.UTF_8);
    }

}
