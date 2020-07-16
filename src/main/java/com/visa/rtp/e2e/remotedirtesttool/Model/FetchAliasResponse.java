package com.visa.rtp.e2e.remotedirtesttool.Model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FetchAliasResponse {

    @NotNull
    private String value;
    @NotNull
    private String valueType;
    private String IssuerName;
    //@Temporal(value = TemporalType.TIMESTAMP)
    @NotNull
    private String  timeSelectedAsPreferred;
    @NotNull
    private String recipientName;
    private String contactEmail;
    private String firstNameLocal;
    private String lastNameLocal;
    private String address1;
    private String address2;
    private String city;
    private String postalCode;
    @NotNull
    private String recipientCountry;
    public FetchAliasResponse() {
        super();
        // TODO Auto-generated constructor stub
    }
    public FetchAliasResponse(String value, String valueType, String issuerName, String timeSelectedAsPreferred,
            String recipientName, String contactEmail, String firstNameLocal, String lastNameLocal, String address1,
            String address2, String city, String postalCode, String recipientCountry) {
        super();
        this.value = value;
        this.valueType = valueType;
        IssuerName = issuerName;
        this.timeSelectedAsPreferred = timeSelectedAsPreferred;
        this.recipientName = recipientName;
        this.contactEmail = contactEmail;
        this.firstNameLocal = firstNameLocal;
        this.lastNameLocal = lastNameLocal;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.postalCode = postalCode;
        this.recipientCountry = recipientCountry;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getValueType() {
        return valueType;
    }
    public void setValueType(String valueType) {
        this.valueType = valueType;
    }
    public String getIssuerName() {
        return IssuerName;
    }
    public void setIssuerName(String issuerName) {
        IssuerName = issuerName;
    }
    public String getTimeSelectedAsPreferred() {
        return timeSelectedAsPreferred;
    }
    public void setTimeSelectedAsPreferred(String timeSelectedAsPreferred) {
        //Date newDate = new Date();
        this.timeSelectedAsPreferred = timeSelectedAsPreferred;
    }
    public String getRecipientName() {
        return recipientName;
    }
    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }
    public String getContactEmail() {
        return contactEmail;
    }
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
    public String getFirstNameLocal() {
        return firstNameLocal;
    }
    public void setFirstNameLocal(String firstNameLocal) {
        this.firstNameLocal = firstNameLocal;
    }
    public String getLastNameLocal() {
        return lastNameLocal;
    }
    public void setLastNameLocal(String lastNameLocal) {
        this.lastNameLocal = lastNameLocal;
    }
    public String getAddress1() {
        return address1;
    }
    public void setAddress1(String address1) {
        this.address1 = address1;
    }
    public String getAddress2() {
        return address2;
    }
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getRecipientCountry() {
        return recipientCountry;
    }
    public void setRecipientCountry(String recipientCountry) {
        this.recipientCountry = recipientCountry;
    }
    @Override
    public String toString() {
        return "FetchAliasResponse [value=" + value + ", valueType=" + valueType + ", IssuerName=" + IssuerName
                + ", timeSelectedAsPreferred=" + timeSelectedAsPreferred + ", recipientName=" + recipientName
                + ", contactEmail=" + contactEmail + ", firstNameLocal=" + firstNameLocal + ", lastNameLocal="
                + lastNameLocal + ", address1=" + address1 + ", address2=" + address2 + ", city=" + city
                + ", postalCode=" + postalCode + ", recipientCountry=" + recipientCountry + "]";
    }
    
    

}
