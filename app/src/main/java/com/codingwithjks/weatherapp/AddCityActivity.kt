package com.codingwithjks.weatherapp

import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingwithjks.weatherapp.adapter.CityAdapter
import com.codingwithjks.weatherapp.databinding.ActivityAddCityBinding
import kotlinx.android.synthetic.main.activity_add_city.*

class AddCityActivity: AppCompatActivity() {
    private lateinit var binding: ActivityAddCityBinding
    val cityList =  ObservableArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        fab.setOnClickListener { showdialog() }
    }

    fun showdialog(){
        val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Ajouter une ville")

// Set up the input
        val input = EditText(this)
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setHint("Enter Text")
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

// Set up the buttons
        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            // Here you get get input text from the Edittext
            cityList.add(input.text.toString())
            val adapter = CityAdapter(cityList)
            recycler.layoutManager = LinearLayoutManager(recycler.context, LinearLayoutManager.VERTICAL, false)
            recycler.adapter = adapter
        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }
}