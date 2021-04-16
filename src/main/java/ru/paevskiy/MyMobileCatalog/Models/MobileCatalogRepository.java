package ru.paevskiy.MyMobileCatalog.Models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MobileCatalogRepository extends JpaRepository<MobileCatalog, Integer> {
    @Modifying
    @Query("update MobileCatalog mc " +
            "set mc.fullName=:fullName, " +
            "mc.position=:position, " +
            "mc.mydepartment=:departmentId, " +
            "mc.serviceNumber=:serviceNumber, " +
            "mc.personalPhoneNumber=:personalPhoneNumber, " +
            "mc.serviceMobilePhoneNumber=:serviceMobilePhoneNumber " +
            "where mc.id=:catalog_id")
    void updateCatalog(@Param("fullName") String fullName,
                       @Param("position") String position,
                       @Param("departmentId") Department departmentId,
                       @Param("serviceNumber") String serviceNumber,
                       @Param("personalPhoneNumber") String personalPhoneNumber,
                       @Param("serviceMobilePhoneNumber") String serviceMobilePhoneNumber,
                       @Param("catalog_id") Integer id);

}