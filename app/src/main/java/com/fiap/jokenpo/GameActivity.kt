package com.fiap.jokenpo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*

class GameActivity : AppCompatActivity() {

    private val delayTela = 1000L
    private var numeroAleatorio: Random?=null
    var pontos:Int = 0
    var jogadas:Int = 0
    private var PEDRA = 1
    private var TESOURA = 2
    private var PAPEL = 3

    //Funções Derrota Vitoria Empate
    private fun venceu(){
        //strings.xml
        pontos+=2
        jogadas++
        tvResultado!!.text = getString(R.string.venceu)
        //XML color.
        tvResultado!!.setTextColor(ContextCompat.getColor(this,R.color.vitoria))
        tvPontuacao!!.text = pontos.toString()
        tvTentativas!!.text = jogadas.toString()
    }

    private fun perdeu(){
        jogadas++
        tvResultado!!.text = getString(R.string.derrota)
        tvResultado!!.setTextColor(ContextCompat.getColor(this,R.color.derrota))
        tvTentativas!!.text = jogadas.toString()
        Handler(Looper.getMainLooper()).postDelayed({
            val telaGameOver = Intent(this,GameOverActivity::class.java)
            telaGameOver.putExtra("PONTUACAO", pontos)
            startActivity(telaGameOver)
            finish()
        },delayTela)

    }

    private fun empate(){
        pontos++
        jogadas++
        tvResultado!!.text = getString(R.string.empate)
        tvResultado!!.setTextColor(ContextCompat.getColor(this,R.color.empate))
        tvPontuacao!!.text = pontos.toString()
        tvTentativas!!.text = jogadas.toString()
    }

    private fun realizarJogada(jogadaPlayer:Int){
        var jogadaPC = numeroAleatorio!!.nextInt(3)+1
        when (jogadaPC){
            PEDRA ->{
                ivResultadoPC!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.pixel_pedra))
                when (jogadaPlayer){
                    TESOURA -> perdeu()
                    PEDRA -> empate()
                    PAPEL -> venceu()
                }
            }

            TESOURA -> {
                ivResultadoPC!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.pixel_tesoura))
                when(jogadaPlayer){
                    TESOURA -> empate()
                    PEDRA -> venceu()
                    PAPEL -> perdeu()
                }
            }

            PAPEL -> {
                ivResultadoPC!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.pixel_papel))
                when(jogadaPlayer){
                    TESOURA -> venceu()
                    PEDRA -> perdeu()
                    PAPEL -> empate()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        //Resetando visualmente o text
        tvPontuacao!!.text = pontos.toString()
        tvTentativas!!.text = jogadas.toString()

        numeroAleatorio=Random()

        idPedra.setOnClickListener {
            ivResultadoPlayer!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.pixel_pedra))
            realizarJogada(PEDRA)
        }

        idTesoura.setOnClickListener {
            ivResultadoPlayer!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.pixel_tesoura))
            realizarJogada(TESOURA)
        }

        idPapel.setOnClickListener {
            ivResultadoPlayer!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.pixel_papel))
            realizarJogada(PAPEL)
        }

    }
}
