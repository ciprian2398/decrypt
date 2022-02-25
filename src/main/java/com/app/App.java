package com.app;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

public class App {

    public static String encryptorPassword = "";

    public static void main(String[] args) {
        if (args.length > 2) {
            String command = args[0];
            encryptorPassword = args[1];
            String token = args[2];

            if (command.equalsIgnoreCase("encrypt")) {
                System.out.println(encrypt(token));
            } else if (command.equalsIgnoreCase("decrypt")) {
                System.out.println(decrypt(token));
            } else {
                printUsage();
            }

            encryptorPassword = args[0];
        } else {
            printUsage();
        }
    }

    private static void printUsage() {
        System.out.println("usage:");
        System.out.println("\tdecrypt [encryptor password] [token]");
        System.out.println("\tencrypt [encryptor password] [token]");
    }

    private static StandardPBEStringEncryptor createEncryptor() {
        final StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(encryptorPassword);
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

