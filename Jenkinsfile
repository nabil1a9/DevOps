pipeline {

    agent {
        node {
            label 'ubuntu-agent'
        }
    }
    
    stages {
        stage ('GIT') {
            steps {
               echo "Getting Project from Git"; 
                    git branch: "enis", 
                    url: "https://github.com/nabil1a9/DevOps.git",
                    credentialsId: "github-cred";
            }
        }
        stage('Build') {
            steps {
                sh "mvn clean package -DskipTests"
            }
        }

        stage('Docker Build') {
      steps {
          script {
      	sh 'docker build -t enisgharbia/springboot_devops:latest .'
      }
      }
    }
    }}
