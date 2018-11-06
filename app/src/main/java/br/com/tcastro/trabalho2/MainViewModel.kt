package br.com.tcastro.trabalho2

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import io.realm.Realm

class MainViewModel: ViewModel() {
    val realm = Realm.getDefaultInstance()
    var usuario = ObservableField<String>()
    var senha = ObservableField<String>()
}