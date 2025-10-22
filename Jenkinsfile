pipeline {
    agent any
    tools {
        maven 'Maven_3.9.11'
        jdk 'jdk-23'
    }
    stages {
        stage('Clonar repositorio') {
            steps {
                echo '🔄 Clonando repositorio...'
                git branch: 'main', url: 'https://github.com/crisMdn/Api_reserva.git'
            }
        }

        stage('Compilar') {
            steps {
                dir('apireserva/apireserva') {  //  Ruta exacta donde está el pom.xml
                    echo '⚙️ Ejecutando mvn clean install...'
                    bat 'mvn clean install -DskipTests'
                }
            }
        }

        stage('Ejecutar pruebas') {
            steps {
                dir('apireserva/apireserva') {  //  misma ruta para ejecutar tests
                    echo '🧪 Ejecutando pruebas...'
                    bat 'mvn test'
                }
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
