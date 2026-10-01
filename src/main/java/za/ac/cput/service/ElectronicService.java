package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Electronic;
import za.ac.cput.factory.ElectronicFactory;
import za.ac.cput.repository.ElectronicRepository;
import za.ac.cput.service.impl.IElectronicService;

import java.util.List;

@Service
public class ElectronicService implements IElectronicService {

    private final ElectronicRepository repository;

    @Autowired
    public ElectronicService(ElectronicRepository repository){
        this.repository = repository;
    }


    @Override
    public List<Electronic> findByCustomerId(int customerId) {
        return repository.findByCustomerId(customerId);
    }

    @Override
    public Electronic create(Electronic electronic) {
        if (electronic == null) return null;

        Electronic validated = ElectronicFactory.buildElectronic(
                electronic.getBrand(),
                electronic.getPrice(),
                electronic.getCustomer(),
                electronic.getVoltage()
        );

        if (validated == null) return null;

        return repository.save(validated);
    }

    @Override
    public Electronic read(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Electronic update(Electronic electronic) {
        if (electronic == null) return null;
        if (!repository.existsById(electronic.getProductId())) return null;

        Electronic validated = ElectronicFactory.buildElectronic(
                electronic.getBrand(),
                electronic.getPrice(),
                electronic.getCustomer(),
                electronic.getVoltage()
        );

        if (validated == null) return null;

        Electronic toSave = new Electronic.Builder()
                .copy(validated)
                .setProductId(electronic.getProductId())
                .build();

        return repository.save(toSave);

    }

    @Override
    public void delete(Integer id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
        }
    }

    @Override
    public List<Electronic> getAll() {
        return repository.findAll();
    }
}
