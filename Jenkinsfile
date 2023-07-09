pipeline {
    agent any
    environment {
        PATH = "/usr/lib/jvm/jdk-17/bin:$PATH"
        JAVA_HOME="/usr/lib/jvm/jdk-17/"
    }
        stages {
            stage('recuperation du projet'){
                steps {
                    git branch: 'master',
                    credentialsId: 'jenkins_to_github',
                    url :'git@github.com:lenny1411/ProjetStage.git'
                }
            }
            stage("Compile, build and test") {
                steps {
                    sh "bash ./mvnw clean package"
                }
            }
            stage("Docker build") {
                steps{
                    sh "docker build -t 172.17.0.3:5000/app ."
                }
            }
            stage("Docker push") {
                steps{
                    sh "docker push 172.17.0.3:5000/app"
                }
            }
        }
}