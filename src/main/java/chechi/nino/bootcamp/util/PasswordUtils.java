package chechi.nino.bootcamp.util;

import java.util.UUID;

public class PasswordUtils {

    public static String generateRandomPassword() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

}
