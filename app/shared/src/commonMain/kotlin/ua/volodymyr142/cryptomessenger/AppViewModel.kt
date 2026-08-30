package ua.volodymyr142.cryptomessenger

import androidx.lifecycle.ViewModel
import ua.volodymyr142.cryptomessenger.core.crypto.SymmetricCipher

class AppViewModel(
    val symmetricCipher: SymmetricCipher,
) : ViewModel()
