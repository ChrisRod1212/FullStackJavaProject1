package com.revature.repositories;

import com.revature.models.Reimbursement;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDAOImpl implements ReimbursementDAO{


    /**
     * <ul>
     *     <li>Should Update an existing Reimbursement record in the DB with the provided information.</li>
     *     <li>Should throw an exception if the update is unsuccessful.</li>
     *     <li>Should return a Reimbursement object with updated information.</li>
     * </ul>
     */

    @Override
    public List<Reimbursement> getAllReimbursements() {
        List<Reimbursement> reimbursements = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection()){
            String sql = "SELECT * FROM ers_reimbursement ORDER BY reimb_submitted desc ;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                reimbursements.add(new Reimbursement(rs.getInt(1), rs.getTimestamp(2),  rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getDouble(7), rs.getInt(8), rs.getTimestamp(9)));
            }
/*
*  private int reimbId;
    private Status status;
    private User author;
    private User resolver;
    private double amount;
    private Date submitted;
    private Date resolved;
    private String description;
    private int typeId;
* */
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }

        return reimbursements;
    }

    @Override
    public List<Reimbursement> getAllReimbursementsGivenAuthor(int author) {
        List<Reimbursement> reimbursements = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection()){
            String sql = "SELECT * FROM ers_reimbursement WHERE reimb_author = ? ORDER BY reimb_submitted desc;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,author);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                reimbursements.add(new Reimbursement(rs.getInt(1), rs.getTimestamp(2),  rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getDouble(7), rs.getInt(8), rs.getTimestamp(9)));
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }

        return reimbursements;
    }

    @Override
    public List<Reimbursement> getAllReimbursementsGivenStatus(int status) {
        List<Reimbursement> reimbursements = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection()){
            String sql = "select * from ers_reimbursement where reimb_status_id = ? ORDER BY reimb_submitted desc";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,status);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                reimbursements.add(new Reimbursement(rs.getInt(1), rs.getTimestamp(2),  rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getDouble(7), rs.getInt(8), rs.getTimestamp(9)));
            }

        }catch (SQLException sqle){
            sqle.printStackTrace();
        }

        return reimbursements;
    }

    @Override
    public Reimbursement getReimbursementGivenReimbId(int reimbId) {
        Reimbursement reimbursement = new Reimbursement();
        try (Connection conn = ConnectionFactory.getConnection()){
            String sql = "select * from ers_reimbursement where reimb_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,reimbId);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                reimbursement = new Reimbursement(rs.getInt(1),
                        rs.getTimestamp(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getDouble(7),
                        rs.getInt(8),
                        rs.getTimestamp(9));
            }

        }catch (SQLException sqle){
            sqle.printStackTrace();
        }

        return reimbursement;
    }


    @Override
    public void createReimbursement(Reimbursement reimbursement) {
        try(Connection conn = ConnectionFactory.getConnection()){
            String sql =
                    "insert into ers_reimbursement (reimb_author, reimb_amount, reimb_type_id, reimb_description)" +
                    "values (?, ?, ?, ?);";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, reimbursement.getAuthor());
            ps.setDouble(2, reimbursement.getAmount());
            ps.setInt(3, reimbursement.getTypeId());
            ps.setString(4,reimbursement.getDescription());


            ps.executeUpdate();

        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

    @Override
    public void resolveReimbursement(Reimbursement reimbursement) {
        try(Connection conn = ConnectionFactory.getConnection()){

            String sql = "update ers_reimbursement set (reimb_status_id , reimb_resolved, reimb_resolver) = (?,now(), ?) where reimb_id = ? ;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, reimbursement.getStatus());
            ps.setInt(2, reimbursement.getResolver());
            ps.setInt(3, reimbursement.getReimbId());

            ps.executeUpdate();

        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }
}
