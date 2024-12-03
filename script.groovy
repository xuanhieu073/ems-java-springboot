def buildJar() {
    echo "bulding the application"
    sh "mvn package"
}

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'dockerhub-credential', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t xuanhieu073/ems:1.0 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push xuanhieu073/ems:1.0"
    }
}

def deployApp() {
    echo "deploying the application..."
    // def dockerCmd = "docker run -p 8080:8080 -d xuanhieu073/demo-app:jma-2.0"
    def dockerComposeCmd = "docker-compose -f docker-compose.yaml up --detach"
    sshagent(['my-server-key']) {
        sh "ssh -o StrictHostKeyChecking=no ec2-user@54.169.156.157 ${dockerComposeCmd}"
        sh "scp docker-compose.yaml ec2-user@54.169.156.157:/home/ec2-user"
    }
}

return this