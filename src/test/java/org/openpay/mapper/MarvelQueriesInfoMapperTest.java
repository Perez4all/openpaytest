package org.openpay.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MarvelQueriesInfoMapperTest {

    @Autowired
    private MarvelQueriesInfoMapper marvelQueriesInfoMapper;

    @Test
    void testByteToString(){
        String expected = "Openpay test";
        byte[] text = expected.getBytes();
        String result = marvelQueriesInfoMapper.byteToString(text);
        Assertions.assertEquals(expected, result);
    }
}
