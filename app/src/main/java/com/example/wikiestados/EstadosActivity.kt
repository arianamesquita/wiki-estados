package com.example.wikiestados

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class EstadosActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estados)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val imgEstado = intent.getIntExtra("imgEstados", 0)
        if(imgEstado > 0){
            val img = findViewById<View>(R.id.img) as ImageView
            img.setImageResource(imgEstado)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                supportFinishAfterTransition()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}