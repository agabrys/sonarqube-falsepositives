pipeline {
    agent any
    options {
        buildDiscarder(logRotator(artifactDaysToKeepStr: '0', artifactNumToKeepStr: '0', daysToKeepStr: '-1', numToKeepStr: '10'))
        timestamps()
    }
    stages {
        stage('Build') {
            steps {
                withMaven(maven: '3.6.0', jdk: '10', mavenLocalRepo: '.repository', publisherStrategy: 'EXPLICIT') {
                    sh 'mvn install'
                }
            }
        }
        stage('SonarQube') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    withMaven(maven: '3.6.0', jdk: '10', mavenLocalRepo: '.repository', publisherStrategy: 'EXPLICIT') {
                        sh 'mvn sonar:sonar'
                    }
                }
            }
        }
    }
    post {
        always {
            cleanWs()
        }
    }
}
