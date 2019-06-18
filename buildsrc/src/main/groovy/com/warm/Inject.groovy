package com.warm

import com.google.common.io.Files
import javassist.ClassPool
import javassist.CtClass
import org.apache.commons.io.FileUtils


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

        FileUtils.copyFile(new File(absolutePath), new File(dest))

    }

}