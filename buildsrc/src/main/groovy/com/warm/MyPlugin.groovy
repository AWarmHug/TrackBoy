package com.warm

import org.gradle.api.Plugin
import org.gradle.api.Project


class MyPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println "------TrackPlugin开始工作-----"
        project.android.registerTransform(new JavassistTransform(project))
    }
}


