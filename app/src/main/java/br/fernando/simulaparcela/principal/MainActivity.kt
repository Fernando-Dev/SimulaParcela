package br.fernando.simulaparcela.principal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.acao_sobre -> {
                startActivity(Intent(this, SobreActivity::class.java))

                true
            }
            R.id.acao_sair -> {
                finish()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }


}
