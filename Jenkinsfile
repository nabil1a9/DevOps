pipeline {

    agent {
        node {
            label 'ubuntu-agent'
        }
    }
    environment {
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "192.168.249.134:8081"
        SONAR_SERVER_URL = "http://192.168.249.134:9000/"
        PROJECT_NAME = "devopsBackend"
        PROJECT_KEY = "devopsBackend"
        SONAR_USERNAME = "admin"
        SONAR_PASSWORD = "123"
        NEXUS_REPOSITORY = "Devops_Project"
        DOCKER_IMAGE_NAME = "enisgharbia/springboot_devops:latest"
        DOCKER_FRONT_IMAGE_NAME = "enisgharbia/devops_angular:latest"
	
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
        stage('Docker Push') {
    steps {
        withCredentials([usernamePassword(credentialsId: 'dockerHub', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
            sh "echo ${env.dockerHubPassword} | docker login -u ${env.dockerHubUser} --password-stdin"
            sh "docker push ${DOCKER_IMAGE_NAME}"
        }
    }
    }
    stage("Static Code Analysis: SonarQube") {
            steps {
                sh "mvn sonar:sonar  -Dsonar.projectKey=kaddem   -Dsonar.host.url=http://192.168.33.10:9000   -Dsonar.login=e72d4ee92e195fecea3c413fbd800e149def1550"
            }
        }
    }}
