package br.fernando.simulaparcela.principal


import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.fernando.simulaparcela.R
import br.fernando.simulaparcela.utilitario.MascaraUtilitario
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

    var FORMATAR_NUMERO_CPF = "###.###.###-##"
    var FORMATAR_NUMERO_CELULAR = "(###)#####-####"
    var FORMATAR_NUMERO_CEP = "#####-###"
    var FORMATAR_DATA = "##/##/####"
    var FORMATAR_HORA = "##:##"
    var FORMATAR_VALOR_MONETARIO = "R$ #.###.###,##"
    var FORMATAR_TAXA_JUROS = "#,##%"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val calendar = Calendar.getInstance()
        ano = calendar[Calendar.YEAR]
        mes = calendar[Calendar.MONTH]
        dia = calendar[Calendar.DAY_OF_MONTH]


        edtData = Date()

//          formatando os campos

//        edt_valor_emprestimo.addTextChangedListener(
//            mascaraCampo(
//                edt_valor_emprestimo,
//                FORMATAR_VALOR_MONETARIO
//            )
//        )
        var ocorrente = ""
        edt_valor_emprestimo.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val texto = p0.toString()

                if (texto != ocorrente) {
                    edt_valor_emprestimo.removeTextChangedListener(this)

                    val local = Locale.getDefault()

                    val currency = Currency.getInstance(local)
                    val limpaTexto: String = texto.replace("[${currency.symbol},.]".toRegex(), "")
                    var convertida:Double
                    try {
                        convertida = limpaTexto.toDouble()
                    }catch (e:NumberFormatException){
                        convertida = 0.00
                    }
                    val formatador = NumberFormat.getCurrencyInstance(local)
                    formatador.maximumFractionDigits
                    val formatada = formatador.format(convertida / 100)

                    ocorrente = formatada
                    edt_valor_emprestimo.setText(formatada)
                    edt_valor_emprestimo.setSelection(formatada.length)
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
            edt_quantidade_parcelas.setText("")
            edt_taxa_juros.setText("")
            edt_valor_emprestimo.setText("")
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
                            Toast.makeText(this@MainActivity, "Carencia maior que 60 dias.", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@MainActivity, "Carencia menor que 30 dias.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

    fun mascaraCampo(editText: EditText, mascara: String): TextWatcher {

        return object : TextWatcher {

            var estaAtualizando: Boolean = false;
            var textoAntigo: String = "";

            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                var str: String = semMascaraCampo(p0.toString())

                var masc = ""

                if (estaAtualizando) {
                    textoAntigo = str
                    estaAtualizando = false

                    return
                }
                var i = 0

                for (m in mascara.toCharArray()) {
                    if (m != '#' && str.length > textoAntigo.length) {
                        masc += m
                        continue
                    }
                    try {
                        masc += str[i]
                    } catch (e: Exception) {
                        break
                    }
                    i++
                }
                estaAtualizando = true
                editText.setText(mascara)
                editText.setSelection(mascara.length)

            }
        }

    }


    fun semMascaraCampo(s: String): String {
        return s.replace("[.]".toRegex(), "").replace("[-]".toRegex(), "").replace("[/]".toRegex(), "")
            .replace("[(]".toRegex(), "").replace("[ ]".toRegex(), "").replace("[:]".toRegex(), "")
            .replace("[)]".toRegex(), "").replace("[R$]".toRegex(), "").replace("[%]".toRegex(), "")
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

    private val dataListener =
        OnDateSetListener { view, anoSelecionado, mesSelecionado, diaSelecionado ->
            ano = anoSelecionado
            mes = mesSelecionado
            dia = diaSelecionado
            edtData = criarData(anoSelecionado, mesSelecionado, diaSelecionado)
            AtualizarData()
        }

    private fun criarData(anoSelecionado: Int, mesSelecionado: Int, diaSelecionado: Int): Date {
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
        var v = valor.toDouble()
        return v
    }


}
