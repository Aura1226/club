package org.donghyun.club.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/sample")
public class SampleController {

    //모두가 접근 가능한 경로, 회원만 가능한 경로, 관리자만 가능한 경로 만들거
    @GetMapping("/all")
    public void all(){
        log.info("all.........");
    }

    @GetMapping("/member")
    public void member(){
        log.info("member.....");
    }

    @GetMapping("/admin")
    public void admin(){
        log.info("admin.........");
    }
}
