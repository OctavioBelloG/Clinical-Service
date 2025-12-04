package com.example.service;

import java.util.List;
import java.sql.Timestamp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.model.MedicalRecord;
import com.example.dto.MedicalRecordRequest;
import com.example.dto.MedicalRecordResponse;
import com.example.mapper.MedicalRecordMapper;
import com.example.repository.MedicalRecordRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository repository;

    // Métodos findAll/findById omitidos para brevedad, agrégalos si faltan en tu interfaz

    @Override
    public MedicalRecordResponse findByPatientId(Long patientId) {
        // CORRECCIÓN: findByPatientId
        MedicalRecord m = repository.findByPatientId(patientId)
                .orElseThrow(() -> new EntityNotFoundException("MedicalRecord for Patient ID not found: " + patientId));
        return MedicalRecordMapper.toResponse(m);
    }
    
    @Override
    public MedicalRecordResponse create(MedicalRecordRequest req) {
        // CORRECCIÓN: Validación con findByPatientId
        if (repository.findByPatientId(req.getPatientId()).isPresent()) { 
             throw new IllegalArgumentException("El paciente ya tiene un historial médico");
        }
        MedicalRecord saved = repository.save(MedicalRecordMapper.toEntity(req));
        return MedicalRecordMapper.toResponse(saved);
    }
    
    @Override
    public MedicalRecordResponse update(Long id, MedicalRecordRequest req) {
        MedicalRecord existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MedicalRecord not found: " + id));
        
        MedicalRecordMapper.copyToEntity(req, existing);
        existing.setLastUpdated(new Timestamp(System.currentTimeMillis())); 
        
        MedicalRecord saved = repository.save(existing);
        return MedicalRecordMapper.toResponse(saved);
    }
    
    @Override
    public MedicalRecordResponse updateLastUpdatedDate(Long id) {
        MedicalRecord existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MedicalRecord not found: " + id));
        existing.setLastUpdated(new Timestamp(System.currentTimeMillis())); 
        MedicalRecord saved = repository.save(existing);
        return MedicalRecordMapper.toResponse(saved);
    }
    
    @Override
    public List<MedicalRecordResponse> searchRecords(String keyword) {
        // Asegúrate de tener este método custom en tu repositorio o bórralo si no lo usas
        // return repository.searchByKeyword(keyword).stream()...
        return List.of(); 
    }
}