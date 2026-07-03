package csd230.s26.lab1.controllers;

import csd230.s26.lab1.entities.MagazineEntity;
import csd230.s26.lab1.entities.CartEntity;
import csd230.s26.lab1.repositories.MagazineRepository;
import csd230.s26.lab1.repositories.CartRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/magazine")
public class MagazineController {
    private final MagazineRepository magazineRepository;
    private final CartRepository cartRepository;

    // Standard Constructor Injection
    public MagazineController(MagazineRepository magazineRepository, CartRepository cartRepository) {
        this.magazineRepository = magazineRepository;
        this.cartRepository = cartRepository;
    }

    @GetMapping
    public String getAllMagazines(Model model) {
        model.addAttribute("magazine", magazineRepository.findAll());
        return "magazineList"; // Refers to magazineList.html
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("magazine", new MagazineEntity());
        return "magazineForm";
    }

    @PostMapping("/add")
    public String addMagazine(@ModelAttribute MagazineEntity magazine) {
        magazineRepository.save(magazine);
        return "redirect:/magazine";
    }

    @GetMapping("/delete/{id}")
    public String deleteMagazine(@PathVariable Long id) {
        MagazineEntity magazine = magazineRepository.findById(id).orElse(null);
        if (magazine != null) {
            // STEP 1: Remove magazine from all Many-to-Many Cart relationships
            for (CartEntity cart : magazine.getCarts()) {
                cart.getProducts().remove(magazine);
                cartRepository.save(cart);
            }
            // STEP 2: Now it is safe to delete
            magazineRepository.deleteById(id);
        }
        return "redirect:/magazines";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        MagazineEntity magazine = magazineRepository.findById(id).orElse(null);
        model.addAttribute("magazine", magazine); // This object NOW HAS AN ID (e.g., 5)
        return "magazineForm";
    }


//    @GetMapping("/edit/{id}")
//    public String showEditForm(@PathVariable Long id, Model model) {
//        // Look for the magazine by ID
//        MagazineEntity magazine = magazineRepository.findById(id).orElse(null);
//
//        if (magazine != null) {
//            model.addAttribute("magazine", magazine); // Pack the existing magazine into the suitcase
//            return "magazine-form"; // Send them to the exact same form used for adding
//        }
//
//        return "redirect:/magazines"; // If magazine not found, go back to list
//    }
}