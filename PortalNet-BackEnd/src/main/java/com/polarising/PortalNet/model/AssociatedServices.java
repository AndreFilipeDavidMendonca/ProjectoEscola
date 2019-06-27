package com.polarising.PortalNet.model;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class AssociatedServices {

    private int ID;
    private int clientID;
    private String serviceID;
    private String installationAddress;
    private int postalCode;
    private Date alterationDate;
    private Date contractEndDate;
    private int workerNumber;

    public AssociatedServices(int ID, int clientID, String serviceID, String installationAddress, int postalCode, Date alterationDate, Date contractEndDate, int workerNumber) {
        this.ID = ID;
        this.clientID = clientID;
        this.serviceID = serviceID;
        this.installationAddress = installationAddress;
        this.postalCode = postalCode;
        this.alterationDate = alterationDate;
        this.contractEndDate = contractEndDate;
        this.workerNumber = workerNumber;
    }

    public int getID() {
        return ID;
    }

    public int getClientID() {
        return clientID;
    }

    public String getServiceID() {
        return serviceID;
    }

    public String getInstallationAddress() {
        return installationAddress;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public Date getAlterationDate() {
        return alterationDate;
    }

    public Date getContractEndDate() {
        return contractEndDate;
    }

    public int getWorkerNumber() {
        return workerNumber;
    }
}

