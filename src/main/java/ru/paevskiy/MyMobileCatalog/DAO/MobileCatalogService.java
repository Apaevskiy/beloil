package ru.paevskiy.MyMobileCatalog.DAO;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.paevskiy.MyMobileCatalog.Models.MobileCatalog;

import java.util.List;

@Service
public class MobileCatalogService {
    private final JdbcTemplate jdbcTemplate;

    public MobileCatalogService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(MobileCatalog mobileCatalog) {
        String save = "insert into public.mobilecatalog " +
                "(fullName, position,departmentId,serviceNumber,personalPhoneNumber,serviceMobilePhoneNumber) " +
                "values ('" + mobileCatalog.getFullName() + "', '" +
                mobileCatalog.getPosition() + "', '" +
                mobileCatalog.getMyDepartment().getDepartmentId() + "', '" +
                mobileCatalog.getServiceNumber() + "', '" +
                mobileCatalog.getPersonalPhoneNumber() + "', '" +
                mobileCatalog.getServiceMobilePhoneNumber() + "')";
        jdbcTemplate.update(save);
    }

    public List<MobileCatalog> readAll() {
//        Integer resultSet = jdbcTemplate.queryForObject("select COUNT(*) from mobilecatalog where id = '3' limit 1", Integer.class);

        String read = "Select mc.id, mc.fullname, mc.position, mc.servicenumber, mc.personalphonenumber,mc.servicemobilephonenumber, dps.departmentsid, dps.name from mobilecatalog mc " +
                "join departments dps ON dps.departmentsid=mc.departmentid order by mc.id";
        return jdbcTemplate.query(read, new MobileCatalogMapper());
    }

    public boolean delete(int id) {
        if (existsById(id)) {
            String strDelete = "delete from mobilecatalog where id = "+id;
            jdbcTemplate.update(strDelete);
            return true;
        }
        return false;
    }
    public boolean updateCatalog(MobileCatalog mobileCatalog, int id) {
        if (existsById(id)) {
            String strUpdate = "update public.MobileCatalog set " +
                    "fullName='"+mobileCatalog.getFullName()+
                    "', position='"+mobileCatalog.getPosition()+
                    "', departmentid='"+mobileCatalog.getMyDepartment().getDepartmentId()+
                    "', serviceNumber='"+mobileCatalog.getServiceNumber()+
                    "', personalPhoneNumber='"+mobileCatalog.getPersonalPhoneNumber()+
                    "', serviceMobilePhoneNumber='"+mobileCatalog.getServiceMobilePhoneNumber()+
                    "' where id="+id;
            System.out.println(strUpdate);
            jdbcTemplate.update(strUpdate);
            return true;
        }
        return false;
    }
    public boolean existsById(int id){
        String sql = "select COUNT(*) from mobilecatalog where id = '"+id+"' limit 1";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count != null && count != 0;
    }
}
