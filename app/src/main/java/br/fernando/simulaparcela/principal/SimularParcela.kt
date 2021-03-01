package br.fernando.simulaparcela.principal


import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.Layout
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import br.fernando.simulaparcela.R
import br.fernando.simulaparcela.utilitario.TaxaJurosGrupo
import br.fernando.simulaparcela.utilitario.TaxaJurosIndividual
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_parcela_resultado.*
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.pow


class SimularParcela : AppCompatActivity() {
    lateinit var containerResultado: LinearLayout
    var taxaJuros = 0.0
    var tac = 0.03
    var valorEmprestimo = 0.0
    var qtdeParcelas = 0
    var qtdeDiasOPeracao = 0
    var valorTotal = 0.0
    lateinit var dataSimulacao: Date
    var diasDeCarencia: Int = 0
    var dia: Int = 0
    var mes: Int = 0
    var ano: Int = 0
    val trintaDias = 30
    val sessentaDias = 60
    lateinit var edtData: Date
    lateinit var taxaJurosGrupo: TaxaJurosGrupo
    lateinit var taxaJurosIndividual: TaxaJurosIndividual

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taxaJurosGrupo = TaxaJurosGrupo(this)
        taxaJurosIndividual = TaxaJurosIndividual(this)

        containerResultado = findViewById(R.id.ll_resultado)

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



        btn_limpar.setOnClickListener {

            edt_data_primeiro_pagamento.setText("")
            edt_valor_emprestimo.setText("0,00")
            edt_quantidade_parcelas.setText("")

            containerResultado.removeAllViews()

        }

