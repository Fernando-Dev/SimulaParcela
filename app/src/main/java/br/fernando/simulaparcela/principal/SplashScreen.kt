package br.fernando.simulaparcela.principal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.os.postDelayed
import br.fernando.simulaparcela.R
import com.rbddevs.splashy.Splashy

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSplashy()

    }

    fun setSplashy(){
        Splashy(this)
            .setLogo(R.drawable.carteira)
            .setTitle(R.string.app_name)
            .setSubTitle("")
            .setTitleColor(R.color.colorPrimary)
            .setBackgroundColor(R.color.cor_branca)
            .setFullScreen(true)
            .setTime(2000)
            .setAnimation(Splashy.Animation.SLIDE_IN_LEFT_BOTTOM,1000)
            .show()

        Splashy.onComplete(object :Splashy.OnComplete{
            override fun onComplete() {
                mostrarTelaPrincipal()
            }
        })

    }


    fun mostrarTelaPrincipal() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
