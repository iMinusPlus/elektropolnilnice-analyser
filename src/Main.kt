package task

import java.io.File
import java.io.InputStream
import java.io.OutputStream

const val ERROR_STATE = 0

const val EOF = -1
const val NEWLINE = '\n'.code

fun name(symbol: Symbol) =
    when (symbol) {
        Symbol.REAL -> "real"
        Symbol.VARIABLE -> "variable"
        Symbol.PLUS -> "plus"
        Symbol.MINUS -> "minus"
        Symbol.TIMES -> "times"
        Symbol.DIVIDES -> "divides"
        Symbol.INTEGER_DIVIDES -> "integer-divides"
        Symbol.POW -> "pow"
        Symbol.LPAREN -> "lparen"
        Symbol.RPAREN -> "rparen"
        Symbol.LCURLYBRACKET -> "lcurly"
        Symbol.RCURLYBRACKET -> "rcurly"
        Symbol.QUOTATIONMARK -> "mark"
        Symbol.REGION -> "region"
        Symbol.CITY -> "city"
        Symbol.CHARGINGSTATION -> "charging_station"
        Symbol.CHARGER -> "charger"
        Symbol.PARKINGSPOT -> "parking_spot"
        Symbol.POINT -> "point"
        Symbol.BOX -> "box"

        else -> throw Error("Invalid symbol")
    }

fun printTokens(scanner: Scanner, output: OutputStream) {
    val writer = output.writer(Charsets.UTF_8)

    var token = scanner.getToken()
    while (token.symbol != Symbol.EOF) {
        writer.append("${name(token.symbol)}(\"${token.lexeme}\") ") // The output ends with a space!
        token = scanner.getToken()
    }
    writer.appendLine()
    writer.flush()
}
fun readFile(inputPath: String): String {
    return File(inputPath).readText()
}

fun main(args: Array<String>) {
    //val inputPathFile = args[0]
    //val outputPathFile = args[1]
    //printTokens(Scanner(ForForeachFFFAutomaton, readFile(inputPathFile).byteInputStream()), File(outputPathFile).outputStream())

    printTokens(Scanner(BNF, readFile("Elektropolnilnice_Primer01.txt").byteInputStream()), System.out)
}


