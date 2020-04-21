package org.devops


//scan
def SonarScan(sonarServer,projectName,projectDesc,projectPath,branchName){
    
    //定义sonar服务器列表
    def servers = ["test":"sonarqube-test","prod":"sonarqube-prod"]
    
    //使用sonar服务器
    withSonarQubeEnv("${servers[sonarServer]}"){

        def scannerHome = "/usr/local/sonar-scanner-3.4.0.1729/"
        def sonarDate = sh  returnStdout: true, script: 'date  +%Y%m%d%H%M%S'
        sonarDate = sonarDate - "\n"
        
        sh """ 
            ${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=${projectName} \
            -Dsonar.projectName=${projectName} -Dsonar.projectVersion=${sonarDate} -Dsonar.ws.timeout=30 \
            -Dsonar.projectDescription=${projectDesc} -Dsonar.links.homepage=http://www.baidu.com \
            -Dsonar.sources=${projectPath} -Dsonar.sourceEncoding=UTF-8 -Dsonar.java.binaries=target/classes \
            -Dsonar.java.test.binaries=target/test-classes -Dsonar.java.surefire.report=target/surefire-reports  
        """
    }
}
