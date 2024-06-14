package com.khooch.onestopgroceries.repository;

import com.khooch.onestopgroceries.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
