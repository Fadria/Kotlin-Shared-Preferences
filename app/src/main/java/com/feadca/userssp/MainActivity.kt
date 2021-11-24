package com.feadca.userssp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.feadca.userssp.adapters.UserAdapter
import com.feadca.userssp.databinding.ActivityMainBinding
import com.feadca.userssp.model.User

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: UserAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        userAdapter = UserAdapter(getUsers()) // Inicializamos el adapter
        linearLayoutManager = LinearLayoutManager(this) // Enviamos el contexto actual


        // RecyclerView que tiene el XML del MainActivity
        binding.recyclerView.apply{
            layoutManager = linearLayoutManager
            adapter = userAdapter
        }
    }

    private fun getUsers(): MutableList<User>
    {
        val users = mutableListOf<User>()

        val user1 = User(1, "Antonio", "Adria", "")
        val user2 = User(2, "Federico", "Adria", "")

        users.add(user1)
        users.add(user2)

        return users
    }
}