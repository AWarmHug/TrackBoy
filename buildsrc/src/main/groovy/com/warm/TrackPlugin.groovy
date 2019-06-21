package com.warm

import org.gradle.api.Plugin
import org.gradle.api.Project


class TrackPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println "------TrackPlugin开始工作-----"
        project.android.registerTransform(new TrackTransform(project))
        println "------TrackPlugin结束工作-----"
    }
}


