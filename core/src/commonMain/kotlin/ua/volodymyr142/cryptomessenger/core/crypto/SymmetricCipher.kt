package ua.volodymyr142.cryptomessenger.core.crypto

/**
 * Low-level AES-256-GCM primitive. Caller supplies `key`, `iv`, and `aad` explicitly so this
 * type can be exercised directly against NIST GCM known-answer test vectors, which specify a
 * fixed IV per vector.
 *
 * This primitive does NOT generate its own IV and provides NO guarantee of nonce uniqueness.
 * Reusing a `(key, iv)` pair breaks AES-GCM's confidentiality and authenticity entirely. Callers
 * are responsible for supplying a never-repeating `(key, iv)` pair. The only sanctioned caller in
 * this codebase is the ratchet (see #7), which derives a fresh key/IV deterministically per
 * message from ratchet state.
 */
expect object SymmetricCipher {

    fun encrypt(key: ByteArray, iv: ByteArray, plaintext: ByteArray, aad: ByteArray): ByteArray
    
    fun decrypt(key: ByteArray, iv: ByteArray, ciphertextWithTag: ByteArray, aad: ByteArray): ByteArray
}