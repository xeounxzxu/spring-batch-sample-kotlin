package com.example.batchapp.orcale.repository

import com.example.batchapp.orcale.domain.Item
import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository : JpaRepository<Item, Long> {
}
