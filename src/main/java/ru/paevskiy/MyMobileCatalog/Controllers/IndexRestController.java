package ru.paevskiy.MyMobileCatalog.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.paevskiy.MyMobileCatalog.Models.MobileCatalog;
import ru.paevskiy.MyMobileCatalog.DAO.MobileCatalogService;

import java.util.Arrays;
import java.util.List;

@RestController
public class IndexRestController {
    private final MobileCatalogService mobileCatalogService;

    @Autowired
    public IndexRestController(MobileCatalogService mobileCatalogService) {
        this.mobileCatalogService = mobileCatalogService;
    }

    @GetMapping(value = "/mobileCatalog")
    public ResponseEntity<List<MobileCatalog>> read() {
        final List<MobileCatalog> mobileCatalogLists = mobileCatalogService.readAll();
        return new ResponseEntity<>(mobileCatalogLists, HttpStatus.OK);
    }
    @PostMapping(value = "/mobileCatalog")
    public ResponseEntity<?> create(@RequestBody MobileCatalog mobileCatalog) {
        System.out.println("Create: " + mobileCatalog);
        mobileCatalogService.create(mobileCatalog);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping(value = "/mobileCatalog/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody MobileCatalog mobileCatalog) {
        System.out.println("Update: "+mobileCatalog);
        final boolean updated = mobileCatalogService.updateCatalog(mobileCatalog, id);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @DeleteMapping(value = "/mobileCatalog/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        System.out.println("delete: " + id);
        final boolean deleted = mobileCatalogService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @DeleteMapping(value = "/mobileCatalog/All")
    public ResponseEntity<?> deleteAll(@RequestBody int[] list) {
        System.out.println("deleteALL: " + Arrays.toString(list));
        final boolean deleted = mobileCatalogService.deleteList(list);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
