package domain.users;

/**
 * Created by Þðà on 28.06.2016.
 */
public class Admin extends AbstractUser {

    String admin_id = "ADMIN007";

    public Admin(String userName, String secondName, String loginName, String password) {
        super(userName, secondName, loginName, password,FunctionRole.ADMIN);
    }


    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }
}
