pipeline {
    agent any
    //parameters {
      //  booleanParam(name: 'executeTest', defaultValue: true, description: '')
   // }
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage ("test app") {
             agent {
                docker { image 'maven:latest' }
            }
            steps {
                script {
                    echo "testing app"
                    gv.testPrejar()
                }
            }
        }
        stage("build jar") {
            when {
                expression {
                    BRANCH_NAME == 'dev'
                }
            }
             agent {
                docker { image 'maven:latest' }
            }
            steps {
                script {
                    echo "building jar"
                    gv.buildJar()
                }
            }
        }
        stage("build image") {
            when {
                expression {
                    BRANCH_NAME == 'dev'
                }
            }                
            agent any
            steps {
                script {
                    echo "building image"
                    gv.buildImage()
                }
            }
        }
        stage("deploy") {  
            when {
                expression {
                    BRANCH_NAME == 'main'
                }
            }
            steps {
                script {
                    echo "deploying"
                    gv.deployApp()
                }
            }
        }
    }   
}
