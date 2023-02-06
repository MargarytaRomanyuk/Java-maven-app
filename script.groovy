def buildJar() {
    echo "Testing and building the application..."
    sh 'mvn test'
    sh 'mvn package'
} 

def buildImage() {
    echo "Building the docker image..."
   withCredentials([usernamePassword(credentialsId: 'dockerhub-credenntials', passwordVariable: 'PASSWD', usernameVariable: 'USER')]) {
       sh 'docker build -t magharyta/my-repo:jma-1.1.0 .'
       sh "echo $PASSWD | docker login -u $USER --password-stdin"
       sh 'docker push magharyta/my-repo:jma-1.1.0'
    }
} 

def deployApp() {
    echo 'Deploying the application...'
} 

return this
