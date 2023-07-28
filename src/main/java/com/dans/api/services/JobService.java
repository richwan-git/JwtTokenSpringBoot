package com.dans.api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.dans.api.dto.ResponseModel;
import com.dans.api.models.JobDetail;
import com.dans.api.utils.http.HttpHelper;
import com.dans.api.utils.jwt.JwtHelper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JobService {
    @Autowired
    public HttpHelper httpHelper;

    @Autowired
    public JwtHelper jwtHelper;

    public ResponseModel getJobList(String token) throws Exception {
        ResponseModel response = new ResponseModel();

        if (!jwtHelper.validateJwtToken(token))
            throw new Exception("token invalid");

        String resp = httpHelper.doSimpleHttpRequest(HttpMethod.GET,
                "http://dev3.dansmultipro.co.id/api/recruitment/positions.json");

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonArray = mapper.readTree(resp);
        List<JobDetail> list = new ArrayList<JobDetail>();
        for (JsonNode element : jsonArray) {
            JobDetail jl = mapper.treeToValue(element, JobDetail.class);
            list.add(jl);
        }

        response.data = list;
        response.status = 200;

        return response;
    }

    public ResponseModel getJobDetail(String id, String token) throws Exception {
        ResponseModel response = new ResponseModel();

        if (!jwtHelper.validateJwtToken(token))
            throw new Exception("token invalid");

        String resp = httpHelper.doSimpleHttpRequest(HttpMethod.GET,
                "http://dev3.dansmultipro.co.id/api/recruitment/positions/" + id);

        if (resp.equals("{}")) {
            throw new Exception("Id not found");
        }
        ObjectMapper mapper = new ObjectMapper();
        JobDetail jd = mapper.readValue(resp, JobDetail.class);

        response.data = jd;
        response.status = 200;

        return response;
    }
}
