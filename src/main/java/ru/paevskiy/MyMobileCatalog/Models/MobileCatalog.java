package ru.paevskiy.MyMobileCatalog.Models;

import java.util.List;

public class MobileCatalog {

    private int id;
    private String fullName;
    private String position;
    private Department myDepartment;
    private List<String> serviceNumber; // Предположим 22-22
    private List<String> personalPhoneNumber; // +375447783866
    //    @Pattern(regexp = "\\+375[0-9]{9}", message = "Телефонный номер должен начинаться с +375, затем - 9 цифр")
    private List<String> serviceMobilePhoneNumber;  // +375447783866

    public MobileCatalog(int id, String fullName, String position, Department myDepartment, List<String> serviceNumber, List<String> personalPhoneNumber, List<String> serviceMobilePhoneNumber) {
        this.id = id;
        this.fullName = fullName;
        this.position = position;
        this.myDepartment = myDepartment;
        this.serviceNumber = serviceNumber;
        this.personalPhoneNumber = personalPhoneNumber;
        this.serviceMobilePhoneNumber = serviceMobilePhoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Department getMyDepartment() {
        return myDepartment;
    }

    public void setMyDepartment(Department myDepartment) {
        this.myDepartment = myDepartment;
    }

    public List<String> getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(List<String> serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public List<String> getPersonalPhoneNumber() {
        return personalPhoneNumber;
    }

    public void setPersonalPhoneNumber(List<String> personalPhoneNumber) {
        this.personalPhoneNumber = personalPhoneNumber;
    }

    public List<String> getServiceMobilePhoneNumber() {
        return serviceMobilePhoneNumber;
    }

    public void setServiceMobilePhoneNumber(List<String> serviceMobilePhoneNumber) {
        this.serviceMobilePhoneNumber = serviceMobilePhoneNumber;
    }
    public String listToSql(List<String> list){
        return list.toString().replace("[", "{").replace("]", "}");
    }
    @Override
    public String toString() {
        return "MobileCatalog{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", position='" + position + '\'' +
                ", myDepartment=" + myDepartment +
                ", serviceNumber=" + serviceNumber +
                ", personalPhoneNumber=" + personalPhoneNumber +
                ", serviceMobilePhoneNumber=" + serviceMobilePhoneNumber +
                '}';
    }
}
