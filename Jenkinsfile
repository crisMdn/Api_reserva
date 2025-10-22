pipeline {
    agent any

    tools {
        maven 'Maven_3.9.11'
        jdk 'JDK17'
    }

    environment {
        IMAGE_NAME = "api_reserva"
        IMAGE_TAG = "latest"
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
                echo '⚙️ Compilando con Maven...'
                bat '''
                    cd apireserva\\apireserva
                    mvn clean install -DskipTests
                '''
            }
        }

        stage('Ejecutar pruebas') {
            steps {
                echo '🧪 Ejecutando pruebas...'
                bat '''
                    cd apireserva\\apireserva
                    mvn test
                '''
            }
        }

        stage('Construir imagen Docker') {
            steps {
                echo '🐳 Construyendo imagen Docker...'
                bat '''
                    docker build -t %IMAGE_NAME%:%IMAGE_TAG% -f Dockerfile .
                '''
            }
        }

        stage('Ejecutar contenedor Docker') {
            steps {
                echo '🚀 Ejecutando contenedor Docker...'
                bat '''
                    docker stop %IMAGE_NAME% || echo "No hay contenedor previo"
                    docker rm %IMAGE_NAME% || echo "No hay contenedor para eliminar"
                    docker run -d -p 8080:8080 --name %IMAGE_NAME% %IMAGE_NAME%:%IMAGE_TAG%
                '''
            }
        }
    }

    post {
        always {
            echo '🧹 Limpiando recursos Docker...'
            bat 'docker system prune -f'
        }
        success {
            echo '✅ Pipeline completado correctamente.'
        }
        failure {
            echo '❌ Error durante la compilación, pruebas o despliegue.'
        }
    }
}
