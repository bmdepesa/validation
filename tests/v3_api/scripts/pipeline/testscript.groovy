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
        def params = [variables: [CLUSTER_TYPES, EXTRA_VARS, K8S_UPGRADE_VERSIONS]] 
        loadEnvironmentVariables this, params
        println("${env.ver3}")
      }
    }
  } catch (Exception err) {
      //
  } finally {
      // finally
  }
}