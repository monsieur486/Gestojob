package com.mr486.gestojob.service;

import com.mr486.gestojob.model.Telephone;
import com.mr486.gestojob.repository.TelephoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class TelephoneService {

  @Autowired
  private TelephoneRepository telephoneRepository;

  public Boolean existe(Long id) {
    return telephoneRepository.existsById(id);
  }

  public Telephone telephoneById(Long id) {
    return telephoneRepository.findById(id).orElse(null);
  }

  public List<Telephone> allTelephone() {
    List<Telephone> telephones;
    telephones = telephoneRepository.findAll();
    return telephones;
  }

  public Telephone saveTelephone(Telephone telephone) {
    return telephoneRepository.save(telephone);
  }

  public Telephone updateTelephone(Long id, Telephone telephone) {
    telephone.setId(id);
    return telephoneRepository.save(telephone);
  }

  public void deleteTelephoneById(Long id) {
    this.telephoneRepository.deleteById(id);
  }

  public Long count() {
    return telephoneRepository.count();
  }

}
