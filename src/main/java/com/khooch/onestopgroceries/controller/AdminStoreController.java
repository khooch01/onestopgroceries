package com.khooch.onestopgroceries.controller;

import com.khooch.onestopgroceries.entity.Store;
import com.khooch.onestopgroceries.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/stores")
public class AdminStoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping
    public String viewStores(Model model) {
        List<Store> stores = storeService.getAllStores();
        model.addAttribute("stores", stores);
        return "admin/view_stores";
    }

    @GetMapping("/add")
    public String showAddStoreForm(Model model) {
        model.addAttribute("store", new Store());
        return "admin/add_store";
    }

    @PostMapping("/add")
    public String addStore(@Valid @ModelAttribute("store") Store store, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/add_store";
        }
        storeService.saveStore(store);
        return "redirect:/admin/stores";
    }

    @GetMapping("/edit/{id}")
    public String showEditStoreForm(@PathVariable Long id, Model model) {
        Store store = storeService.getStoreById(id);
        model.addAttribute("store", store);
        return "admin/edit_store";
    }

    @PostMapping("/edit/{id}")
    public String editStore(@PathVariable Long id, @Valid @ModelAttribute("store") Store store, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/edit_store";
        }
        store.setId(id);
        storeService.saveStore(store);
        return "redirect:/admin/stores";
    }

    @GetMapping("/delete/{id}")
    public String deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
        return "redirect:/admin/stores";
    }

    @GetMapping("/{id}")
    public String viewStoreDetails(@PathVariable Long id, Model model) {
        Store store = storeService.getStoreById(id);
        model.addAttribute("store", store);
        return "admin/store_details";
    }
}
