package ru.paevskiy.MyMobileCatalog.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @Column(name = "departmentsid")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int departmentId;

    @Column(name = "name")
    private String nameDepartment;

    @OneToMany(mappedBy = "mydepartment", fetch = FetchType.EAGER)
    private Collection<MobileCatalog> catalogs;

    public int getDepartmentId() {
        return departmentId;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", nameDepartment='" + nameDepartment + '\'' +
                '}';
    }
}
