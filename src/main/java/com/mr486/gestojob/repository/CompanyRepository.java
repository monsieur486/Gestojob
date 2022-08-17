package com.mr486.gestojob.repository;

import com.mr486.gestojob.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
  List<Company> findByNameContaining(String name);
}
