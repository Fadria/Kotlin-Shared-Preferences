package com.feadca.userssp

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.feadca.userssp.adapters.UserAdapter
import com.feadca.userssp.databinding.ActivityMainBinding
import com.feadca.userssp.model.User
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText

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
        val storedUsername = preferences.getString(getString(R.string.sp_username), "No user")
        Log.i("SPLog", isFirstTime.toString()) // Log del valor de SP
        Log.i("SPLog", storedUsername.toString()) // Log del valor de SP

        if(isFirstTime) // El usuario no se ha registrado
        {
            // Vista personalizada para nuestro diálogo
            val dialogView = layoutInflater.inflate(R.layout.dialog_register, null)

            MaterialAlertDialogBuilder(this)
                .setTitle(R.string.dialog_title)
                .setView(dialogView)
                .setCancelable(false) // No se puede cerrar el diálogo por ningún motivo, a no ser que nos registremos
                .setPositiveButton(R.string.dialog_confirm) { dialogInterface, i ->
                    // Nombre de usuario indicado por el usuario en el diálogo
                    val username = dialogView.findViewById<TextInputEditText>(R.id.etUsername).text.toString()
                    with(preferences.edit())
                    {
                        // Los datos se almacenan en forma de diccionario, es decir, clave => valor
                        putBoolean(getString(R.string.sp_first_time), false).commit()
                        putString(getString(R.string.sp_username), username)
                            .apply() // La inserción se ejecuta en segundo plano para no bloquear la actividad
                    }

                    // Informamos al usuario de que se ha registrado correctamente
                    Toast.makeText(this, R.string.register_success, Toast.LENGTH_LONG).show()
                }
                // Lo comentamos para que nuestro diálogo nunca se pueda cancelar
                //.setNegativeButton(R.string.dialog_cancel, null)
                .show()
        }else{
            // Obtenemos el nombre del usuario registrado y lo mostramos cada vez que abra la app
            val username = preferences.getString(getString(R.string.sp_username), getString(R.string.hint_username))
            Toast.makeText(this, "Bienvenido de nuevo, $username", Toast.LENGTH_LONG).show()
        }

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