package com.mr486.gestojob.service;

import com.mr486.gestojob.model.Mail;
import com.mr486.gestojob.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class MailService {

  @Autowired
  private MailRepository mailRepository;

  public Boolean existe(Long id) {
    return mailRepository.existsById(id);
  }

  public Mail mailById(Long id) {
    return mailRepository.findById(id).orElse(null);
  }

  public List<Mail> mailsByCompanyId(Long id) {
    List<Mail> mails;
    mails = mailRepository.findByCompanyIdOrderByMailDateDesc(id);
    return mails;
  }

  public Mail saveMail(Mail mail) {
    return mailRepository.save(mail);
  }

  public Mail updateMail(Long id, Mail mail) {
    mail.setId(id);
    return mailRepository.save(mail);
  }

  public void deleteMailById(Long id) {
    this.mailRepository.deleteById(id);
  }

  public Long count() {
    return mailRepository.count();
  }

}
