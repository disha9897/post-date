package com.rubik.post_date.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rubik.post_date.components.DateMapper;
import com.rubik.post_date.domain.dto.BasicResponse;
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
import java.util.ArrayList;
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

    public List<DataDsEntity> postPrint(LocalDate inputDate) throws Exception {
        DataDsEntity entity = new DataDsEntity();
        String jsonData = dateMapper.dateMapp(inputDate);
        if (jsonData == null) {
            throw new Exception("No data to send. jsonData is null.");
        }

        String url = "https://ewayasp.webtel.in/EWayBill/v1.4/GetEWBOtherParty";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entityToBePosted = new HttpEntity<>(jsonData, headers);

        try {
            ResponseEntity<List<BasicResponse>> responseEntity = restTemplate.exchange(url, HttpMethod.POST,
                    entityToBePosted,
                    new ParameterizedTypeReference<List<BasicResponse>>() {});

            List<BasicResponse> responseList = responseEntity.getBody();
            if (responseList == null) {
                throw new Exception("Received null response from the external API.");
            }

            List<DataDsEntity> dataDsEntityList = responseList.stream()
                    .map(this::mapDtoToEntity)
                    .collect(Collectors.toList());

            return dataDsRepository.saveAll(dataDsEntityList);

        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            throw new Exception("API error: " + ex.getStatusCode() + " " + ex.getMessage());
        } catch (ResourceAccessException ex) {
            throw new Exception("Connection error: " + ex.getMessage());
        } catch (Exception ex) {
            throw new Exception("Unexpected error: " + ex.getMessage());
        }
    }

    public DataDsEntity mapDtoToEntity(BasicResponse basicResponse) {
        DataDsEntity entity = new DataDsEntity();
        entity.setStatus(basicResponse.getStatus());
        entity.setError((String) basicResponse.getError());
        entity.setErrorCode((String) basicResponse.getErrorCode());
        entity.setEWayBillNo((String) basicResponse.getEWayBillNo());
        entity.setCEWB((String) basicResponse.getCEWB());
        entity.setDate(basicResponse.getDate());
        entity.setValidUpTo((String) basicResponse.getValidUpTo());
        entity.setGSTIN(basicResponse.getGSTIN());
        entity.setDocNo((String) basicResponse.getDocNo());
        entity.setUniqueKey((String) basicResponse.getUniqueKey());

        List<DataDsEntity.EWBDetails> ewbEntities = new ArrayList<>();
        if (basicResponse.getEWBDetails() != null) {
            for (BasicResponse.EWBDetails ewbDetails : basicResponse.getEWBDetails()) {
                DataDsEntity.EWBDetails ewb = new DataDsEntity.EWBDetails();
                ewb.setStatus(ewbDetails.getStatus());
                ewb.setEwbNo(ewbDetails.getEwbNo());
                ewb.setEwayBillDate(ewbDetails.getEwayBillDate());
                ewb.setGenMode(ewbDetails.getGenMode());
                ewb.setGenGstin(ewbDetails.getGenGstin());
                ewb.setDocNo(ewbDetails.getDocNo());
                ewb.setDocDate(ewbDetails.getDocDate());
                ewb.setFromGstin(ewbDetails.getFromGstin());
                ewb.setFromTradeName(ewbDetails.getFromTradeName());
                ewb.setToGstin(ewbDetails.getToGstin());
                ewb.setToTradeName(ewbDetails.getToTradeName());
                ewb.setTotInvValue(ewbDetails.getTotInvValue());
                ewb.setHsnCode(ewbDetails.getHsnCode());
                ewb.setHsnDesc(ewbDetails.getHsnDesc());
                ewb.setRejectStatus(ewbDetails.getRejectStatus());
                ewbEntities.add(ewb);
            }
        }
        entity.setEWBDetails(ewbEntities);

        entity.setAlert((String) basicResponse.getAlert());
        entity.setInfo((String) basicResponse.getInfo());

        return entity;
    }
}
