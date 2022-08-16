package com.mr486.gestojob.repository;

import com.mr486.gestojob.model.PhoneCall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneCallRepository extends JpaRepository<PhoneCall, Long> {
  List<PhoneCall> findByCompanyIdOrderByPhoneCallDateDesc(Long id);
}
