package com.example.wikiestados

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wikiestados.model.EstadoData
import com.example.wikiestados.model.Estados

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var estados: List<Estados>? = emptyList()
    private lateinit var adapter: EstadosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)

        estados = EstadoData.getEstados()
        adapter = EstadosAdapter(this, estados, onClickEstados())
        recyclerView.adapter = adapter

        println(estados)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_exemplo_recycler_view, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_linear_layout -> {
                recyclerView.layoutManager = LinearLayoutManager(this)
                return true
            }
            R.id.action_grid_layout -> {
                recyclerView.layoutManager = GridLayoutManager(this, 2)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onClickEstados(): EstadosAdapter.EstadosOnClickListener {
        return object : EstadosAdapter.EstadosOnClickListener {
            @SuppressLint("RestrictedApi")
            override fun onClickEstados(holder: EstadosAdapter.EstadosViewHolder?, idx: Int){
                val estados = estados?.getOrNull(idx)?:return
                val context = holder?.itemView?.context?:return
                val intent = Intent(baseContext, EstadosActivity::class.java)
                intent.putExtra("imgEstados", estados.bandeira)

                val key = getString(R.string.transition_key)
                val img: ImageView = holder.img ?: return
                val opts1 = ActivityOptions.makeSceneTransitionAnimation(
                    this@MainActivity,
                    img,
                    key
                )
                val opts2 = ActivityOptions.makeCustomAnimation(
                    context,
                    R.anim.shake,
                    R.anim.abc_slide_in_top
                )
                startActivity(intent, opts2.toBundle())
                Toast.makeText(this@MainActivity, "Estado: ${estados.nome}", Toast.LENGTH_LONG).show()
            }
        }
    }
}