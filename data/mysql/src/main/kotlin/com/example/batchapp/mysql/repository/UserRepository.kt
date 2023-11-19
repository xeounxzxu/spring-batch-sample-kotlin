package com.example.batchapp.mysql.repository

import com.example.batchapp.mysql.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long>
