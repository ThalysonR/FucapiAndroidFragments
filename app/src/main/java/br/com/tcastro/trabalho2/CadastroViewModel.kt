package br.com.tcastro.trabalho2

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import br.com.tcastro.trabalho2.dao.Usuario
import br.com.tcastro.trabalho2.enums.EstadosFormCadastro
import br.com.tcastro.trabalho2.extensions.toRx
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import io.realm.Realm
import io.realm.kotlin.where

class CadastroViewModel: ViewModel() {
    val campoEmail: ObservableField<String> = ObservableField()
    val campoSenha: ObservableField<String> = ObservableField()
    val campoConfirmaSenha: ObservableField<String> = ObservableField()
    val realm = Realm.getDefaultInstance()

    val formState: Observable<EstadosFormCadastro> = Observables.combineLatest(campoEmail.toRx(), campoSenha.toRx(), campoConfirmaSenha.toRx()) { email, senha, confirmaSenha ->
        when {
            (email.isEmpty()) -> EstadosFormCadastro.UsuarioVazio
            (senha.isEmpty()) -> EstadosFormCadastro.SenhaVazio
            (senha != confirmaSenha) -> EstadosFormCadastro.ConfirmarDiferente
            (findUser(email) != null) -> EstadosFormCadastro.UsuarioJaCadastrado
            else -> EstadosFormCadastro.Valido
        }
    }

    fun initFields() {
        campoEmail.set("")
        campoSenha.set("")
        campoConfirmaSenha.set("")
    }

    private fun findUser(email: String): Usuario? {
        return realm.where<Usuario>()
            .equalTo("email", email)
            .findFirst()
    }
}