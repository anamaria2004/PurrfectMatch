package com.wad.firstmvc.controllers;

import com.wad.firstmvc.domain.Pet;
import com.wad.firstmvc.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdoptController {

    @Autowired
    private PetRepository petRepository;

    @GetMapping("/adopt")
    public String adopt(Model model) {
        List<Pet> pets = petRepository.findAll();
        System.out.println("Pets found: " + pets.size());
        model.addAttribute("pets", pets);
        return "adopt";
    }

}
