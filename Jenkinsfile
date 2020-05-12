node {
  docker.image('gradle:jdk11').inside {
    stage('Setup') {
      checkout scm
      sh 'chmod +x ./gradlew'
    }

    stage('Build') {
      sh './gradlew build -s'
      archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
    }
  }
}
