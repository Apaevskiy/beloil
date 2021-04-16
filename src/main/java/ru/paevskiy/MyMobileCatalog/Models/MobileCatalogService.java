package ru.paevskiy.MyMobileCatalog.Models;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MobileCatalogService {
    private final MobileCatalogRepository mobileCatalogRepository;

    public MobileCatalogService(MobileCatalogRepository mobileCatalogRepository) {
        this.mobileCatalogRepository = mobileCatalogRepository;
    }

    public void create(MobileCatalog mobileCatalog) {
        mobileCatalogRepository.save(mobileCatalog);
    }

    public List<MobileCatalog> readAll() {
        List<MobileCatalog> list = mobileCatalogRepository.findAll();
        System.out.println(list);
        return list;
    }

    public boolean update(MobileCatalog mobileCatalog, int id) {
        if (mobileCatalogRepository.existsById(id)) {
            mobileCatalog.setId(id);
            mobileCatalogRepository.save(mobileCatalog);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        if (mobileCatalogRepository.existsById(id)) {
            mobileCatalogRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @Transactional
    public boolean updateCatalog(MobileCatalog mobileCatalog, int id) {
        if (mobileCatalogRepository.existsById(id)) {
            mobileCatalog.setId(id);
            mobileCatalogRepository.updateCatalog(mobileCatalog.getFullName(),
                    mobileCatalog.getPosition(),
                    mobileCatalog.getMyDepartment(),
                    mobileCatalog.getServiceNumber(),
                    mobileCatalog.getPersonalPhoneNumber(),
                    mobileCatalog.getServiceMobilePhoneNumber(),
                    id);
            return true;
        }
        return false;
    }
}
