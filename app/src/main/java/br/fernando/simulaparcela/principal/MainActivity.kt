package br.fernando.simulaparcela.principal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.fernando.simulaparcela.Adapter.PagerAdapter
import br.fernando.simulaparcela.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentAdapter = PagerAdapter(supportFragmentManager)
        view_pager_main.adapter = fragmentAdapter

        tabs_main.setupWithViewPager(view_pager_main)
    }
}
