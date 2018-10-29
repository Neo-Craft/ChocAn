package org.chocan.entities;

import java.util.Date;

public class Service {

    private Date serviceDate;   // Date of service (MM-DD-YYYY).
    private Date receiveDate;   // Date and time data were received by the computer (MM-DD-YYYY HH:MM:SS)
    private String provider;    // TODO Bind to a Provider object
    private int serviceCode;    // 6digits
    private short paidFee;      // Fee to be paid (up to $999.99)
    private String serviceName;



    public Service(Date serviceDate, Date receiveDate, String provider, int serviceCode, short paidFee, String serviceName){
        this.serviceDate = serviceDate;
        this.receiveDate = receiveDate;
        this.provider = provider;
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

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public int getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(int serviceCode) {
        this.serviceCode = serviceCode;
    }

    public short getPaidFee() {
        return paidFee;
    }

    public void setPaidFee(short paidFee) {
        this.paidFee = paidFee;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
