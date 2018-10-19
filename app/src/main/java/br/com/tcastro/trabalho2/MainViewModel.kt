package br.com.tcastro.trabalho2

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField

class MainViewModel: ViewModel() {
    var usuario = ObservableField<String>()
    var senha = ObservableField<String>()
}