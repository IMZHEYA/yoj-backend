package com.yupi.yoj.judge.codesandbox.impl;

import com.yupi.yoj.judge.codesandbox.CodeSandbox;
import com.yupi.yoj.judge.codesandbox.model.ExecutecodeCodeRequest;
import com.yupi.yoj.judge.codesandbox.model.ExecutecodeResponse;

/**
 * 第三方代码沙箱：调用网上现成的代码沙箱
 */
public class ThirdPartyCodeSandbox implements CodeSandbox {
    @Override
    public ExecutecodeResponse executeCode(ExecutecodeCodeRequest excodeCodeRequest) {
        System.out.println("第三方代码沙箱");
        return null;
    }
}
