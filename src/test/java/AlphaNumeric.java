import org.apache.commons.lang3.RandomStringUtils;

public class AlphaNumeric {

    public static void main(String[] args) {

        String alpha = RandomStringUtils.randomAlphabetic(3);
        String num = RandomStringUtils.randomNumeric(3);

        System.out.println(alpha+num+"@email.com");
    }
}
