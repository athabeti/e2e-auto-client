package com.visa.rtp.e2e.remotedirtesttool.Service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;

import com.visa.rtp.e2e.remotedirtesttool.Exception.FetApiRequestBodyException;
import com.visa.rtp.e2e.remotedirtesttool.Model.FetchAliasRequest;


public class FetchApiRequestValidator {

    String[] typeArry = { "phone", "email", "nationalid", "iban", "ussd", "directoryID" };
    String[] customNetworkArry = { "social", "ecomm", "fi", "fintech" };
    private FetchAliasRequest inputReq;

    @Value("${spring.custom.orginatorCountry.list}")
    private String restrcitOriginatorCountryList;

    @Value("${spring.custom.enable.restrict.OrginatorCountry.flag}")
    private boolean restrcitOriginatorCountryFlag;

    public FetchApiRequestValidator(FetchAliasRequest fetchAliasReq) {
        this.inputReq = fetchAliasReq;
    }

    public void validateAliasType() throws FetApiRequestBodyException {

        if (inputReq.getAliasType() == null) {
            throw new FetApiRequestBodyException(
                    " NULL value in not allowed in field \"Type\" " + "One of the below value is expected "
                            + "\"phone\", \"email\", \"nationalid\", \"iban\", \"ussd\", \"bid\", \"custom\"");
        }

        System.out.println(" *********** in validate Alias Type " + inputReq.getAliasType());

        if (!Arrays.asList(typeArry).contains(inputReq.getAliasType().toLowerCase())) {

            System.out.println(" :: input type value " + inputReq.getAliasType() + " is not supported ");
            throw new FetApiRequestBodyException("One of the below value is expected in filed “type” ::  "
                    + "phone, email, nationalid, iban, ussd, bid, custom");
        }
    }

    public void validateCustomType() throws FetApiRequestBodyException {

        System.out.println(" in Valid Custom Type :: ");
        if ((inputReq.getAliasType() != null)) {

            System.out.println(" ********* In directoryID validation :: ");

            if ((inputReq.getAliasType().equalsIgnoreCase("directoryID")) && inputReq.getDirectoryName() == null) {
                System.out.println(" :: directoryName is Mandatory for aliasType 06 ");
                throw new FetApiRequestBodyException(" :: directoryName is Mandatory for aliasType 06 ");
            }
        } else {
            System.out.println("********* AliasType NULL ::");
        }

        if (inputReq.getDirectoryType() != null) {

            if (!Arrays.asList(customNetworkArry).contains(inputReq.getDirectoryType().toLowerCase())) {

                System.out.println(" :: " + inputReq.getDirectoryType() + " is not in list ");
                throw new FetApiRequestBodyException(
                        " Value in field “networkType” ::  Should be fi,  fintech, social or  ecomm ");
            }

        }

    }

    public void validateNetworkType() {

        System.out.println(" In ValidNetwork Type validation :: ");

        if (inputReq.getAliasType() == "custom") {

            if (Arrays.asList(customNetworkArry).contains(inputReq.getDirectoryType())) {
                System.out.println(" :: " + inputReq.getDirectoryType() + " is not a valid network Type it "
                        + "should be either of \"ecomm\" or \" social \"");
            }

        }

    }

    public void validateAllInputRequest() {
        // validateAliasType();
        validateCustomType(); // validateNetworkType();
        //validateOriginatorCountry();

    }

    public void validateOriginatorCountry() throws FetApiRequestBodyException {

        System.out.println(" In validateOriginatorCountry :: ");
        System.out.println(restrcitOriginatorCountryList);
        System.out.println(restrcitOriginatorCountryFlag);
        
        String[] countryList = restrcitOriginatorCountryList.split(",");
    

        if (inputReq.getOriginatorCountry() != null) {
            if (restrcitOriginatorCountryFlag) {

                for (String cty : countryList) {
                    if (cty.equalsIgnoreCase(inputReq.getOriginatorCountry())) {
                        throw new FetApiRequestBodyException(" input OriginatorCountry "
                                + inputReq.getOriginatorCountry() + " is not supported :: ");
                    }

                }
            }

        } else {
            System.out.println("OriginatorCountry is not in request ");
        }
    }

}
