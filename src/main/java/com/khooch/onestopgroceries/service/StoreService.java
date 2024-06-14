package com.khooch.onestopgroceries.service;

import com.khooch.onestopgroceries.entity.Store;
import com.khooch.onestopgroceries.exception.StoreNotFoundException;
import com.khooch.onestopgroceries.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    public Store saveStore(Store store) {
        return storeRepository.save(store);
    }

    public Store getStoreById(Long id) {
        Optional<Store> optionalStore = storeRepository.findById(id);
        if (optionalStore.isPresent()) {
            return optionalStore.get();
        } else {
            throw new StoreNotFoundException(id);
        }
    }

    public void deleteStore(Long id) {
        storeRepository.deleteById(id);
    }
}
