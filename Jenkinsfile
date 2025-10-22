pipeline {
    agent any
    stages {
        stage('Clonar repositorio') {
            steps {
                git branch: 'main', url: 'https://github.com/crisMdn/Api_reserva.git'
            }
        }
        stage('Compilar') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Ejecutar pruebas') {
            steps {
                sh 'mvn test'
            }
        }
    }
}
