package com.rubik.post_date.controller;

import com.rubik.post_date.domain.dto.BasicResponse;
import com.rubik.post_date.domain.entity.DataDsEntity;
import com.rubik.post_date.service.DataDsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/data")
public class DataDsController {

    @Autowired
    private DataDsService dataDsService;

    @PostMapping
    public ResponseEntity<List<DataDsEntity>> postData(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyyMMdd") LocalDate inputDate) {
       try{
           List<DataDsEntity> dsEntities = dataDsService.postPrint(inputDate);
           return ResponseEntity.ok(dsEntities);
       } catch (Exception e){
           e.printStackTrace();
           return ResponseEntity.badRequest().build();
       }
    }
}
