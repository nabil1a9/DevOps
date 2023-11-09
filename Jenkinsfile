pipeline {

    agent {
        node {
            label 'agent-ubuntu1'
        }
    }
    
    stages {
        stage ('GIT') {
            steps {
               echo "Getting Project from Git"; 
                    git branch: "amal", 
                    url: "git@github.com:nabil1a9/DevOps.git",
                    credentialsId: "github-credentials";
            }
        }
    
        stage("Build Artifact") {
            steps {
                sh "mvn clean package -DskipTests"
            }
        }
        
         stage("SRC Analysis Testing") {
            steps {
                sh "mvn sonar:sonar  -Dsonar.projectKey=kaddem   -Dsonar.host.url=http://192.168.33.10:9000   -Dsonar.login=fdaa27b5c74f362778d2f96760f704b5ef1e5b31"
            }
        }
        
        stage("Unit Testing") {
            steps {
                sh "echo ------"
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
