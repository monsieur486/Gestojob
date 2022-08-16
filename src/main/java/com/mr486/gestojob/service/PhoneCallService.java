package com.mr486.gestojob.service;

import com.mr486.gestojob.model.Mail;
import com.mr486.gestojob.model.PhoneCall;
import com.mr486.gestojob.repository.PhoneCallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class PhoneCallService {

  @Autowired
  private PhoneCallRepository phoneCallRepository;

  public Boolean existe(Long id) {
    return phoneCallRepository.existsById(id);
  }

  public PhoneCall phoneCallById(Long id) {
    return phoneCallRepository.findById(id).orElse(null);
  }

  public List<PhoneCall> phoneCallsByCompanyId(Long id) {
    List<PhoneCall> phoneCalls;
    phoneCalls = phoneCallRepository.findByCompanyIdOrderByPhoneCallDateDesc(id);
    return phoneCalls;
  }

  public PhoneCall savePhoneCall(PhoneCall phoneCall) {
    return phoneCallRepository.save(phoneCall);
  }

  public PhoneCall updatePhoneCall(Long id, PhoneCall phoneCall) {
    phoneCall.setId(id);
    return phoneCallRepository.save(phoneCall);
  }

  public void deletePhoneCallById(Long id) {
    this.phoneCallRepository.deleteById(id);
  }

  public Long count() {
    return phoneCallRepository.count();
  }

}
