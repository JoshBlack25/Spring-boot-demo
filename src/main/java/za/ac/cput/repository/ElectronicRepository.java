package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Electronic;

import java.util.List;

@Repository
public interface ElectronicRepository extends JpaRepository<Electronic, Integer> {
    List<Electronic> findByCustomerId(int customerId);
}
