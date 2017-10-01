package com.toptechsol.ipc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toptechsol.ipc.model.Certificate;
import com.toptechsol.ipc.repository.CertificateRepository;

@Service("certificateService")
public class CertificateServiceImpl implements CertificateService {

	@Autowired
	private CertificateRepository certificateRepository;
	@Override
	public Certificate findById(Long id) {
		return certificateRepository.findOne(id);
	}

	@Override
	public Certificate save(Certificate certifcate) {
		return certificateRepository.save(certifcate);
	}

	@Override
	public void deleteCertificate(Long id) {
		certificateRepository.delete(id);
		
	}


}
