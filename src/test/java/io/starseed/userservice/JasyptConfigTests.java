package io.starseed.userservice;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JasyptConfigTests {
    @Test
    void jasyptTest(){
        String url = "jdbc:postgresql://localhost:5432/userservice?socketTimeout=30";
        String username = "starseed";
        String password = "HimaAji42";

        System.out.println("db url:" + jasyptEncoding(url));
        System.out.println("db username:" + jasyptEncoding(username));
        System.out.println("db password:" + jasyptEncoding(password));
    }

    public String jasyptEncoding(String value) {
        //String encPassword = System.getProperty("jasypt.encryptor.password"); // 못 읽는다.
        String encPassword = "the_more_boundless_your_vision_the_more_real_you_are";
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword(encPassword);
        return pbeEnc.encrypt(value);
    }
}
