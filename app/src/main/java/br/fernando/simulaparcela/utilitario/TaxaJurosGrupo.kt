package br.fernando.simulaparcela.utilitario

import android.content.Context
import br.fernando.simulaparcela.principal.FirstFragment

data class TaxaJurosGrupo(var context: Context) {
    val listaJurosGrupo = mutableListOf<Double>(
        1.51,
        1.516,
        1.522,
        1.528,
        1.534,
        1.54,
        1.546,
        1.552,
        1.558,
        1.564,
        1.57,
        1.576,
        1.582,
        1.588,
        1.594,
        1.6,
        1.606,
        1.612,
        1.618,
        1.624,
        1.63,
        1.636,
        1.642,
        1.648,
        1.654,
        1.66,
        1.666,
        1.672,
        1.678,
        1.684,
        1.69
    )

    fun buscarTaxa(diasCarencia: Int): Double {
        val dias = 30
        var resultado = diasCarencia - dias
        var e: Double = 0.0
        for ((i,v) in listaJurosGrupo.withIndex()) {
            if (i == resultado) {
                e = v
            }
        }

        return e
    }

    /*val _30:Double = 1.51,
    val trintaUm: Double = 1.516,
    val trintaDois: Double = 1.522,
    val trintaTres: Double = 1.528,
    val trintaQuatro: Double = 1.534,
    val trintaCinco: Double = 1.54,
    val trintaSeis: Double = 1.546,
    val trintaSete: Double = 1.552,
    val trintaOito: Double = 1.558,
    val trintaNove: Double = 1.564,
    val Quarenta: Double = 1.57,
    val QuarentaUm: Double = 1.576,
    val QuarentaDois: Double = 1.582,
    val QuarentaTres: Double = 1.588,
    val QuarentaQuatro: Double = 1.594,
    val QuarentaCinco: Double = 1.6,
    val QuarentaSeis: Double = 1.606,
    val QuarentaSete: Double = 1.612,
    val QuarentaOito: Double = 1.618,
    val QuarentaNove: Double = 1.624,
    val Cinquenta: Double = 1.63,
    val CinquentaUm: Double = 1.636,
    val CinquentaDois: Double = 1.642,
    val CinquentaTres: Double = 1.648,
    val CinquentaQuatro: Double = 1.654,
    val CinquentaCinco: Double = 1.66,
    val CinquentaSeis: Double = 1.666,
    val CinquentaSete: Double = 1.672,
    val CinquentaOito: Double = 1.678,
    val CinquentaNove: Double = 1.684,
    val Sessenta: Double = 1.69*/

}


