package com.example.simpleoldapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.simpleoldapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Response

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

        /*lifecycleScope.launch{
            viewModel.uiState.collect {
                val resultToText = it.firstDieValue.toString()
                binding.resultDice.text = resultToText
            }
        }*/

        viewModel.registerUser(RegisterCred("eve.holt@reqres.in", "pistol")) { code, message, response ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            if (code == 200) {
                Log.d("MainActivity", "Registration success! with code: $code, message: $message, response: $response")
            } else {
                Log.d("MainActivity", "Registration failed! with code: $code, message: $message, response: $response")
            }
        }

        /*val retIn = RetrofitInstance.provideRetrofit(RetrofitInstance.provideClient()).create(ApiInterface::class.java)
        val registrationData = RegisterCred("jakub@yahoo.com", "ErikJeKral")



        retIn.registerUser(registrationData).enqueue( object : Callback<Response> {

            override fun onResponse(call: retrofit2.Call<Response>, response: Response) {
                if (response.isSuccessful) {
                    val msg = response.body()?.string()
                    msg?.let { Log.d("MainActivity", it) }
                    Toast.makeText(this@MainActivity, "Registration success!", Toast.LENGTH_SHORT)
                        .show()
                    Log.d("MainActivity", "Registration success!")

                } else {
                    Toast.makeText(this@MainActivity, "Registration failed!", Toast.LENGTH_SHORT)
                        .show()
                    Log.d("MainActivity", "Registration failed!")
                }
            }

        })

        override fun onFailure(call: retrofit2.Call<Response>, t: Throwable) {
            Toast.makeText(
                this@MainActivity,
                t.message,
                Toast.LENGTH_SHORT
            ).show()
            t.localizedMessage?.let { Log.e("MainActivity", it) }
        }

*/
    }
}