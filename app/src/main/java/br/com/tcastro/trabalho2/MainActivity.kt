package br.com.tcastro.trabalho2

import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import br.com.tcastro.trabalho2.dao.Usuario
import br.com.tcastro.trabalho2.databinding.ActivityMainBinding
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MainViewModel::class.java)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.vm = vm
        realm = Realm.getDefaultInstance()
        realm.beginTransaction()
//        val usuario = realm.createObject<Usuario>()
//        usuario.email = "Thalyson"
//        usuario.senha = "Thalyson"
//        realm.commitTransaction()

        main_login_btn.setOnClickListener {
//            Toast.makeText(this, vm.usuario.get(), Toast.LENGTH_SHORT).show()
//            val intent = Intent(this, WelcomeActivity::class.java)
//            intent.putExtra("nome", vm.usuario.get().toString())
//            startActivity(intent)
            val user = realm.where<Usuario>()
                .contains("email", "Tha")
                .findFirst()
            Toast.makeText(this, user?.email, Toast.LENGTH_SHORT).show()
        }
    }
}
