package com.rubik.post_date.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BasicResponse{
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
    public String eWBDetails;

    @JsonProperty("Alert")
    public Object alert;

    @JsonProperty("Info")
    public Object info;
}

