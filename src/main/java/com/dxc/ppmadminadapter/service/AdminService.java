package com.dxc.ppmadminadapter.service;

import com.dxc.ppmadminadapter.model.Patient;
import com.dxc.ppmadminadapter.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private PatientRepository patientRepository;

    public List<String> checkDeletedProfiles(List<String> patientIds) {
        return patientRepository.findPatientIds(patientIds);
    }

    public List<String> addPatientProfiles(List<String> patientIds) {
        List<String> existedProfiles = patientRepository.findPatientIds(patientIds);
        if(existedProfiles.isEmpty()) {
            return add(patientIds);
        } else {
            patientIds.removeAll(existedProfiles);
            return add(patientIds);
        }
    }

    public List<String> add(List<String> patientIds) {
        for (String id: patientIds) {
            Patient patient = new Patient();
            patient.setPatientId(id);
            patient.setDeleted(false);
            patientRepository.saveAndFlush(patient);
        }
        return patientIds;
    }

}
