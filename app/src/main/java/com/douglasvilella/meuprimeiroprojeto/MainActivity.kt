package com.douglasvilella.meuprimeiroprojeto

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.douglasvilella.meuprimeiroprojeto.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)

        //Recuperar email passado pelo intent

        val email = intent.getStringExtra("INTENT_EMAIL")


        //Acessar o arquivo de preferencias compartilhadas SharePrefs

        val sharedPrefs = getSharedPreferences(
                "cadastro_$email",          //Nome do arquivo
                Context.MODE_PRIVATE              //Tipo do acesso
        )
        // Recuperar dados no arquivo de SharePrefs
        // As aspas vazias definem o valor padrao em caso de erro

        val nome = sharedPrefs.getString("NOME", "")
        val sobrenome = sharedPrefs.getString("SOBRENOME", "")
        val continente = sharedPrefs.getString("CONTINENTE", "")


        //Exibir os dados recuperados na tela
        binding.txvMainNome.text = "$nome $sobrenome"
        binding.txvMainEmail.text = email
        binding.txvMainContinente.text = continente

        binding.btnMainSair.setOnClickListener {

            //Criando uma caixa de diálogo
            val alert = AlertDialog.Builder(this)

            //Definindo Titulo da caixa de Alerta
            alert.setTitle("Atenção!")

            //Definindo corpo da mensagem

            alert.setMessage("Deseja mesmo sair?")

            //Definir rótulo do botão e escutando seu click
            alert.setPositiveButton("Sair") { dialog, which ->
                val mIntent = Intent(this, LoginActivity::class.java)
                startActivity(mIntent)

                //Eliminando as telas da pilha
                finishAffinity()
            }

            //Definindo o rótulo do botão  e escutando seu click
            alert.setNeutralButton("Não") {dialog, which ->}
            /* desativa a possibilidade do usuário cancelar a caixa de diálogo
            ao clicar fora da mesma, dessa forma o usuário é obrigado a interagir
            com os botões
             */

            alert.setCancelable(false)

            alert.show()

        }

        binding.btnMainSite.setOnClickListener{
            val mIntent = Intent(this, WebActivity::class.java)
            startActivity(mIntent)
            finish()
        }



    }
}