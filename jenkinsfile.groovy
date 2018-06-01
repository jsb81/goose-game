node {
    def mvnHome = tool "Maven"
    stage ('Clone'){
        checkout scm
    }
    stage ('Unit tests'){
        sh "mvn clean test"
        junit "target/surefire-reports/**/*.xml"
    }
    stage('Integration test'){
        echo 'qui dovrei lanciare i test di integrazione'
        // sh "mvn test.compile failsafe:integration-test"
    }
}