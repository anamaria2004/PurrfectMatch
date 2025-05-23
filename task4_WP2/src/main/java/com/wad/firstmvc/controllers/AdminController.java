package com.wad.firstmvc.controllers;

import com.wad.firstmvc.domain.Pet;
import com.wad.firstmvc.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PetRepository petRepository;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("pets", petRepository.findAll());
        return "admin-dashboard";
    }

    @GetMapping("/pets/new")
    public String showAddPetForm(Model model) {
        model.addAttribute("pet", new Pet());
        return "add-pet";
    }

    @PostMapping("/pets/save")
    public String savePet(@ModelAttribute Pet pet, @RequestParam("photo") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // Create the uploads folder if not exists
                Path uploadDir = Paths.get("src/main/resources/static/uploads");
                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                }

                // Sanitize and get original filename
                String filename = file.getOriginalFilename();

                // Target location to save the file
                Path filePath = uploadDir.resolve(filename);

                // Save the file to the target location
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                // Optionally set photoPath on pet entity to reference the path for display
                pet.setPhotoPath("/uploads" + filename);

            } catch (IOException e) {
                e.printStackTrace();
                // handle error: maybe add flash message or return error page
            }
        }

        petRepository.save(pet);

        return "redirect:/admin/dashboard";
    }

    @PostMapping("/pets/delete/{id}")
    public String deletePet(@PathVariable Long id) {
        petRepository.deleteById(id);
        return "redirect:/admin/dashboard";
    }


}

