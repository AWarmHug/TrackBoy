package com.warm

class Utils{
    static String getClassName( String name) {
        return name.replace('\\', '.').replace('/', '.')
    }
}