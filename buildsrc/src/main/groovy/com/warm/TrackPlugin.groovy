package com.warm

import com.warm.ext.TrackExt
import org.gradle.api.Plugin
import org.gradle.api.Project

class TrackPlugin implements Plugin<Project> {


    @Override
    void apply(Project project) {
        println "------TrackPlugin开始工作-----"
//        TrackExtension trackExt = project.extensions.create("track", TrackExtension, project)
        project.extensions.add("track", new TrackExt(project))



        project.android.registerTransform(new TrackTransform(project))
        println "------TrackPlugin结束工作-----"
    }
}


