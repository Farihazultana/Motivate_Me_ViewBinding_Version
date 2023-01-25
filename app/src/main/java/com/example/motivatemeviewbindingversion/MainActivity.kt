package com.example.motivatemeviewbindingversion

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.DocumentsContract
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService
import com.example.motivatemeviewbindingversion.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    //        Initialize our binding object!
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
//        Old way, no viewBinding!
//        setContentView(R.layout.activity_main)

//        New way featuring viewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        set click listeners
        binding.mainActivityBtUpdate.setOnClickListener { updateMessage() }

//        Check the bundle
        if(savedInstanceState != null){
            binding.mainActivityTvMessage.text = savedInstanceState.getString("myMessage")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("myMessage", binding.mainActivityTvMessage.text.toString())
    }

    private fun updateMessage(){
        val username = binding.mainActivityEtName.text

        val motivationalMessages = listOf("Keep Working Hard!", "Never Give Up!", "Keep Your Eye On The Prize!", "Difficulty is Growth!", "Today is the first day of the rest of your life!")
        val index = (0..4).random()
        val currentMessage = motivationalMessages[index]

        if(username.toString() == ""){
            binding.mainActivityTvMessage.text = "Make sure to enter you name!"
        }else{
            binding.mainActivityTvMessage.text = "Hello $username! $currentMessage"
        }

        binding.mainActivityEtName.setText("")
        hideKeyBoard()
    }

    private fun hideKeyBoard(){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.mainActivityEtName.windowToken,0)
    }
}