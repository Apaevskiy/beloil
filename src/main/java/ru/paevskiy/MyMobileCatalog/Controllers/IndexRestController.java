package ru.paevskiy.MyMobileCatalog.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.paevskiy.MyMobileCatalog.Models.Department;
import ru.paevskiy.MyMobileCatalog.Models.MobileCatalog;
import ru.paevskiy.MyMobileCatalog.DAO.MobileCatalogService;

import java.util.Arrays;
import java.util.List;

@RestController
public class IndexRestController {
    private final MobileCatalogService service;

    @Autowired
    public IndexRestController(MobileCatalogService service) {
        this.service = service;
    }
    // Возвращает список всех подразделений
    @GetMapping(value = "/departments")
    public ResponseEntity<List<Department>> readDepartment() {
        final List<Department> departmentLists = service.readAllDepartments();
        return new ResponseEntity<>(departmentLists, HttpStatus.OK);
    }
    // Возвращает список всех пользователей
    @GetMapping(value = "/mobileCatalog")
    public ResponseEntity<List<MobileCatalog>> read() {
        final List<MobileCatalog> mobileCatalogLists = service.readAll();
        return new ResponseEntity<>(mobileCatalogLists, HttpStatus.OK);
    }
    // Создание нового пользователя
    @PostMapping(value = "/mobileCatalog")
    public ResponseEntity<?> create(@RequestBody MobileCatalog mobileCatalog) {
        System.out.println("Create: " + mobileCatalog);
        service.create(mobileCatalog);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    // Изменение пользователя по id
    @PutMapping(value = "/mobileCatalog/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody MobileCatalog mobileCatalog) {
        System.out.println("Update: "+mobileCatalog);
        final boolean updated = service.updateCatalog(mobileCatalog, id);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    // Удаление пользователя по id
    @DeleteMapping(value = "/mobileCatalog/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        System.out.println("delete: " + id);
        final boolean deleted = service.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    // Удаление нескольких пользователей
    @DeleteMapping(value = "/mobileCatalog/All")
    public ResponseEntity<?> deleteAll(@RequestBody int[] list) {
        System.out.println("deleteALL: " + Arrays.toString(list));
        final boolean deleted = service.deleteList(list);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
