package ru.paevskiy.MyMobileCatalog.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Entity
@Table(name = "mobilecatalog")
public class MobileCatalog {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Column(name = "fullname")
    private String fullName;

    @Column(name = "position")
    @NotEmpty(message = "Position should not be empty")
    private String position;

//    @Column(name = "departmentid")
    @NotEmpty(message = "Department ID should not be empty")
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "departmentid")
    private Department mydepartment;

    @Column(name = "servicenumber")
//    @Type(type = "ru.easyjava.data.hibernate.type.NetworkObjectType")
    @NotEmpty(message = "Service number should not be empty")
    @Size(max = 4, message = "Service number should be no more 4 characters")
    private String serviceNumber; // Предположим 22-22

    @Column(name = "personalphonenumber")
    @Pattern(regexp = "\\+375[0-9]{9}", message = "Телефонный номер должен начинаться с +375, затем - 9 цифр")
    private String personalPhoneNumber; // +375447783866

    @Column(name = "servicemobilephonenumber")
    @Pattern(regexp = "\\+375[0-9]{9}", message = "Телефонный номер должен начинаться с +375, затем - 9 цифр")
    private String serviceMobilePhoneNumber;  // +375447783866=

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
        return mydepartment;
    }

    public void setMyDepartment(Department myDepartment) {
        this.mydepartment = myDepartment;
    }

    public String getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(String serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public String getPersonalPhoneNumber() {
        return personalPhoneNumber;
    }

    public void setPersonalPhoneNumber(String personalPhoneNumber) {
        this.personalPhoneNumber = personalPhoneNumber;
    }

    public String getServiceMobilePhoneNumber() {
        return serviceMobilePhoneNumber;
    }

    public void setServiceMobilePhoneNumber(String serviceMobilePhoneNumber) {
        this.serviceMobilePhoneNumber = serviceMobilePhoneNumber;
    }

    @Override
    public String toString() {
        return "MobileCatalog{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", position='" + position + '\'' +
                ", mydepartment=" + mydepartment +
                ", serviceNumber='" + serviceNumber + '\'' +
                ", personalPhoneNumber='" + personalPhoneNumber + '\'' +
                ", serviceMobilePhoneNumber='" + serviceMobilePhoneNumber + '\'' +
                '}';
    }
}
