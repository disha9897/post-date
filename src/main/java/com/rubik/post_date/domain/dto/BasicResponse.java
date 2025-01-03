package com.rubik.post_date.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rubik.post_date.components.EWBDetailsDeserializer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicResponse {

    @JsonProperty("Status")
    public String status;

    @JsonProperty("Error")
    public Object error;

    @JsonProperty("ErrorCode")
    public Object errorCode;

    @JsonProperty("EWayBillNo")
    public Object eWayBillNo;

    @JsonProperty("CEWB")
    public Object cEWB;

    @JsonProperty("Date")
    public String date;

    @JsonProperty("ValidUpTo")
    public Object validUpTo;

    @JsonProperty("GSTIN")
    public String gSTIN;

    @JsonProperty("DocNo")
    public Object docNo;

    @JsonProperty("UniqueKey")
    public Object uniqueKey;

    @JsonProperty("EWBDetails")
    @JsonDeserialize(using = EWBDetailsDeserializer.class)
    public List<EWBDetails> eWBDetails = new ArrayList<>();

    @JsonProperty("Alert")
    public Object alert;

    @JsonProperty("Info")
    public Object info;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EWBDetails {
        public String status;
        public long ewbNo;
        public String ewayBillDate;
        public String genMode;
        public String genGstin;
        public String docNo;
        public String docDate;
        public String fromGstin;
        public String fromTradeName;
        public String toGstin;
        public String toTradeName;
        public double totInvValue;
        public int hsnCode;
        public String hsnDesc;
        public String rejectStatus;
    }
}
