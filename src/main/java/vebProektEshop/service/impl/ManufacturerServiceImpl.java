package vebProektEshop.service.impl;

import vebProektEshop.model.Category;
import vebProektEshop.model.Manufacturer;
import vebProektEshop.repository.ManufacturerRepository;
import vebProektEshop.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return this.manufacturerRepository.findById(id);
    }

    @Override
    public List<Manufacturer> findAll() {
        return this.manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> save(String name, String address) {
        return Optional.of(this.manufacturerRepository.save(new Manufacturer(name, address)));
    }
    @Override
    public List<Manufacturer> listManufacturers() {
        return manufacturerRepository.findAll();
    }
    @Override
    public void deleteById(Long id) {
        this.manufacturerRepository.deleteById(id);
    }

    @Override
    public Manufacturer create(String name,String address) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Manufacturer m = new Manufacturer(name,address);
        manufacturerRepository.save(m);
        return m;
    }
}
