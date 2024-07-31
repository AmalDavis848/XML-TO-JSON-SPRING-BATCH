package com.example.RestBatch.model;


import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "firstName", "lastName", "emailAddress", "cellPhone" })
@XmlRootElement(name = "EmployeeContact")
public class EmployeeContactXml {

    @XmlAttribute(required = true)
    protected String team;

    @XmlAttribute(required =true)
    protected String role;

    @XmlAttribute(required=true)
    protected String status;

    @XmlElement(name = "FirstName", required = true)
    protected String firstName;

    @XmlElement(name = "LastName", required = true)
    protected String lastName;

    @XmlElement(name = "EmailAddress", required = true)
    protected String emailAddress;

    @XmlElement(name = "CellPhone", required = true)
    protected String cellPhone;

    public String getCellPhone() {
        return cellPhone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }

    public String getTeam() {
        return team;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "ContactXml [team=" + team + ", role=" + role + ", status=" + status + ", firstName=" + firstName
                + ", lastName=" + lastName + ", emailAddress=" + emailAddress + ", cellPhone=" + cellPhone + "]";
    }



}