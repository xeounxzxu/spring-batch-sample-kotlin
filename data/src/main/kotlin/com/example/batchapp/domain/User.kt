package com.example.batchapp.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "USERS")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var point: Double? = null,
    var email: String? = null,
    var gender: String? = null,
    var firstName: String? = null,
    var lastName: String? = null
) : BaseEntity() {
    constructor(
        point: Double?,
        email: String?,
        gender: String?,
        firstName: String?,
        lastName: String?
    ) : this(null, point, email, gender, firstName, lastName)
}
