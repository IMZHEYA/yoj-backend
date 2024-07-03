package com.yupi.yoj.judge.codesandbox;

import com.yupi.yoj.judge.codesandbox.impl.ExampleCodeSandbox;
import com.yupi.yoj.judge.codesandbox.model.ExecutecodeCodeRequest;
import com.yupi.yoj.judge.codesandbox.model.ExecutecodeResponse;
import com.yupi.yoj.model.enums.QuestionSubmitLanguageEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SpringBootTest
class codesandboxTest {


    @Value("${codeSandbox.type:example}")
    private String type;

    @Test
    void executeCode(){
        String type = "remote";
        CodeSandbox codesandbox = CodeSandboxFactory.newInstance(type);
        String code = "public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        int a = Integer.parseInt(args[0]);\n" +
                "        int b = Integer.parseInt(args[1]);\n" +
                "        System.out.println(\"结果:\" + (a + b));\n" +
                "    }\n" +
                "}";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1 2","3 4");
        ExecutecodeCodeRequest executecodeCodeRequest = ExecutecodeCodeRequest.builder().code(code).language(language).inputList(inputList).build();

        ExecutecodeResponse executecodeResponse = codesandbox.executeCode(executecodeCodeRequest);
        Assertions.assertNotNull(executecodeResponse);
    }

    @Test
    void executeCodeByValue(){
        CodeSandbox codesandbox = CodeSandboxFactory.newInstance(type);
        String code = "int main(){}";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1 2","3 4");
        ExecutecodeCodeRequest executecodeCodeRequest = ExecutecodeCodeRequest.builder().code(code).language(language).inputList(inputList).build();

        ExecutecodeResponse executecodeResponse = codesandbox.executeCode(executecodeCodeRequest);
        Assertions.assertNotNull(executecodeResponse);
    }

    @Test
    void executeCodeByProxy(){
        CodeSandbox codesandbox = CodeSandboxFactory.newInstance(type);
        codesandbox = new CodeSandboxProxy(codesandbox);
        String code = "int main(){}";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1 2","3 4");
        ExecutecodeCodeRequest executecodeCodeRequest = ExecutecodeCodeRequest.builder().code(code).language(language).inputList(inputList).build();

        ExecutecodeResponse executecodeResponse = codesandbox.executeCode(executecodeCodeRequest);
        Assertions.assertNotNull(executecodeResponse);
    }

}