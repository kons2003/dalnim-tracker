package com.kons.dalnimtracker.repository;

import com.kons.dalnimtracker.domain.Sighting;
import org.springframework.data.jpa.repository.JpaRepository; // findById, findAll, save, delete 등 CRUD 제공
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SightingRepository extends JpaRepository<Sighting, Integer> {

    // 전체 목격 기록 Id 최신순(내림차순) 조회
    List<Sighting> findAllByOrderByIdDesc();
}
