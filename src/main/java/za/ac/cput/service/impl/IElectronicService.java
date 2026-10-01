package za.ac.cput.service.impl;

import za.ac.cput.domain.Electronic;

import java.util.List;

public interface IElectronicService extends IService<Electronic, Integer> {

    List<Electronic> findByCustomerId(int customerId);
}
