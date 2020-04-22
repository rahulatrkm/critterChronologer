package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    PetService petService;

    private PetDTO getPetsDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setBirthDate(pet.getDob());
        petDTO.setName(pet.getName());
        petDTO.setNotes(pet.getNote());
        petDTO.setOwnerId(pet.getCustomer().getId());
        petDTO.setType(pet.getType());
        return  petDTO;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = new Pet();
        pet.setType(petDTO.getType());
        pet.setName(petDTO.getName());
        pet.setDob(petDTO.getBirthDate());
        pet.setNote(petDTO.getNotes());
        return getPetsDTO(petService.savePet(pet, petDTO.getOwnerId()));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return getPetsDTO(petService.getPetById(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = petService.getAllPets();
        return pets.stream().map(this::getPetsDTO).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = petService.getPetsByCustomerId(ownerId);
        return pets.stream().map(this::getPetsDTO).collect(Collectors.toList());
    }
}
