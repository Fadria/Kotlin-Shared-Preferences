package com.feadca.userssp

import com.feadca.userssp.model.User

interface OnClickListener {
    fun onClick(user: User, position: Int)
}