package com.kons.dalnimtracker.repository;

import com.kons.dalnimtracker.domain.Sighting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository; // findById, findAll, save, delete 등 CRUD 제공
import org.springframework.stereotype.Repository;

@Repository
public interface SightingRepository extends JpaRepository<Sighting, Integer> {

    // catStatus가 일치하는 데이터만 페이징 처리하여 조회
    Page<Sighting> findByCatStatus(String catStatus, Pageable pageable);
}
