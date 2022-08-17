package com.mr486.gestojob.repository;

import com.mr486.gestojob.model.Company;
import com.mr486.gestojob.model.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {
  List<Mail> findByCompanyIdOrderByMailDateDesc(Long id);

}
