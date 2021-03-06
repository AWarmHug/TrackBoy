package com.warm

import com.android.build.api.transform.*
import com.google.common.collect.Sets
import com.warm.ext.TrackConfig
import javassist.ClassPool
import javassist.CtClass
import javassist.JarClassPath
import org.apache.commons.codec.digest.DigestUtils
import org.gradle.api.Project
import org.objectweb.asm.ClassReader

import java.util.jar.JarFile

class TrackTransform extends Transform {
    private TrackConfig config;
    Project project

    TrackTransform(Project project, TrackConfig config) {
        this.project = project
        this.config = config
    }

    @Override
    String getName() {
        return "TrackTransform"
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
        config.excludes.add(Injector.without)
        println config

        println "Track-Plugin-----transform开始------"
        def inputs = transformInvocation.inputs
        def outputProvider = transformInvocation.outputProvider

        outputProvider.deleteAll()

        ClassPool pool = ClassPool.getDefault()
        pool.appendClassPath(project.android.bootClasspath[0].toString())

        Map<String, String> dirMap = new HashMap<>();
        Map<String, String> jarMap = new HashMap<>();

        inputs.each { TransformInput input ->
            input.directoryInputs.each {
                pool.appendClassPath(it.file.absolutePath)

                // 获取output目录
                File dest = outputProvider.getContentLocation(it.name,
                        it.getContentTypes(), it.getScopes(),
                        Format.DIRECTORY)


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

        println Injector.PACKAGE_WIDGET
        println Injector.without

        dirMap.each {
            if (it.key.contains(Injector.PACKAGE_WIDGET)) {
                File file = new File(it.key)
                ClassReader reader = new ClassReader(new FileInputStream(file))
                CtClass ctClass = pool.get(Utils.getClassName(reader.className));
                Injector.clazz.put(ctClass.superclass.name, ctClass.name)
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
                if (!jarEntry.isDirectory() && entryName.contains(Injector.PACKAGE_WIDGET)) {
                    ClassReader reader = new ClassReader(inJarFile.getInputStream(jarEntry))
                    CtClass ctClass = pool.get(Utils.getClassName(reader.className));
                    Injector.clazz.put(ctClass.superclass.name, ctClass.name)

                }
            }
        }

        println Injector.clazz.toMapString()

        println Injector.clazz.size()


        println("Track-Plugin-----处理Dir开始-----")
        dirMap.each {
            Injector.injectDir(pool, it.key, it.value,config)
        }
        println("Track-Plugin-----处理Dir结束-----")


        println("Track-Plugin-----处理Jar开始-----")
        jarMap.each {
            Injector.injectJar(pool, it.key, it.value,config)
        }
        println("Track-Plugin-----处理Jar结束-----")


        println "Track-Plugin-----transform结束------"

    }


}