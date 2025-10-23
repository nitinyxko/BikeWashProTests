pipeline {
    agent any
    
    parameters {
        choice(name: 'TEST_SUITE', choices: ['TS_Smoke', 'TS_Authentication', 'TS_CustomerFlow', 'TS_Regression'])
        choice(name: 'DEVICE', choices: ['00116646S016789', 'emulator-5554'])
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Start Appium') {
            steps {
                bat 'start /B appium --base-path /wd/hub --port 4723'
                sleep 10
            }
        }
        
        stage('Execute Tests') {
            steps {
                bat """
                    katalon -noSplash -runMode=console ^
                    -projectPath="${WORKSPACE}\\BikeWashProTests.prj" ^
                    -testSuitePath="Test Suites/${params.TEST_SUITE}" ^
                    -browserType="Android"
                """
            }
        }
        
        stage('Publish Reports') {
            steps {
                publishHTML([
                    reportDir: 'Reports',
                    reportFiles: '*/execution.html',
                    reportName: 'Test Report'
                ])
            }
        }
    }
    
    post {
        always {
            bat 'taskkill /F /IM node.exe /T || exit 0'
            archiveArtifacts artifacts: 'Reports/**/*', allowEmptyArchive: true
        }
        success {
            emailext subject: "✅ Tests PASSED - ${params.TEST_SUITE}",
                     body: "Build ${BUILD_NUMBER} succeeded",
                     to: 'qa-team@bikewash.com'
        }
        failure {
            emailext subject: "❌ Tests FAILED - ${params.TEST_SUITE}",
                     body: "Build ${BUILD_NUMBER} failed",
                     to: 'qa-team@bikewash.com'
        }
    }
}