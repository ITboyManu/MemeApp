package com.example.memeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.memeapp.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
   val url:String="https://meme-api.com/gimme"
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
    }

    private fun getData() {


        val queue = Volley.newRequestQueue(this)

// Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val jsonobject=JSONObject(response)

                binding.textView2.text=jsonobject.getString("title")
                binding.textView3.text=jsonobject.getString("author")
                Glide.with(this@MainActivity).load(jsonobject.get("url")).into(binding.imageView)

                Log.e("response","memedata"+response.toString())
            },
            {error ->
                Toast.makeText(this@MainActivity,"${error.localizedMessage}",Toast.LENGTH_SHORT)
                    .show()

            })

// Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

}