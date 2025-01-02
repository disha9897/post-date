package com.rubik.post_date.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.persistence.*;
import lombok.*;

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
    private String status;
    private String error;
    private String errorCode;
    private String eWayBillNo;
    private String cEWB;

    @Column(name = "input_date")
    private String date;

    private String validUpTo;
    private String gSTIN;
    private String docNo;
    private String uniqueKey;
    private String eWBDetails;
    private String alert;
    private String info;
    private String userDate;

}
