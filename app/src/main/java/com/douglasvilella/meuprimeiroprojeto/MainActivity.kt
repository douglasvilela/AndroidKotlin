package com.douglasvilella.meuprimeiroprojeto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun jogarDado(view: View){
        var texto = findViewById<View>(R.id.txtMensagem) as TextView
        texto.setText("Teste")
        /*adicionei um comentario no MainActivity*/
    }
}