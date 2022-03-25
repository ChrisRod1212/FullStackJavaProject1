package com.revature.controllers;

import com.revature.models.JsonResponse;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import io.javalin.http.Context;

import java.util.List;
import java.util.Objects;

public class ReimbursementController {
    private ReimbursementService reimbursementService;

    public ReimbursementController() { this.reimbursementService = new ReimbursementService(); }

    public ReimbursementController(ReimbursementService reimbursementService){
        this.reimbursementService = reimbursementService;
    }

    public void getReimbursementGivenReimbId(Context context){
        Integer reimbId = Integer.parseInt(context.pathParam("reimbId"));

        Reimbursement reimbursement = reimbursementService.getReimbursementGivenReimbId(reimbId);
        context.json(new JsonResponse(true, "Listing reimbursement: " + reimbId, reimbursement));
    }

    public void getAllReimbursements(Context context){

        List<Reimbursement> reimbursements = reimbursementService.getAllReimbursements();
        context.json(new JsonResponse(true, "Listing all reimbursements" ,reimbursements));
    }

    public void getAllReimbursementsGivenAuthor(Context context){
        Integer author = Integer.parseInt(Objects.requireNonNull(context.pathParam("author")));

        List<Reimbursement> reimbursements = reimbursementService.getAllReimbursementsGivenAuthor(author);
        context.json(new JsonResponse(true, "Listing all reimbursements for user " + author, reimbursements));
    }

    public void getAllReimbursementsGivenStatus(Context context){
        Integer status = Integer.parseInt(Objects.requireNonNull(context.pathParam("status")));

        List<Reimbursement> reimbursements = reimbursementService.getAllReimbursementsGivenStatus(status);
        context.json(new JsonResponse(true, "Listing all reimbursements with status: " + status, reimbursements));
    }

    public void createReimbursement(Context context){

        Reimbursement reimbursement = context.bodyAsClass(Reimbursement.class);
        reimbursementService.createReimbursement(reimbursement);
            context.json( new JsonResponse(true, "Reimbursement:" + reimbursement.getReimbId() + " has been created", reimbursement));



    }

    public void resolveReimbursement(Context context){
        Reimbursement reimbursement = context.bodyAsClass(Reimbursement.class);

        reimbursementService.resolveReimbursement(reimbursement);

        context.json(new JsonResponse(true, "Reimbursement " + reimbursement.getReimbId() + "resolved.", reimbursement.getReimbId()));

    }

}
