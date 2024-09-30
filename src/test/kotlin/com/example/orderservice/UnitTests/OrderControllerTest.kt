// src/test/kotlin/com/example/orderservice/OrderControllerTest.kt
package com.example.orderservice

import com.example.orderservice.Controller.OrderController
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(OrderController::class)
class OrderControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `should calculate total cost`() {
        val requestBody = """{"apples": 2, "oranges": 3}"""
        val expectedTotalCost = 2 * 0.6 + 3 * 0.25

        mockMvc.perform(MockMvcRequestBuilders.post("/order")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.totalCost").value(expectedTotalCost))
    }
}
