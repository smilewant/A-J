package run.further.com.annoprocessor;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedOptions;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

@SupportedOptions(value = {"archmage_module_packageName"})
public class AnnoProcessor extends AbstractProcessor {
    private static final String SUFFIX = "$$ZYAO";
    private Types mTypeUtils;
    private Elements mElementUtils;
    private Filer mFiler;
    private Messager mMessager;
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        mTypeUtils = processingEnv.getTypeUtils();
        mElementUtils = processingEnv.getElementUtils();
        mFiler = processingEnv.getFiler();
        mMessager = processingEnv.getMessager();
        mMessager.printMessage(Diagnostic.Kind.NOTE,
                "Handle @Module element: ");
        for (Element annotatedElement : roundEnv.getElementsAnnotatedWith(Name.class)) {
            if (annotatedElement.getKind() != ElementKind.CLASS) {

            }

            Name annotation = annotatedElement.getAnnotation(Name.class);
            String name = annotation.name();
            String newClassName = name + SUFFIX;
            StringBuilder builder = new StringBuilder()
                    .append("package com.further.run.concurrent;\n\n")
                    .append("public class ")
                    .append(newClassName)
                    .append(" {\n\n") // open class
                    .append("\tpublic String getMessage() {\n") // open method
                    .append("\t\treturn \"");
            builder.append(name).append("!\\n");
            builder.append("\";\n") // end return
                    .append("\t}\n") // close method
                    .append("}\n"); // close class

            mMessager.printMessage(Diagnostic.Kind.NOTE,
                    "Handle @Module element: " + newClassName);
            try { // write the file
                JavaFileObject source = mFiler.createSourceFile("com.further.run.concurrent."+newClassName);
                Writer writer = source.openWriter();
                writer.write(builder.toString());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                // Note: calling e.printStackTrace() will print IO errors
                // that occur from the file already existing after its first run, this is normal
            }

        }
        return false;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }


    @SuppressWarnings("unchecked")
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return new LinkedHashSet() {{
            add(Name.class.getName());
        }};
    }
}
