package br.com.tcastro.trabalho2

import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import br.com.tcastro.trabalho2.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val vm = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MainViewModel::class.java)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.vm = vm

        main_login_btn.setOnClickListener {
//            Toast.makeText(this, vm.usuario.get(), Toast.LENGTH_SHORT).show()
            val intent = Intent(this, WelcomeActivity::class.java)
            intent.putExtra("nome", vm.usuario.get().toString())
            startActivity(intent)
        }
    }
}
