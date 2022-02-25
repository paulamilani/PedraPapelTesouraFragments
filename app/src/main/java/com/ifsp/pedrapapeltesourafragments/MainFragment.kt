package com.ifsp.pedrapapeltesourafragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ifsp.pedrapapeltesourafragments.databinding.FragmentMainBinding
import kotlin.random.Random

class MainFragment : Fragment(), View.OnClickListener {

    private lateinit var fragmentMainBinding: FragmentMainBinding

    var qtd_jogador = 0

    // 0 -> Pedra
    // 1 -> Papel
    // 2 -> Tesoura

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false)
        Log.d("CREATE VIEW", "criar main fragmento")
        fragmentMainBinding.zeroBt.setOnClickListener(this)
        fragmentMainBinding.umBt.setOnClickListener(this)
        fragmentMainBinding.doisBt.setOnClickListener(this)
        return fragmentMainBinding.root

    }

    override fun onClick(view: View) {
        var jogadaHumano = -1
        when (view.id) {
            R.id.zeroBt -> jogadaHumano = 0
            R.id.umBt -> jogadaHumano = 1
            R.id.doisBt -> jogadaHumano = 2
        }
        jogar(jogadaHumano)
    }

    fun jogar(jogadaHumano: Int) {
        val random = Random(System.currentTimeMillis())
        val jogadaComputador1 = random.nextInt(3)
        val jogadaComputador2 = random.nextInt(3)
        var jgC1 = " "
        var jgC2 = " "
        var hu = " "

        // ------ set imagens ----//
        //computador 1
        var imageJogadaComputadorId1 = -1
        when (jogadaComputador1) {
            0 -> imageJogadaComputadorId1 = R.mipmap.pedra
            1 -> imageJogadaComputadorId1 = R.mipmap.papel
            2 -> imageJogadaComputadorId1 = R.mipmap.tesoura
        }
        fragmentMainBinding.jogadaComputador1.setImageResource(imageJogadaComputadorId1)

        //coputador 2
        var imageJogadaComputadorId2 = -1
        when (jogadaComputador2) {
            0 -> imageJogadaComputadorId2 = R.mipmap.pedra
            1 -> imageJogadaComputadorId2 = R.mipmap.papel
            2 -> imageJogadaComputadorId2 = R.mipmap.tesoura
        }
        fragmentMainBinding.jogadaComputador2.setImageResource(imageJogadaComputadorId2)

        when (jogadaHumano) {
            0 -> hu = "Pedra"
            1 -> hu = "Papel"
            2 -> hu = "Tesoura"
        }
        when (jogadaComputador1) {
            0 -> jgC1 = "Pedra"
            1 -> jgC1 = "Papel"
            2 -> jgC1 = "Tesoura"
        }
        when (jogadaComputador2) {
            0 -> jgC2 = "Pedra"
            1 -> jgC2 = "Papel"
            2 -> jgC2 = "Tesoura"
        }

        // --------- exibe jogadas e resultado ------//
        val resultadoSb = StringBuilder()

        // -------- verifica radio button qtd jogadores e executa validações -------//
        if (qtd_jogador == 2) {
            fragmentMainBinding.jogadaComputador2.setVisibility(View.INVISIBLE)
            fragmentMainBinding.resultadoC2.setVisibility(View.INVISIBLE)
            if (jogadaHumano == jogadaComputador1) {
                resultadoSb.append("Empate")
            } else {
                if (jogadaHumano == 0 && jogadaComputador1 == 2 || jogadaHumano == 1 && jogadaComputador1 == 0 || jogadaHumano == 2 && jogadaComputador1 == 1) {
                    resultadoSb.append("Você venceu")
                } else {
                    resultadoSb.append("Você perdeu")
                }
            }
        }
        if (qtd_jogador == 3) {
            fragmentMainBinding.jogadaComputador2.setVisibility(View.VISIBLE)
            fragmentMainBinding.resultadoC2.setVisibility(View.VISIBLE)
            if (jogadaHumano == jogadaComputador1 && jogadaHumano == jogadaComputador2) {
                resultadoSb.append("Empate")
            } else if (jogadaHumano == jogadaComputador2) {
                if (jogadaHumano == 0 && jogadaComputador1 == 2 || jogadaHumano == 1 && jogadaComputador1 == 0 || jogadaHumano == 2 && jogadaComputador1 == 1) {
                    resultadoSb.append("Você Venceu e tem 1 empate com ComputadorDOIS")
                }
            } else if (jogadaHumano == jogadaComputador1) {
                if (jogadaHumano == 0 && jogadaComputador2 == 2 || jogadaHumano == 1 && jogadaComputador2 == 0 || jogadaHumano == 2 && jogadaComputador2 == 1) {
                    resultadoSb.append("Você Venceu e tem 1 empate com ComputadorUM")
                }
            } else if (jogadaComputador1 == jogadaComputador2) {
                if (jogadaHumano == 0 && jogadaComputador2 == 2 || jogadaHumano == 1 && jogadaComputador2 == 0 || jogadaHumano == 2 && jogadaComputador2 == 1) {
                    resultadoSb.append("Você Venceu")
                }
            } else {
                resultadoSb.append("Você perdeu")
            }
        }

        val resultadoH = " Sua jogada: $hu\n"
        fragmentMainBinding.resultadoH.setText(resultadoH)
        val resultadoC1 = " ComputadorUM: $jgC1\n"
        fragmentMainBinding.resultadoC1.setText(resultadoC1)
        val resultadoC2 = " ComputadorDOIS: $jgC2\n"
        fragmentMainBinding.resultadoC2.setText(resultadoC2)
        fragmentMainBinding.resultadoTv.setText(resultadoSb.toString())
    }
}