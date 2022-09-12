package com.fiap.jokenpo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fiap.jokenpo.adapter.RankingAdapter
import com.fiap.jokenpo.model.Jogador
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_ranking.*
import kotlinx.android.synthetic.main.activity_sobre.*

class RankingActivity : AppCompatActivity() {

    private lateinit var jogadorArrayList : ArrayList<Jogador>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        jogadorArrayList = arrayListOf()

        val database = FirebaseDatabase.getInstance().getReference("Jogadores")

        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (jogadorSnapshot in snapshot.children){
                        val jogador = Jogador(jogadorSnapshot.child("nome").value as String,
                            (jogadorSnapshot.child("pontos").value).toString().toInt()
                        )
                        jogadorArrayList.add(jogador!!)
                    }
                    configureList(jogadorArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })

        btnVoltaar.setOnClickListener {
            finish()
        }
    }

    //função retorna uma lista preenchida
    fun configureList(jogadores: List<Jogador>){
        val recyclerView = rvRanking
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RankingAdapter(jogadores, this) {
            Toast.makeText(
                this,
                "O jogador: " + it.nome + " tem um total de " + it.pontos + " pontos.",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}




