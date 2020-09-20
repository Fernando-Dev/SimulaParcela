@file:Suppress("UNREACHABLE_CODE")

package br.fernando.simulaparcela.utilitario

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

object MascaraUtilitario {


    var FORMATAR_NUMERO_CPF = "###.###.###-##"
    var FORMATAR_NUMERO_CELULAR = "(###)#####-####"
    var FORMATAR_NUMERO_CEP = "#####-###"
    var FORMATAR_DATA = "##/##/####"
    var FORMATAR_HORA = "##:##"
    var FORMATAR_VALOR_MONETARIO = "R$ #.###.###,##"
    var FORMATAR_TAXA_JUROS = "#,##%"


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

                var mascara = ""

                if (estaAtualizando) {
                    textoAntigo = str
                    estaAtualizando = false

                    return
                }
                var i = 0

                for (m in mascara.toCharArray()) {
                    if (m != '#' && str.length > textoAntigo.length) {
                        mascara += m
                        continue
                    }
                    try {
                        mascara += str[i]
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
            .replace("[)]".toRegex(), "").replace("[R$]".toRegex(),"").replace("[%]".toRegex(),"")
    }


}