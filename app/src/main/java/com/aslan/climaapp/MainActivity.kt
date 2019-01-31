package com.aslan.climaapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    var tvCiudad:TextView? = null
    var tvGrados:TextView? = null
    var tvEstdo:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCiudad = findViewById(R.id.tvCiudad)
        tvGrados = findViewById(R.id.tvGrados)
        tvEstdo = findViewById(R.id.tvEstado)


        if(Network.hayRed(this)) {
            //EJECUTAR SOLICITUD HTTP
            Toast.makeText(this,"Hay red",Toast.LENGTH_SHORT).show()
            Log.d("Entro","Prueba de logcat")
            //API Key: 7391d815a5132bd9ccddb30572d9fc56
            //Clave ciudad tijuana: 3981609

            val url = "http://api.openweathermap.org/data/2.5/weather?id=3981609&units=metric&lang=es&appid=7391d815a5132bd9ccddb30572d9fc56"

            val queue = Volley.newRequestQueue(this)

            val solicitud = StringRequest(Request.Method.GET,url,Response.Listener<String>{
                    response ->
//                try {
                    Log.d("SolicitudHTTPVolley",response)
                    val gson = Gson()
                    val ciudad = gson.fromJson(response,Ciudad::class.java)

                    //Mapeamos los resultados con los views
                    tvCiudad?.text = ciudad.name
                    tvGrados?.text = ciudad.main.temp.toString()+"°"
                    //Como es un arreglo accedemos por su indice
                    tvEstdo?.text = ciudad.weather.get(0).description
//                } catch (e:Error) {}
            },Response.ErrorListener{})

            queue.add(solicitud)


        }
        else {
            //MOSTRAR MENSAJE DE ERROR
            Toast.makeText(this,"No hay red",Toast.LENGTH_SHORT).show()
        }
       }

}
