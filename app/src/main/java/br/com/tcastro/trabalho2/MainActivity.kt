package br.com.tcastro.trabalho2

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import br.com.tcastro.trabalho2.dao.Usuario
import br.com.tcastro.trabalho2.databinding.ActivityMainBinding
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.vm = mainViewModel

        main_login_btn.setOnClickListener {
            val usuario = mainViewModel.realm.where<Usuario>()
                .equalTo("email", mainViewModel.usuario.get()!!)
                .findFirst()
            if(usuario == null) Toast.makeText(this, "Usuário não encontrado", Toast.LENGTH_SHORT).show()
            else {
                if(usuario.senha == mainViewModel.senha.get()){
                    val intent = Intent(this, WelcomeActivity::class.java)
                    intent.putExtra("nome", usuario.email)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Senha incorreta", Toast.LENGTH_SHORT).show()
                }
            }
        }
        main_cadastrar_btn.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }
}
