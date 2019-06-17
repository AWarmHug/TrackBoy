package com.warm

import com.android.build.api.transform.Format
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformInvocation
import com.google.common.collect.Sets
import javassist.ClassPool
import javassist.CtClass
import javassist.JarClassPath
import org.apache.commons.io.FileUtils
import org.gradle.api.Project

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
        mProject.logger.error "-----transform开始------"
        def inputs = transformInvocation.inputs
        def outputProvider = transformInvocation.outputProvider

        outputProvider.deleteAll()

        ClassPool pool = ClassPool.getDefault()
        pool.appendClassPath(mProject.android.bootClasspath[0].toString())

        Map<String, String> dirMap = new HashMap<>();
        Map<String, String> jarMap = new HashMap<>();

        inputs.each { TransformInput input ->
            input.jarInputs.each {
                def classPath = new JarClassPath(it.file.absolutePath)
                pool.appendClassPath(classPath)

                // 重命名输出文件
                String jarName = jarInput.getName();
                String md5Name = DigestUtils.md5Hex(jarInput.getFile().getAbsolutePath());
                if (jarName.endsWith(".jar")) {
                    jarName = jarName.substring(0, jarName.length() - 4);
                }
                //生成输出路径
                File dest = outputProvider.getContentLocation(jarName + md5Name,
                        it.getContentTypes(), it.getScopes(), Format.JAR);
                jarMap.put(it.file.absolutePath,dest)

            }


            input.directoryInputs.each {
                pool.appendClassPath(it.file.absolutePath)

                // 获取output目录
                File dest = outputProvider.getContentLocation(it.name,
                        it.getContentTypes(), it.getScopes(),
                        Format.DIRECTORY);
                dirMap.put(it.file.absolutePath,dest)
            }
        }



        mProject.logger.error "-----transform结束------"

    }
}