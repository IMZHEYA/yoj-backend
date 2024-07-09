package com.yupi.yoj.model.dto.question;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CodeTemplateTest {

    @Test
    void tem() throws FileNotFoundException {
        Gson gson = new Gson();
        FileReader reader = new FileReader("D:\\project\\yoj-backend\\src\\main\\resources\\codeTemplate.json");
        Map<String,String> codeMap = gson.fromJson(reader, Map.class);
        CodeTemplate codeTemplate = new CodeTemplate();
        codeTemplate.setJava(codeMap.get("java"));
        codeTemplate.setPython(codeMap.get("python"));
        codeTemplate.setCpp(codeMap.get("cpp"));
        codeTemplate.setC(codeMap.get("c"));
        System.out.println(codeTemplate.getCpp());
    }


}