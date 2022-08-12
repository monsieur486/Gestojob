package com.mr486.gestojob.repository;

import com.mr486.gestojob.model.PhoneCall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneCallRepository extends JpaRepository<PhoneCall, Long> {
}
