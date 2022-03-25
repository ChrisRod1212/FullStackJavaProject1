package com.revature.models;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

import static java.lang.Double.compare;

/**
 * This AbstractReimbursement class defines a minimum functionality for
 * interacting with reimbursements in the ERS application.
 *
 * All reimbursements in this application must at least have:
 * <ul>
 *     <li>ID</li>
 *     <li>Status</li>
 *     <li>Author</li>
 *     <li>Resolver</li>
 *     <li>Amount</li>
 * </ul>
 *
 * Additional fields can be added to the concrete {@link com.revature.models.Reimbursement} class.
 *
 * @author Center of Excellence
 */
public class AbstractReimbursement {

    private int reimbId;
    private int status;
    private int author;
    private int resolver;
    private double amount;
    private Timestamp submitted;
    private Timestamp resolved;
    private String description;
    private int typeId;



    //Reimbursement(int reimbId, Status status, User author, User resolver, double amount, String submitted, String resolved, String description, int typeId)
    public AbstractReimbursement() {
        super();
    }

    public AbstractReimbursement(int reimbId, Timestamp resolved, String description, int author, int resolver, int typeId, double amount, int status, Timestamp submitted) {
        super();
        this.reimbId = reimbId;
        this.status = status;
        this.author = author;
        this.resolver = resolver;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.typeId = typeId;

    }

    public AbstractReimbursement(int author, double amount, String description, int typeId) {
        super();
        this.author = author;
        this.amount = amount;
        this.description = description;
        this.typeId = typeId;
    }

    public int getReimbId() {

        return reimbId;
    }

    public void setReimbId(int reimbId) {

        this.reimbId = reimbId;
    }

    public int getStatus() {

        return status;
    }

    public void setStatus(int status) {

        this.status = status;
    }

    public int getAuthor() {

        return author;
    }

    public void setAuthor(int author) {

        this.author = author;
    }

    public int getResolver() {

        return resolver;
    }

    public void setResolver(int resolver) {

        this.resolver = resolver;
    }

    public double getAmount() {

        return amount;
    }

    public void setAmount(double amount) {

        this.amount = amount;
    }

    public Timestamp getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Timestamp submitted) {
        this.submitted = submitted;
    }

    public Timestamp getResolved() {
        return resolved;
    }

    public void setResolved(Timestamp resolved) {
        this.resolved = resolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractReimbursement that = (AbstractReimbursement) o;
        return reimbId == that.reimbId && compare(that.amount, amount) == 0 && status == that.status && Objects.equals(author, that.author) && Objects.equals(resolver, that.resolver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimbId, status, author, resolver, amount, submitted, resolved, description, typeId);
    }
//(reimbId, status, author, resolver, amount, submitted, resolved, description, typeId)
    @Override
    public String toString() {
        return "AbstractReimbursement{" +
                "reimbursement id=" + reimbId +
                ", status=" + status +
                ", author=" + author +
                ", resolver=" + resolver +
                ", amount=" + amount +
                ", submitted=" + submitted +
                ", resolved=" + resolved +
                ", description=" + description +
                ", typeId=" + typeId +
                '}';
    }
}
