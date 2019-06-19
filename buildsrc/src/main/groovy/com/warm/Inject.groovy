package com.warm

import android.view.View
import com.google.common.io.Files
import javassist.ClassPool
import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOUtils
import org.objectweb.asm.ClassReader

import java.util.jar.JarFile
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

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
                    if (filePath.endsWith(".class") && !filePath.contains('R$') && !filePath.contains('R.class') && !filePath.contains('BuildConfig.class')) {
                        if (filePath.contains("ItemView")) {

                            def itemViewCtClass = pool.get("com.warm.app_plugin.ItemView")
                            if (itemViewCtClass.isFrozen()) {
                                itemViewCtClass.defrost()
                            }
                            itemViewCtClass.superclass = pool.get("com.warm.library_plugin.widget.TView");
                            itemViewCtClass.writeFile(dest)
                            itemViewCtClass.detach()
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

//        ZipInputStream zis = new ZipInputStream(new FileInputStream(absolutePath))

        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(dest));


        def inJarFile = new JarFile(absolutePath)


        def enumeration = inJarFile.entries();

        while (enumeration.hasMoreElements()) {
            def jarEntry = enumeration.nextElement()
            if (jarEntry == null) {
                continue
            }
            def entryName = jarEntry.name

            zos.putNextEntry(new ZipEntry(entryName))

            if (!jarEntry.isDirectory() && entryName.endsWith(".class") && !entryName.contains('R$') && !entryName.contains('R.class') && !entryName.contains('BuildConfig.class')) {
                ClassReader reader = new ClassReader(inJarFile.getInputStream(jarEntry))
                println "-----------${reader.className}----------"
                String superClassName = Utils.getClassName(reader.superName)
                if (superClassName == View.class.name) {
                    def itemViewCtClass = pool.get(Utils.getClassName(reader.className))
                    if (itemViewCtClass != null) {
                        if (itemViewCtClass.isFrozen()) {
                            itemViewCtClass.defrost()
                        }
                        itemViewCtClass.superclass = pool.get("com.warm.app_plugin.MyView")
                        zos.write(itemViewCtClass.toBytecode())
                        IOUtils.write(itemViewCtClass.toBytecode(),zos)
                        itemViewCtClass.detach()
                    }
                } else {
                    IOUtils.copy(inJarFile.getInputStream(jarEntry), zos)
                }
            } else {
                IOUtils.copy(inJarFile.getInputStream(jarEntry), zos)
            }
        }
//        zis.close()
        zos.close()

    }

}