pipeline {
    agent any
    stages {
        stage('Clonar repositorio') {
            steps {
                git 'https://github.com/crisMdn/Api_reserva.git'
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
