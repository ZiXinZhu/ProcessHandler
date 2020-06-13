package com.zzx.transactions.controller;


import com.zzx.transactions.utils.ExecelUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ExecelController {

    @GetMapping("/download")
    public void xlsDownload(HttpServletResponse response){
        ExecelUtil.download(response);
    }
}
