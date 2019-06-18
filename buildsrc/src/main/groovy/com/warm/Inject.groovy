package com.warm

import com.google.common.io.Files
import javassist.ClassPool
import javassist.CtClass
import org.apache.commons.io.FileUtils
import org.objectweb.asm.ClassReader
import sun.tools.jar.resources.jar

import java.util.jar.JarFile
import java.util.zip.ZipFile


class Inject {
    static injectDir(ClassPool pool, String absolutePath, String dest) {

        println "------------------"
        println("absolutePath=${absolutePath},dest=${dest}")

        File dir = new File(absolutePath);

        if (dir.isDirectory()) {
            dir.eachFileRecurse {
                String filePath = it.absolutePath
                if (it.isFile()) {
                    File out = new File(dest + filePath.substring(absolutePath.length()))
                    Files.createParentDirs(out)
                    if (filePath.endsWith(".class") && !filePath.contains('R$') && !filePath.contains('R.class') && !filePath.contains('$') && !filePath.contains('BuildConfig.class')) {
                        if (filePath.contains("ItemView")) {

                            def itemViewCtClass = pool.get("com.warm.app_plugin.ItemView")
                            if (itemViewCtClass.isFrozen()) {
                                itemViewCtClass.defrost()
                            }
                            itemViewCtClass.superclass = pool.get("com.warm.app_plugin.MyView");
                            itemViewCtClass.writeFile(dest)
                        } else {
                            FileUtils.copyFile(it, out)
                        }
                    } else {
                        FileUtils.copyFile(it, out)
                    }
                }

            }
        }

    }

    static injectJar(ClassPool pool, String absolutePath, String dest) {
        println "------------------"
        println("absolutePath=${absolutePath},dest=${dest}")
        println "------------------"
        Files.createParentDirs(new File(dest))

        def jarFile = new JarFile(absolutePath)

        def enumeration = jarFile.entries();

        while (enumeration.hasMoreElements()) {
            def jarEntry = enumeration.nextElement()
            def entryName = jarEntry.name
            if (!jarEntry.isDirectory() && entryName.endsWith(".class") && !entryName.contains('R$') && !entryName.contains('R.class') && !entryName.contains('$') && !entryName.contains('BuildConfig.class')) {
                ClassReader reader = new ClassReader(jarFile.getInputStream(jarEntry));
                if (reader.superName == "android/view/View"){
                    def itemViewCtClass = pool.get(reader.className)
                    if (itemViewCtClass.isFrozen()) {
                        itemViewCtClass.defrost()
                    }
                    itemViewCtClass.superclass = pool.get("com.warm.app_plugin.MyView")
                    itemViewCtClass.writeFile()
                }else {

                }
            }

        }

        FileUtils.copyFile(new File(absolutePath), new File(dest))

    }

}