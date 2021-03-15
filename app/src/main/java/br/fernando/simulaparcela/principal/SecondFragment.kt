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
import android.widget.LinearLayout
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import br.fernando.simulaparcela.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_first.*
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class SecondFragment : Fragment() {

    lateinit var containerResultado: LinearLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val secondView = inflater.inflate(R.layout.fragment_second, container, false)

        containerResultado = secondView.findViewById(R.id.ll_distribuicao_resultado)

        val txtNomeA = secondView.findViewById<TextInputEditText>(R.id.txt_nome_cliente_a)
        val txtNomeB = secondView.findViewById<TextInputEditText>(R.id.txt_nome_cliente_b)
        val txtNomeC = secondView.findViewById<TextInputEditText>(R.id.txt_nome_cliente_c)
        val txtNomeD = secondView.findViewById<TextInputEditText>(R.id.txt_nome_cliente_d)

        val edtValorA = secondView.findViewById<TextInputEditText>(R.id.edt_valor_emprestimo_a)
        val edtValorB = secondView.findViewById<TextInputEditText>(R.id.edt_valor_emprestimo_b)
        val edtValorC = secondView.findViewById<TextInputEditText>(R.id.edt_valor_emprestimo_c)
        val edtValorD = secondView.findViewById<TextInputEditText>(R.id.edt_valor_emprestimo_d)

        val edtValorParcela = secondView.findViewById<TextInputEditText>(R.id.edt_valor_parcela)
        val edtQtdeParcela = secondView.findViewById<TextInputEditText>(R.id.edt_quantidade_parcela)

        val btnCalcularDistribuicao = secondView.findViewById<Button>(R.id.btn_calcular_distribuicao)
        val btnLimparDistribuicao = secondView.findViewById<Button>(R.id.btn_limpar_distribuicao)

        edtValorA.addTextChangedListener(object : TextWatcher {
            var textoAtual: String = ""
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
            var textoAtual: String = ""
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
            var textoAtual: String = ""
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
            var textoAtual: String = ""
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

        edtValorParcela.addTextChangedListener(object : TextWatcher {
            var textoAtual: String = ""
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //                    COMPARANDO OS TEXTOS SE HA DIFERENCA
                if (p0.toString().compareTo(textoAtual) != 0) {
//                    REMOVENDO A ESCUTA DO METODO
                    edtValorParcela.removeTextChangedListener(this)
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
                    edtValorParcela.setText(textoAtual)
//                    COLOCANDO O CURSOR NO FINAL DO TEXTO
                    edtValorParcela.setSelection(textoAtual.length)
//                        CHAMANDO A ESCUTA NO METODO PRINCIPAL
                    edtValorParcela.addTextChangedListener(this)

                }
            }
        })

        btnLimparDistribuicao.setOnClickListener {

            esconderTeclado(secondView)

            containerResultado.removeAllViews()

            txtNomeA.setText("")
            txtNomeB.setText("")
            txtNomeC.setText("")
            txtNomeD.setText("")

            edtValorA.setText(R.string.valor_vaio)
            edtValorB.setText(R.string.valor_vaio)
            edtValorC.setText(R.string.valor_vaio)
            edtValorD.setText(R.string.valor_vaio)
            edtValorParcela.setText(R.string.valor_vaio)

            edtQtdeParcela.setText("")
        }

        btnCalcularDistribuicao.setOnClickListener {
            esconderTeclado(secondView)

            containerResultado.removeAllViews()

            val txtValorA = transformaValor(edtValorA.text.toString())
            val txtValorB = transformaValor(edtValorB.text.toString())
            val txtValorC = transformaValor(edtValorC.text.toString())
            val txtValorD = transformaValor(edtValorD.text.toString())
            val txtValorParcela = transformaValor(edtValorParcela.text.toString())

            if (txtNomeA.text.toString().isBlank() || edtValorA.text.toString().isBlank() || (txtValorA.equals(0.0))) {
                Snackbar.make(layout_principal, "NOME A OU VALOR A ESTÁ VAZIO", Snackbar.LENGTH_LONG).show()
            } else if (txtNomeB.text.toString().isBlank() || edtValorB.text.toString().isBlank() || (txtValorB.equals(
                    0.0
                ))
            ) {
                Snackbar.make(layout_principal, "NOME B OU VALOR B ESTÁ VAZIO", Snackbar.LENGTH_LONG).show()
            } else if (txtNomeC.text.toString().isBlank() || edtValorC.text.toString().isBlank() || (txtValorC.equals(
                    0.0
                ))
            ) {
                Snackbar.make(layout_principal, "NOME C OU VALOR C ESTÁ VAZIO", Snackbar.LENGTH_LONG).show()
            } else if (txtNomeD.text.toString().isBlank() || edtValorD.text.toString().isBlank() || (txtValorD.equals(
                    0.0
                ))
            ) {
                Snackbar.make(layout_principal, "NOME D OU VALOR D ESTÁ VAZIO", Snackbar.LENGTH_LONG).show()
            } else if (edtValorParcela.text.toString().isBlank() || (txtValorParcela.equals(0.0))) {
                Snackbar.make(layout_principal, "VALOR DA PARCELA ESTÁ VAZIO", Snackbar.LENGTH_LONG).show()

            }else{

            }

        }






        return secondView
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