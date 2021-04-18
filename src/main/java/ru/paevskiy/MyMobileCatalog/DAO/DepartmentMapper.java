package ru.paevskiy.MyMobileCatalog.DAO;

import org.springframework.jdbc.core.RowMapper;
import ru.paevskiy.MyMobileCatalog.Models.Department;
import ru.paevskiy.MyMobileCatalog.Models.MobileCatalog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class DepartmentMapper implements RowMapper<Department> {
    @Override
    public Department mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Department(
                resultSet.getInt("departmentsid"),
                resultSet.getString("name")
        );
    }
}