        btn_calcular.setOnClickListener {
            var texto = edt_valor_emprestimo.text.toString()
            texto = texto.replace("[R$]".toRegex(), "").trim()
            if (edt_data_primeiro_pagamento.text!!.isEmpty()) {
                Snackbar.make(layout_principal, "DATA DA PRIMEIRA PARCELA ESTÁ VAZIA", Snackbar.LENGTH_LONG).show()
            } else if (texto.isEmpty()) {
                Snackbar.make(layout_principal, "VALOR DO EMPRÉSTIMO ESTÁ VAZIO", Snackbar.LENGTH_LONG).show()
            } else if (texto.compareTo("0,00") == 0) {
                Snackbar.make(layout_principal, "VALOR DO EMPRÉSTIMO ESTÁ VAZIO", Snackbar.LENGTH_LONG).show()
            } else if (edt_quantidade_parcelas.text!!.isEmpty()) {
                Snackbar.make(layout_principal, "QUANTIDADE DE PARCELAS ESTÁ VAZIO", Snackbar.LENGTH_LONG).show()
            } else {

                var handler = Handler()
                handler.run {

                    containerResultado.removeAllViews()
                    diasDeCarencia =
                        contadorDeDiasCarencia(dataSimulacao, edt_data_primeiro_pagamento.text.toString())
                    if (diasDeCarencia >= trintaDias) {
                        if (diasDeCarencia <= sessentaDias) {
                            if (radio_button_grupo.isChecked) {
                                taxaJuros = taxaJurosGrupo.buscarTaxa(diasDeCarencia)
                                taxaJuros = transformaTaxa(taxaJuros)
                                valorEmprestimo = tranformaValorEmprestimo(edt_valor_emprestimo.text.toString())
                                qtdeDiasOPeracao = transformaParcela(edt_quantidade_parcelas.text.toString())
                                qtdeParcelas = edt_quantidade_parcelas.text.toString().toInt()
                                valorTotal = fazerCalculoTotal(taxaJuros, qtdeDiasOPeracao, valorEmprestimo)

//                              layout inflado dinamicamente
                                var inflater: LayoutInflater = LayoutInflater.from(applicationContext)

                                var linhaResultado: View =
                                    inflater.inflate(R.layout.item_parcela_resultado, containerResultado, false)

                                var txtValorEmprestimo = linhaResultado.findViewById<TextView>(R.id.txt_valor_emprestimo)

                                txtValorEmprestimo.setText(arredondar(valorEmprestimo))

                                var txtVencimento = linhaResultado.findViewById<TextView>(R.id.txt_vencimento)

                                txtVencimento.setText(edt_data_primeiro_pagamento.text.toString())

                                var txtCarencia = linhaResultado.findViewById<TextView>(R.id.txt_carencia)

                                txtCarencia.setText(diasDeCarencia.toString() + " dias")

                                var txtQtdeParcela = linhaResultado.findViewById<TextView>(R.id.txt_qtde_parcela)

                                txtQtdeParcela.setText(qtdeParcelas.toString())

                                var txtValorParcela = linhaResultado.findViewById<TextView>(R.id.txt_valor_parcela)

                                txtValorParcela.setText(arredondar(fazerCalculoParcela(valorTotal, qtdeParcelas)))

                                var txtValorTotal = linhaResultado.findViewById<TextView>(R.id.txt_valor_total)

                                txtValorTotal.setText(arredondar(valorTotal))

                                containerResultado.addView(linhaResultado)

                            } else if (radio_button_individual.isChecked) {
                                taxaJuros = taxaJurosIndividual.buscarTaxa(diasDeCarencia)
                                taxaJuros = transformaTaxa(taxaJuros)
                                valorEmprestimo = tranformaValorEmprestimo(edt_valor_emprestimo.text.toString())
                                qtdeDiasOPeracao = transformaParcela(edt_quantidade_parcelas.text.toString())
                                qtdeParcelas = edt_quantidade_parcelas.text.toString().toInt()

                                valorTotal = fazerCalculoTotal(taxaJuros, qtdeDiasOPeracao, valorEmprestimo)

                                //     layout inflado dinamicamente
                                var inflater: LayoutInflater = LayoutInflater.from(applicationContext)

                                var linhaResultado: View =
                                    inflater.inflate(R.layout.item_parcela_resultado, containerResultado, false)

                                var txtValorEmprestimo = linhaResultado.findViewById<TextView>(R.id.txt_valor_emprestimo)

                                txtValorEmprestimo.setText(arredondar(valorEmprestimo))

                                var txtVencimento = linhaResultado.findViewById<TextView>(R.id.txt_vencimento)

                                txtVencimento.setText(edt_data_primeiro_pagamento.text.toString())

                                var txtCarencia = linhaResultado.findViewById<TextView>(R.id.txt_carencia)

                                txtCarencia.setText(diasDeCarencia.toString() + " dias")

                                var txtQtdeParcela = linhaResultado.findViewById<TextView>(R.id.txt_qtde_parcela)

                                txtQtdeParcela.setText(qtdeParcelas.toString())

                                var txtValorParcela = linhaResultado.findViewById<TextView>(R.id.txt_valor_parcela)

                                txtValorParcela.setText(arredondar(fazerCalculoParcela(valorTotal, qtdeParcelas)))

                                var txtValorTotal = linhaResultado.findViewById<TextView>(R.id.txt_valor_total)

                                txtValorTotal.setText(arredondar(valorTotal))

                                containerResultado.addView(linhaResultado)
                            }
                        } else {
                            Snackbar.make(layout_principal, "CARÊNCIA MAIOR QUE 60 DIAS", Snackbar.LENGTH_LONG).show()
                        }
                    } else {
                        Snackbar.make(layout_principal, "CARÊNCIA MENOR QUE 30 DIAS", Snackbar.LENGTH_LONG).show()
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
        var valorNovo = valor * tac
        valorNovo = valor + valorNovo
        var adicao = 1 + taxa
        var potenciacao = adicao.pow(parcelas)
        var resultadoTotal = valorNovo * potenciacao

        return resultadoTotal
    }

    fun transformaTaxa(taxa: Double): Double {
        var taxaResultante = taxa / 100
        taxaResultante = taxaResultante / 30
        return taxaResultante;
    }

    fun transformaParcela(parcela: String): Int {
        var p = parcela.toInt()
        p = p * trintaDias
        p = p + diasDeCarencia
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
        var textoParaDecimal = textoSemFormatacao.trim().toDouble() / 100

        return textoParaDecimal
    }


}
