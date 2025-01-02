package com.rubik.post_date.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DateRequestDto {

    @JsonProperty("GSTIN")
    public String GSTIN;

    @JsonProperty("Year")
    public String Year;

    @JsonProperty("Month")
    public String Month;

    @JsonProperty("Date")
    public String Date;

    @JsonProperty("EFUserName")
    public String EFUserName;

    @JsonProperty("EFPassword")
    public String EFPassword;

    @JsonProperty("CDKey")
    public String CDKey;

    @JsonProperty("EWBUserName")
    public String EWBUserName;

    @JsonProperty("EWBPassword")
    public String EWBPassword;
}

