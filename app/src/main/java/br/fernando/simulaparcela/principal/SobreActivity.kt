package br.fernando.simulaparcela.principal

import android.R.color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.fernando.simulaparcela.R
import mehdi.sakout.aboutpage.AboutPage
import mehdi.sakout.aboutpage.Element
import java.util.*



class SobreActivity : AppCompatActivity() {

    var adsElement = Element()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mostraTelaSobre())

    }

    fun mostraTelaSobre():View{
        val aboutPage = AboutPage(this)
            .setDescription("O App Simula Parcela foi desenvolvido para ajudar o Agente Prospera no desenvolvimento de suas atividades realizadas diariamente. A função empréstimo ajudará na simulação de empréstimos e no compartilhamento destas informações de forma mais ágil. A função distribuição ajudará na comunicação referente aos valores liberados pelo banco aos clientes, também de forma mais fácil e simples. E por último na função revitalização o Agente Prospera terá como descobrir de forma mais rápida o saldo devedor de cada cliente, assim facilitando na conclusão desta tarefa.")
            .isRTL(false)
            .setImage(R.drawable.carteira)
            .addItem(Element().setTitle("Versão 1.0"))
            .addItem(adsElement)
            .addGroup("Entre em contato")
            .addEmail("fernando_dev@outlook.com.br")
            .addWebsite("https://www.linkedin.com/in/fernando-nascimento-7280b2b6/")
            .addPlayStore("br.fernando.simulaparcela")
            .addItem(getCopyRightsElement())
            .create()

        return aboutPage
    }


    fun getCopyRightsElement(): Element? {
        val copyRightsElement = Element()
        val copyrights = String.format(
            getString(R.string.copy_right),
            Calendar.getInstance().get(Calendar.YEAR)
        )
        copyRightsElement.setTitle(copyrights)
        copyRightsElement.setIconTint(mehdi.sakout.aboutpage.R.color.about_item_icon_color)
        copyRightsElement.setIconNightTint(color.white)
        copyRightsElement.setGravity(Gravity.CENTER)
        copyRightsElement.setOnClickListener(View.OnClickListener {
            Toast.makeText(
                this,
                copyrights,
                Toast.LENGTH_SHORT
            ).show()
        })
        return copyRightsElement
    }


}
