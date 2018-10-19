package br.com.tcastro.trabalho2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_tabs.*

class TabsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabs)
        val adapter = PagerAdapter(supportFragmentManager)
        tabs_viewpager.adapter = adapter
    }
}
