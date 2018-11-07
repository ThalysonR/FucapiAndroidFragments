package br.com.tcastro.trabalho2

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import br.com.tcastro.trabalho2.dao.Usuario
import br.com.tcastro.trabalho2.databinding.ActivityCadastroBinding
import br.com.tcastro.trabalho2.enums.EstadosFormCadastro
import io.reactivex.disposables.CompositeDisposable
import io.realm.kotlin.createObject
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {
    private val disposer: CompositeDisposable = CompositeDisposable()
    lateinit var cadastroViewModel: CadastroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityCadastroBinding>(this, R.layout.activity_cadastro)
        cadastroViewModel = ViewModelProviders.of(this).get(CadastroViewModel::class.java)
        binding.vm = cadastroViewModel

        validaFormulario()
        configSalvarBtn()
        cadastroViewModel.initFields()
    }

    private fun configSalvarBtn() {
        cadastro_salvar_btn.setOnClickListener {
            cadastroViewModel.realm.executeTransaction {
                val novoUsuario =
                    cadastroViewModel.realm.createObject<Usuario>(primaryKeyValue = cadastroViewModel.campoEmail.get()!!)
                novoUsuario.senha = cadastroViewModel.campoSenha.get()!!
                Toast.makeText(this, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun validaFormulario() {
        val subs = cadastroViewModel.formState.subscribe { estado ->
            cadastro_salvar_btn.isEnabled = estado == EstadosFormCadastro.Valido
            cadastro_layout_email.error = when(estado) {
                EstadosFormCadastro.UsuarioJaCadastrado -> "Usuário já cadastrado!"
                EstadosFormCadastro.UsuarioVazio -> "Campo Obrigatório"
                else -> ""
            }
            cadastro_layout_senha.error = if (estado == EstadosFormCadastro.SenhaVazio) "Campo Obrigatório" else ""
            cadastro_layout_confirmar_senha.error = if (estado == EstadosFormCadastro.ConfirmarDiferente) "Senha Inválida" else ""
        }
        disposer.add(subs)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposer.dispose()
    }
}
