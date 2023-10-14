package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var tvInput: TextView?=null
    var lastNumeric : Boolean = false
    var lastDot : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }
    fun onDigit(view:View){
        tvInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false

    }
    fun onClear(view:View){
        tvInput?.text =""
    }
    fun onDecimal(view:View){
        if(lastNumeric && !lastDot){
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view:View){
        tvInput?.text?.let {

            if(lastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }

    }
    fun OnEqual(view:View){
        if(lastNumeric) {
            var tvValue =
                tvInput?.text.toString() // this step is done because textview is a different datatype and we want it in string
            var prefix = ""
            try {

                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")) {
                    val splitvalue = tvValue.split("-");
//                99   -   1 and this is stored in the form of array
                    var one1 = splitvalue[0]// 99
                    var two1 = splitvalue[1]//1
                    if(prefix.isNotEmpty()){
                        one1 = prefix+one1
                    }
                    tvInput?.text = removeZeroAfterDecimal((one1.toDouble() - two1.toDouble()).toString())

                }
                else if(tvValue.contains("+")){
                    val splitvalue = tvValue.split("+")
                    var one1 = splitvalue[0]// 99
                    var two1 = splitvalue[1]//1
                    if(prefix.isNotEmpty()){
                        one1 = prefix+one1
                    }
                    tvInput?.text = removeZeroAfterDecimal((one1.toDouble() + two1.toDouble()).toString())

                }
                else if(tvValue.contains("*")){
                    val splitvalue = tvValue.split("*")
                    var one1 = splitvalue[0]// 99
                    var two1 = splitvalue[1]//1
                    if(prefix.isNotEmpty()){
                        one1 = prefix+one1
                    }
                    tvInput?.text = removeZeroAfterDecimal((one1.toDouble() * two1.toDouble()).toString())

                }
                else if(tvValue.contains("/")){
                    val splitvalue = tvValue.split("/")
                    var one1 = splitvalue[0]// 99
                    var two1 = splitvalue[1]//1
                    if(prefix.isNotEmpty()){
                        one1 = prefix+one1
                    }
                    tvInput?.text = removeZeroAfterDecimal((one1.toDouble() / two1.toDouble()).toString())

                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }
    private fun removeZeroAfterDecimal(result :String): String{
        var s  = result
        if(s.contains(".0")){
            s = result.substring(0,result.length-2)
        }
        return s
    }
    private fun isOperatorAdded(value: String):Boolean{
        return if(value.startsWith("-")){
            false
        }else{
                       value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")

        }
    }

}