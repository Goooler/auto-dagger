package se.ansman.deager

import assertk.assertThat
import assertk.assertions.contains
import com.tschuchort.compiletesting.KotlinCompilation
import org.intellij.lang.annotations.Language
import java.io.File
import java.io.OutputStream
import kotlin.test.assertEquals

abstract class DeagerCompilation(protected val workingDir: File) {
    fun compile(@Language("kotlin") vararg sources: String): Result =
        compile(sources.mapIndexed { i, contents ->
            TestSourceFile.Inline.kotlin(contents, "Test$i.kt")
        })

    fun compile(vararg sources: TestSourceFile): Result = compile(sources.asList())

    fun compile(sources: List<TestSourceFile>): Result =
        compile(sources) {
            workingDir = this@DeagerCompilation.workingDir
            inheritClassPath = true
            messageOutputStream = OutputStream.nullOutputStream()
        }

    protected abstract fun compile(
        sources: List<TestSourceFile>,
        configuration: KotlinCompilation.() -> Unit
    ): Result

    protected abstract val KotlinCompilation.Result.filesGeneratedByAnnotationProcessor: Sequence<File>

    inner class Result(private val result: KotlinCompilation.Result) {
        val exitCode: KotlinCompilation.ExitCode get() = result.exitCode
        val messages: String get() = result.messages

        val filesGeneratedByAnnotationProcessor: Sequence<File> get() = result.filesGeneratedByAnnotationProcessor

        fun assertIsSuccessful() {
            assertEquals(KotlinCompilation.ExitCode.OK, exitCode, messages)
        }

        fun assertFailedWithMessage(message: String) {
            assertEquals(KotlinCompilation.ExitCode.COMPILATION_ERROR, exitCode, messages)
            assertThat(messages).contains(message)
        }


        fun findGeneratedFile(name: String): File? =
            filesGeneratedByAnnotationProcessor.find { it.name == name }

        fun readGeneratedFile(name: String): String =
            requireNotNull(findGeneratedFile(name)) {
                "No file was generated with name $name. Generated files: ${filesGeneratedByAnnotationProcessor.joinToString { it.name }}\n$messages".trim()
            }.readText().trim()

        fun loadClass(className: String): Class<*> = result.classLoader.loadClass(className)
    }

}