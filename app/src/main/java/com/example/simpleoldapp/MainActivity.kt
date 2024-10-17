package com.example.simpleoldapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.simpleoldapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import okhttp3.ResponseBody

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        val viewModel by viewModels<DiceRollViewModel>()

        setContentView(binding.root)


        binding.btn.setOnClickListener {
            viewModel.rollDice()
        }

        lifecycleScope.launch{
            viewModel.uiState.collect {
                val resultToText = it.firstDieValue.toString()
                binding.resultDice.text = resultToText
            }
        }

        val retIn = RetrofitInstance.provideRetrofit(RetrofitInstance.provideClient()).create(ApiInterface::class.java)
        val registrationData = RegisterCred("jakub@yahoo.com", "ErikJeKral")

        retIn.registerUser(registrationData).enqueue( object :
            Callback<ResponseBody> {
            override fun onFailure(call: Call, t: Throwable) {
                Toast.makeText(
                    this@MainActivity,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onResponse(call: Call, response: Response) {
                if (response.code == 201) {
                    Toast.makeText(this@MainActivity, "Registration success!", Toast.LENGTH_SHORT)
                        .show()

                }
                else{
                    Toast.makeText(this@MainActivity, "Registration failed!", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }
        )

    }
}