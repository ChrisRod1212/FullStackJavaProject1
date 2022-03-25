package com.revature.models;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * This concrete Reimbursement class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>Description</li>
 *     <li>Creation Date</li>
 *     <li>Resolution Date</li>
 *     <li>Receipt Image</li>
 * </ul>
 *
 */
public class Reimbursement extends AbstractReimbursement {

    public Reimbursement() {
        super();
    }

    /**
     * This includes the minimum parameters needed for the {@link com.revature.models.AbstractReimbursement} class.
     * If other fields are needed, please create additional constructors.
     */
    public Reimbursement(int reimbId, Timestamp resolved, String description, int author, int resolver, int typeId, double amount, int status, Timestamp submitted) {
        super(reimbId, resolved, description, author, resolver, typeId, amount, status, submitted);
    }
    public Reimbursement(int author, double amount, String description, int typeId) {
        super(author, amount, description, typeId);
    }
}















