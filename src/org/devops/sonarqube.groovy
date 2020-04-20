package org.devops

//scan
def SonarScan(projectName,projectDesc,projectPath){
        def scannerHome = "/usr/local/sonar-scanner-3.4.0.1729/"
        def sonarServer = "http://sonar.alstru.com/"
        def sonarDate = sh  returnStdout: true, script: 'date  +%Y%m%d%H%M%S'
        sonarDate = sonarDate - "\n"
            
        sh """ 
            ${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=${projectName} \
            -Dsonar.projectName=${projectName} -Dsonar.projectVersion=${sonarDate} -Dsonar.ws.timeout=30 \
            -Dsonar.login=tiger -Dsonar.password=tiger899181 \
            -Dsonar.projectDescription=${projectDesc} -Dsonar.links.homepage=http://www.alstru.com \
            -Dsonar.sources=${projectPath} -Dsonar.sourceEncoding=UTF-8 -Dsonar.java.binaries=target/classes \
            -Dsonar.java.test.binaries=target/test-classes -Dsonar.java.surefire.report=target/surefire-reports 
        """
    }
    
    //def qg = waitForQualityGate()
    //if (qg.status != 'OK') {
        //error "Pipeline aborted due to quality gate failure: ${qg.status}"
    //}
}
