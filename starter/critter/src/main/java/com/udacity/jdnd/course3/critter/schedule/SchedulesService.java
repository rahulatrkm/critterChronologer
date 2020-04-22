package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomersRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetsRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulesService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private PetsRepository petsRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomersRepository customersRepository;

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }
    public List<Schedule> getAllSchedulesForPet(Long petId) {
        Pet pet = petsRepository.getOne(petId);
        return scheduleRepository.getAllByPet(pet);
    }

    public List<Schedule> getAllSchedulesForEmployee(Long employeeId) {
        Employee employee = employeeRepository.getOne(employeeId);
        return scheduleRepository.getAllByEmployee(employee);
    }

    public List<Schedule> getAllScheduleForCustomer(Long customerId) {
        Customer customer = customersRepository.getOne(customerId);
        return scheduleRepository.getAllByPets(customer.getPets());
    }

    public Object saveSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds) {
        List<Employee> employees = employeeRepository.findAllById(employeeIds);
        List<Pet> pets = petsRepository.findAllById(petIds);
        schedule.setEmployees(employees);
        schedule.setPets(pets);
        return scheduleRepository.save(schedule);
    }
}
