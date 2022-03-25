package com.revature.services;

import com.revature.models.Reimbursement;
import com.revature.repositories.ReimbursementDAO;
import com.revature.repositories.ReimbursementDAOImpl;

import java.util.List;

/**
 * The ReimbursementService should handle the submission, processing,
 * and retrieval of Reimbursements for the ERS application.
 *
 * {@code process} and {@code getReimbursementsByStatus} are the minimum methods required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create Reimbursement</li>
 *     <li>Update Reimbursement</li>
 *     <li>Get Reimbursements by ID</li>
 *     <li>Get Reimbursements by Author</li>
 *     <li>Get Reimbursements by Resolver</li>
 *     <li>Get All Reimbursements</li>
 * </ul>
 */
public class ReimbursementService {

    private ReimbursementDAO reimbursementDAO;

    public ReimbursementService(ReimbursementDAO reimbursementDAO){
        this.reimbursementDAO = reimbursementDAO;
    }

    public ReimbursementService(){

        this.reimbursementDAO = new ReimbursementDAOImpl();
    }
//List<Reimbursement> getAllReimbursements(Integer id);
//    List<Reimbursement> getGetAllReimbursementsGivenUserId(User user);
//    List<Reimbursement> getGetAllReimbursementsGivenStatus(Status status);
//    void createReimbursement();
//    void resolveReimbursement();
    public List<Reimbursement> getAllReimbursements(){
        return this.reimbursementDAO.getAllReimbursements();
    }

    public List<Reimbursement> getAllReimbursementsGivenAuthor(int author){
        return this.reimbursementDAO.getAllReimbursementsGivenAuthor(author);
    }

    public List<Reimbursement> getAllReimbursementsGivenStatus(int status){
        return this.reimbursementDAO.getAllReimbursementsGivenStatus(status);
    }

    /*public Reimbursement createReimbursement(Reimbursement reimbursement){

        return this.reimbursementDAO.createReimbursement(reimbursement);

    }*/
    public void createReimbursement(Reimbursement reimbursement){
        this.reimbursementDAO.createReimbursement(reimbursement);
    }

    public Reimbursement resolveReimbursement(Reimbursement reimbursement){
        Reimbursement reimbursementFromDb = (Reimbursement) reimbursementDAO.getReimbursementGivenReimbId(reimbursement.getReimbId());

        //if reimbursement does not exist, return false
        if(reimbursementFromDb == null){
            return reimbursement;
        }

        //if reimbursement is not pending, return false
        if(reimbursementFromDb.getStatus() != 0){
            return reimbursement;
        }

        //execute resolveReimbursement then return true
        this.reimbursementDAO.resolveReimbursement(reimbursement);
        return reimbursement;
    }

    public Reimbursement getReimbursementGivenReimbId(Integer reimbId) {
        return this.reimbursementDAO.getReimbursementGivenReimbId(reimbId);
    }

    /**
     * <ul>
     *     <li>Should ensure that the user is logged in as a Finance Manager</li>
     *     <li>Must throw exception if user is not logged in as a Finance Manager</li>
     *     <li>Should ensure that the reimbursement request exists</li>
     *     <li>Must throw exception if the reimbursement request is not found</li>
     *     <li>Should persist the updated reimbursement status with resolver information</li>
     *     <li>Must throw exception if persistence is unsuccessful</li>
     * </ul>
     *
     * Note: unprocessedReimbursement will have a status of PENDING, a non-zero ID and amount, and a non-null Author.
     * The Resolver should be null. Additional fields may be null.
     * After processing, the reimbursement will have its status changed to either APPROVED or DENIED.
     */
    /*public Reimbursement process(Reimbursement unprocessedReimbursement, Status finalStatus, User resolver) {
        return null;
    }*/

    /**
     * Should retrieve all reimbursements with the correct status.
     *//*
    public List<Reimbursement> getReimbursementsByStatus(Status status) {
        return Collections.emptyList();
    }*/
}