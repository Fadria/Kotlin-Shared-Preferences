package com.feadca.userssp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.feadca.userssp.adapters.UserAdapter
import com.feadca.userssp.databinding.ActivityMainBinding
import com.feadca.userssp.model.User

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: UserAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preferences = getPreferences(Context.MODE_PRIVATE) // Almacenamiento interno

        // Obtenemos el valor del SP o el valor true, si este no existe
        val isFirstTime = preferences.getBoolean(getString(R.string.sp_first_time), true)
        Log.i("SPFirstTime", isFirstTime.toString()) // Log del valor de SP

        userAdapter = UserAdapter(getUsers(), this) // Inicializamos el adapter
        linearLayoutManager = LinearLayoutManager(this) // Enviamos el contexto actual


        // RecyclerView que tiene el XML del MainActivity
        binding.recyclerView.apply{
            setHasFixedSize(true) // Las vistas siempre medirán lo mismo, optimizamos el rendimiento
            layoutManager = linearLayoutManager
            adapter = userAdapter
        }
    }

    private fun getUsers(): MutableList<User>
    {
        val users = mutableListOf<User>()

        val user1 = User(1, "Federico", "Adria", "https://media-exp1.licdn.com/dms/image/C5603AQFkkkMUS03b-g/profile-displayphoto-shrink_100_100/0/1519306801283?e=1643241600&v=beta&t=5H2HPHbtWyHipjarXyXNCZaQyJtZD4TIzbR1uCxq3qU")
        val user2 = User(2, "Antonio", "Adria", "https://cdn.pixabay.com/photo/2016/11/13/10/06/budgie-1820620_1280.jpg")

        users.add(user1)
        users.add(user2)
        users.add(user1)
        users.add(user2)
        users.add(user1)
        users.add(user2)
        users.add(user1)
        users.add(user2)
        users.add(user1)
        users.add(user2)
        users.add(user1)
        users.add(user2)
        users.add(user1)
        users.add(user2)
        users.add(user1)
        users.add(user2)

        return users
    }

    override fun onClick(user: User, position: Int) {
        Toast.makeText(this, "$position: ${user.getFullName()}", Toast.LENGTH_SHORT).show()
    }
}