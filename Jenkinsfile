pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                notifyBuild()
                echo 'Building..'
                sh 'mvn clean'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                /* `make check` returns non-zero on test failures,
                * using `true` to allow the Pipeline to continue
                * nonetheless
                */
                sh 'mvn test || true'
                junit 'target/surefire-reports/*.xml'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
    post {
        always {
            archive 'target/**/*.jar'
        }

        success {
            notifyBuild('SUCCESSFUL')
        }
        failure {
            notifyBuild('FAILED')
        }
    }
}

def notifyBuild(String buildStatus = 'STARTED') {
    // build status of null means successful
    buildStatus =  buildStatus ?: 'SUCCESSFUL'

    // Default values
    def colorName = 'RED'
    def colorCode = '#FF0000'
    def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
    def summary = "${subject} (${env.BUILD_URL})"
    def details = """<p>STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
      <p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>"""

    // Override default values based on build status
    if (buildStatus == 'STARTED') {
        color = 'YELLOW'
        colorCode = '#FFFF00'
    } else if (buildStatus == 'SUCCESSFUL') {
        color = 'GREEN'
        colorCode = '#00FF00'
    } else {
        color = 'RED'
        colorCode = '#FF0000'
    }

    // Send notifications
    slackSend (channel: '#jenkins', color: colorCode, message: summary)
}
