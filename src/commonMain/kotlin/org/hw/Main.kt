package org.hw
import org.hw.greet.greet
import org.hw.greet.add
fun main() {
    println(greet("Kotlin"))
    println("2 + 3 = ${add(2, 3)}")
    for (i in 1..5) {
        println("i = $i")
    }
}
