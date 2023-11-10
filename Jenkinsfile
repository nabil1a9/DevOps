

pipeline {

    agent any

    stages {
        stage('GIT') {
            steps {
                // Checkout the code from your Git repository
                script {
                    checkout scm
                }
            }
        }

        stage('MAVEN') {
            steps {
                // Run the Maven clean command
                script {
                    sh 'mvn clean compile'
		}
            }
        }

         stage('JUNIT/JACOCO') {
    steps {
        script {
            // Set up Maven
            def mvnHome = tool 'M2_HOME'
            // Run tests with Maven, this will also generate the JaCoCo reports
            sh "${mvnHome}/bin/mvn clean test"
        }
    }
    post {
        always {
            // Publish JUnit test results
            junit '**/target/surefire-reports/TEST-*.xml'

            // Publish JaCoCo test coverage report
            jacoco(
                execPattern: '*/*.exec', // Path to JaCoCo exec files
                classPattern: '**/classes', // Path to class files
                sourcePattern: '**/src/main/java' // Path to source files
            )
        }
    }
}
       stage('SonarQube Analysis') {
    	    steps {
               // Execute SonarQube analysis using Maven
	     	    withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                    sh "mvn sonar:sonar -Dsonar.login=${env.SONAR_TOKEN}"
		   }
    		}
	    }

      stage('NEXUS') {
            steps {
  		        script {
          		        sh 'mvn deploy '
			}
          }

       }
         stage('DOCKER BUILD') {
        steps {
            script {

                // Le point (.) indique que le contexte de build est le répertoire courant
                sh 'docker build -t walid0999/kaddem-app:0.0.1 .'
            }
        }
         }
            stage('DOCKER PUSH') {
        steps {
            script {


                 // Using withCredentials to securely inject the Docker Hub token
            withCredentials([string(credentialsId: 'dock_token', variable: 'DOCKERHUB_TOKEN')]) {
                // Logging into Docker Hub using the token
                sh 'echo $DOCKERHUB_TOKEN | docker login -u walid0999 --password-stdin'
            }

                    // Push the image to Docker Hub
                    sh 'docker push walid0999/kaddem-app:0.0.1'
                }
            }

                }
                stage('Docker Compose BUILD/RUN') {
            steps {
                script {
                    // Start your application and database with Docker Compose
                    sh 'docker-compose -f docker-compose.yml up -d app db'
                }
            }
        }

                }
                }


