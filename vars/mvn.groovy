def call(){
  node {
    checkout scm

    stage ('env'){
        withEnv(["PATH+MAVEN=${tool 'mvn-3.6.0'}/bin"
                "JAVA_HOME=${tool 'openjdk9'}"]) {
            sh 'mvn --version'
            sh "mvn clean ${params.testType}"
          }
      }
      stage ('junit'){
          junit 'target/surefire-reports/*.xml'
          jacoco execPattern: 'target/**.exec'
      }

  }

}
