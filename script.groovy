def buildJar() {
    echo "building the application..."
    sh 'mvn package'
    sh 'mvn --version'
    sh 'ls -la'
} 

def buildImage() {
    echo "building the docker image..."
   withCredentials([usernamePassword(credentialsId: 'dockerhub-credenntials', passwordVariable: 'PASSWD', usernameVariable: 'USER')]) {
       sh 'docker build -t magharyta/my-repo:jma-1.1.0 .'
       sh "echo $PASSWD | docker login -u $USER --password-stdin"
       sh 'docker push  push magharyta/my-repo:jma-1.1.0'
       sh 'ls -la'
       sh 'pwd'
       sh 'docker --version'
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this
