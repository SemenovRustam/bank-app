pipeline {
    agent any

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

        stage('Deploy All') {
            steps {
                echo 'Здесь можно вызвать Helm для деплоя всех сервисов в тестовый или прод namespace'
            }
        }
    }
}
