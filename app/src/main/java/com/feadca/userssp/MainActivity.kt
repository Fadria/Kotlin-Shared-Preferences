package com.feadca.userssp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.feadca.userssp.adapters.UserAdapter
import com.feadca.userssp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: UserAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAdapter = UserAdapter(mutableListOf()) // Inicializamos el adapter
        linearLayoutManager = LinearLayoutManager(this) // Enviamos el contexto actual

        // RecyclerView que tiene el XML del MainActivity
        binding.recyclerView.apply{
            layoutManager = linearLayoutManager
            adapter = userAdapter
        }
    }
}