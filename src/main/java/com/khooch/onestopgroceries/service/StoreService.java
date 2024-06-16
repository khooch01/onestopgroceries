package com.khooch.onestopgroceries.service;

import com.khooch.onestopgroceries.entity.Store;
import com.khooch.onestopgroceries.exception.StoreNotFoundException;
import com.khooch.onestopgroceries.repository.StoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    private static final Logger logger = LoggerFactory.getLogger(StoreService.class);

    @Autowired
    private StoreRepository storeRepository;

    public List<Store> getAllStores() {
        logger.debug("Fetching all stores");
        return storeRepository.findAll();
    }

    public Store saveStore(Store store) {
        logger.debug("Saving store: {}", store);
        return storeRepository.save(store);
    }

    public Store getStoreById(Long id) {
        logger.debug("Fetching store with ID: {}", id);
        Optional<Store> optionalStore = storeRepository.findById(id);
        if (optionalStore.isPresent()) {
            return optionalStore.get();
        } else {
            logger.error("Store not found with ID: {}", id);
            throw new StoreNotFoundException(id);
        }
    }

    public void deleteStore(Long id) {
        logger.debug("Deleting store with ID: {}", id);
        storeRepository.deleteById(id);
    }
}
