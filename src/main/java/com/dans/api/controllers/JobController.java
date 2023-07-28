package com.dans.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dans.api.dto.ResponseModel;
import com.dans.api.services.JobService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("job")
public class JobController {
    @Autowired
    public JobService jobService;

    @GetMapping("/list")
    public ResponseEntity<ResponseModel> getJobList(@Valid @RequestHeader(name = "accessToken") String token)
            throws Exception {
        ResponseModel response = jobService.getJobList(token);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/detail")
    public ResponseEntity<ResponseModel> getJobDetail(@Valid @RequestHeader(name = "accessToken") String token,
            @Valid @RequestParam(name = "id") String id) throws Exception {
        ResponseModel response = jobService.getJobDetail(id, token);
        return ResponseEntity.ok(response);
    }
}
