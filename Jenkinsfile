pipeline {
  agent { label 'azure-agent' }         // change to 'any' if running on master
  options { timestamps() }
  parameters {
    string(name: 'EXEC_MODE', defaultValue: 'GRID', description: 'GRID or LOCAL')
    string(name: 'BROWSER', defaultValue: 'chrome', description: 'chrome or firefox')
  }

  environment {
    GRID_URL = "http://localhost:4444/wd/hub"
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Start Selenium Grid') {
      when { expression { params.EXEC_MODE == 'GRID' } }
      steps {
        sh 'docker-compose -f docker-compose.yml up -d'
        sh 'sleep 10'
        sh 'docker ps --filter "name=selenium" --format "{{.Names}}: {{.Status}}" || true'
      }
    }
    stage('Clean Reports') {
        steps {
            sh 'rm -rf reports/* || true'
        }
    }
    stage('Run Tests') {
      steps {
        sh """
          mvn -B clean test -Dexecution.mode=${params.EXEC_MODE} -Dbrowser=${params.BROWSER} -Dgrid.url=${env.GRID_URL}
        """
      }
      post {
        always {
          junit 'target/surefire-reports/*.xml'           // if using surefire
        }
      }
    }

    stage('Publish Extent Report') {
      steps {
        publishHTML (target: [
          reportDir: 'reports',
          reportFiles: 'ExtentReport*.html',
          reportName: 'Extent Report',
          alwaysLinkToLastBuild: true,
          keepAll: true
        ])
      }
    }

    stage('Tear Down Grid') {
      when { expression { params.EXEC_MODE == 'GRID' } }
      steps {
        sh 'docker-compose -f docker-compose.yml down || true'
      }
    }
  }

  post {
    always {

      archiveArtifacts artifacts: 'reports/spark/**', fingerprint: true

       publishHTML([
                  reportDir: 'reports',
                  reportFiles: 'ExtentReport_*.html',
                  reportName: 'Extent Report',
                  keepAll: true,
                  alwaysLinkToLastBuild: true,
                  allowMissing: false
              ])
    }
  }
}
