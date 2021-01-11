package isengard.db;

public class Adapter {

    private static int role;

    public static boolean login(String login, String pass) {
        role = 0;
        return true;
    }

    public static int getRole() {
        return role;
    }

    public static void backup() {

    }


}
