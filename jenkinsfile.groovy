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
    stage('Build Artifact'){
        sh "mvn -Dmaven.test.skip=true package"
        archiveArtifacts artifacts: "target/goosegame-1.0-SNAPSHOT-jar-with-dependencies.jar", fingerprint: true
    }
    stage('Build Docker file'){
        sh "docker build -t goose-game:{$env.BuildNumber} ."
    }
}