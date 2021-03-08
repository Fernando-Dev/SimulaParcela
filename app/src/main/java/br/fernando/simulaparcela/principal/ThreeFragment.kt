package br.fernando.simulaparcela.principal

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import br.fernando.simulaparcela.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_first.*
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class ThreeFragment : Fragment() {

    var textoAtual: String = ""
    val valorDeCampoVazio = 10.0
    lateinit var containerResultado: LinearLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val threeView = inflater.inflate(R.layout.fragment_three, container, false)

        containerResultado = threeView.findViewById(R.id.ll_revitalizacao_resultado)

        val edtNomeClienteA = threeView.findViewById<TextInputEditText>(R.id.txt_revit_nome_cliente_a)
        val edtNomeClienteB = threeView.findViewById<TextInputEditText>(R.id.txt_revit_nome_cliente_b)
        val edtNomeClienteC = threeView.findViewById<TextInputEditText>(R.id.txt_revit_nome_cliente_c)
        val edtNomeClienteD = threeView.findViewById<TextInputEditText>(R.id.txt_revit_nome_cliente_d)

        val edtSaldoDevido = threeView.findViewById<TextInputEditText>(R.id.edt_revit_saldo_devido)
        val edtValorA = threeView.findViewById<TextInputEditText>(R.id.edt_revit_valor_emprestimo_a)
        val edtValorB = threeView.findViewById<TextInputEditText>(R.id.edt_revit_valor_emprestimo_b)
        val edtValorC = threeView.findViewById<TextInputEditText>(R.id.edt_revit_valor_emprestimo_c)
        val edtValorD = threeView.findViewById<TextInputEditText>(R.id.edt_revit_valor_emprestimo_d)

        edtValorA.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //                    COMPARANDO OS TEXTOS SE HA DIFERENCA
                if (p0.toString().compareTo(textoAtual) != 0) {
//                    REMOVENDO A ESCUTA DO METODO
                    edtValorA.removeTextChangedListener(this)
//                    PEGANDO O LOCAL
                    val local = Locale.getDefault()
//                        DEFININDO LOCAL PADRAO DA MOEDA
                    val currency = Currency.getInstance(local)
//                    RETIRANDO FORMATACAO DO TEXTO
                    var textoSemFormatacao: String = p0.toString().replace("[${currency.symbol},.]".toRegex(), "")
//                    CONVERTENDO TEXTO EM UM TIPO DOUBLE
//                    RETIRANDO OS ESPACOS DO INICIO DA STRING
                    var textoParaDecimal = textoSemFormatacao.trim().toDouble()
//                    FORMANTANDO TEXTO EM ESTILO MOEDA
                    var textoFormatado = NumberFormat.getCurrencyInstance().format(textoParaDecimal / 100)

                    textoAtual = textoFormatado
//                    SETANDO O TEXTO FORMATADO
                    edtValorA.setText(textoAtual)
//                    COLOCANDO O CURSOR NO FINAL DO TEXTO
                    edtValorA.setSelection(textoAtual.length)
//                        CHAMANDO A ESCUTA NO METODO PRINCIPAL
                    edtValorA.addTextChangedListener(this)
                }
            }
        })

        edtValorB.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //                    COMPARANDO OS TEXTOS SE HA DIFERENCA
                if (p0.toString().compareTo(textoAtual) != 0) {
//                    REMOVENDO A ESCUTA DO METODO
                    edtValorB.removeTextChangedListener(this)
//                    PEGANDO O LOCAL
                    val local = Locale.getDefault()
//                        DEFININDO LOCAL PADRAO DA MOEDA
                    val currency = Currency.getInstance(local)
//                    RETIRANDO FORMATACAO DO TEXTO
                    var textoSemFormatacao: String = p0.toString().replace("[${currency.symbol},.]".toRegex(), "")
//                    CONVERTENDO TEXTO EM UM TIPO DOUBLE
//                    RETIRANDO OS ESPACOS DO INICIO DA STRING
                    var textoParaDecimal = textoSemFormatacao.trim().toDouble()
//                    FORMANTANDO TEXTO EM ESTILO MOEDA
                    var textoFormatado = NumberFormat.getCurrencyInstance().format(textoParaDecimal / 100)

                    textoAtual = textoFormatado
//                    SETANDO O TEXTO FORMATADO
                    edtValorB.setText(textoAtual)
//                    COLOCANDO O CURSOR NO FINAL DO TEXTO
                    edtValorB.setSelection(textoAtual.length)
//                        CHAMANDO A ESCUTA NO METODO PRINCIPAL
                    edtValorB.addTextChangedListener(this)
                }
            }
        })

        edtValorC.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //                    COMPARANDO OS TEXTOS SE HA DIFERENCA
                if (p0.toString().compareTo(textoAtual) != 0) {
//                    REMOVENDO A ESCUTA DO METODO
                    edtValorC.removeTextChangedListener(this)
//                    PEGANDO O LOCAL
                    val local = Locale.getDefault()
//                        DEFININDO LOCAL PADRAO DA MOEDA
                    val currency = Currency.getInstance(local)
//                    RETIRANDO FORMATACAO DO TEXTO
                    var textoSemFormatacao: String = p0.toString().replace("[${currency.symbol},.]".toRegex(), "")
//                    CONVERTENDO TEXTO EM UM TIPO DOUBLE
//                    RETIRANDO OS ESPACOS DO INICIO DA STRING
                    var textoParaDecimal = textoSemFormatacao.trim().toDouble()
//                    FORMANTANDO TEXTO EM ESTILO MOEDA
                    var textoFormatado = NumberFormat.getCurrencyInstance().format(textoParaDecimal / 100)

                    textoAtual = textoFormatado
//                    SETANDO O TEXTO FORMATADO
                    edtValorC.setText(textoAtual)
//                    COLOCANDO O CURSOR NO FINAL DO TEXTO
                    edtValorC.setSelection(textoAtual.length)
//                        CHAMANDO A ESCUTA NO METODO PRINCIPAL
                    edtValorC.addTextChangedListener(this)
                }
            }
        })

        edtValorD.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //                    COMPARANDO OS TEXTOS SE HA DIFERENCA
                if (p0.toString().compareTo(textoAtual) != 0) {
//                    REMOVENDO A ESCUTA DO METODO
                    edtValorD.removeTextChangedListener(this)
//                    PEGANDO O LOCAL
                    val local = Locale.getDefault()
//                        DEFININDO LOCAL PADRAO DA MOEDA
                    val currency = Currency.getInstance(local)
//                    RETIRANDO FORMATACAO DO TEXTO
                    var textoSemFormatacao: String = p0.toString().replace("[${currency.symbol},.]".toRegex(), "")
//                    CONVERTENDO TEXTO EM UM TIPO DOUBLE
//                    RETIRANDO OS ESPACOS DO INICIO DA STRING
                    var textoParaDecimal = textoSemFormatacao.trim().toDouble()
//                    FORMANTANDO TEXTO EM ESTILO MOEDA
                    var textoFormatado = NumberFormat.getCurrencyInstance().format(textoParaDecimal / 100)

                    textoAtual = textoFormatado
//                    SETANDO O TEXTO FORMATADO
                    edtValorD.setText(textoAtual)
//                    COLOCANDO O CURSOR NO FINAL DO TEXTO
                    edtValorD.setSelection(textoAtual.length)
//                        CHAMANDO A ESCUTA NO METODO PRINCIPAL
                    edtValorD.addTextChangedListener(this)
                }
            }
        })

        edtSaldoDevido.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //                    COMPARANDO OS TEXTOS SE HA DIFERENCA
                if (p0.toString().compareTo(textoAtual) != 0) {
//                    REMOVENDO A ESCUTA DO METODO
                    edtSaldoDevido.removeTextChangedListener(this)
//                    PEGANDO O LOCAL
                    val local = Locale.getDefault()
//                        DEFININDO LOCAL PADRAO DA MOEDA
                    val currency = Currency.getInstance(local)
//                    RETIRANDO FORMATACAO DO TEXTO
                    var textoSemFormatacao: String = p0.toString().replace("[${currency.symbol},.]".toRegex(), "")
//                    CONVERTENDO TEXTO EM UM TIPO DOUBLE
//                    RETIRANDO OS ESPACOS DO INICIO DA STRING
                    var textoParaDecimal = textoSemFormatacao.trim().toDouble()
//                    FORMANTANDO TEXTO EM ESTILO MOEDA
                    var textoFormatado = NumberFormat.getCurrencyInstance().format(textoParaDecimal / 100)

                    textoAtual = textoFormatado
//                    SETANDO O TEXTO FORMATADO
                    edtSaldoDevido.setText(textoAtual)
//                    COLOCANDO O CURSOR NO FINAL DO TEXTO
                    edtSaldoDevido.setSelection(textoAtual.length)
//                        CHAMANDO A ESCUTA NO METODO PRINCIPAL
                    edtSaldoDevido.addTextChangedListener(this)
                }

            }
        })

        val btnLimparRevita = threeView.findViewById<Button>(R.id.btn_limpar_revita)
        btnLimparRevita.setOnClickListener {
            containerResultado.removeAllViews()
            esconderTeclado(btnLimparRevita)

            edtSaldoDevido.setText("0,00")
            edtNomeClienteA.setText("")
            edtNomeClienteB.setText("")
            edtNomeClienteC.setText("")
            edtNomeClienteD.setText("")

            edtValorA.setText("0,00")
            edtValorB.setText("0,00")
            edtValorC.setText("0,00")
            edtValorD.setText("0,00")
        }

        val btnCalcularRevita = threeView.findViewById<Button>(R.id.btn_calcular_revita)
        btnCalcularRevita.setOnClickListener {
            containerResultado.removeAllViews()
            esconderTeclado(btnCalcularRevita)
            if (edtSaldoDevido.text!!.isEmpty()) {
                Snackbar.make(layout_principal, "Saldo devido está vazio!", Snackbar.LENGTH_LONG).show()
            } else if (edtNomeClienteA.text!!.isEmpty()) {
                edtNomeClienteA.setText("Cliente A")
            } else if (edtNomeClienteB.text!!.isEmpty()) {
                edtNomeClienteB.setText("Cliente B")
            } else if (edtNomeClienteC.text!!.isEmpty()) {
                edtNomeClienteC.setText("Cliente C")
            } else if (edtNomeClienteD.text!!.isEmpty()) {
                edtNomeClienteD.setText("Cliente D")
            } else if (edtValorA.text!!.isEmpty()) {
                edtValorA.setText(valorDeCampoVazio.toString())
            } else if (edtValorB.text!!.isEmpty()) {
                edtValorB.setText(valorDeCampoVazio.toString())
            } else if (edtValorC.text!!.isEmpty()) {
                edtValorC.setText(valorDeCampoVazio.toString())
            } else if (edtValorD.text!!.isEmpty()) {
                edtValorD.setText(valorDeCampoVazio.toString())
            } else {
                var valorA = transformaValor(edtValorA.text.toString())
                var valorB = transformaValor(edtValorB.text.toString())
                var valorC = transformaValor(edtValorC.text.toString())
                var valorD = transformaValor(edtValorD.text.toString())
                var valorSaldoDevido = transformaValor(edtSaldoDevido.text.toString())
                var somaTotal = calculoSomaEmprestimoTotal(valorA, valorB, valorC, valorD)

                var resultadoA = calculoRevita(somaTotal, valorSaldoDevido, valorA)
                var resultadoB = calculoRevita(somaTotal, valorSaldoDevido, valorB)
                var resultadoC = calculoRevita(somaTotal, valorSaldoDevido, valorC)
                var resultadoD = calculoRevita(somaTotal, valorSaldoDevido, valorD)

                val infla = getContext()?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

                val linhaResultadoRevita =
                    infla.inflate(R.layout.item_revitalizacao_resultado, containerResultado, false)

                val nomeRevitResultadoA = linhaResultadoRevita.findViewById<TextView>(R.id.txt_res_revit_nome_a)
                val nomeRevitResultadoB = linhaResultadoRevita.findViewById<TextView>(R.id.txt_res_revit_nome_b)
                val nomeRevitResultadoC = linhaResultadoRevita.findViewById<TextView>(R.id.txt_res_revit_nome_c)
                val nomeRevitResultadoD = linhaResultadoRevita.findViewById<TextView>(R.id.txt_res_revit_nome_d)

                val valorRevitResultadoA = linhaResultadoRevita.findViewById<TextView>(R.id.txt_res_revit_valor_a)
                val valorRevitResultadoB = linhaResultadoRevita.findViewById<TextView>(R.id.txt_res_revit_valor_b)
                val valorRevitResultadoC = linhaResultadoRevita.findViewById<TextView>(R.id.txt_res_revit_valor_c)
                val valorRevitResultadoD = linhaResultadoRevita.findViewById<TextView>(R.id.txt_res_revit_valor_d)

                val valorTotalResultado = linhaResultadoRevita.findViewById<TextView>(R.id.txt_res_revit_valor_total)

                nomeRevitResultadoA.setText(edtNomeClienteA.text.toString())
                nomeRevitResultadoB.setText(edtNomeClienteB.text.toString())
                nomeRevitResultadoC.setText(edtNomeClienteC.text.toString())
                nomeRevitResultadoD.setText(edtNomeClienteD.text.toString())

                valorRevitResultadoA.setText(arredondar(resultadoA))
                valorRevitResultadoB.setText(arredondar(resultadoB))
                valorRevitResultadoC.setText(arredondar(resultadoC))
                valorRevitResultadoD.setText(arredondar(resultadoD))

                valorTotalResultado.setText(
                    arredondar(
                        calculoSomaEmprestimoTotal(
                            resultadoA,
                            resultadoB,
                            resultadoC,
                            resultadoD
                        )
                    )
                )

                containerResultado.addView(linhaResultadoRevita)

            }

        }

        return threeView
    }

    fun esconderTeclado(view: View) {
//        pegando o contexto
        val inputMethodManager: InputMethodManager =
            getContext()?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        verificando se o teclado esta ativo e aparecendo na tela
        if (inputMethodManager.isActive) {
//            escondendo o teclado da tela
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun arredondar(valor: Double): String {
        var v = DecimalFormat("R$ #,##0.00").format(valor)
        return v
    }

    fun calculoSomaEmprestimoTotal(v1: Double, v2: Double, v3: Double, v4: Double): Double {
        var soma = v1 + v2 + v3 + v4
        return soma
    }


    fun calculoRevita(valorSoma: Double, valorDevido: Double, valorEmprestimo: Double): Double {
        var resultado = valorDevido / valorSoma
        resultado = resultado * valorEmprestimo

        return resultado
    }

    fun transformaValor(valor: String): Double {
//        PEGANDO O LOCAL
        val local = Locale.getDefault()
//                        DEFININDO LOCAL PADRAO DA MOEDA
        val currency = Currency.getInstance(local)
//                    RETIRANDO FORMATACAO DO TEXTO
        var textoSemFormatacao: String = valor.replace("[${currency.symbol},.]".toRegex(), "")
//                    CONVERTENDO TEXTO EM UM TIPO DOUBLE
//                    RETIRANDO OS ESPACOS DO INICIO DA STRING
        var textoParaDecimal = textoSemFormatacao.trim().toDouble() / 100

        return textoParaDecimal
    }


}