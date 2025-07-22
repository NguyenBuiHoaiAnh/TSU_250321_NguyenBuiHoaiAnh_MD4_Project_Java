package dao.admin;

import model.Admin;

public interface AdminDAO {
    Admin getAdminPermission(String username,String password);
}
