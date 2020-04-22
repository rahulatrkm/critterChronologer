package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomersRepository;
import com.udacity.jdnd.course3.critter.repository.PetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private PetsRepository petsRepository;

    public List<Customer> getAllCustomers() {
        return customersRepository.findAll();
    }

    public Customer getCustomerGetId(Long petId){
        return petsRepository.getOne(petId).getCustomer();
    }

    public Customer saveCustomer(Customer customer, List<Long> petIds) {
        List<Pet> pets = new ArrayList<>();
        if (petIds != null && !petIds.isEmpty()) {
            pets = petIds.stream().map((petId) -> petsRepository.getOne(petId)).collect(Collectors.toList());
        }
        customer.setPets(pets);
        return customersRepository.save(customer);
    }
}
