package com.warm

import com.warm.ext.TrackConfig

import org.gradle.api.Plugin
import org.gradle.api.Project

class TrackPlugin implements Plugin<Project> {


    @Override
    void apply(Project project) {
//        TrackExtension trackExt = project.extensions.create("track", TrackExtension, project)
        TrackConfig config = project.extensions.create("track", TrackConfig)

        project.android.registerTransform(new TrackTransform(project, config))
    }
}


