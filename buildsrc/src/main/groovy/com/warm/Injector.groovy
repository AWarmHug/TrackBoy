package com.warm


import com.google.common.io.Files
import com.warm.ext.TrackConfig
import javassist.ClassPool
import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOUtils
import org.objectweb.asm.ClassReader

import java.util.jar.JarFile
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

class Injector {

    public static final String PACKAGE_WIDGET = "com/warm/library_plugin/widget";

    public static final String without = "com${File.separator}warm${File.separator}library_plugin"


    public static Map<String, String> clazz = new HashMap<>()


    static injectDir(ClassPool pool, String absolutePath, String dest, TrackConfig config) {
        println "------------------"
        println("absolutePath=${absolutePath}")
        println "dest=${dest}"

        File dir = new File(absolutePath);

        if (dir.isDirectory()) {
            dir.eachFileRecurse { File file ->
                String filePath = file.absolutePath

                if (file.isFile()) {
                    File out = new File(dest + filePath.substring(absolutePath.length()))
                    Files.createParentDirs(out)
                    if (filePath.endsWith(".class") && !filePath.contains('R$') && !filePath.contains('R.class') && !filePath.contains('BuildConfig.class') && config.filter(filePath)) {
                        println "--------正在转换----------"
                        println("Path=${filePath}")
                        println "Dest=${dest}"

                        ClassReader reader = new ClassReader(new FileInputStream(filePath))
                        String superClassName = Utils.getClassName(reader.superName)

                        String value = clazz.get(superClassName)

                        if (value != null) {
                            def itemViewCtClass = pool.get(Utils.getClassName(reader.className))
                            if (itemViewCtClass.isFrozen()) {
                                itemViewCtClass.defrost()
                            }
                            itemViewCtClass.superclass = pool.get(value);
                            itemViewCtClass.writeFile(dest)
//                            IOUtils.write(itemViewCtClass.toBytecode(),new FileOutputStream(dest + filePath.substring(absolutePath.length())))
                            itemViewCtClass.detach()
                        } else {
                            FileUtils.copyFile(file, out)
                        }
                    } else {
                        FileUtils.copyFile(file, out)
                    }
                }

            }
        }

    }

    static injectJar(ClassPool pool, String absolutePath, String dest, TrackConfig config) {

        println "------------------"
        println "absolutePath=${absolutePath}"
        println "dest=${dest}"

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

            if (!jarEntry.isDirectory() && entryName.endsWith(".class") && !entryName.contains('R$') && !entryName.contains('R.class') && !entryName.contains('BuildConfig.class') && config.filter(entryName)) {

                println "--------正在转换----------"
                println("Path=${entryName}")
                println "Dest=${dest}"

                ClassReader reader = new ClassReader(inJarFile.getInputStream(jarEntry))
                String superClassName = Utils.getClassName(reader.superName)

                String value = clazz.get(superClassName)

                if (value != null) {
                    def itemViewCtClass = pool.get(Utils.getClassName(reader.className))
                    if (itemViewCtClass != null) {
                        if (itemViewCtClass.isFrozen()) {
                            itemViewCtClass.defrost()
                        }
                        itemViewCtClass.superclass = pool.get(value)
                        IOUtils.write(itemViewCtClass.toBytecode(), zos)
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