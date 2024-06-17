package com.example.basic

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class JasyptTest {
    private var jasypt: StandardPBEStringEncryptor? = null

    @Test
    @DisplayName("테스트 케이스 테스트")
    final fun printTestCase() {
        println(">>>>>>>>>>>>> success")
    }

    @BeforeEach
    fun before() {
        jasypt = StandardPBEStringEncryptor()

        jasypt!!.setPassword("test1")

        jasypt!!.setAlgorithm("PBEWithMD5AndDES")
    }

    @Test
    @DisplayName("jaspt 암복호화 테스트")
    final fun decodeTest() {
        var array: Array<String> =
            arrayOf(
                "a",
                "v",
                "c",
            )

        pritText(array, jasypt!!)
    }

    /**
     * Print method </br>
     * @param array Array<String>
     * @param jasypt StandardPBEStringEncryptor
     * @return void
     */
    private fun pritText(
        array: Array<String>,
        jasypt: StandardPBEStringEncryptor,
    ) {
        for (encrypt in array) {
            val encryptedText = jasypt.encrypt(encrypt)
            val decryptText = jasypt.decrypt(encryptedText)
            println("encryptedText:  $encryptedText")
            println("decode text :  $decryptText")
        }
    }
}
