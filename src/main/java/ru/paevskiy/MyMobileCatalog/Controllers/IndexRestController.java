package ru.paevskiy.MyMobileCatalog.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.paevskiy.MyMobileCatalog.Models.MobileCatalog;
import ru.paevskiy.MyMobileCatalog.Models.MobileCatalogService;

import java.util.List;

@RestController
public class IndexRestController {
    private final MobileCatalogService mobileCatalogService;

    @Autowired
    public IndexRestController(MobileCatalogService mobileCatalogService) {
        this.mobileCatalogService = mobileCatalogService;
    }


    @GetMapping("/mobileCatalog")}
    @ResponseBody
    public List<MobileCatalog> getMobileCatalog() {
        System.out.println(myDATA.mobileCatalogs.size());
        return myDATA.mobileCatalogs;
    }

    @GetMapping("/department")
    @ResponseBody
    public List<Department> getDepartments() {
        return myDATA.departments;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
        public MobileCatalog create(@RequestBody MobileCatalog mobileCatalog) {
//        myDATA.mobileCatalogs.add(mobileCatalog);
        testDAO.save(mobileCatalog);
        return myDATA.mobileCatalogs.get(myDATA.mobileCatalogs.size()-1);
    }
    @DeleteMapping("/mobileCatalog/{id}")
    public void delete(@PathVariable("id") int id) {
        testDAO.delete(id);
    }
    @RequestMapping(value = "/employee", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public void updateEmployee(@RequestBody MobileCatalog mobileCatalog) {
        testDAO.update(mobileCatalog);
    }
    @RequestMapping(value = "/{id}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public void delete(@PathVariable("id") Long id) {
        testDAO.delete(id);
    }
}
