package com.maxirucci.recyclerviewtp_its

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maxirucci.recyclerviewtp_its.adapters.ProductosAdapter
import com.maxirucci.recyclerviewtp_its.models.ProductoModel

const val TAG = "getProguctos"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getProducto()

    }

    private fun getProducto(){
        val dataBase = Firebase.firestore
        val listaProductos = mutableListOf<ProductoModel>()
        this.title = "Buscando ofertas..."

        dataBase.collection("productos").get()
            .addOnSuccessListener {
                for (doc in it){
                    Log.d(TAG, "${doc.id} => ${doc.data}")

                    listaProductos.add(ProductoModel(
                        doc.getString("descripcion"),
                        doc.getBoolean("isOferta"),
                        doc.getString("nombre"),
                        doc.getDouble("precio"),
                        doc.getString("urlImagen")
                    ))
                }
            }
            .addOnCompleteListener {
                this.title = "Lista de productos"
                llenarRecyclerView(listaProductos)
                Log.d(TAG, "$listaProductos")
            }
    }

    private fun llenarRecyclerView(listaProductos: MutableList<ProductoModel>) {
        val recyclerView = findViewById<RecyclerView>(R.id.rvProductos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ProductosAdapter(this, listaProductos)
    }
}