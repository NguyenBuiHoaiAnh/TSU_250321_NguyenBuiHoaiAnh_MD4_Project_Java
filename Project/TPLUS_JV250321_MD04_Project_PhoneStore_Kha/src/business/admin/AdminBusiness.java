package business.admin;

import model.Admin;

public interface AdminBusiness {
    Admin getAdminPermission(String username, String password);
}
