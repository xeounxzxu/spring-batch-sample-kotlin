package com.example.openfeignapi.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long>, CustomUserRepository

interface CustomUserRepository

class CustomUserRepositoryImpl : CustomUserRepository
