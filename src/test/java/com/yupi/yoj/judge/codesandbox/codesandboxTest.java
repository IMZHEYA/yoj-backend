package com.yupi.yoj.judge.codesandbox;

import com.yupi.yoj.judge.codesandbox.impl.ExampleCodeSandbox;
import com.yupi.yoj.judge.codesandbox.model.ExecutecodeCodeRequest;
import com.yupi.yoj.judge.codesandbox.model.ExecutecodeResponse;
import com.yupi.yoj.model.enums.QuestionSubmitLanguageEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class codesandboxTest {


    @Test
    void executeCode(){
        CodeSandbox codesandbox = new ExampleCodeSandbox();
        String code = "int main(){}";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1 2","3 4");
        ExecutecodeCodeRequest executecodeCodeRequest = ExecutecodeCodeRequest.builder().code(code).language(language).inputList(inputList).build();

        ExecutecodeResponse executecodeResponse = codesandbox.executeCode(executecodeCodeRequest);
        Assertions.assertNotNull(executecodeResponse);
    }

}