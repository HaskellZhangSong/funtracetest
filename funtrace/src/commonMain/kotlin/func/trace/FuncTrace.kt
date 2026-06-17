package func.trace

fun _funcTraceEnter(functionName: String) {
    println(">>> [TRACE] Entering $functionName")
}

fun _funcTraceExit(functionName: String) {
    println("<<< [TRACE] Exiting $functionName")
}

