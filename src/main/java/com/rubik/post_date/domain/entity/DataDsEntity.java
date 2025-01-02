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

    @Column(length = 4000)
    private String eWBDetails;

    @Column(length = 4000)
    private String alert;

    @Column(length = 4000)
    private String info;

    @Column(length = 4000)
    private String userDate;

}
