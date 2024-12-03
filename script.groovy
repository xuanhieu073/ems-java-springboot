def startMySQL() {
    sh """
    docker run --rm -d \
        --name mysql-build \
        -e MYSQL_ROOT_PASSWORD=123 \
        -e MYSQL_DATABASE=ems \
        -p 5200:3306 \
        mysql:8.0
    """

    // Wait for MySQL to be ready
    sh """
    echo "Waiting for MySQL to be ready..."
    until nc -z localhost 5200; do
        sleep 1
    done
    echo "MySQL is ready!"
    """
}

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
        sh "scp docker-compose.yaml ec2-user@13.229.105.228:/home/ec2-user"
        sh "ssh -o StrictHostKeyChecking=no ec2-user@13.229.105.228 ${dockerComposeCmd}"
    }
}

return this