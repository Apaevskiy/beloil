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

    // Создание нового пользователя
    public void create(MobileCatalog mobileCatalog) {
        String save = "insert into public.mobilecatalog " +
                "(fullName, position,departmentId,serviceNumber,personalPhoneNumber,serviceMobilePhoneNumber) " +
                "values ('" + mobileCatalog.getFullName() + "', '" +
                mobileCatalog.getPosition() + "', '" +
                mobileCatalog.getMyDepartment().getDepartmentId() + "', '" +
                mobileCatalog.listToSql(mobileCatalog.getServiceNumber()) + "', '" +
                mobileCatalog.listToSql(mobileCatalog.getPersonalPhoneNumber()) + "', '" +
                mobileCatalog.listToSql(mobileCatalog.getServiceMobilePhoneNumber()) + "')";
        jdbcTemplate.update(save);
    }
    // Получение всех пользователей
    public List<MobileCatalog> readAll() {
        String read = "Select mc.id, mc.fullname, mc.position, mc.servicenumber, mc.personalphonenumber,mc.servicemobilephonenumber, dps.departmentsid, dps.name from mobilecatalog mc " +
                "join departments dps ON dps.departmentsid=mc.departmentid order by mc.id";
        return jdbcTemplate.query(read, new MobileCatalogMapper());
    }
    // Удаление пользователя по id
    public boolean delete(int id) {
        if (existsById(id)) {
            String strDelete = "delete from mobilecatalog where id = "+id;
            jdbcTemplate.update(strDelete);
            return true;
        }
        return false;
    }
    // Удаление нескольких пользователей по id
    public boolean deleteList(int[] idList) {
        StringBuilder strDeleteList = new StringBuilder("delete from mobilecatalog where id=");
        int i=0;
        while (i<idList.length-1){
            if (!existsById(idList[i])) {
                return false;
            }else {
                strDeleteList.append(idList[i]);
                strDeleteList.append(" or id = ");
            }
            i++;
        }
        if (!existsById(i)) {
            return false;
        }else {
            strDeleteList.append(idList[i]);
        }
        jdbcTemplate.update(strDeleteList.toString());
        return true;
    }
    // Изменение пользователя по id
    public boolean updateCatalog(MobileCatalog mobileCatalog, int id) {
        if (existsById(id)) {
            String strUpdate = "update public.MobileCatalog set " +
                    "fullName='"+mobileCatalog.getFullName()+
                    "', position='"+mobileCatalog.getPosition()+
                    "', departmentid='"+mobileCatalog.getMyDepartment().getDepartmentId()+
                    "', serviceNumber='"+mobileCatalog.listToSql(mobileCatalog.getServiceNumber())+
                    "', personalPhoneNumber='"+mobileCatalog.listToSql(mobileCatalog.getPersonalPhoneNumber())+
                    "', serviceMobilePhoneNumber='"+mobileCatalog.listToSql(mobileCatalog.getServiceMobilePhoneNumber())+
                    "' where id="+id;
            jdbcTemplate.update(strUpdate);
            return true;
        }
        return false;
    }
    // Проверка, есть ли пользователь с таким id
    public boolean existsById(int id){
        String sql = "select COUNT(*) from mobilecatalog where id = '"+id+"' limit 1";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count != null && count != 0;
    }
}
