package com.example.test

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.DefaultRetryPolicy
import com.android.volley.toolbox.JsonObjectRequest
import com.google.gson.Gson
import okhttp3.*
import okhttp3.RequestBody
import org.json.JSONObject

import java.io.IOException
import java.util.concurrent.CountDownLatch


class MainActivity : AppCompatActivity() {
    var client = OkHttpClient()
    var url = "http://192.168.0.2:8080/backend_moviles/api/sistema/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var button2 = findViewById<Button>(R.id.button2)

        button2.setOnClickListener {
           run()
        //runpost()
            //rundelete()
           // runId()
        }
    }

    fun run() {
       // val etLocation = findViewById<EditText>(R.id.etLocation)
        val request = Request.Builder()
            //.url("http://10.0.2.2:28019/api/usuarios")
            .url(url+"obtenerAlumno")
            .build()
        var countDownLatch: CountDownLatch = CountDownLatch(1)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println(e.message.toString())
                countDownLatch.countDown();
                //Toast.makeText(applicationContext,e.message.toString(),Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call, responseHttp: okhttp3.Response) {
                val gson = Gson()
                var valor = responseHttp.body()?.string()
                var entidadJson = gson?.fromJson<Alumnos>(valor, Alumnos::class.java)
                //println(entidadJson)
                println(entidadJson[4])
                countDownLatch.countDown();

                //Toast.makeText(applicationContext,valor.toString(),Toast.LENGTH_SHORT).show()
            }
        })
        countDownLatch.await();
    }

    fun runId() {
        // val etLocation = findViewById<EditText>(R.id.etLocation)
        val request = Request.Builder()
            //.url("http://10.0.2.2:28019/api/usuarios")
            .url(url+"buscarAlumnoId/21")
            .build()
        var countDownLatch: CountDownLatch = CountDownLatch(1)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println(e.message.toString())
                countDownLatch.countDown();
                //Toast.makeText(applicationContext,e.message.toString(),Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call, responseHttp: okhttp3.Response) {
                val gson = Gson()
                var valor = responseHttp.body()?.string()
                var entidadJson = gson?.fromJson<Alumno>(valor, Alumno::class.java)
                println(entidadJson)
                countDownLatch.countDown();

                //Toast.makeText(applicationContext,valor.toString(),Toast.LENGTH_SHORT).show()
            }
        })
        countDownLatch.await();
    }

    @SuppressLint("SetTextI18n")
    fun runpost() {


        // Post parameters
        // Form fields and values
        // Form fields and values
        val jsonObject = JSONObject()
        jsonObject.put("carrera",21)
        jsonObject.put("cedula",705)
        jsonObject.put("email","lslsl@gmail.com")
        jsonObject.put("fecha_nac","22-22-22")
        jsonObject.put("id",88)
        jsonObject.put("nombre","karl")
        jsonObject.put("telefono","22-22-22")



        // Volley post request with parameters
        val request = JsonObjectRequest(com.android.volley.Request.Method.POST,url+"insertarAlumno",jsonObject,
            { response->
                // Process the json
                try {
                   // etCord.setText("Response: $response")
                    println(response)
                }catch (e:Exception){
                    //etClima.setText("Exception: $e")
                    println(e)
                }

            }, {
                // Error in request
              //  etHumedad.setText("Volley error: $it")
                println("Error request:"+it)
                if(it.message?.contains("false") == true){
                    println("Falló")
                }
                if(it.message?.contains("true") == true){
                    println("Bien")
                }
            })


        // Volley request policy, only one time request to avoid duplicate transaction
        request.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
            // 0 means no retry
            0, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
            1f // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        // Add the volley post request to the request queue
        VolleySingleton.getInstance(this).addToRequestQueue(request)
    }

    @SuppressLint("SetTextI18n")
    fun runput() {


        // Post parameters
        // Form fields and values
        // Form fields and values
        val jsonObject = JSONObject()
        jsonObject.put("carrera",21)
        jsonObject.put("cedula",705)
        jsonObject.put("email","lslsl@gmail.com")
        jsonObject.put("fecha_nac","22-22-22")
        jsonObject.put("id",88)
        jsonObject.put("nombre","karl")
        jsonObject.put("telefono","22-22-22")



        // Volley post request with parameters
        val request = JsonObjectRequest(com.android.volley.Request.Method.PUT,url+"insertarAlumno/81",jsonObject,
            { response ->
                // Process the json
                try {
                    // etCord.setText("Response: $response")
                    println(response)
                }catch (e:Exception){
                    //etClima.setText("Exception: $e")
                    println(e)
                }

            }, {
                // Error in request
                //  etHumedad.setText("Volley error: $it")
                println("Error reques: t"+it)
                println("Error request:"+it)
                if(it.message?.contains("false") == true){
                    println("Falló")
                }
                if(it.message?.contains("true") == true){
                    println("Bien")
                }
            })


        // Volley request policy, only one time request to avoid duplicate transaction
        request.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
            // 0 means no retry
            0, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
            1f // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        // Add the volley post request to the request queue
        VolleySingleton.getInstance(this).addToRequestQueue(request)
    }

    @SuppressLint("SetTextI18n")
    fun rundelete() {


        // Post parameters
        // Form fields and values
        // Form fields and values
        val jsonObject = JSONObject()
        jsonObject.put("carrera",21)
        jsonObject.put("cedula",705)
        jsonObject.put("email","lslsl@gmail.com")
        jsonObject.put("fecha_nac","22-22-22")
        jsonObject.put("id",88)
        jsonObject.put("nombre","karl")
        jsonObject.put("telefono","22-22-22")



        // Volley post request with parameters
        val request = JsonObjectRequest(com.android.volley.Request.Method.DELETE,url+"eliminarAlumno/82",jsonObject,
            { response ->
                // Process the json
                try {
                    // etCord.setText("Response: $response")
                    println(response)
                }catch (e:Exception){
                    //etClima.setText("Exception: $e")
                    println(e)
                }

            }, {
                // Error in request
                //  etHumedad.setText("Volley error: $it")
                println("Error reques: t"+it)
                println("Error request:"+it)
                if(it.message?.contains("false") == true){
                    println("Falló")
                }
                if(it.message?.contains("true") == true){
                    println("Bien")
                }
            })


        // Volley request policy, only one time request to avoid duplicate transaction
        request.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
            // 0 means no retry
            0, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
            1f // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        // Add the volley post request to the request queue
        VolleySingleton.getInstance(this).addToRequestQueue(request)
    }


}