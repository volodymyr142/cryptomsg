package ua.volodymyr142.cryptomessenger.core.crypto

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertFailsWith

class SymmetricCipherTest {

    // NIST GCM AES-256 known-answer vectors, verified independently against Python's
    // `cryptography` AESGCM before being copied here.
    private data class Vector(
        val key: String,
        val nonce: String,
        val plaintext: String,
        val aad: String,
        val result: String, // ciphertext || tag, as returned by SymmetricCipher.encrypt
    )

    private val vectors = listOf(
        Vector(
            key = "feffe9928665731c6d6a8f9467308308feffe9928665731c6d6a8f9467308308",
            nonce = "54cc7dc2c37ec006bcc6d1da",
            plaintext = "007c5e5b3e59df24a7c355584fc1518d",
            aad = "",
            result = "d50b9e252b70945d4240d351677eb10f937cdaef6f2822b6a3191654ba41b197",
        ),
        Vector(
            key = "feffe9928665731c6d6a8f9467308308feffe9928665731c6d6a8f9467308308",
            nonce = "e1934f5db57cc983e6b180e7",
            plaintext = "73ed042327f70fe9c572a61545eda8b2a0c6e1d6c291ef19248e973aee6c312012f490c2c6f6166f4a59431e182663fcaea05a",
            aad = "0a8a18a7150e940c3d87b38e73baee9a5c049ee21795663e264b694a949822b639092d0e67015e86363583fcf0ca645af9f43375f05fdb4ce84f411dcbca73c2220dea03a20115d2e51398344b16bee1ed7c499b353d6c597af8",
            result = "fc1ae2b5dcd2c4176c3f538b4c3cc21197f79e608cc3730167936382e4b1e5a7b75ae1678bcebd876705477eb0e0fdbbcda92fb9a0dc58c8d8f84fb590e0422e6077ef",
        ),
    )

    @Test
    fun `encrypt matches NIST GCM known-answer vectors`() {
        for (v in vectors) {
            val actual = SymmetricCipher.encrypt(
                key = v.key.hexToByteArray(),
                iv = v.nonce.hexToByteArray(),
                plaintext = v.plaintext.hexToByteArray(),
                aad = v.aad.hexToByteArray(),
            )
            assertContentEquals(v.result.hexToByteArray(), actual)
        }
    }

    @Test
    fun `decrypt matches NIST GCM known-answer vectors`() {
        for (v in vectors) {
            val actual = SymmetricCipher.decrypt(
                key = v.key.hexToByteArray(),
                iv = v.nonce.hexToByteArray(),
                ciphertextWithTag = v.result.hexToByteArray(),
                aad = v.aad.hexToByteArray(),
            )
            assertContentEquals(v.plaintext.hexToByteArray(), actual)
        }
    }

    @Test
    fun `decrypt with wrong key throws`() {
        val v = vectors[0]
        val wrongKey = v.key.hexToByteArray().also { it[0] = (it[0] + 1).toByte() }
        assertFailsWith<Exception> {
            SymmetricCipher.decrypt(wrongKey, v.nonce.hexToByteArray(), v.result.hexToByteArray(), v.aad.hexToByteArray())
        }
    }

    @Test
    fun `decrypt with wrong iv throws`() {
        val v = vectors[0]
        val wrongIv = v.nonce.hexToByteArray().also { it[0] = (it[0] + 1).toByte() }
        assertFailsWith<Exception> {
            SymmetricCipher.decrypt(v.key.hexToByteArray(), wrongIv, v.result.hexToByteArray(), v.aad.hexToByteArray())
        }
    }

    @Test
    fun `decrypt with wrong aad throws`() {
        val v = vectors[1] // has non-empty aad
        val wrongAad = v.aad.hexToByteArray().also { it[0] = (it[0] + 1).toByte() }
        assertFailsWith<Exception> {
            SymmetricCipher.decrypt(v.key.hexToByteArray(), v.nonce.hexToByteArray(), v.result.hexToByteArray(), wrongAad)
        }
    }

    @Test
    fun `decrypt with flipped ciphertext bit throws`() {
        val v = vectors[0]
        val tampered = v.result.hexToByteArray().also { it[0] = (it[0].toInt() xor 1).toByte() }
        assertFailsWith<Exception> {
            SymmetricCipher.decrypt(v.key.hexToByteArray(), v.nonce.hexToByteArray(), tampered, v.aad.hexToByteArray())
        }
    }
}