package com.xxl.job.executor.service.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.executor.XxlJobExecutor;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

/**
 * @author WH
 * @version 1.0
 * @date 2020/4/12 19:19
 * @Description xxl-job  测试Handler
 */
@Component
public class TestHandler {


    @XxlJob("testHandler")
    public ReturnT<String> execute(String param) throws Exception {

        System.out.println("这是一个xxl-job的测试任务");

        return ReturnT.SUCCESS;
    }
}
