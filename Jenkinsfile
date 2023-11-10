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
        stage('Build') {
            steps {
               sh "mvn clean package -DskipTests"
            }
        }
        
    }
 
}
