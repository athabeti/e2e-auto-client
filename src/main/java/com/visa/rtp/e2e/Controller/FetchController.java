package com.visa.rtp.e2e.remotedirtesttool.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.visa.rtp.e2e.remotedirtesttool.Model.FetchAliasRequest;
import com.visa.rtp.e2e.remotedirtesttool.Model.FetchAliasResponse;
import com.visa.rtp.e2e.remotedirtesttool.Model.FetchReq;
import com.visa.rtp.e2e.remotedirtesttool.Model.FetchResponseEntity;
import com.visa.rtp.e2e.remotedirtesttool.Service.FetchApiService;
import com.visa.rtp.e2e.remotedirtesttool.Service.FetchV2ApiServices;

@RestController
public class FetchController {

    @Autowired
    private FetchApiService fetchservice;

    @Autowired
    private FetchV2ApiServices fetchv2AliasService;

    @PostMapping("/v1/fetchAlias")
    public FetchResponseEntity getalias(@Valid @RequestBody FetchReq fetchReq) {

        return fetchservice.getAliasDetails(fetchReq);
    }

    @PostMapping("/v2/fetchAlias")
    public FetchAliasResponse getV2alias(@Valid @RequestBody FetchAliasRequest fetchReq) {

        return fetchv2AliasService.getV2AliasData(fetchReq);
    }
    
    
    
}
