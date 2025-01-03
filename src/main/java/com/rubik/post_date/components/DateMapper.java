package com.rubik.post_date.components;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rubik.post_date.domain.dto.DateRequestDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DateMapper {

    public String dateMapp(LocalDate inputDate){
        DateRequestDto dateRequestDto = new DateRequestDto();

        dateRequestDto.GSTIN = "27AAECK3714E1ZT";
        dateRequestDto.Year = String.valueOf(inputDate.getYear());
        dateRequestDto.Month = String.format("%02d", inputDate.getMonthValue());
        dateRequestDto.Date = inputDate.toString().replace("-", "");
//        dateRequestDto.Year = "2024";
//        dateRequestDto.Month = "12";
//        dateRequestDto.Date = "20241228";
        dateRequestDto.EFUserName = "7B159427-1528-49A3-92CB-1533BA8AED19";
        dateRequestDto.EFPassword = "0F859F8A-761E-4364-976C-66B40B045D49";
        dateRequestDto.CDKey = "1696973";
        dateRequestDto.EWBUserName = "ksmh@1234_API_KS";
        dateRequestDto.EWBPassword = "Satish@123456";

        ObjectMapper objectMapper = new ObjectMapper();
        try{
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dateRequestDto);
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }

//        try {
//            String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dateRequestDto);
//            System.out.println("Serialized JSON:");
//            System.out.println(jsonString);
//
//            DateRequestDto deserializedObject = objectMapper.readValue(jsonString, DateRequestDto.class);
//            System.out.println("Deserialized Object:");
//            System.out.println(deserializedObject);
//
//            return jsonString;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
    }
}
