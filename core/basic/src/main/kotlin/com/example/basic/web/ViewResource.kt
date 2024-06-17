package com.example.basic.web

import com.example.basic.dto.TempDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ViewResource {
    @GetMapping("/hello")
    fun shoByHello(model: Model): String {
        var tempDTO = TempDTO("vvv", "vvv2")
        model.addAttribute("user", tempDTO)
        return "hello"
    }
}
