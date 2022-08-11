package com.mr486.gestojob.repository;

import com.mr486.gestojob.model.Compagny;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompagnyRepository extends JpaRepository<Compagny, Long> {
}
