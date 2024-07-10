package com.yupi.yoj.judge.codesandbox;

import com.yupi.yoj.judge.codesandbox.impl.RemoteCodeSandbox;
import com.yupi.yoj.judge.codesandbox.model.ExecutecodeCodeRequest;
import com.yupi.yoj.judge.codesandbox.model.ExecutecodeResponse;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

@Slf4j
public class CodeSandboxProxy implements CodeSandbox{


    private final  CodeSandbox codeSandbox;


    public CodeSandboxProxy(CodeSandbox codeSandbox) {
        this.codeSandbox = codeSandbox;
    }

    @Override
    public ExecutecodeResponse executeCode(ExecutecodeCodeRequest excodeCodeRequest) {
        log.info("代码沙箱请求信息" + excodeCodeRequest.toString());
        ExecutecodeResponse executecodeResponse = codeSandbox.executeCode(excodeCodeRequest);
        log.info("代码沙箱响应信息" + executecodeResponse.toString());
        return executecodeResponse;
    }
}
