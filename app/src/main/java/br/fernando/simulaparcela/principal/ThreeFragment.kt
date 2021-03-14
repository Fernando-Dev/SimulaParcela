package br.fernando.simulaparcela.principal

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.content.getSystemService
import androidx.core.view.size
import androidx.fragment.app.Fragment
import br.fernando.simulaparcela.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_first.layout_principal
import kotlinx.android.synthetic.main.fragment_three.*
import org.w3c.dom.Text
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class ThreeFragment : Fragment() {

    var textoAtual: String = ""
    lateinit var containerResultado: LinearLayout

    lateinit var containerLinhaValores: LinearLayout

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
                    val textoSemFormatacao: String = p0.toString().replace("[${currency.symbol},.]".toRegex(), "")
//                    CONVERTENDO TEXTO EM UM TIPO DOUBLE
//                    RETIRANDO OS ESPACOS DO INICIO DA STRING
                    val textoParaDecimal = textoSemFormatacao.trim().toDouble()
//                    FORMANTANDO TEXTO EM ESTILO MOEDA
                    val textoFormatado = NumberFormat.getCurrencyInstance().format(textoParaDecimal / 100)

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
                    val textoSemFormatacao: String = p0.toString().replace("[${currency.symbol},.]".toRegex(), "")
//                    CONVERTENDO TEXTO EM UM TIPO DOUBLE
//                    RETIRANDO OS ESPACOS DO INICIO DA STRING
                    val textoParaDecimal = textoSemFormatacao.trim().toDouble()
//                    FORMANTANDO TEXTO EM ESTILO MOEDA
                    val textoFormatado = NumberFormat.getCurrencyInstance().format(textoParaDecimal / 100)

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
                    val textoSemFormatacao: String = p0.toString().replace("[${currency.symbol},.]".toRegex(), "")
//                    CONVERTENDO TEXTO EM UM TIPO DOUBLE
//                    RETIRANDO OS ESPACOS DO INICIO DA STRING
                    val textoParaDecimal = textoSemFormatacao.trim().toDouble()
//                    FORMANTANDO TEXTO EM ESTILO MOEDA
                    val textoFormatado = NumberFormat.getCurrencyInstance().format(textoParaDecimal / 100)

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
                    val textoSemFormatacao: String = p0.toString().replace("[${currency.symbol},.]".toRegex(), "")
//                    CONVERTENDO TEXTO EM UM TIPO DOUBLE
//                    RETIRANDO OS ESPACOS DO INICIO DA STRING
                    val textoParaDecimal = textoSemFormatacao.trim().toDouble()
//                    FORMANTANDO TEXTO EM ESTILO MOEDA
                    val textoFormatado = NumberFormat.getCurrencyInstance().format(textoParaDecimal / 100)

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
                    val textoSemFormatacao: String = p0.toString().replace("[${currency.symbol},.]".toRegex(), "")
//                    CONVERTENDO TEXTO EM UM TIPO DOUBLE
//                    RETIRANDO OS ESPACOS DO INICIO DA STRING
                    val textoParaDecimal = textoSemFormatacao.trim().toDouble()
//                    FORMANTANDO TEXTO EM ESTILO MOEDA
                    val textoFormatado = NumberFormat.getCurrencyInstance().format(textoParaDecimal / 100)

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

            edtSaldoDevido.setText(R.string.valor_vaio)
            edtNomeClienteA.setText("")
            edtNomeClienteB.setText("")
            edtNomeClienteC.setText("")
            edtNomeClienteD.setText("")

            edtValorA.setText(R.string.valor_vaio)
            edtValorB.setText(R.string.valor_vaio)
            edtValorC.setText(R.string.valor_vaio)
            edtValorD.setText(R.string.valor_vaio)
        }

        val btnCalcularRevita = threeView.findViewById<Button>(R.id.btn_calcular_revita)

        btnCalcularRevita.setOnClickListener {

            containerResultado.removeAllViews()

            esconderTeclado(btnCalcularRevita)

            var saldoDevidoLimpo: Boolean = false
            var nomeAVaio: Boolean = false
            var valorAVaio: Boolean = false
            var nomeBVaio: Boolean = false
            var valorBVaio: Boolean = false
            var nomeCVaio: Boolean = false
            var valorCVaio: Boolean = false
            var nomeDVaio: Boolean = false
            var valorDVaio: Boolean = false
            var textoDevido = 0.0
            if (edtSaldoDevido.text.toString().isNotBlank()) {
                textoDevido = transformaValor(edtSaldoDevido.getText().toString())
            }
            if (edtSaldoDevido.text!!.isBlank() || (textoDevido.equals(0.0))) {
                saldoDevidoLimpo = true
            }
            if (edtNomeClienteA.text!!.isBlank() || edtValorA.text!!.isBlank()) {
                valorAVaio = true
                nomeAVaio = true
            }
            if (edtNomeClienteB.text!!.isBlank() || edtValorB.text!!.isBlank()) {
                nomeBVaio = true
                valorBVaio = true
            }
            if (edtNomeClienteC.text!!.isBlank() || edtValorC.text!!.isBlank()) {
                nomeCVaio = true
                valorCVaio = true
            }
            if (edtNomeClienteD.text!!.isBlank() || edtValorD.text!!.isBlank()) {
                nomeDVaio = true
                valorDVaio = true

            }

            if (saldoDevidoLimpo &&
                (nomeAVaio && valorAVaio) &&
                (nomeBVaio && valorBVaio) &&
                (nomeCVaio && valorCVaio) &&
                (nomeDVaio && valorDVaio)
            ) {
                Snackbar.make(layout_principal, "TODOS OS CAMPOS ESTÃO VAZIOS!", Snackbar.LENGTH_LONG).show()
            } else if (!saldoDevidoLimpo &&
                (!nomeAVaio && !valorAVaio) &&
                (!nomeBVaio && !valorBVaio) &&
                (!nomeCVaio && !valorCVaio) &&
                (!nomeDVaio && !valorDVaio)
            ) {

                val edtNomeA = edtNomeClienteA.text.toString()
                val edtNomeB = edtNomeClienteB.text.toString()
                val edtNomeC = edtNomeClienteC.text.toString()
                val edtNomeD = edtNomeClienteD.text.toString()

                val valorA = transformaValor(edtValorA.text.toString())
                val valorB = transformaValor(edtValorB.text.toString())
                val valorC = transformaValor(edtValorC.text.toString())
                val valorD = transformaValor(edtValorD.text.toString())

                val valorSaldoDevido = transformaValor(edtSaldoDevido.text.toString())
                val somaTotal = calculoSomaEmprestimoQuatro(valorA, valorB, valorC, valorD)

                val resultadoA = calculoRevita(somaTotal, valorSaldoDevido, valorA)
                val resultadoB = calculoRevita(somaTotal, valorSaldoDevido, valorB)
                val resultadoC = calculoRevita(somaTotal, valorSaldoDevido, valorC)
                val resultadoD = calculoRevita(somaTotal, valorSaldoDevido, valorD)

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

                val valorTotalResultado =
                    linhaResultadoRevita.findViewById<TextView>(R.id.txt_res_revit_valor_total)

                nomeRevitResultadoA.setText(edtNomeA)
                nomeRevitResultadoB.setText(edtNomeB)
                nomeRevitResultadoC.setText(edtNomeC)
                nomeRevitResultadoD.setText(edtNomeD)

                valorRevitResultadoA.setText(arredondar(resultadoA))
                valorRevitResultadoB.setText(arredondar(resultadoB))
                valorRevitResultadoC.setText(arredondar(resultadoC))
                valorRevitResultadoD.setText(arredondar(resultadoD))

                valorTotalResultado.setText(
                    arredondar(
                        calculoSomaEmprestimoQuatro(
                            resultadoA,
                            resultadoB,
                            resultadoC,
                            resultadoD
                        )
                    )
                )

                containerResultado.addView(linhaResultadoRevita)
            } else {
                val textoValorA = transformaValor(edtValorA.text.toString())
                if (saldoDevidoLimpo) {
                    Snackbar.make(layout_principal, "SALDO DEVIDO VAZIO!", Snackbar.LENGTH_LONG).show()
                } else if ((nomeAVaio || valorAVaio) || (textoValorA.equals(0.0))
                ) {
                    Snackbar.make(layout_principal, "NOME A OU VALOR A ESTÁ VAZIO!", Snackbar.LENGTH_LONG)
                        .show()

                } else {

                    val edtNomeA = edtNomeClienteA.text.toString()
                    var edtNomeB = "sem nome"
                    var edtNomeC = "sem nome"
                    var edtNomeD = "sem nome"

                    if (!edtNomeClienteB.text.toString().isBlank()) {
                        edtNomeB = edtNomeClienteB.text.toString()
                    }
                    if (!edtNomeClienteC.text.toString().isBlank()) {
                        edtNomeC = edtNomeClienteC.text.toString()
                    }
                    if (!edtNomeClienteD.text.toString().isBlank()) {
                        edtNomeD = edtNomeClienteD.text.toString()
                    }

                    val valorA = transformaValor(edtValorA.text.toString())
                    var valorB = 0.0
                    var valorC = 0.0
                    var valorD = 0.0

                    if (!edtValorB.text.toString().isBlank() || edtValorB.text.toString().compareTo("0,00") != 0) {
                        valorB = transformaValor(edtValorB.text.toString())
                    }
                    if (!edtValorC.text.toString().isBlank() || edtValorC.text.toString().compareTo("0,00") != 0) {
                        valorC = transformaValor(edtValorC.text.toString())
                    }
                    if (!edtValorD.text.toString().isBlank() || edtValorD.text.toString().compareTo("0,00") != 0) {
                        valorD = transformaValor(edtValorD.text.toString())
                    }

                    val valorSaldoDevido = transformaValor(edtSaldoDevido.text.toString())

                    val somaTotal = calculoSomaEmprestimoQuatro(valorA, valorB, valorC, valorD)

                    val resultadoA = calculoRevita(somaTotal, valorSaldoDevido, valorA)
                    val resultadoB = calculoRevita(somaTotal, valorSaldoDevido, valorB)
                    val resultadoC = calculoRevita(somaTotal, valorSaldoDevido, valorC)
                    val resultadoD = calculoRevita(somaTotal, valorSaldoDevido, valorD)

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

                    val valorTotalResultado =
                        linhaResultadoRevita.findViewById<TextView>(R.id.txt_res_revit_valor_total)

                    nomeRevitResultadoA.setText(edtNomeA)
                    nomeRevitResultadoB.setText(edtNomeB)
                    nomeRevitResultadoC.setText(edtNomeC)
                    nomeRevitResultadoD.setText(edtNomeD)

                    valorRevitResultadoA.setText(arredondar(resultadoA))
                    valorRevitResultadoB.setText(arredondar(resultadoB))
                    valorRevitResultadoC.setText(arredondar(resultadoC))
                    valorRevitResultadoD.setText(arredondar(resultadoD))

                    valorTotalResultado.setText(
                        arredondar(
                            calculoSomaEmprestimoQuatro(
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
        val v = DecimalFormat("R$ #,##0.00").format(valor)
        return v
    }

    fun calculoSomaEmprestimoQuatro(v1: Double, v2: Double, v3: Double, v4: Double): Double {
        val soma = v1 + v2 + v3 + v4
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
        val textoSemFormatacao: String = valor.replace("[${currency.symbol},.]".toRegex(), "")
//                    CONVERTENDO TEXTO EM UM TIPO DOUBLE
//                    RETIRANDO OS ESPACOS DO INICIO DA STRING
        val textoParaDecimal = textoSemFormatacao.trim().toDouble() / 100

        return textoParaDecimal
    }


}