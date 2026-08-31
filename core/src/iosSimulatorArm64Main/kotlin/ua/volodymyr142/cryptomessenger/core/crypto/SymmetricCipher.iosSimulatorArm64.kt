package ua.volodymyr142.cryptomessenger.core.crypto

actual object SymmetricCipher {
    actual fun encrypt(
        key: ByteArray,
        iv: ByteArray,
        plaintext: ByteArray,
        aad: ByteArray,
    ): ByteArray {
        throw UnsupportedOperationException("AES-GCM not yet implemented for iosSimulatorArm64 target; see issue #5 follow-up")
    }

    actual fun decrypt(
        key: ByteArray,
        iv: ByteArray,
        ciphertextWithTag: ByteArray,
        aad: ByteArray,
    ): ByteArray {
        throw UnsupportedOperationException("AES-GCM not yet implemented for iosSimulatorArm64 target; see issue #5 follow-up")
    }
}
