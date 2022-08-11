package com.mr486.gestojob.service;

import com.mr486.gestojob.model.Compagny;
import com.mr486.gestojob.repository.CompagnyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CompagnyService {

  @Autowired
  private CompagnyRepository compagnyRepository;

  public Boolean existe(Long id) {
    return compagnyRepository.existsById(id);
  }

  public Compagny compagnyById(Long id) {
    return compagnyRepository.findById(id).orElse(null);
  }

  public List<Compagny> allCompagny() {
    List<Compagny> compagnies;
    compagnies = compagnyRepository.findAll();
    return compagnies;
  }

  public Compagny saveCompagny(Compagny compagny) {
    return compagnyRepository.save(compagny);
  }

  public Compagny updateCompagny(Long id, Compagny compagny) {
    compagny.setId(id);
    return compagnyRepository.save(compagny);
  }

  public void deleteCompagnyById(Long id) {
    this.compagnyRepository.deleteById(id);
  }

  public Long count() {
    return compagnyRepository.count();
  }

}
