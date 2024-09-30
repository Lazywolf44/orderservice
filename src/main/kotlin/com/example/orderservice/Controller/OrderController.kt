package com.example.orderservice.Controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
class OrderController {
    private val orders = mutableMapOf<Int, OrderResponse>()
    private var orderIdCounter = 1

    @PostMapping
    fun createOrder(@RequestBody request: OrderRequest): OrderResponse {
        val appleCost = 0.60
        val orangeCost = 0.25

        val applesToCharge = (request.apples / 2 + request.apples % 2).toDouble()
        val orangesToCharge = ((request.oranges / 3) * 2 + request.oranges % 3).toDouble()

        val totalCost = applesToCharge * appleCost + orangesToCharge * orangeCost
        val order = OrderResponse(request.apples, request.oranges, totalCost)

        orders[orderIdCounter] = order  // Store the order in memory with a unique ID
        orderIdCounter++  // Increment order ID counter

        return order
    }

    @GetMapping("/{id}")
    fun getOrderById(@PathVariable id: Int): OrderResponse? {
        return orders[id]
    }


    @GetMapping
    fun getAllOrders(): List<OrderResponse> {
        return orders.values.toList()
    }
}



data class OrderRequest(
    val apples: Int,
    val oranges: Int
)

data class OrderResponse(
    val apples: Int,
    val oranges: Int,
    val totalCost: Double
)