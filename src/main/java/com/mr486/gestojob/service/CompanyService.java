package com.mr486.gestojob.service;

import com.mr486.gestojob.dto.CompanyDto;
import com.mr486.gestojob.model.Company;
import com.mr486.gestojob.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CompanyService {

  @Autowired
  private CompanyRepository companyRepository;

  public Boolean existe(Long id) {
    return companyRepository.existsById(id);
  }

  public Company companyById(Long id) {
    return companyRepository.findById(id).orElse(null);
  }

  public List<Company> allCompany() {
    List<Company> compagnies;
    compagnies = companyRepository.findAll(Sort.by("name"));
    return compagnies;
  }

  public List<Company> findByNameContaining(String title) {
    List<Company> compagnies;
    compagnies = companyRepository.findByNameContaining(title);
    return compagnies;
  }

  public Company saveCompany(CompanyDto companyDto) {
    Company result = new Company(
      companyDto.getName(),
      companyDto.getEmail(),
      companyDto.getTelephone(),
      companyDto.getAdress(),
      companyDto.getComplement(),
      companyDto.getPostalCode(),
      companyDto.getCity(),
      companyDto.getNegative(),
      companyDto.getComment()
    );
    return companyRepository.save(result);
  }

  public Company updateCompany(Long id, Company company) {
    company.setId(id);
    return companyRepository.save(company);
  }

  public void deleteCompanyById(Long id) {
    this.companyRepository.deleteById(id);
  }

  public Long count() {
    return companyRepository.count();
  }

}
