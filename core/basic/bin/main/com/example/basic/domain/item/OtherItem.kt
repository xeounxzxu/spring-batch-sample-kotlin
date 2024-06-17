package com.example.basic.domain.item

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
@Qualifier("otherItem")
class OtherItem : Item {
    override fun getName(): String = "other_item"
}
