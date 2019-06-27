package com.warm.ext

import org.gradle.api.Project

class TrackExt {
    Project mProject;

    Config mConfig;

    TrackExt(Project project) {
        mProject = project
        mConfig = new Config()
    }

    def config(Closure closure) {
        mProject.configure(mConfig, closure)
    }

}