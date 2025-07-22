package business.admin.imp;

import business.admin.AdminBusiness;
import dao.admin.AdminDAO;
import dao.admin.imp.AdminDAOImp;
import model.Admin;
import validate.Validator;

public class AdminBusinessImp implements AdminBusiness {
    private AdminDAO adminDAO;

    public AdminBusinessImp(){
        adminDAO = new AdminDAOImp();
    }

    @Override
    public Admin getAdminPermission(String username, String password) {
        if (Validator.isEmpty(username) || Validator.isEmpty(password)) {
            return null;
        }
        return adminDAO.getAdminPermission(username, password);
    }
}
