node {
    def mvnHome = tool "Maven"
    stage ('Clone'){
        checkout scm
    }
    stage ('Unit tests'){
        sh "mvn clean test"
    }
}