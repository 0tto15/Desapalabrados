pipeline {
    agent any

    tools {
        jdk 'jdk23'
        maven 'maven'
    }

    stages {
        stage('Limpieza y Preparación') {
            steps {
                echo 'Limpiando el espacio de trabajo...'
                // Borra ejecuciones previas para evitar conflictos
                sh 'mvn clean'
            }
        }

        stage('Compilación') {
            steps {
                echo 'Compilando el proyecto Desapalabrados...'
                // Compila el código fuente y las clases de prueba sin ejecutarlas aún
                sh 'mvn compile test-compile'
            }
        }

        stage('Ejecución de Tests') {
            steps {
                echo 'Lanzando las pruebas unitarias con JUnit y Mockito...'
                // Ejecuta los tests
                sh 'mvn test'
            }
        }

    post {
        always {
            echo 'Archivando los resultados de los tests...'
            // gráficas de cuántos tests han pasado y cuántos han fallado
            junit '**/target/surefire-reports/*.xml'
        }
        success {
            echo 'La pipeline ha terminado.'
        }
        failure {
            echo 'La pipeline ha fallado. Revisa los logs para ver qué test o compilación se rompió.'
        }
    }
}