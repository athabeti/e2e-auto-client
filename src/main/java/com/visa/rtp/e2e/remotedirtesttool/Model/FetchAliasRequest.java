package com.visa.rtp.e2e.remotedirtesttool.Model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = false)
public class FetchAliasRequest {

    @NotNull(message = " alias is Mandetory fields")
    private String alias;
    /*
     * @Size(min=2,max=2,message="aliseType value should be lenght of 2 : \r\n" +
     * "Values which goes under type are :: \r\n" + " 01 – Phone number \r\n" +
     * "02 – Email address \r\n" + "03 – National ID \r\n" +
     * "04 – IBAN (International Bank Account Number) \r\n" + "05 – USSD\r")
     */

    private String aliasType;

    private String directoryType;

    private String directoryName;
    @Size(min=2,max=2)
    private String originatorCountry;
    

    public FetchAliasRequest() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    
    public FetchAliasRequest(String alias, String aliasType,
            String directoryType, String directoryName, String originatorCountry) {
        super();
        this.alias = alias;
        this.aliasType = aliasType;
        this.directoryType = directoryType;
        this.directoryName = directoryName;
        this.originatorCountry = originatorCountry;
    }



    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }



    public String getAliasType() {
        return aliasType;
    }



    public void setAliasType(String aliasType) {
        this.aliasType = aliasType;
    }



    public String getDirectoryType() {
        return directoryType;
    }



    public void setDirectoryType(String directoryType) {
        this.directoryType = directoryType;
    }



    public String getDirectoryName() {
        return directoryName;
    }



    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }



    public String getOriginatorCountry() {
        return originatorCountry;
    }



    public void setOriginatorCountry(String originatorCountry) {
        this.originatorCountry = originatorCountry;
    }



    @Override
    public String toString() {
        return "FetchAliasRequest [alias=" + alias + ", aliasType=" + aliasType + ", directoryType=" + directoryType
                + ", directoryName=" + directoryName + ", originatorCountry=" + originatorCountry + "]";
    }
    

}
