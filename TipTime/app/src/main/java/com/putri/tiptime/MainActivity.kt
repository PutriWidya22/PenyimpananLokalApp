package com.putri.tiptime

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.putri.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnHitung.setOnClickListener { calculateTip()}
    }

    @SuppressLint("StringFormatInvalid")
    private fun calculateTip() {
        //1.mengambil data angka dari edit teks -> angka double
        val stringInTextField = binding.editBiayaJasa.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if(cost == null){
            binding.textTotal.text = ""
            return
        }

        //2. mengambil info presentase tip yang dipilih
        val tipPercentage = when (binding.radioGroup.checkedRadioButtonId) {
            R.id.radioAmazing -> 0.20
            R.id.radioGood -> 0.15
            else -> 0.10
        }

        //3. menghitung tip yang akan dibayar
        var tip = tipPercentage * cost

        //4. mengambil info apakah akan dibulatkan ke atas?
        if (binding.switch3.isChecked){
            tip = ceil(tip)
        }

        //5. ditampilkan sesuai format mata uang
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.textTotal.text = getString(R.string.total_tip_yang_diberikan, formattedTip)
        }
    }