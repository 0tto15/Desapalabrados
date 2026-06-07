pipeline {
    agent any

    tools {
        jdk 'jdk23'
        maven 'maven'
    }

    stages {
        stage('Limpieza y Preparación') {
        // Borra ejecuciones previas para evitar conflictos
            steps {
                echo 'Limpiando el espacio de trabajo...'
                sh 'mvn clean'
            }
        }
        stage('Compilación') {
        // Compila el código fuente y las clases de prueba sin ejecutarlas aún
            steps {
                echo 'Compilando el proyecto...'
                sh 'mvn compile test-compile'
            }
        }
        stage('Ejecución de Tests') {
            steps {
                echo 'Ejecutando tests unitarios...'
                // Ejecuta los tests
                sh 'mvn test'
            }
        }
    }

    post {
        always {
            echo 'Archivando los resultados de los tests...'
            junit 'target/surefire-reports/*.xml'
            // gráficas de cuántos tests han pasado y cuántos han fallado
        }
        success {
            echo '¡La pipeline se ejecutó correctamente!'
        }
        failure {
            echo 'La pipeline ha fallado. Revisa los logs para ver qué test o compilación se rompió.'
        }
    }
}
