package se.ansman.dagger.auto.compiler.kapt.processing

import com.squareup.javapoet.AnnotationSpec
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.TypeName
import se.ansman.dagger.auto.compiler.TypeLookup
import se.ansman.dagger.auto.compiler.kapt.JavaPoetRenderEngine
import se.ansman.dagger.auto.compiler.processing.AutoDaggerEnvironment
import se.ansman.dagger.auto.compiler.processing.AutoDaggerLogger
import se.ansman.dagger.auto.compiler.processing.RenderEngine
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.Element

class KaptEnvironment(
    val environment: ProcessingEnvironment,
) : AutoDaggerEnvironment<Element, TypeName, ClassName, AnnotationSpec, JavaFile> {
    override val renderEngine: RenderEngine<TypeName, ClassName, AnnotationSpec> get() = JavaPoetRenderEngine
    override val logger: AutoDaggerLogger<Element> = AutoDaggerKaptLogger(
        messager = environment.messager,
        enableLogging = environment.options[AutoDaggerKaptLogger.enableLogging]?.toBooleanStrict() ?: false
    )

    val typeLookup = TypeLookup(environment.elementUtils::getTypeElement)

    override fun write(file: JavaFile) {
        file.writeTo(environment.filer)
    }
}