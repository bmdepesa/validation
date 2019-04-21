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

      stage('Precheck') { 
        def vars = [CLUSTER_TYPES, EXTRA_VARS, K8S_UPGRADE_VERSIONS]
        def params = [variables: vars] 
        loadEnvironmentVariables params
        sh "env"
      }
    }
  } catch (Exception err) {
      //
  } finally {
      // finally
  }
}