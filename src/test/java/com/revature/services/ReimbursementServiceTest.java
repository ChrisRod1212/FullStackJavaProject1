package com.revature.services;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReimbursementServiceTest {

    ReimbursementService reimbursementService;

    ReimbursementDAO reimbursementDAO = Mockito.mock(ReimbursementDAO.class);

    public ReimbursementServiceTest(){
        reimbursementService = new ReimbursementService(reimbursementDAO);
    }

    @Test
    void getAllReimbursements() {
        //arrange
        List<Reimbursement> expectedOutput = new ArrayList<>();
        expectedOutput.add(new Reimbursement(1, Timestamp.valueOf("2022-07-01 00:00:00.857"), "descriptiontest1", 5, 1, 2, 4000.25, 0, Timestamp.valueOf("2021-07-05 00:00:00.857")));
        expectedOutput.add(new Reimbursement(1, Timestamp.valueOf("2022-01-10 00:00:00.857"), "descriptiontest2", 2, 0, 3, 4000.25, 0, Timestamp.valueOf("2021-07-02 00:00:00.857")));
        expectedOutput.add(new Reimbursement(1, Timestamp.valueOf("2022-05-02 00:00:00.857"), "descriptiontest3", 5, 0, 2, 4000.25, 1, Timestamp.valueOf("2021-07-03 00:00:00.857")));
        Mockito.when(reimbursementDAO.getAllReimbursements()).thenReturn(expectedOutput);
        //act
        List<Reimbursement> actualOutput = reimbursementService.getAllReimbursements();
        //assert
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getGetAllReimbursementsGivenUserId() {
        //arrange
        Integer author = 2;
        List<Reimbursement> expectedOutput = new ArrayList<>();
        expectedOutput.add(new Reimbursement(1, Timestamp.valueOf("2022-07-01 00:00:00.857"), "descriptiontest1", 5, 1, 2, 4000.25, 0, Timestamp.valueOf("2021-07-05 00:00:00.857")));
        expectedOutput.add(new Reimbursement(1, Timestamp.valueOf("2022-05-02 00:00:00.857"), "descriptiontest3", 5, 0, 2, 4000.25, 1, Timestamp.valueOf("2021-07-03 00:00:00.857")));

        Mockito.when(reimbursementDAO.getAllReimbursementsGivenAuthor(author)).thenReturn(expectedOutput);
        //act
        List<Reimbursement> actualOutput = reimbursementService.getAllReimbursementsGivenAuthor(author);

        //assert
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getGetAllReimbursementsGivenStatus() {
        //arrange
        Integer status = 1;
        List<Reimbursement> expectedOutput = new ArrayList<>();
        expectedOutput.add(new Reimbursement(1, Timestamp.valueOf("2022-07-01 00:00:00.857"), "descriptiontest1", 5, 1, 2, 4000.25, 0, Timestamp.valueOf("2021-07-05 00:00:00.857")));
        expectedOutput.add(new Reimbursement(1, Timestamp.valueOf("2022-01-10 00:00:00.857"), "descriptiontest2", 2, 0, 3, 4000.25, 0, Timestamp.valueOf("2021-07-02 00:00:00.857")));
        Mockito.when(reimbursementDAO.getAllReimbursementsGivenStatus(status)).thenReturn(expectedOutput);
        //act
        List<Reimbursement> actualOutput = reimbursementService.getAllReimbursementsGivenStatus(status);

        //assert
        assertEquals(expectedOutput, actualOutput);
    }
//todo createReimbTest
    @Test
    void createReimbursement() {
        //arrange
        int author = 1;
        double amount = 596d;
        String description = "description";
        int typeId = 1;
        Reimbursement reimbursementToPass = new Reimbursement(author, amount, description, typeId);

        //act
        reimbursementService.createReimbursement(reimbursementToPass);
        //assert
        Mockito.verify(reimbursementDAO).createReimbursement(reimbursementToPass);
    }

    /*@Test
    void resolveReimbursement() {
        //arrange
        int resolver = 1;
        double amount = 596d;
        String description = "description";
        int typeId = 1;
        int reimbId = 1;
        int status = 1;
        int author = 0;

        Timestamp resolved = new Timestamp(1535774675000L);
        Timestamp submitted = new Timestamp(1535773675000L);
        Reimbursement reimbursementToPass = new Reimbursement(reimbId, resolved, description, author, resolver, typeId, amount, status, submitted);

        //act
        reimbursementService.resolveReimbursement(reimbursementToPass);
        //assert
        Mockito.verify(reimbursementDAO).resolveReimbursement(reimbursementToPass);
    }*/
}