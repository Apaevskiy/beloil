package ru.paevskiy.MyMobileCatalog.DAO;

import org.springframework.jdbc.core.RowMapper;
import ru.paevskiy.MyMobileCatalog.Models.Department;
import ru.paevskiy.MyMobileCatalog.Models.MobileCatalog;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MobileCatalogMapper implements RowMapper<MobileCatalog> {
    @Override
    public MobileCatalog mapRow(ResultSet resultSet, int i) throws SQLException {
        String[] serviceNumber = (String[])resultSet.getArray("servicenumber").getArray();
        String[] personalPhoneNumber = (String[])resultSet.getArray("personalphonenumber").getArray();
        String[] serviceMobilePhoneNumber = (String[])resultSet.getArray("servicemobilephonenumber").getArray();
        return new MobileCatalog(
                resultSet.getInt("id"),
                resultSet.getString("fullname"),
                resultSet.getString("position"),
                new Department(resultSet.getInt("departmentsid"),resultSet.getString("name")),
                Arrays.asList(serviceNumber),
                Arrays.asList(personalPhoneNumber),
                Arrays.asList(serviceMobilePhoneNumber)
        );
    }
}
