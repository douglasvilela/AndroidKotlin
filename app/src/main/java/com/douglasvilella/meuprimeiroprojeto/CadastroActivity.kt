package com.douglasvilella.meuprimeiroprojeto

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.douglasvilella.meuprimeiroprojeto.databinding.ActivityCadastroBinding

class CadastroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCadastroBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // Criando uma lista de opções para o spinner
        val listaContinentes = arrayListOf("Continente","África","Antártida","América","Europa","Oceania","Ásia")
        //Criando um adaptador para o Spinner
        val spinnerAdapter = ArrayAdapter(
                this,                                    //Contexto
                android.R.layout.simple_spinner_dropdown_item,          //Layout
                listaContinentes                                //dados
        )

        //Plugando o adaptador no Spinner
        binding.spnCadastroContinente.adapter = spinnerAdapter
        //Quando o botão CADASTRAR for clicado
        binding.btnCadastroCadastrar.setOnClickListener{
            // os dados digitados  serao capturados e salvos em variaveis
            val nome = binding.edtCadastroNome.text.toString().trim()
            val sobrenome = binding.edtCadastroSobrenome.text.toString().trim()
            val email = binding.edtCadastroEmail.text.toString().trim()
            val senha = binding.edtCadastroNome.text.toString().trim()
            val continente = binding.spnCadastroContinente.selectedItem.toString()


            if (nome.isEmpty()      ||
                    sobrenome.isEmpty() ||
                    email.isEmpty()     ||
                    senha.isEmpty()     ||
                    continente == listaContinentes[0]
            ){
                //se qualquer campo não estiver prenchido uma mensagem de erro será exibida
                Toast.makeText(
                        this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
            } else{
                val sharedPrefs = getSharedPreferences(
                        "cadastro_$email",
                        Context.MODE_PRIVATE
                )
                //
                val editPrefs = sharedPrefs.edit()
                //Aqui os dados sao preparados para serem salvos no arquivo
                // Os dados sao salvos no formato chave -> valor
                editPrefs.putString("NOME", nome)
                editPrefs.putString("SOBRENOME", email)
                editPrefs.putString("EMAIL", email)
                editPrefs.putString("SENHA", senha)
                editPrefs.putString("CONTINENTE", continente)

                //Aqui os dados sao salvos no arquivo
                // O método apply realiza o processo de salvar fora do thread principal para
                // que o app nao fique travado caso o processo demore

                editPrefs.apply()


                val mIntent = Intent(this, MainActivity::class.java)

                mIntent.putExtra("INTENT_EMAIL", email)
                startActivity(mIntent)

                finishAffinity()
            }
        }


    }
}