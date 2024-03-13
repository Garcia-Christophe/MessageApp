pipeline {
    agent any
    tools {
       ant '1.10.14'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://gitlab.com/G.Christophe/messageapp'
            }
        }
        stage('Compile') {
            steps {
                sh 'ant compile'
            }
        }
    }
}
