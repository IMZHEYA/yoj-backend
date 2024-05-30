package com.yupi.yoj.judge.codesandbox;

import com.yupi.yoj.judge.codesandbox.model.ExecutecodeCodeRequest;
import com.yupi.yoj.judge.codesandbox.model.ExecutecodeResponse;

public interface CodeSandbox {
    /**
     * 执行代码
     * @param excodeCodeRequest
     * @return
     */
    ExecutecodeResponse executeCode(ExecutecodeCodeRequest excodeCodeRequest);
}
