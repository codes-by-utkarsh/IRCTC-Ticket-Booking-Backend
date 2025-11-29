package ticket.booking.utils;

import org.mindrot.jbcrypt.BCrypt;

public class UserServiceUtils
{
    public static String hashPassword(String password)
    {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public static boolean checkPassword(String hashPassword, String password)
    {
        return BCrypt.checkpw(password, hashPassword);
    }
}
