package br.fernando.simulaparcela

import android.os.AsyncTask
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    var taxaJuros = 0.0
    var valorEmprestimo = 0.0
    var qtdeParcelas = 0
    var valorTotal = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ll_resultado.visibility = View.INVISIBLE

        btn_limpar.setOnClickListener {

            ll_resultado.visibility = View.INVISIBLE

            edt_quantidade_parcelas.setText("")
            edt_taxa_juros.setText("")
            edt_valor_emprestimo.setText("")
        }


        btn_calcular.setOnClickListener {

            if (edt_valor_emprestimo.text!!.isEmpty()) {
                Toast.makeText(this, "Valor do empréstimo está vazio!", Toast.LENGTH_SHORT).show()
            } else if (edt_quantidade_parcelas.text!!.isEmpty()) {
                Toast.makeText(this, "Quantidade de parcelas está vazio!", Toast.LENGTH_SHORT).show()
            } else if (edt_taxa_juros.text!!.isEmpty()) {
                Toast.makeText(this, "Taxa de juros está vazio!", Toast.LENGTH_SHORT).show()
            } else {

                var handler = Handler()
                handler.run {

                    taxaJuros = transformaTaxa(edt_taxa_juros.text.toString())
                    valorEmprestimo = tranformaValorEmprestimo(edt_valor_emprestimo.text.toString())
                    qtdeParcelas = transformaParcela(edt_quantidade_parcelas.text.toString())

                    valorTotal = fazerCalculoTotal(taxaJuros, qtdeParcelas, valorEmprestimo)

                    ll_resultado.visibility = View.VISIBLE

                    txt_valor_juros.setText(arredondar(fazerCalculoJuros(valorTotal, valorEmprestimo)))

                    txt_valor_parcela.setText(arredondar(fazerCalculoParcela(valorTotal, qtdeParcelas)))

                    txt_valor_total.setText(arredondar(valorTotal))

                }
            }
        }

    }

    fun arredondar(valor: Double): String {
        var v = DecimalFormat("R$ #,##0.00").format(valor)
        return v
    }


    fun fazerCalculoJuros(valorTotal: Double, valorEmprestimo: Double): Double {
        var resultado = valorTotal - valorEmprestimo
        return resultado
    }

    fun fazerCalculoParcela(valorTotal: Double, parcela: Int): Double {
        var valorParcela = valorTotal / parcela
        return valorParcela
    }

    fun fazerCalculoTotal(taxa: Double, parcelas: Int, valor: Double): Double {

        var adicao = 1 + taxa
        var potenciacao = adicao.pow(parcelas)
        var resultadoTotal = valor * potenciacao

        return resultadoTotal
    }

    fun transformaTaxa(taxa: String): Double {
        val taxaResultante = taxa.toDouble() / 100
        return taxaResultante;
    }

    fun transformaParcela(parcela: String): Int {
        var p = parcela.toInt()
        return p
    }

    fun tranformaValorEmprestimo(valor: String): Double {
        var v = valor.toDouble()
        return v
    }


}
