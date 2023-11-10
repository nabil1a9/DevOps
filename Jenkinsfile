pipeline {

    agent {
        node {
            label 'ubuntu-agent1'
        }
    }
    environment {
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "192.168.56.11:8081"
        SONAR_SERVER_URL = "http://192.168.249.134:9000/"
        PROJECT_NAME = "devopsBackend"
        PROJECT_KEY = "devopsBackend"
        SONAR_USERNAME = "admin"
        SONAR_PASSWORD = "123"
        NEXUS_REPOSITORY = "Devops_Project"
        DOCKER_IMAGE_NAME = "salimsghaier/springboot_devops:latest"
        DOCKER_FRONT_IMAGE_NAME = "salimsghaier/devops_angular:latest"
	
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
        stage('Build') {
            steps {
               sh "mvn clean package -DskipTests"
            }
        }
	stage("SRC Analysis Testing") {
            steps {
                sh "mvn sonar:sonar -Dsonar.projectKey=kaddemapi  -Dsonar.host.url=http://192.168.56.11:9000 -Dsonar.login=2ecfaee20a3708fc2a43d6067f0ee7c659a5a33d"
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
        stage('Docker Build') {
            steps {
                script {
                    sh 'docker build -t ${DOCKER_IMAGE_NAME} .'
                }
            }
        }

        
	stage('Deploy application with monitoring') {
                        steps {
                          sh 'docker-compose up -d'  
                            
                        }
                    }    

    }
 
}
