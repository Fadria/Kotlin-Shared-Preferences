package com.feadca.userssp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.feadca.userssp.OnClickListener
import com.feadca.userssp.R
import com.feadca.userssp.databinding.ItemUserBinding
import com.feadca.userssp.model.User

class UserAdapter(private val users: List<User>, private val listener:OnClickListener):
    RecyclerView.Adapter<UserAdapter.ViewHolder>()
{
    private lateinit var context: Context

    // Infla la vista XML en el listado
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)

        return ViewHolder(view)
    }

    // Rellena la información de la lista, funcionamiento similar a un foreach
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]

        with(holder)
        {
            setListener(user, position+1)
            binding.tvOrder.text = (position + 1).toString()
            binding.tvName.text = user.getFullName()
            Glide.with(context) // Librería para cargar imágenes de internet
                .load(user.url) // Cargamos la imagen del usuario
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop() // Imagen circular
                .into(binding.imgPhoto) // Donde volcamos la imagen
        }
    }

    // Indica cuántos elementos hay en el adapter
    override fun getItemCount(): Int = users.size

    // Usamos inner para indicar que se trata de una clase interna
    // Esta clase contiene las viastas de cada objeto en item_user.xml
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        val binding = ItemUserBinding.bind(view)

        fun setListener(user: User, position: Int)
        {
            binding.root.setOnClickListener{listener.onClick(user, position)}
        }
    }
}