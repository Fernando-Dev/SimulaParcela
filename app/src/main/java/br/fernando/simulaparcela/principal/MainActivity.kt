package br.fernando.simulaparcela.principal


import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.fernando.simulaparcela.R
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.pow


class MainActivity : AppCompatActivity() {
    var taxaJuros = 0.0
    var valorEmprestimo = 0.0
    var qtdeParcelas = 0
    var valorTotal = 0.0
    lateinit var dataSimulacao: Date
    var diasDeCarencia: Int = 0
    var dia: Int = 0
    var mes: Int = 0
    var ano: Int = 0
    lateinit var edtData: Date


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val calendar = Calendar.getInstance()
        ano = calendar[Calendar.YEAR]
        mes = calendar[Calendar.MONTH]
        dia = calendar[Calendar.DAY_OF_MONTH]


        edtData = Date()

        edt_valor_emprestimo.addTextChangedListener(object : TextWatcher {
            var textoAtual: String = ""
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                    COMPARANDO OS TEXTOS SE HA DIFERENCA
                if (p0.toString().compareTo(textoAtual) != 0) {
//                    REMOVENDO A ESCUTA DO METODO
                    edt_valor_emprestimo.removeTextChangedListener(this)
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
                    edt_valor_emprestimo.setText(textoAtual)
//                    COLOCANDO O CURSOR NO FINAL DO TEXTO
                    edt_valor_emprestimo.setSelection(textoAtual.length)
//                        CHAMANDO A ESCUTA NO METODO PRINCIPAL
                    edt_valor_emprestimo.addTextChangedListener(this)
                }
            }

        })


//        pegando a data atual do sistema
        dataSimulacao = Calendar.getInstance().time
        //        formatando a data
        var dataSimulacaoFormatada = SimpleDateFormat("dd/M/yyyy", Locale.getDefault())
        txt_data_simulacao.setText(dataSimulacaoFormatada.format(dataSimulacao))

        ll_resultado.visibility = View.INVISIBLE

        btn_limpar.setOnClickListener {
            ll_resultado.visibility = View.INVISIBLE

            edt_data_primeiro_pagamento.setText("")
            edt_valor_emprestimo.setText("0,00")
            edt_quantidade_parcelas.setText("")
            edt_taxa_juros.setText("")

        }

        btn_calcular.setOnClickListener {
            if (edt_data_primeiro_pagamento.text!!.isEmpty()) {
                Toast.makeText(this, "Data da primeira parcela está vazio!", Toast.LENGTH_SHORT).show()
            } else if (edt_valor_emprestimo.text!!.isEmpty()) {
                Toast.makeText(this, "Valor do empréstimo está vazio!", Toast.LENGTH_SHORT).show()
            } else if (edt_quantidade_parcelas.text!!.isEmpty()) {
                Toast.makeText(this, "Quantidade de parcelas está vazio!", Toast.LENGTH_SHORT).show()
            } else if (edt_taxa_juros.text!!.isEmpty()) {
                Toast.makeText(this, "Taxa de juros está vazio!", Toast.LENGTH_SHORT).show()
            } else {

                var handler = Handler()
                handler.run {
                    diasDeCarencia =
                        contadorDeDiasCarencia(dataSimulacao, edt_data_primeiro_pagamento.text.toString())
                    if (diasDeCarencia >= 30) {
                        if (diasDeCarencia <= 60) {
                            taxaJuros = transformaTaxa(edt_taxa_juros.text.toString())
                            valorEmprestimo = tranformaValorEmprestimo(edt_valor_emprestimo.text.toString())
                            qtdeParcelas = transformaParcela(edt_quantidade_parcelas.text.toString())

                            valorTotal = fazerCalculoTotal(taxaJuros, qtdeParcelas, valorEmprestimo)

                            ll_resultado.visibility = View.VISIBLE

                            txt_valor_juros.setText(arredondar(fazerCalculoJuros(valorTotal, valorEmprestimo)))

                            txt_valor_parcela.setText(arredondar(fazerCalculoParcela(valorTotal, qtdeParcelas)))

                            txt_valor_total.setText(arredondar(valorTotal))
                        } else {
                            Toast.makeText(this@MainActivity, "Carencia maior que 60 dias.", Toast.LENGTH_SHORT)
                                .show()
                        }
                    } else {
                        Toast.makeText(this@MainActivity, "Carencia menor que 30 dias.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


    fun selecionarData(view: View) {
        showDialog(view.id)
    }

    override fun onCreateDialog(id: Int, args: Bundle?): Dialog? {
        if (id == img_selecionar_data.id) {
            return DatePickerDialog(this, dataListener, ano, mes, dia)
        }
        return super.onCreateDialog(id, args)
    }

    override fun onCreateDialog(id: Int): Dialog? {
        when (id) {
            img_selecionar_data.id ->
                return DatePickerDialog(this, dataListener, ano, mes, dia)
        }
        return null
    }

    private
    val dataListener =
        OnDateSetListener { view, anoSelecionado, mesSelecionado, diaSelecionado ->
            ano = anoSelecionado
            mes = mesSelecionado
            dia = diaSelecionado
            edtData = criarData(anoSelecionado, mesSelecionado, diaSelecionado)
            AtualizarData()
        }

    private fun criarData(
        anoSelecionado: Int,
        mesSelecionado: Int,
        diaSelecionado: Int
    ): Date {
        val calendar = Calendar.getInstance()
        calendar[anoSelecionado, mesSelecionado] = diaSelecionado
        return calendar.time
    }

    private fun AtualizarData() {
        edt_data_primeiro_pagamento.setText(
            StringBuilder().append(dia).append("/").append(
                mes + 1
            ).append("/").append(ano).append("")
        )
    }


    fun contadorDeDiasCarencia(dataSimulacao: Date, dataPrimeiraParcela: String): Int {

//        converter o texto em data
        var converterTextoEmData = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        var dataConvertida = converterTextoEmData.parse(dataPrimeiraParcela)
//        fazendo a subtracao
        var diffEmMil = Math.abs(dataConvertida.time - dataSimulacao.time)
//            descobrindo a qauntidade de dias
        var diff = TimeUnit.DAYS.convert(diffEmMil, TimeUnit.MILLISECONDS)

        return diff.toInt() + 1
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
//        PEGANDO O LOCAL
        val local = Locale.getDefault()
//                        DEFININDO LOCAL PADRAO DA MOEDA
        val currency = Currency.getInstance(local)
//                    RETIRANDO FORMATACAO DO TEXTO
        var textoSemFormatacao: String = valor.replace("[${currency.symbol},.]".toRegex(), "")
//                    CONVERTENDO TEXTO EM UM TIPO DOUBLE
//                    RETIRANDO OS ESPACOS DO INICIO DA STRING
        var textoParaDecimal = textoSemFormatacao.trim().toDouble()/100

        return textoParaDecimal
    }


}
