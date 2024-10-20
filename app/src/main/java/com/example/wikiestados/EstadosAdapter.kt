package com.example.wikiestados

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wikiestados.model.Estados

class EstadosAdapter(
    private val context: Context,
    private val estados: List<Estados>?,
    private val onClickListener: EstadosOnClickListener
): RecyclerView.Adapter<EstadosAdapter.EstadosViewHolder>() {

    interface EstadosOnClickListener{
        fun onClickEstados(holder: EstadosViewHolder?, idx: Int)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EstadosViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_estados, parent, false)
        return EstadosViewHolder(view)
    }

    override fun getItemCount(): Int = estados?.size ?: 0

    override fun onBindViewHolder(
        holder: EstadosViewHolder,
        position: Int
    ) {
        val estados = estados!![position]
        holder.tNome.text = estados.nome
        holder.img.setImageResource(estados.bandeira ?: R.drawable.distrito_federal)

        holder.itemView.setOnClickListener{
            onClickListener.onClickEstados(holder, position)
        }
    }

    class EstadosViewHolder(view: View): RecyclerView.ViewHolder(view){
        var tNome: TextView = view.findViewById(R.id.tNome)
        var img: ImageView = view.findViewById(R.id.img)
    }
}