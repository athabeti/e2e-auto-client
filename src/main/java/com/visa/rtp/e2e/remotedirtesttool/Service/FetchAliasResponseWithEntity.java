package com.visa.rtp.e2e.remotedirtesttool.Service;

import com.visa.rtp.e2e.remotedirtesttool.Exception.FetchAiasResponseException;
import com.visa.rtp.e2e.remotedirtesttool.Model.FetchAliasResponse;
import com.visa.rtp.e2e.remotedirtesttool.Model.FetchResponse;
import com.visa.rtp.e2e.remotedirtesttool.Model.FetchResponseEntity;

public class FetchAliasResponseWithEntity {

    //FetchApiRequestValidator fetchReqVal;
    FetchResponse fetchResponse;

    public FetchAliasResponse mapResponseObject(FetchResponseEntity responseEntity) throws FetchAiasResponseException {
        // System.out.println(" responseEntity ******* "+responseEntity.toString());

        FetchAliasResponse fetchAliasResponse;
        try {

            fetchAliasResponse = new FetchAliasResponse(responseEntity.getValue(), responseEntity.getValueType(),
                    "RADSimulator",responseEntity.getTimeSelectedAsPreferred(),
                    responseEntity.getFirstName(),responseEntity.getContactEmail(),
                    responseEntity.getFirstNameLocal(),responseEntity.getLastNameLocal(),
                    responseEntity.getAddress1(),responseEntity.getAddress2(),
                    responseEntity.getCity(),responseEntity.getPostalCode(),
                    responseEntity.getCountry()
                    );

            // System.out.println(fetchAliasResponse.toString());

        } catch (Exception e) {

            throw new FetchAiasResponseException(" Issue is construing response body :: ");

        }

        return fetchAliasResponse;
    }

}
