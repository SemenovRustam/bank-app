pipeline {
    agent any

    environment {
        KUBE_NAMESPACE = "my-namespace"
    }

    stages {
        stage('Build All Microservices') {
            parallel {
                stage('Account') {
                    steps {
                        dir('account') {
                            sh './gradlew clean build'
                        }
                    }
                }
                stage('Cash') {
                    steps {
                        dir('cash') {
                            sh './gradlew clean build'
                        }
                    }
                }
                stage('Blocker') {
                    steps {
                        dir('blocker') {
                            sh './gradlew clean build'
                        }
                    }
                }
                stage('Exchange') {
                    steps {
                        dir('exchange') {
                            sh './gradlew clean build'
                        }
                    }
                }
                stage('Exchange Generator') {
                    steps {
                        dir('exchange-generator') {
                            sh './gradlew clean build'
                        }
                    }
                }
                stage('Front') {
                    steps {
                        dir('front') {
                            sh './gradlew clean build'
                        }
                    }
                }
                stage('Gateway') {
                    steps {
                        dir('gateway') {
                            sh './gradlew clean build'
                        }
                    }
                }
                stage('Notifications') {
                    steps {
                        dir('notifications') {
                            sh './gradlew clean build'
                        }
                    }
                }
                stage('Transfer') {
                    steps {
                        dir('transfer') {
                            sh './gradlew clean build'
                        }
                    }
                }
            }
        }

        stage('Deploy All via Helm') {
            steps {
                echo 'Deploying all microservices via Helm'

                script {
                    def chartsDir = "$./bank-app-umbrella/charts"

                    def services = [
                        "account": "account-0.1.0.tgz",
                        "cash": "cash-0.1.0.tgz",
                        "blocker": "blocker-0.1.0.tgz",
                        "exchange": "exchange-0.1.0.tgz",
                        "exchange-generator": "exchange-generator-0.1.0.tgz",
                        "front": "front-0.1.0.tgz",
                        "gateway": "gateway-0.1.0.tgz",
                        "notifications": "notifications-0.1.0.tgz",
                        "transfer": "transfer-0.1.0.tgz",
                        "keycloak": "keycloak-0.1.0.tgz",
                        "postgresql": "postgresql-12.12.10.tgz"
                    ]

                    services.each { svc, chart ->
                        sh """
                            helm upgrade --install ${svc} ${chartsDir}/${chart} \
                            --namespace ${KUBE_NAMESPACE} --create-namespace \
                            -f ${chartsDir}/values.yaml
                        """
                    }
                }
            }
        }
    }
}
