package com.nhnacademy.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.encrypt.TextEncryptor;

@SpringBootTest(
        properties={"CONFIG_ENCRYPT_KEY=my-secret-key"}
)
class ConfigApplicationTests {

    @Autowired
    private TextEncryptor textEncryptor;

    @Value("${encrypt.key}")
    private String encryptKey;

    /**
     * encrypt.key 값이 정상적으로 주입되는지 확인하는 테스트 메서드
     */
    @Test
    @DisplayName("encrypt.key 값 확인")
    void check_encrypt_key() {
        System.out.println("=======================================");
        System.out.println("encrypt.key: '"+encryptKey+"'");
        System.out.println("=======================================");
    }

    /**
     * 문자열을 암호화하는 테스트 메서드
     * originalText에 암호화하고 싶은 문자열을 넣고 실행하면 암호화된 문자열이 출력됨
     * {cipher} 접두사는 암호화된 문자열임을 표시해주는 용도로, yml이나 properties 파일에 암호화된 문자열을 넣을 때 사용
     */
    @Test
    @DisplayName("암호화 테스트")
    void encryptor_encode_test() {
        String originalText="my-secret-password";

        String encryptedText= textEncryptor.encrypt(originalText);

        System.out.println("=======================================");
        System.out.println("암호화된 텍스트: '{cipher}"+encryptedText+"'");
        System.out.println("=======================================");
    }

    /**
     * 암호화된 텍스트를 복호화하는 테스트 메서드
     * 앞에 붙은 {cipher}는 암호화된 문자열임을 표시해주는 접두사로, 복호화 시에는 제거하고 사용
     */
    @Test
    @DisplayName("복호화 테스트")
    void encryptor_decode_test() {
        String encryptedText="a9fec3f6402cbdbccac4ebb388645777e0868f070cc049489e7843c3ad2ded0ee2db51a209238af2e9b912da953664e1";

        String decryptedText= textEncryptor.decrypt(encryptedText);

        System.out.println("=======================================");
        System.out.println("복호화된 텍스트: '"+decryptedText+"'");
        System.out.println("=======================================");
    }
}
