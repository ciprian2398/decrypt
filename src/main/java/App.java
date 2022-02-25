import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

public class App {
    public static void main(String[] args) {
        System.out.println(encrypt("hello"));

        //todo change the "test" to your token
        System.out.println(decrypt("test"));
    }

    private static StandardPBEStringEncryptor createEncryptor() {
        final StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("jasypt.encryptor.password");
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }


    public static String decrypt(final String data) {
        return createEncryptor().decrypt(data);
    }


    public static String encrypt(final String plainText) {
        return createEncryptor().encrypt(plainText);
    }
}

