package com.douglasvilella.meuprimeiroprojeto

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.douglasvilella.meuprimeiroprojeto.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    //Técnica utilizada para inicializar uma variável sem que ela seja nula
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Quando o botão ENTRAR for clicado faça
        binding.btnLoginEntrar.setOnClickListener{
            // aqui os dados digitados sao capturados e salvos em variaveis
            val email = binding.edtLoginEmail.text.toString().trim().toLowerCase()
            val senha = binding.edtLoginSenha.text.toString().trim()

            //Aqui é feita a validação dos campos
            //Se ambos estiverem preenchidos o campo do else é executado

            if (email.isEmpty()){
                binding.edtLoginEmail.error = "Campo Obrigatório"
                binding.edtLoginEmail.requestFocus()
            } else if(senha.isEmpty()){
                binding.edtLoginSenha.error = "Insira sua senha"
                binding.edtLoginSenha.requestFocus()

            } else{
                //Acessando o arquivo de preferencias compartilhadas
                val sharedPrefs = getSharedPreferences(
                        "cadastro_$email",
                        Context.MODE_PRIVATE
                )

                // recuperando dados no arquivo de preferencias compartilhadas
                val emailPrefs = sharedPrefs.getString("EMAIL", "")
                val senhaPrefs = sharedPrefs.getString("SENHA" ,"")

                if (email == emailPrefs && senha == senhaPrefs){
                    //Se tudo estiver certo mensagem de sucesso exibida
                    Toast.makeText(this, "Usuário Logado", Toast.LENGTH_LONG).show()

                    //E Em seguida abrir MainActivity
                    val mIntent = Intent(this, MainActivity::class.java)
                    // Passando o email via Intent para Main Activity
                    mIntent.putExtra("INTENT_EMAIL", email)
                    startActivity(mIntent)

                    finish()
                } else {
                    Toast.makeText(this, "Email ou senha invalidos", Toast.LENGTH_LONG).show()
                }
            }
        }

        //Quando o botão CADASTRAR for clicado faça
        binding.btnLoginCadastrar.setOnClickListener{
            val mIntent = Intent(this, CadastroActivity::class.java)
            startActivity(mIntent)
            }
        }
}