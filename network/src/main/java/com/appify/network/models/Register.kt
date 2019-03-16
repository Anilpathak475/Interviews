package com.appify.network.models

data class Register(val firstName: String = "",
                    val lastName: String = "",
                    val phoneNumber: String = "",
                    val email: String = "",
                    val password: String = "")