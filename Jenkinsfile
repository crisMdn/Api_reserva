pipeline {
    agent any
    stages {
        stage('Clonar repositorio') {
            steps {
                echo '🔄 Clonando repositorio...'
                git branch: 'main', url: 'https://github.com/crisMdn/Api_reserva.git'
            }
        }

        stage('Compilar') {
            steps {
                echo '⚙️ Ejecutando mvn clean install...'
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Ejecutar pruebas') {
            steps {
                echo '🧪 Ejecutando pruebas...'
                bat 'mvn test'
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline completado correctamente.'
        }
        failure {
            echo '❌ Error durante la compilación o pruebas.'
        }
    }
}
