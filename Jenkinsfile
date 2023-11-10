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
	stage("Static Code Analysis: SonarQube") {
            steps {
                sh "mvn sonar:sonar  -Dsonar.projectKey=kaddem   -Dsonar.host.url=http://192.168.33.10:9000   -Dsonar.login=squ_30274d182b05e6cad5e3b4e53b11635c53d369e6"
            }
        }

	stage("Publish to Nexus Repository Manager") {
            steps {
                script {
                    def nexusRepository = "Devops_Project"
                    pom = readMavenPom file: "pom.xml"
                    filesByGlob = findFiles(glob: "target/*.${pom.packaging}")
                    echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                    artifactPath = filesByGlob[0].path
                    artifactExists = fileExists artifactPath

                    if (artifactExists) {
                        echo "* File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}"
                        nexusArtifactUploader(
                            nexusVersion: NEXUS_VERSION,
                            protocol: NEXUS_PROTOCOL,
                            nexusUrl: NEXUS_URL,
                            groupId: pom.groupId,
                            version: pom.version,
                            repository: nexusRepository,
                            credentialsId: 'nexus-cred',
                            artifacts: [
                                [artifactId: pom.artifactId, classifier: '', file: artifactPath, type: pom.packaging],
                                [artifactId: pom.artifactId, classifier: '', file: "pom.xml", type: "pom"]
                            ]
                        )
                    } else {
                        error "* File: ${artifactPath}, could not be found"
                    }
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
    
    }}
