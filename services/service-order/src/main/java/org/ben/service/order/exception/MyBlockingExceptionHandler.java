package org.ben.service.order.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ben.model.common.R;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;

@Component
public class MyBlockingExceptionHandler implements BlockExceptionHandler {

    private ObjectMapper objectMapper;

    public MyBlockingExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String s, BlockException e) throws Exception {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter writer = httpServletResponse.getWriter();

        R error = R.error(500, s + "被sentinel限制，原因：" + e);
        writer.write(this.objectMapper.writeValueAsString(error));
    }
}
