package com.warm

import com.android.build.api.transform.*
import com.google.common.collect.Sets
import javassist.ClassPool
import javassist.CtClass
import javassist.JarClassPath
import org.apache.commons.codec.digest.DigestUtils
import org.gradle.api.Project
import org.objectweb.asm.ClassReader

import java.util.jar.JarFile

class JavassistTransform extends Transform {
    Project mProject

    JavassistTransform(Project project) {
        mProject = project
    }

    @Override
    String getName() {
        return "JavassistTransform"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return Sets.immutableEnumSet(QualifiedContent.DefaultContentType.CLASSES)
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return Sets.immutableEnumSet(QualifiedContent.Scope.PROJECT, QualifiedContent.Scope.PROJECT_LOCAL_DEPS,
                QualifiedContent.Scope.SUB_PROJECTS, QualifiedContent.Scope.SUB_PROJECTS_LOCAL_DEPS,
                QualifiedContent.Scope.EXTERNAL_LIBRARIES)
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation)
        println "-----transform开始------"
        def inputs = transformInvocation.inputs
        def outputProvider = transformInvocation.outputProvider

        outputProvider.deleteAll()

        ClassPool pool = ClassPool.getDefault()
        pool.appendClassPath(mProject.android.bootClasspath[0].toString())

        Map<String, String> dirMap = new HashMap<>();
        Map<String, String> jarMap = new HashMap<>();

        inputs.each { TransformInput input ->
            input.directoryInputs.each {
                pool.appendClassPath(it.file.absolutePath)

                // 获取output目录
                File dest = outputProvider.getContentLocation(it.name,
                        it.getContentTypes(), it.getScopes(),
                        Format.DIRECTORY);
                dirMap.put(it.file.absolutePath, dest.absolutePath)

            }

            input.jarInputs.each {
                def classPath = new JarClassPath(it.file.absolutePath)
                pool.appendClassPath(classPath)

                // 重命名输出文件
                String jarName = it.name;
                String md5Name = DigestUtils.md5Hex(it.file.absolutePath);
                if (jarName.endsWith(".jar")) {
                    jarName = jarName.substring(0, jarName.length() - 4);
                }
                //生成输出路径
                File dest = outputProvider.getContentLocation(jarName + md5Name,
                        it.getContentTypes(), it.getScopes(), Format.JAR);
                jarMap.put(it.file.absolutePath, dest.absolutePath)

            }

        }

        dirMap.each {
            if (it.key.contains(Inject.PACKAGE_WIDGET)) {
                File file = new File(it.key)
                ClassReader reader = new ClassReader(new FileInputStream(file))
                CtClass ctClass = pool.get(Utils.getClassName(reader.className));
                Inject.clazz.put(ctClass.superclass.name, ctClass.name)
//                if (ctClass.superclass.name.contains("AppCompat")) {
//                    Inject.clazz.put(ctClass.superclass.superclass.name, ctClass.name)
//                }

            }
        }


        jarMap.each {

            def inJarFile = new JarFile(it.key)


            def enumeration = inJarFile.entries();

            while (enumeration.hasMoreElements()) {
                def jarEntry = enumeration.nextElement()
                if (jarEntry == null) {
                    continue
                }
                def entryName = jarEntry.name
                if (!jarEntry.isDirectory() && entryName.contains(Inject.PACKAGE_WIDGET)) {
                    ClassReader reader = new ClassReader(inJarFile.getInputStream(jarEntry))
                    CtClass ctClass = pool.get(Utils.getClassName(reader.className));
                    Inject.clazz.put(ctClass.superclass.name, ctClass.name)
//                    if (ctClass.superclass.name.contains("AppCompat")) {
//                        Inject.clazz.put(ctClass.superclass.superclass.name, ctClass.name)
//                    }

                }
            }
        }

        println Inject.clazz.toMapString()

        println Inject.clazz.size()


        println("-----处理Dir开始-----")
        dirMap.each {
            Inject.injectDir(pool, it.key, it.value)
        }
        println("-----处理Dir结束-----")


        println("-----处理Jar开始-----")
        jarMap.each {
            Inject.injectJar(pool, it.key, it.value)
        }
        println("-----处理Jar结束-----")


        println "-----transform结束------"

    }


}