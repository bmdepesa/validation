#!groovy

rootPath = "/src/rancher-validation/"
containerPrefix = "${JOB_NAME}${BUILD_NUMBER}"
rancherConfig = "rancher_env.config"
imageName = "rancher-validation-${JOB_NAME}${env.BUILD_NUMBER}"
testsDir = "tests/v3_api/"
paramsConfigFile = "params_env.config"

containerName = containerPrefix + "-validation"

node {
  try {
    wrap([$class: 'AnsiColorBuildWrapper', 'colorMapName': 'XTerm', 'defaultFg': 2, 'defaultBg':1]) {
      deleteDir()

      withCredentials([string(credentialsId: 'AWS_ACCESS_KEY_ID', variable: 'version3')]) {
        sh "echo $version3"
      }

      stage('Precheck') { 
        def params = [variables: [CLUSTER_TYPES, EXTRA_VARS, K8S_UPGRADE_VERSIONS]] 
        
        loadEnvironmentVariables params
        
        println("test: ${env.version3}")
      }
    }
  } catch (Exception err) {
      println err
  } finally {
      // finally
  }
}