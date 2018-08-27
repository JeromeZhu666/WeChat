package xin.jerome.weichat.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Check util
 *
 * @author Jerome Zhu
 * @since 2018.08.27 10:44
 */
public class CheckUtil {

    private static final String TOKEN = "jerome";

    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        List<String> strs = Arrays.asList(TOKEN,timestamp,nonce);
        // Sort and toString
        String str = strs.stream()
                .sorted()
                .collect(Collectors.joining());

        // Encrypted str use Sha1
        String sha1Str = SHA1Util.encode(str);

        return sha1Str.equals(signature);
    }

}
