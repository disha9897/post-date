package com.rubik.post_date.service;

import com.rubik.post_date.components.DateMapper;
import com.rubik.post_date.domain.dto.BasicResponse;
import com.rubik.post_date.domain.dto.DateRequestDto;
import com.rubik.post_date.domain.entity.DataDsEntity;
import com.rubik.post_date.repository.DataDsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataDsService {

    @Autowired
    private DataDsRepository dataDsRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DateMapper dateMapper;



//    public List<DataDsEntity> postPrint(String inputDate) throws Exception {
//        DataDsEntity entity = new DataDsEntity();
//        String jsonData = dateMapper.dateMapp(inputDate);
//        if(jsonData == null) {
//            throw new Exception("No data to send. jsonData is null.");
//        }
//
//        String url = "https://ewayasp.webtel.in/EWayBill/v1.4/GetEWBOtherParty";
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> entityToBePosted = new HttpEntity<>(jsonData, headers);
//        System.out.println(jsonData);
//        try {
//            ResponseEntity<List<BasicResponse>> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entityToBePosted,
//                    new ParameterizedTypeReference<List<BasicResponse>>() {});
//
//            List<BasicResponse> responseList = responseEntity.getBody();
//            if (responseList == null) {
//                throw new Exception("Received null response from the external API.");
//            }
//
//            List<DataDsEntity> dataDsEntityList = responseList.stream().map(this::mapDtoToEntity).collect(Collectors.toList());
//
//            return dataDsRepository.saveAll(dataDsEntityList);
//
//        } catch (HttpClientErrorException | HttpServerErrorException ex) {
//            throw new Exception("API error: " + ex.getStatusCode() + " " + ex.getMessage());
//        } catch (ResourceAccessException ex) {
//            throw new Exception("Connection error: " + ex.getMessage());
//        } catch (Exception ex) {
//            throw new Exception("Unexpected error: " + ex.getMessage());
//        }
////        List<BasicResponse> responseList = restTemplate.exchange(url, HttpMethod.POST, entityToBePosted, new ParameterizedTypeReference<List<BasicResponse>>() {
////        }).getBody();
////
////        List<DataDsEntity> dataDsEntityList = responseList.stream().map(this::mapDtoToEntity).collect(Collectors.toList());
////        return dataDsRepository.saveAll(dataDsEntityList);
//    }

    public List<DataDsEntity> postPrint(LocalDate inputDate) throws Exception {
        DataDsEntity entity = new DataDsEntity();
        String jsonData = dateMapper.dateMapp(inputDate); // Pass LocalDate to the mapper
        if (jsonData == null) {
            throw new Exception("No data to send. jsonData is null.");
        }

        String url = "https://ewayasp.webtel.in/EWayBill/v1.4/GetEWBOtherParty";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entityToBePosted = new HttpEntity<>(jsonData, headers);
        System.out.println(jsonData);

        try {
            ResponseEntity<List<BasicResponse>> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entityToBePosted,
                    new ParameterizedTypeReference<List<BasicResponse>>() {});

            List<BasicResponse> responseList = responseEntity.getBody();
            if (responseList == null) {
                throw new Exception("Received null response from the external API.");
            }

            List<DataDsEntity> dataDsEntityList = responseList.stream().map(this::mapDtoToEntity).collect(Collectors.toList());

            return dataDsRepository.saveAll(dataDsEntityList);

        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            throw new Exception("API error: " + ex.getStatusCode() + " " + ex.getMessage());
        } catch (ResourceAccessException ex) {
            throw new Exception("Connection error: " + ex.getMessage());
        } catch (Exception ex) {
            throw new Exception("Unexpected error: " + ex.getMessage());
        }
    }


    public DataDsEntity mapDtoToEntity(BasicResponse basicResponse){
        DataDsEntity entity = new DataDsEntity();
        entity.setStatus(basicResponse.status);
        entity.setError((String) basicResponse.error);
        entity.setErrorCode((String) basicResponse.errorCode);
        entity.setEWayBillNo((String) basicResponse.eWayBillNo);
        entity.setCEWB((String) basicResponse.cEWB);
        entity.setDate(basicResponse.date);  // From the API response
        entity.setValidUpTo((String) basicResponse.validUpTo);
        entity.setGSTIN(basicResponse.gSTIN);
        entity.setDocNo((String) basicResponse.docNo);
        entity.setUniqueKey((String) basicResponse.uniqueKey);
        entity.setEWBDetails(basicResponse.eWBDetails);
        entity.setAlert((String) basicResponse.alert);
        entity.setInfo((String) basicResponse.info);

        return entity;
    }
}
