package com.example.sleuthdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SleuthDemoApplication

fun main(args: Array<String>) {
    runApplication<SleuthDemoApplication>(*args)
}
