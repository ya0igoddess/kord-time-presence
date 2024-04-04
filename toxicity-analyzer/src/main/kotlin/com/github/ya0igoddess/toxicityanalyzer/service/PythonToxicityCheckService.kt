package com.github.ya0igoddess.toxicityanalyzer.service

import com.github.ya0igoddess.toxicityanalyzer.model.ToxicityCheckResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class PythonToxicityCheckService : IToxicityCheck {
    private val process = ProcessBuilder()
        .directory(File("."))
        .command("python", "./analyzer_model/main.py")
        .also { it.environment()["PYTHONIOENCODING"] = "UTF-8" }
            .redirectError(File("errors.txt"))
            .start()

    private val reader = BufferedReader(InputStreamReader(process.inputStream))
    private val writer = OutputStreamWriter(process.outputStream)
    private val mutex = Mutex()

    override suspend fun checkIfMessageToxic(message: String): ToxicityCheckResult = mutex.withLock {
        withContext(Dispatchers.IO) {
            writer.write(message.replace('\n', ' ') + '\n')
            writer.flush()
            val result = reader.readLine()
            ToxicityCheckResult.fromString(result)
        }
    }
}