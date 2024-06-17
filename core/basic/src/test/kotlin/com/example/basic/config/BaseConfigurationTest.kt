package com.example.basic.config

import com.example.basic.domain.item.Item
import com.example.basic.domain.item.MyItem
import com.example.basic.domain.item.OtherItem
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
internal class BaseConfigurationTest {
    private var context: AnnotationConfigApplicationContext? = null

    @BeforeEach
    fun init() {
        context =
            AnnotationConfigApplicationContext().apply {
                this.scan("com.example.base.config.base")
                this.refresh()
            }
    }

    @Test
    @Disabled
    fun `defaultYn Bean 생성 유무 체크`() {
        val defaultYn = context?.getBean("defaultYn") as Boolean
        Assertions.assertThat(defaultYn::class.java).isEqualTo(Boolean::class.java)
    }

    @Test
    @Disabled
    fun `myItem Bean 등록 유무 체크`() {
        val myItem = context?.getBean("myItem") as Item

        Assertions.assertThat(myItem::class.java).isEqualTo(MyItem::class.java)
        org.junit.jupiter.api.Assertions.assertEquals(myItem.getName(), "my_item")
    }

    @Test
    @Disabled
    fun `otherItem Bean 등록 유무 체크`() {
        val myItem = context?.getBean("otherItem") as Item

        Assertions.assertThat(myItem::class.java).isEqualTo(OtherItem::class.java)
        org.junit.jupiter.api.Assertions.assertEquals(myItem.getName(), "other_item")
    }
}
