package ru.paevskiy.MyMobileCatalog.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MobileCatalogController {
    public MobileCatalogController() {
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
