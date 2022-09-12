package com.fiap.jokenpo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fiap.jokenpo.model.Jogador
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_game_over.*

class GameOverActivity : AppCompatActivity() {

    private val delayTela = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        //Recuperar variavel pontos
        val pontos = intent.extras!!.getInt("PONTUACAO")

        btJogarNovamente.setOnClickListener {
            val database = FirebaseDatabase.getInstance().getReference("Jogadores")

            database.child(etNomePlayer.text.toString())
                .setValue(Jogador(etNomePlayer.text.toString(),pontos))
                .addOnSuccessListener {
                    Toast.makeText(this@GameOverActivity,
                        "SUCESSO no Envio de Dados",
                        Toast.LENGTH_SHORT).show()

                    Handler(Looper.getMainLooper()).postDelayed({
                        //Iniciando a tela
                        val Game = Intent(this,GameActivity::class.java)
                        startActivity(Game)
                        finish()
                    },delayTela)
                }
                .addOnFailureListener{
                    Toast.makeText(this@GameOverActivity,
                        "Falha no Envio de Dados",
                        Toast.LENGTH_SHORT).show()
                };
        }

        btVoltarMenu.setOnClickListener{
            val Menu = Intent(this,MenuActivity::class.java)
            startActivity(Menu)
            finish()
        }
    }
}
