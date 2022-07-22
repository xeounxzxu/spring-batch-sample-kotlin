package io.example.dataserving.dto

import io.example.dataserving.domain.User

data class UserDTO(
    private var id: Long? = null,
    // private var createdDate: Date? = null,
    // private var updatedDate: Date? = null,
    private var point: Double? = null,
    private var email: String? = null,
    private var gender: String? = null,
    private var firstName: String? = null,
    private var lastName: String? = null
) {
    fun getId() = this.id

    // fun getCreatedDate() = this.createdDate
    // fun getUpdatedDate() = this.updatedDate
    fun getPoint() = this.point
    fun getEmail() = this.email
    fun getGender() = this.gender
    fun getFirstName() = this.firstName
    fun getLastName() = this.lastName

    fun toEntity() =
        User(
            this.point,
            this.email,
            this.gender,
            this.firstName,
            this.lastName
        )
}
