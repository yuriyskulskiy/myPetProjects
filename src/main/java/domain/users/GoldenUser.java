package domain.users;

/**
 * Created by Þðà on 29.06.2016.
 */
public class GoldenUser extends User {

    public GoldenUser(String userName, String secondName, String loginName, String password) {
        super(userName, secondName, loginName, password,FunctionRole.GOLDEN_USER);
    }
}
