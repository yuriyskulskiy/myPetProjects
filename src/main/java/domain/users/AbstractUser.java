package domain.users;

/**
 * Created by Þðà on 28.06.2016.
 */
public abstract class AbstractUser {
    protected String userName;
    protected String secondName;
    protected String loginName;
    protected String password;
    public final FunctionRole  role;

    public AbstractUser(String userName, String secondName, String loginName, String password,FunctionRole  role) {
        this.userName = userName;
        this.secondName = secondName;
        this.loginName = loginName;
        this.password = password;
        this.role=role;
    }
    enum FunctionRole{
         ADMIN,SIMPLE_USER,GOLDEN_USER,ANONYMOUS;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public FunctionRole getRole() {
        return role;
    }
}
