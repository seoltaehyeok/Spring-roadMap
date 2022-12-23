package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final ObjectProvider<MyLogger> myLoggerProvider;  // myLogger를 주입 받는 게 아니라 myLogger를 찾을 수 있는 것을 주입

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString(); // 고객이 어떤 URL 로 요청했는지 알 수 있다.
        MyLogger myLogger = myLoggerProvider.getObject(); // 스코프가 request 인 객체 MyLogger 를 request 생성시점으로 미뤘기 때문에 오류를 막을 수 있음
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
