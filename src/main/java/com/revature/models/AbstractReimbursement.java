package com.revature.models;

import java.util.Objects;

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

    private int id;
    private Status reimb_status_id;
    private int statusId; 
    private User reimb_author;
    private User resolver;
    private int reimb_amount;

    public AbstractReimbursement() {
        super();
    }

    public AbstractReimbursement(int id, Status status, User author, User resolver, int amount) {
        super();
        this.id = id;
        this.reimb_status_id = status;
        this.reimb_author = author;
        this.resolver = resolver;
        this.reimb_amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//	public Reimbursement( double amount, int author, int statusId,  int typeId)
    
    public Status getStatus() {
        return reimb_status_id;
    }

    public void setStatus(Status status) {
        this.reimb_status_id = status;
    }

    public User getAuthor() {
        return reimb_author;
    }

    public void setAuthor(User author) {
        this.reimb_author = author;
    }

    public User getResolver() {
        return resolver;
    }

    public void setResolver(User resolver) {
        this.resolver = resolver;
    }

    public int getAmount() {
        return reimb_amount;
    }

    public void setAmount(int amount) {
        this.reimb_amount = amount;
    }
    

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractReimbursement that = (AbstractReimbursement) o;
        return id == that.id && Double.compare(that.reimb_amount, reimb_amount) == 0 && reimb_status_id == that.reimb_status_id && Objects.equals(reimb_author, that.reimb_author) && Objects.equals(resolver, that.resolver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reimb_status_id, reimb_author, resolver, reimb_amount);
    }

    @Override
    public String toString() {
        return "AbstractReimbursement{" +
                "id=" + id +
                ", status=" + reimb_status_id +
                ", author=" + reimb_author +
                ", resolver=" + resolver +
                ", amount=" + reimb_amount +
                '}' ;
    }
}
