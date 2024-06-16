package com.yanzhen;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LeaveprojectApplicationTests {
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private RepositoryService repositoryService;

    /**
     *  执行时候几张表
     *    act_re_deployment  部署信息
     *    act_re_procdef  流程定义信息
     *    act_ge_bytearray  流程定义 bpm 文件和png文件
     */
    @Test
    void contextLoads() {
        Deployment deployment= repositoryService.createDeployment()
                .addClasspathResource("bpm/leave.bpmn")
                .addClasspathResource("bpm/leave.png")
                .name("请假管理实战")
                .deploy();

        System.out.println("部署名称："+deployment.getName());
    }

}
