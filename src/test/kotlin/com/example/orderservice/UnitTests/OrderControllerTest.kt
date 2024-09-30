// src/test/kotlin/com/example/orderservice/OrderControllerTest.kt
package com.example.orderservice

import com.example.orderservice.Controller.OrderController
import org.junit.jupiter.api.Test
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
    fun `should apply buy one get one free for apples and 3 for 2 on oranges`() {
        val requestBody = """{"apples": 3, "oranges": 4}"""
        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.totalCost").value(1.20 + 0.75))
    }

    @Test
    fun `should retrieve an order by ID`() {
        val requestBody = """{"apples": 4, "oranges": 3}"""
        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(MockMvcResultMatchers.status().isOk)

        mockMvc.perform(MockMvcRequestBuilders.get("/orders/1"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.apples").value(4))
    }

    @Test
    fun `should retrieve all orders`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/orders"))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }


}
