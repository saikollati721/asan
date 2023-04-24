pipeline {
    agent any
    parameters {
        booleanParam(defaultValue: true, description: '', name: 'Build')
    }
     environment {
            DOCKER_HOME = tool name: 'Docker', type: 'org.jenkinsci.plugins.docker.commons.tools.DockerTool'
        }

        stages {
            stage('Set Docker path') {
                steps {
                    withEnv(["PATH+DOCKER=${DOCKER_HOME}/bin"]) {
                        sh 'echo $PATH'
                    }
                }
            }
             stage('Check Docker executable') {
                  steps {
                    sh 'ls ${DOCKER_HOME}/bin'

                  }
                }

                stage('Adding docker permission to jenkins user'){
                    steps{
                        sh 'usermod -aG docker jenkins'
                    }
                }
                stage('Check Docker permission') {
                  steps {
                    sh 'docker --version'
                  }
                }

        stage ('Build') {
            when { expression { return params.Build }}
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'pass', usernameVariable: 'user')]) {
                    sh "docker build -t ${user}/helloapp:${currentBuild.number} ."
                    sh "docker tag ${user}/helloapp:${currentBuild.number} ${user}/helloapp:latest"
                }
            }
        }
        stage ('Push to registry') {
            when { expression { return params.Build }}
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'pass', usernameVariable: 'user')]) {
                    sh "docker login -u ${user} -p ${pass}"
                    sh "docker push ${user}/helloapp:${currentBuild.number}"
                    sh "docker push ${user}/helloapp:latest"
                }
            }
        }
        stage ('Deploy') {
            steps {
                sh "docker stop helloapp || true && docker rm helloapp || true"
                withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'pass', usernameVariable: 'user')]) {
                    sh "docker run -d -p 8085:8085  --name helloapp ${user}/helloapp:latest"
                }
            }
        }
    }
}