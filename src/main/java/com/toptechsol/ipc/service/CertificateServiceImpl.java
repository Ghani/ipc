package com.toptechsol.ipc.service;

import java.util.Date;
import java.util.List;

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

	@Override
	public List<Certificate> listExpiredDocument(Date date) {
		return certificateRepository.listExpiredDocument(date);
	}

	@Override
	public List<Certificate> listDocumentWillExpireInMonths(Date startDate, Date endDate) {
		return certificateRepository.listDocumentWillExpireInMonths(startDate, endDate);
	}


}
