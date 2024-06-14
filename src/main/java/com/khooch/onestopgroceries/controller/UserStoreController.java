package com.khooch.onestopgroceries.controller;

import com.khooch.onestopgroceries.entity.Store;
import com.khooch.onestopgroceries.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user/stores")
public class UserStoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping
    public String viewStores(Model model) {
        List<Store> stores = storeService.getAllStores();
        model.addAttribute("stores", stores);
        return "user/view_stores";
    }

    @GetMapping("/{id}")
    public String viewStoreDetails(@PathVariable Long id, Model model) {
        Store store = storeService.getStoreById(id);
        model.addAttribute("store", store);
        return "user/store_details";
    }
}
