package domain.users;

/**
 * Created by Þðà on 28.06.2016.
 */
public class User extends AbstractUser {
    int startBallance = 1000;
    int totalMoneySpentCounter = 0;
    public static  float discountKoeff = 1;


//    public User(String userName, String secondName, String loginName, String password) {
//        super(userName, secondName, loginName, password, FunctionRole.SIMPLE_USER);
//        this.startBallance = 1000;
//    }
    protected User(String userName, String secondName, String loginName, String password, FunctionRole role,int startBallance) {
        super(userName, secondName, loginName, password, role);

        this.startBallance = startBallance;
    }


    public int getStartBallance() {
        return startBallance;
    }

    public void setStartBallance(int startBallance) {
        this.startBallance = startBallance;
    }

    public int getTotalMoneySpentCounter() {
        return totalMoneySpentCounter;
    }

    public void incrementMoneyCounter(int spentMoney) throws ImpossibleSimpleUserStateExeption {
        this.totalMoneySpentCounter += spentMoney;
        if(totalMoneySpentCounter>1000){

            throw new  ImpossibleSimpleUserStateExeption();
        }
    }

    public static float getDiscountKoeff() {
        return discountKoeff;
    }
//    long id;


}
final class ImpossibleSimpleUserStateExeption extends Exception{};