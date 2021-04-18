package ru.paevskiy.MyMobileCatalog.Models;

public class Department {

    private int departmentId;
    private String nameDepartment;

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public Department(int departmentId, String nameDepartment) {
        this.departmentId = departmentId;
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
