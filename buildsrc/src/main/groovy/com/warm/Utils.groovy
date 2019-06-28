package com.warm


class Utils {
    static String getClassName(String name) {
        return name.replace('\\', '.').replace('/', '.')
    }

    static String getFileName(String name) {
        return name.replace('.', File.separator).replace('\\', File.separator).replace('/',File.separator)
    }
}