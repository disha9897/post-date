package com.rubik.post_date.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rubik.post_date.domain.dto.BasicResponse;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DataDsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(length = 4000)   // adding len
    private String status;

    @Column(length = 4000)
    private String error;

    @Column(length = 4000)
    private String errorCode;

    @Column(length = 4000)
    private String eWayBillNo;

    @Column(length = 4000)
    private String cEWB;

    @Column(name = "input_date", length = 4000)
    private String date;

    @Column(length = 4000)
    private String validUpTo;

    @Column(length = 4000)
    private String gSTIN;

    @Column(length = 4000)
    private String docNo;

    @Column(length = 4000)
    private String uniqueKey;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ewb_details_id")
    public List<EWBDetails> eWBDetails;

    @Column(length = 4000)
    private String alert;

    @Column(length = 4000)
    private String info;

    @Column(length = 4000)
    private String userDate;

    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Table(name = "ewb_details")
    public static class EWBDetails{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private String id;
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
