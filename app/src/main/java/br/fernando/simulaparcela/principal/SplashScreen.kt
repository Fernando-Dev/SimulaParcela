package br.fernando.simulaparcela.principal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.os.postDelayed
import br.fernando.simulaparcela.R

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                mostrarTelaPrincipal()
            }
        }, 2000)
    }


    fun mostrarTelaPrincipal() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}
