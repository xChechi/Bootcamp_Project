package chechi.nino.bootcamp.util;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class PasswordUtils {

    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "@$!%*?&";
    private static final String ALL_CHARS = LOWERCASE + UPPERCASE + DIGITS + SPECIAL_CHARS;

    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 12;

    private static final Random random = new SecureRandom();

    public static String generateRandomPassword() {
        List<String> charCategories = new ArrayList<>();
        charCategories.add(LOWERCASE);
        charCategories.add(UPPERCASE);
        charCategories.add(DIGITS);
        charCategories.add(SPECIAL_CHARS);

        StringBuilder password = new StringBuilder();
        for (int i = 0; i < MIN_LENGTH; i++) {
            String charCategory = charCategories.get(random.nextInt(charCategories.size()));
            password.append(charCategory.charAt(random.nextInt(charCategory.length())));
        }

        while (password.length() < MAX_LENGTH) {
            String charCategory = charCategories.get(random.nextInt(charCategories.size()));
            char randomChar = charCategory.charAt(random.nextInt(charCategory.length()));
            int insertPosition = random.nextInt(password.length() + 1);
            password.insert(insertPosition, randomChar);
        }

        return password.toString();
    }

}
