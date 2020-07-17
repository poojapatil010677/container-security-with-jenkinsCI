def workspace_loc
def myImg
node{
        stage('checkout_code'){
            checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/Dcoder99/Container-Security-With-JenkinsCI.git']]])
            workspace_loc = pwd()
        }
        stage('build_docker_image'){
            myImg = docker.build("test_image")
        }
        stage('static_analysis') {
            build job: 'static_analysis', parameters: [string(name: 'myWorkspace', value: workspace_loc)]
        }
        stage('run_container'){
            build job: 'run_container', parameters: [string(name: 'myWorkspace', value: workspace_loc)]
        }
}

 	// junit is another a Pipeline step (provided by the JUnit plugin)
    // for aggregating test reports.
	// node is Scripted Pipeline-specific syntax that 
    // instructs Jenkins to execute this Pipeline (and any stages contained within it), on any available agent/node. This is effectively
    //  equivalent to agent in Declarative Pipeline-specific syntax.