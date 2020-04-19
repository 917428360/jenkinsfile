package org.devops

//saltstack
def SaltDeploy(hosts,func){
    sh "salt \"${hosts}\" ${func}"
}
