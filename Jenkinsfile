def gv

pipeline {
    agent any
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("build jar") {
                agent {
                   dockerfile {
                       filename 'Dockerfile.build'
                       dir 'build'
                       label 'my-defined-label'
                       additionalBuildArgs  '--build-arg version=1.0.2'
                       args '-v /tmp:/tmp'
                   }
                }
            steps {
                script {
                    echo "building jar"
                    gv.buildJar()
                    gv.buildImage()
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    echo "building image"
                 //   gv.buildImage()
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    echo "deploying"
                    gv.deployApp()
                }
            }
        }
    }   
}
