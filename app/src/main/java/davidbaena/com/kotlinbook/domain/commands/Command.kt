package davidbaena.com.kotlinbook.domain.commands

interface Command<out T> {
    fun execute(): T
}