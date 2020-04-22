package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.pet.Pet;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String phone;
    private String note;
    private String name;

    @OneToMany(targetEntity = Pet.class)
    private List<Pet> pets;

    public void insertPet(Pet pet) {
        pets.add(pet);
    }
}