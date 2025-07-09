package com.telusko.SpringSecEx.service;

import com.telusko.SpringSecEx.model.BusinessContact;
import com.telusko.SpringSecEx.model.BusinessPartners;
import com.telusko.SpringSecEx.repo.BusinessContactRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessContactService {
    private static final Logger logger = LoggerFactory.getLogger(BusinessContactService.class);

    @Autowired
    private BusinessContactRepo businessContactRepo;

    public List<BusinessContact> getBusinessContactsByTinNo(String tinNo) {
        logger.info("Fetching all business contacts for tinNo: {}", tinNo);
        List<BusinessContact> contacts = businessContactRepo.findAllByTinNoIgnoreCase(tinNo);
        logger.info("Found {} business contacts for tinNo: {}", contacts.size(), tinNo);
        return contacts;
    }

    public BusinessContact createBusinessContact(BusinessContact contact) {
        logger.info("Creating business contact for tinNo: {}, slNo: {}", contact.getTinNo(), contact.getSlNo());
        if (contact.getSlNo() != null && businessContactRepo.existsByTinNoAndSlNo(contact.getTinNo(), contact.getSlNo())) {
            logger.error("Business contact already exists for tinNo: {}, slNo: {}", contact.getTinNo(), contact.getSlNo());
            throw new RuntimeException("Business contact with TinNo " + contact.getTinNo() + " and SlNo " + contact.getSlNo() + " already exists");
        }
        BusinessContact savedContact = businessContactRepo.save(contact);
        logger.info("Created business contact: {}", savedContact);
        return savedContact;
    }

    public List<BusinessPartners> getBusinessPartnersByTinNo(String tinNo) {
        logger.info("Fetching business partners for tinNo: {}", tinNo);
        
        List<BusinessContact> contacts = businessContactRepo.findAllByTinNoIgnoreCase(tinNo);
        List<BusinessPartners> partners = contacts.stream().map(contact -> {
            
            BusinessPartners dto = new BusinessPartners();
            dto.setId(contact.getId()); 
            dto.setName(contact.getPersonName());
            dto.setFatherName(contact.getFatherName());
            dto.setStreet(contact.getPresentAddress());
            dto.setArea(contact.getAreaLocality());
            dto.setPlace(contact.getVillageTownCity());
            dto.setTelephone(contact.getTelNo());
            dto.setDateOfBirth(contact.getDob());
            dto.setDateOfEntry(contact.getDateOfEntry());
            dto.setDateOfLeaving(contact.getDateOfLeaving());
            dto.setType(contact.getPartnerType());
            return dto;

        }).collect(Collectors.toList());
        logger.info("Found {} business partners for tinNo: {}", partners.size(), tinNo);
        return partners;
    }
}