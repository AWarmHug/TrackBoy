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
    List<JarClassPath> classPathList = new ArrayList()

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
        transformInvocation.inputs.each { TransformInput input ->
            try {
                input.jarInputs.each {

                    ClassPool pool = ClassPool.getDefault()
                    def classPath = new JarClassPath(it.file.absolutePath)
                    classPathList.add(classPath)
                    pool.appendClassPath(classPath)

                    //project.android.bootClasspath 加入android.jar，否则找不到android相关的所有类
                    pool.appendClassPath(mProject.android.bootClasspath[0].toString())
                    // 重命名输出文件（同目录copyFile会冲突）
                    String outputFileName = it.name.replace(".jar", "") + '-' + it.file.path.hashCode()
                    def output = transformInvocation.outputProvider.getContentLocation(outputFileName, it.contentTypes, it.scopes, Format.JAR)
                    FileUtils.copyFile(it.file, output)
                }
            } catch (Exception e) {
                mProject.logger.error e.getMessage()
            }

            input.directoryInputs.each {


                ClassPool pool = ClassPool.getDefault()
                pool.appendClassPath(it.file.absolutePath)
                pool.appendClassPath(mProject.android.bootClasspath[0].toString())

                File dir = new File(it.file.absolutePath)
                if (!dir.isDirectory()) {
                    return
                }
                dir.eachFileRecurse { File file ->
                    String filePath = file.absolutePath
                    if (filePath.endsWith(".class") && !filePath.contains('R$') && !filePath.contains('$')//代理类
                            && !filePath.contains('R.class') && !filePath.contains("BuildConfig.class")) {
                        CtClass ctClass = pool.get("android.widget.TextView")
                        if (ctClass.isFrozen()) ctClass.defrost()

                        ctClass.setSuperclass(pool.get("com.warm.app_plugin.MyView"))
                        ctClass.writeFile()

                        ctClass.detach()
                    }
                }

                // 获取output目录
                def dest = transformInvocation.outputProvider.getContentLocation(it.name,
                        it.contentTypes, it.scopes,
                        Format.DIRECTORY)

                // 将input的目录复制到output指定目录
                FileUtils.copyDirectory(it.file, dest)
            }
        }

        ClassPool pool = ClassPool.getDefault()
        classPathList.each {
            try {
                pool.removeClassPath(it)
            } catch (Exception e) {
                project.logger.error(e.getMessage())
            }
        }
        classPathList.clear()

    }
}