package br.fernando.simulaparcela.utilitario

import android.content.Context
import br.fernando.simulaparcela.principal.FirstFragment

data class TaxaJurosIndividual(var context: Context) {

    var listaJurosIndividual = mutableListOf<Double>(
        1.74,
        1.746,
        1.752,
        1.758,
        1.764,
        1.77,
        1.776,
        1.782,
        1.788,
        1.794,
        1.8,
        1.806,
        1.812,
        1.818,
        1.824,
        1.83,
        1.836,
        1.842,
        1.848,
        1.854,
        1.86,
        1.866,
        1.872,
        1.878,
        1.884,
        1.89,
        1.896,
        1.902,
        1.908,
        1.914,
        1.92
    )

    fun buscarTaxa(diasCarencia: Int): Double {
        val dias = 30
        var resultado = diasCarencia - dias
        var e: Double = 0.0
        for ((i, v) in listaJurosIndividual.withIndex()) {
            if (i == resultado) {
                e = v
            }
        }

        return e
    }

    /*val trinta: Double = 1.74,
    val trintaUm: Double = 1.746,
    val trintaDois: Double = 1.752,
    val trintaTres: Double = 1.758,
    val trintaQuatro: Double = 1.764,
    val trintaCinco: Double = 1.77,
    val trintaSeis: Double = 1.776,
    val trintaSete: Double = 1.782,
    val trintaOito: Double = 1.788,
    val trintaNove: Double = 1.794,
    val Quarenta: Double = 1.8,
    val QuarentaUm: Double = 1.806,
    val QuarentaDois: Double = 1.812,
    val QuarentaTres: Double = 1.818,
    val QuarentaQuatro: Double = 1.824,
    val QuarentaCinco: Double = 1.83,
    val QuarentaSeis: Double = 1.836,
    val QuarentaSete: Double = 1.842,
    val QuarentaOito: Double = 1.848,
    val QuarentaNove: Double = 1.854,
    val Cinquenta: Double = 1.86,
    val CinquentaUm: Double = 1.866,
    val CinquentaDois: Double = 1.872,
    val CinquentaTres: Double = 1.878,
    val CinquentaQuatro: Double = 1.884,
    val CinquentaCinco: Double = 1.89,
    val CinquentaSeis: Double = 1.896,
    val CinquentaSete: Double = 1.902,
    val CinquentaOito: Double = 1.908,
    val CinquentaNove: Double = 1.914,
    val Sessenta: Double = 1.92*/

}