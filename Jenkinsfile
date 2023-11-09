pipeline {

    agent {
        node {
            label 'ubuntu-agent1'
        }
    }
    
    stages {
        stage ('GIT') {
            steps {
               echo "Getting Project from Git"; 
                    git branch: "salimBranche", 
                    url: "git@github.com:nabil1a9/DevOps.git",
                    credentialsId: "github-cred";
            }
        }
        
        stage("Unit Testing") {
            steps {
                sh "echo ------"
            }
        }
        
        stage("SRC Analysis Testing") {
            steps {
                sh "mvn sonar:sonar -Dsonar.projectKey=kaddemapi  -Dsonar.host.url=http://192.168.56.11:9000 -Dsonar.login=2ecfaee20a3708fc2a43d6067f0ee7c659a5a33d"
            }
        }
        
        stage("Build Artifact") {
            steps {
                sh "mvn clean package -DskipTests"
            }
        }
        
       stage("Deploy artifact to Nexus") {
                steps {
                    sh "mvn deploy -e -DskipTests"
                }
        }
                
        stage("Build docker image") {
            steps {
                sh "docker build -t kaddem-image ."
            }
        }
        
       stage("Deploy docker image to Nexus") {
            steps {
                sh "docker login -u 'admin' -p 'admin' localhost:8082"
                sh "docker tag kaddem-image localhost:8082/kaddem-image"
                sh "docker push localhost:8082/kaddem-image"
            }
        }
        
        stage("Run docker containers") {
            steps {
                sh "docker compose up -d"
            }
        }
        
    }
 
}
