package com.yupi.yoj.judge.codesandbox;

import com.yupi.yoj.judge.codesandbox.impl.ExampleCodeSandbox;
import com.yupi.yoj.judge.codesandbox.impl.RemoteCodeSandbox;
import com.yupi.yoj.judge.codesandbox.impl.ThirdPartyCodeSandbox;

/**
 * 代码沙箱创建工厂：根据指定的字符串参数，创建指定的代码沙箱实例
 */
public class CodeSandboxFactory {
    /**
     * 创建代码沙箱实例
     * @param type 沙箱类型
     * @return 返回的是接口，而不是具体的实现类
     */
    public static CodeSandbox newInstance(String type){
        switch (type){
            case "example":
                return new ExampleCodeSandbox();
            case "remote":
                return new RemoteCodeSandbox();
            case "ThirdParty":
                return new ThirdPartyCodeSandbox();
            default:
                return new ExampleCodeSandbox();
        }
    }

}
