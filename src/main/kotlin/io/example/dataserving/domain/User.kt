package io.example.dataserving.domain

import java.util.Date
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null,
    private var point: Double? = null,
    private var email: String? = null,
    private var gender: String? = null,
    private var firstName: String? = null,
    private var lastName: String? = null
) : BaseEntity() {
    constructor(
        point: Double?,
        email: String?,
        gender: String?,
        firstName: String?,
        lastName: String?
    ) : this(null, point, email, gender, firstName, lastName)
}
