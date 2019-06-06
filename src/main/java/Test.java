import model.dao.DaoFactory;
import model.entities.User;

public class Test {
    public static void main(String[] args) {
//        DaoFactory.getInstance().createUserDao().create(new User(3,"t","t","t","t","t","t","master",true,0L));
        System.out.println(DaoFactory.getInstance().createUserDao().findAll());
        System.out.println(DaoFactory.getInstance().createUserDao().findById(1).get());
        test("Kate1501", "Kate1501");
    }

    public static void test(String login, String password) {
        System.out.println(DaoFactory.getInstance().createUserDao().findByLoginAndPassword(login, password));
    }
}
