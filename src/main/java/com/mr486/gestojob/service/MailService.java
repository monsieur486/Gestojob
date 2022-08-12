package com.mr486.gestojob.service;

import com.mr486.gestojob.model.Mail;
import com.mr486.gestojob.repository.CompagnyRepository;
import com.mr486.gestojob.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class MailService {

  @Autowired
  private MailRepository mailRepository;

  @Autowired
  private CompagnyRepository compagnyRepository;

  public Boolean existe(Long id) {
    return mailRepository.existsById(id);
  }

  public Mail mailById(Long id) {
    return mailRepository.findById(id).orElse(null);
  }

  public List<Mail> mailByCompagnyId(Long id) {
        return mailRepository.findByCompagnyId(id);
  }

  public List<Mail> allMail() {
    List<Mail> mails;
    mails = mailRepository.findAll();
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
