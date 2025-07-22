package dao.admin.imp;

import dao.admin.AdminDAO;
import model.Admin;
import util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class AdminDAOImp implements AdminDAO {
    @Override
    public Admin getAdminPermission(String username, String password) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        Admin admin = null;

        try {
            conn = ConnectionDB.openConnection();

            callSt = conn.prepareCall("{CALL login_admin(?,?)}");
            callSt.setString(1, username);
            callSt.setString(2, password);
            rs = callSt.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            ConnectionDB.closeConnection(conn, callSt);
        }
        return admin;
    }
}
