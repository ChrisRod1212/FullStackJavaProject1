package com.revature.repositories;

import com.revature.models.Reimbursement;

import java.util.List;

public interface ReimbursementDAO {
    List<Reimbursement> getAllReimbursements();
    List<Reimbursement> getAllReimbursementsGivenAuthor(int Author);
    List<Reimbursement> getAllReimbursementsGivenStatus(int status);
    Reimbursement getReimbursementGivenReimbId(int reimbId);


    void createReimbursement(Reimbursement reimbursement);
    void resolveReimbursement(Reimbursement reimbursement);
}
