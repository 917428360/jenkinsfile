package org.devops 

//创建数据库
def CreateDb(dbName){
   sh """
      curl -XPOST 'http://127.0.0.1:8086/query' --data-urlencode 'q=CREATE DATABASE \"${dbName}\"'"
      """
}


def WriteData(){
    measurementName = "${JOB_NAME}".split("-")[0]
    SERVICE_NAME = "${JOB_NAME}"
    BUILD_AGENT = "master"
    BUILD_RESULT = ["SUCCESS":1,"FAILURE":0]["${currentBuild.currentResult}"]
    wrap([$class: 'BuildUser']){
        currentBuild.description = "Trigger By ${$BUILD_USER}"
    }
    
    
    httpRequest httpMode: 'POST', 
            requestBody: """${measurementName},build_number=${BUILD_ID},build_agent_name=${BUILD_AGENT} project_name=\"${SERVICE_NAME}\",build_id=${BUILD_ID},build_time=${currentBuild.duration},build_result=${BUILD_RESULT},build_desc=\"${currentBuild.description}\",tests_passed=10,tests_failed=2,tests_skipped=3 1434055564000000000""", 
            url: 'http://127.0.0.1:8086/write?db=jenkins'
    
    
}
