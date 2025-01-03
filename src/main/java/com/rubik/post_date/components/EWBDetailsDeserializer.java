package com.rubik.post_date.components;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.rubik.post_date.domain.dto.BasicResponse;

import java.io.IOException;
import java.util.List;

public class EWBDetailsDeserializer extends JsonDeserializer<List<BasicResponse.EWBDetails>> {

    @Override
    public List<BasicResponse.EWBDetails> deserialize(JsonParser p, DeserializationContext ctxt) 
            throws IOException, JsonProcessingException {
        String ewbDetailsString = p.getText();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(ewbDetailsString, 
                TypeFactory.defaultInstance().constructCollectionType(List.class, BasicResponse.EWBDetails.class));
    }
}