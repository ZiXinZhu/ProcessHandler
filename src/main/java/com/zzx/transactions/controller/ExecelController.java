package com.zzx.transactions.controller;


import com.zzx.transactions.common.CommonResult;
import com.zzx.transactions.common.ParamterHandler;
import com.zzx.transactions.common.ProcessHandler;
import com.zzx.transactions.utils.ExecelUtil;
import com.zzx.transactions.utils.People;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ExecelController {

    @GetMapping("/download")
    public void xlsDownload(HttpServletResponse response) {
        String name = "厕所是按时按.xlsx";
        String[] title = {"姓名","年龄","时间"};
        List<People> list = new ArrayList<>();
        People people = new People();
        people.setName("zzx");
        people.setAge(25);
        people.setTime(new Date());
        list.add(people);
        People people1 = new People();
        people1.setName("lz");
        people1.setAge(1233121212);
        people1.setTime(new Date());
        list.add(people1);
        ExecelUtil.download(response,name,list,title);
    }

    @PostMapping("/upload")
    public CommonResult<String> readXls(@RequestParam("file") MultipartFile file) {
        CommonResult<String> result = new CommonResult<>();
        ProcessHandler.handler(result, new ParamterHandler() {
            @Override
            public void check() {
            }

            @Override
            public void process() {
                result.setResult(ExecelUtil.upLoadExecel(file));
            }
        });
        return result;
    }
}
