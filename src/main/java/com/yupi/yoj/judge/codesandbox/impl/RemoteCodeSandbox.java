package com.yupi.yoj.judge.codesandbox.impl;

import com.yupi.yoj.judge.codesandbox.CodeSandbox;
import com.yupi.yoj.judge.codesandbox.model.ExecutecodeCodeRequest;
import com.yupi.yoj.judge.codesandbox.model.ExecutecodeResponse;

/**
 * 远程代码沙箱：自己开发的沙箱
 */
public class RemoteCodeSandbox implements CodeSandbox {
    @Override
    public ExecutecodeResponse executeCode(ExecutecodeCodeRequest excodeCodeRequest) {
        System.out.println("远程代码沙箱");
        return null;
    }
}
