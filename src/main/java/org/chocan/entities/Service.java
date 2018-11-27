package org.chocan.entities;

import java.util.Date;

public class Service {

    private Date serviceDate;   // Date of service (MM-DD-YYYY).
    private Date receiveDate;   // Date and time data were received by the computer (MM-DD-YYYY HH:MM:SS)
    private int providerId;
    private int serviceCode;    // 9 digits
    private float paidFee;      // Fee to be paid (up to $999.99)
    private String serviceName;



    public Service(Date serviceDate, Date receiveDate, int provider, int serviceCode, float paidFee, String serviceName){
        this.serviceDate = serviceDate;
        this.receiveDate = receiveDate;
        this.providerId = provider;
        this.serviceCode = serviceCode;
        this.paidFee = paidFee;
        this.serviceName = serviceName;
    }


    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int provider) {
        this.providerId = provider;
    }

    public int getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(int serviceCode) {
        this.serviceCode = serviceCode;
    }

    public float getPaidFee() {
        return paidFee;
    }

    public void setPaidFee(float paidFee) {
        this.paidFee = paidFee;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
