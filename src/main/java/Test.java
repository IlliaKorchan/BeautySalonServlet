import model.dao.DaoFactory;

public class Test {
    public static void main(String[] args) {
        System.out.println(DaoFactory.getInstance().createUserDao().findAll());
    }
}
