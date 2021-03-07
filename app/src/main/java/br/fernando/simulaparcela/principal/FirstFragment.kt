package br.fernando.simulaparcela.principal

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.widget.doBeforeTextChanged
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import br.fernando.simulaparcela.R
import br.fernando.simulaparcela.utilitario.TaxaJurosGrupo
import br.fernando.simulaparcela.utilitario.TaxaJurosIndividual
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_first.*
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.pow

class FirstFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    lateinit var containerResultado: LinearLayout
    lateinit var txtDataPrimeiroPagamento: EditText
    val taxaNominalGrupo = "3.0% a.m."
    val taxaNominalIndividual = "3.5% a.m."
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
    lateinit var taxaJurosGrupo: TaxaJurosGrupo
    lateinit var taxaJurosIndividual: TaxaJurosIndividual


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val firstView = inflater.inflate(R.layout.fragment_first, container, false)

        taxaJurosGrupo = TaxaJurosGrupo(activity!!.applicationContext)
        taxaJurosIndividual = TaxaJurosIndividual(activity!!.applicationContext)

        containerResultado = firstView.findViewById(R.id.ll_resultado)

        val imgCalendario = firstView.findViewById<ImageView>(R.id.img_selecionar_data)

        imgCalendario.setOnClickListener {
        esconderTeclado(imgCalendario)
            //            buscando a data atual
            val calendar = Calendar.getInstance()
//            adicionando trinta dias a partir da data atual
            calendar.add(Calendar.DATE, 30)
//            extraindo as informacoes da data
            ano = calendar.get(Calendar.YEAR)
            mes = calendar.get(Calendar.MONTH)
            dia = calendar.get(Calendar.DAY_OF_MONTH)
//            criando objeto datepickerdialog
            val datePickerDialog = DatePickerDialog(activity!!, this, ano, mes, dia)
//            limitando a data minima apresentada ao usuario
            datePickerDialog.datePicker.minDate = calendar.timeInMillis
//            adicionando mais sessentas dias a data corrente
            calendar.add(Calendar.DATE, 30)
//            limitando a data maxima apresentada ao usuario
            datePickerDialog.datePicker.maxDate = calendar.timeInMillis
//            apresentando o datepickerdialog
            datePickerDialog.show()
        }


        val edtValorEmprestimo = firstView.findViewById<TextView>(R.id.edt_valor_emprestimo)
        edtValorEmprestimo.addTextChangedListener(object : TextWatcher {
            var textoAtual: String = ""
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

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
        val dataSimulacaoFormatada = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val txtDataSimulacao = firstView.findViewById<TextView>(R.id.txt_data_simulacao)
        txtDataPrimeiroPagamento = firstView.findViewById<EditText>(R.id.edt_data_primeiro_pagamento)

        txtDataSimulacao.setText(dataSimulacaoFormatada.format(dataSimulacao))


        val btnLimpar = firstView.findViewById<Button>(R.id.btn_limpar)
        btnLimpar.setOnClickListener {

            esconderTeclado(btnLimpar)
            edt_data_primeiro_pagamento.setText("")
            edt_valor_emprestimo.setText("0,00")
            edt_quantidade_parcelas.setText("")

            containerResultado.removeAllViews()

        }

        val btnCalcular = firstView.findViewById<Button>(R.id.btn_calcular)
        btnCalcular.setOnClickListener {
            esconderTeclado(btnCalcular)
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

                val handler = Handler()
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
                                val infla =
                                    getContext()?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

                                val linhaResultado: View =
                                    infla.inflate(R.layout.item_parcela_resultado, containerResultado, false)

                                val txtValorEmprestimo =
                                    linhaResultado.findViewById<TextView>(R.id.txt_valor_emprestimo)

                                txtValorEmprestimo.setText(arredondar(valorEmprestimo))

                                val txtVencimento = linhaResultado.findViewById<TextView>(R.id.txt_vencimento)

                                txtVencimento.setText(edt_data_primeiro_pagamento.text.toString())

                                val txtCarencia = linhaResultado.findViewById<TextView>(R.id.txt_carencia)

                                txtCarencia.setText(diasDeCarencia.toString() + " dias")

                                val txtQtdeParcela = linhaResultado.findViewById<TextView>(R.id.txt_qtde_parcela)

                                txtQtdeParcela.setText(qtdeParcelas.toString())

                                val txtValorParcela = linhaResultado.findViewById<TextView>(R.id.txt_valor_parcela)

                                txtValorParcela.setText(arredondar(fazerCalculoParcela(valorTotal, qtdeParcelas)))

                                val txtValorTotal = linhaResultado.findViewById<TextView>(R.id.txt_valor_total)

                                txtValorTotal.setText(arredondar(valorTotal))

                                val btnCopiarTexto = linhaResultado.findViewById<Button>(R.id.btn_copiar_texto)

                                btnCopiarTexto.setOnClickListener {
                                    esconderTeclado(btnCopiarTexto)
                                    //                                    pegando o clipboard system service
                                    val clipBoardManager =
                                        getContext()?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
//                                    configurando a string para o recebimento das informacoes
                                    val textoCopiado =
                                        "Tipo: " + "${radio_button_grupo.text}" + "\n" +
                                                "Empréstimo: " + "${txtValorEmprestimo.text}" + "\n" +
                                                "Taxa de Juros: " + "$taxaNominalGrupo" + "\n" +
                                                "Primeira Parcela: " + "${txtVencimento.text}" + "\n" +
                                                "Carência: " + "${txtCarencia.text}" + "\n" +
                                                "Qtde Parcelas: " + "${txtQtdeParcela.text}" + "\n" +
                                                "Valor da Parcela: " + "${txtValorParcela.text}" + "\n" +
                                                "Valor Total: " + "${txtValorTotal.text}"
//                                    passando a string para o clipdata
                                    val clip = ClipData.newPlainText("RESULTADO", textoCopiado)
//                                    setando a string para o clipboardmanager
                                    clipBoardManager.setPrimaryClip(clip)
//                                    toast para avisar o usuario
                                    Snackbar.make(layout_principal, "TEXTO COPIADO!", Snackbar.LENGTH_LONG).show()

                                }

                                containerResultado.addView(linhaResultado)

                            } else if (radio_button_individual.isChecked) {
                                taxaJuros = taxaJurosIndividual.buscarTaxa(diasDeCarencia)
                                taxaJuros = transformaTaxa(taxaJuros)
                                valorEmprestimo = tranformaValorEmprestimo(edt_valor_emprestimo.text.toString())
                                qtdeDiasOPeracao = transformaParcela(edt_quantidade_parcelas.text.toString())
                                qtdeParcelas = edt_quantidade_parcelas.text.toString().toInt()

                                valorTotal = fazerCalculoTotal(taxaJuros, qtdeDiasOPeracao, valorEmprestimo)

                                //     layout inflado dinamicamente
                                val inflaters: LayoutInflater =
                                    getContext()?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

                                val linhaResultado: View =
                                    inflaters.inflate(R.layout.item_parcela_resultado, containerResultado, false)

                                val txtValorEmprestimo =
                                    linhaResultado.findViewById<TextView>(R.id.txt_valor_emprestimo)

                                txtValorEmprestimo.setText(arredondar(valorEmprestimo))

                                val txtVencimento = linhaResultado.findViewById<TextView>(R.id.txt_vencimento)

                                txtVencimento.setText(edt_data_primeiro_pagamento.text.toString())

                                val txtCarencia = linhaResultado.findViewById<TextView>(R.id.txt_carencia)

                                txtCarencia.setText(diasDeCarencia.toString() + " dias")

                                val txtQtdeParcela = linhaResultado.findViewById<TextView>(R.id.txt_qtde_parcela)

                                txtQtdeParcela.setText(qtdeParcelas.toString())

                                val txtValorParcela = linhaResultado.findViewById<TextView>(R.id.txt_valor_parcela)

                                txtValorParcela.setText(arredondar(fazerCalculoParcela(valorTotal, qtdeParcelas)))

                                val txtValorTotal = linhaResultado.findViewById<TextView>(R.id.txt_valor_total)

                                txtValorTotal.setText(arredondar(valorTotal))

                                val btnCopiarTexto = linhaResultado.findViewById<Button>(R.id.btn_copiar_texto)

                                btnCopiarTexto.setOnClickListener {
                                    esconderTeclado(btnCopiarTexto)
                                    //                                    pegando o clipboard system service
                                    val clipBoardManager =
                                        getContext()?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
//                                    configurando a string para o recebimento das informacoes
                                    val textoCopiado =
                                        "Tipo: " + "${radio_button_individual.text}" + "\n" +
                                                "Empréstimo: " + "${txtValorEmprestimo.text}" + "\n" +
                                                "Taxa de Juros: " + "$taxaNominalIndividual" + "\n" +
                                                "Primeira Parcela: " + "${txtVencimento.text}" + "\n" +
                                                "Carência: " + "${txtCarencia.text}" + "\n" +
                                                "Qtde Parcelas: " + "${txtQtdeParcela.text}" + "\n" +
                                                "Valor da Parcela: " + "${txtValorParcela.text}" + "\n" +
                                                "Valor Total: " + "${txtValorTotal.text}"
//                                    passando a string para o clipdata
                                    val clip = ClipData.newPlainText("RESULTADO", textoCopiado)
//                                    setando a string para o clipboardmanager
                                    clipBoardManager.setPrimaryClip(clip)
//                                    toast para avisar o usuario
                                    Snackbar.make(layout_principal, "TEXTO COPIADO!", Snackbar.LENGTH_LONG).show()

                                }

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
        return firstView
    }

    fun esconderTeclado(view: View){
//        pegando o contexto
        val inputMethodManager: InputMethodManager = getContext()?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        verificando se o teclado esta ativo e aparecendo na tela
        if (inputMethodManager.isActive){
//            escondendo o teclado da tela
            inputMethodManager.hideSoftInputFromWindow(view.windowToken,0)
        }
    }


    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
//        formatando a data
        var dataPrimeiroPagamento = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
//        pegando a data atual
        val date = Calendar.getInstance()
//        passando o ano, mes e dia da data atual
        date.set(p1, p2, p3)
//        colocando a data no edittext
        txtDataPrimeiroPagamento.setText(dataPrimeiroPagamento.format(date.time))
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


    /*fun fazerCalculoJuros(valorTotal: Double, valorEmprestimo: Double): Double {
        var resultado = valorTotal - valorEmprestimo
        return resultado
    }*/

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