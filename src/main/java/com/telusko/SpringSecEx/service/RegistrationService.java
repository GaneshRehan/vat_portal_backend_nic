package com.telusko.SpringSecEx.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telusko.SpringSecEx.dto.AcknowledgementDetailsDto;
import com.telusko.SpringSecEx.dto.ApprovalRequestDto;
import com.telusko.SpringSecEx.dto.InspectionRequestDto;
import com.telusko.SpringSecEx.dto.RegistrationDto;
import com.telusko.SpringSecEx.model.ApprovalDetail;
import com.telusko.SpringSecEx.model.InspectionDetail;
import com.telusko.SpringSecEx.model.Registration;
import com.telusko.SpringSecEx.repo.ApprovalDetailRepo;
import com.telusko.SpringSecEx.repo.InspectionDetailRepo;
import com.telusko.SpringSecEx.repo.RegistrationRepo;

import lombok.RequiredArgsConstructor;

/**
 * Service class to handle registration, inspection, and approval processes.
 */
@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationRepo registrationRepo;
    private final InspectionDetailRepo inspectionRepo;
    private final ApprovalDetailRepo approvalRepo;

    /**
     * 3.1 Retrieves all registrations assigned to a checker with the specified
     * status.
     *
     * @param checkerId ID of the checker
     * @param status    Status of the registrations (e.g., "pending")
     * @return List of registrations
     * @throws IllegalArgumentException if checkerId or status is invalid
     */
    public List<Registration> getRegistrationsByCheckerAndStatus(Long checkerId, String status) {
        if (checkerId == null || status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Checker ID and status must not be null or empty");
        }
        return registrationRepo.findByEnteredByAndStatus(checkerId, status);
    }

    /**
     * 3.2 Retrieves all registrations with the specified status.
     *
     * @param status Status of the registrations (e.g., "verified")
     * @return List of registrations
     * @throws IllegalArgumentException if status is invalid
     */
    public List<Registration> getRegistrationsByStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status must not be null or empty");
        }
        return registrationRepo.findByStatus(status);
    }

    /**
     * 3.3 Submits an inspection report for a registration identified by
     * acknowledgment number.
     *
     * @param ackNo Acknowledgment number of the registration
     * @param dto   Inspection request data transfer object
     * @return Saved InspectionDetail
     * @throws IllegalArgumentException if ackNo or DTO is invalid
     */
    @Transactional
    public InspectionDetail submitInspection(Long ackNo, InspectionRequestDto dto) {

        Registration reg = registrationRepo.findByAckNo(ackNo)
                .orElseThrow(() -> new IllegalArgumentException("Registration not found for ackNo: " + ackNo));

        Long regId = reg.getRegId();

        // Check if an InspectionDetail already exists for this regId
        Optional<InspectionDetail> existingOpt = inspectionRepo.findByRegId(regId);

        InspectionDetail detail = existingOpt.orElse(new InspectionDetail());
        detail.setRegId(regId);
        detail.setDateOfVisit(dto.getDateOfVisit());
        detail.setNatureOfBusiness(dto.getNatureOfBusiness());
        detail.setDatePurchases(dto.getDatePurchases());
        detail.setAmountPurchases(dto.getAmountPurchases());
        detail.setDateSales(dto.getDateSales());
        detail.setAmountSales(dto.getAmountSales());
        detail.setCapitalInvested(dto.getCapitalInvested());
        detail.setStockHeld(dto.getStockHeld());
        detail.setBooksMaintained(dto.getBooksMaintained());
        detail.setVerificationRemarks(dto.getVerificationRemarks());
        detail.setOtherInfo(dto.getOtherInfo());
        detail.setGpsLongitude(dto.getGpsLongitude());
        detail.setGpsLatitude(dto.getGpsLatitude());
        detail.setSecurityDeposit(dto.getSecurityDeposit());
        detail.setInspectorComments(dto.getInspectorComments());
        detail.setReportFileUrl(dto.getReportFileUrl());
        detail.setUpdatedStatus(dto.getUpdatedStatus());

        // Update registration status
        reg.setStatus("verified");
        registrationRepo.save(reg);

        return inspectionRepo.save(detail); // will update if entity has ID, else insert
    }

    /**
     * 3.4 Fetches a registration along with its inspection details by
     * acknowledgment number.
     *
     * @param ackNo Acknowledgment number of the registration
     * @return RegistrationDto with registration and inspection details
     * @throws IllegalArgumentException if ackNo is invalid or registration not
     *                                  found
     */
    public RegistrationDto getRegistrationWithInspection(Long ackNo) {

        Registration reg = registrationRepo.findByAckNo(ackNo)
                .orElseThrow(() -> new IllegalArgumentException("No registration found for ackNo: " + ackNo));

        Optional<InspectionDetail> inspOpt = inspectionRepo.findByRegId(reg.getRegId());

        RegistrationDto dto = new RegistrationDto();
        dto.setAckNo(reg.getAckNo());
        dto.setRegistrationType(reg.getRegistrationType());
        dto.setApplicantName(reg.getApplicantName());
        dto.setTradeName(reg.getTradeName());
        dto.setStatus(reg.getStatus());
        dto.setTinNumber(reg.getTinNumber());
        dto.setCreatedAt(reg.getCreatedAt());

        inspOpt.ifPresent(insp -> {
            RegistrationDto.InspectionDto i = new RegistrationDto.InspectionDto();
            i.setDateOfVisit(insp.getDateOfVisit());
            i.setNatureOfBusiness(insp.getNatureOfBusiness());
            i.setDatePurchases(insp.getDatePurchases());
            i.setAmountPurchases(insp.getAmountPurchases());
            i.setDateSales(insp.getDateSales());
            i.setAmountSales(insp.getAmountSales());
            i.setCapitalInvested(insp.getCapitalInvested());
            i.setStockHeld(insp.getStockHeld());
            i.setBooksMaintained(insp.getBooksMaintained());
            i.setVerificationRemarks(insp.getVerificationRemarks());
            i.setOtherInfo(insp.getOtherInfo());
            i.setGpsLongitude(insp.getGpsLongitude());
            i.setGpsLatitude(insp.getGpsLatitude());
            i.setSecurityDeposit(insp.getSecurityDeposit());
            i.setInspectorComments(insp.getInspectorComments());
            i.setReportFileUrl(insp.getReportFileUrl());
            i.setUpdatedStatus(insp.getUpdatedStatus());
            dto.setInspection(i);
        });

        return dto;
    }

    /**
     * 3.5 Submits the final approval for a registration and assigns a TIN if
     * approved.
     *
     * @param ackNo Acknowledgment number of the registration
     * @param dto   Approval request data transfer object
     * @return TIN number if approved, null if rejected
     * @throws IllegalArgumentException if ackNo or DTO is invalid
     */
    @Transactional
    public String submitApproval(Long ackNo, ApprovalRequestDto dto) {

        Registration reg = registrationRepo.findByAckNo(ackNo)
                .orElseThrow(() -> new IllegalArgumentException("No registration found for ackNo: " + ackNo));

        ApprovalDetail approval = new ApprovalDetail();
        approval.setAckNo(ackNo);
        approval.setApproverId(dto.getApproverId());
        approval.setAllYes(dto.getAllYes());
        approval.setApproverComments(dto.getApproverComments());
        approval.setApprovalStatus(dto.getApprovalStatus());

        if ("Approved".equalsIgnoreCase(dto.getApprovalStatus())) {
            String tin = UUID.randomUUID().toString().substring(0, 10).toUpperCase();
            approval.setTinAssigned(tin);
            reg.setTinNumber(tin);
            reg.setStatus("Approved");
        } else {
            reg.setStatus("Rejected");
        }

        registrationRepo.save(reg);
        approvalRepo.save(approval);

        return reg.getTinNumber();
    }

    public AcknowledgementDetailsDto getAcknowledgementDetails(Long ackNo) {
        return registrationRepo.getAcknowledgementDetails(ackNo);
    }

}