package vebProektEshop.service;

import vebProektEshop.model.Category;
import vebProektEshop.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {

    Optional<Manufacturer> findById(Long id);
    List<Manufacturer> findAll();
    Optional<Manufacturer> save(String name, String address);
    List<Manufacturer> listManufacturers();
    void deleteById(Long id);
    Manufacturer create(String name, String address);
}
