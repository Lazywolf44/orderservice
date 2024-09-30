package com.example.orderservice.Controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/order")
class OrderController {
    @PostMapping
    fun createOrder(@RequestBody request: OrderRequest): OrderResponse {
        val appleCost = 0.6
        val orangeCost = 0.25
        val totalCost = (request.apples * appleCost) + (request.oranges * orangeCost)

        return OrderResponse(totalCost)
    }
}

data class OrderRequest(
    val apples: Int,
    val oranges: Int
)

data class OrderResponse(
    val totalCost: Double
)