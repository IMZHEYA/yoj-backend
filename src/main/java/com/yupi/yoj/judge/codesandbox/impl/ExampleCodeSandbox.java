package com.yupi.yoj.judge.codesandbox.impl;

import com.yupi.yoj.judge.codesandbox.CodeSandbox;
import com.yupi.yoj.judge.codesandbox.model.ExecutecodeCodeRequest;
import com.yupi.yoj.judge.codesandbox.model.ExecutecodeResponse;

/**
 * 示例代码沙箱
 */
public class ExampleCodeSandbox implements CodeSandbox {
    @Override
    public ExecutecodeResponse executeCode(ExecutecodeCodeRequest excodeCodeRequest) {
        System.out.println("示例代码沙箱");
        return null;
    }
}
