package com.project.demo.controller;

import com.project.demo.entity.PrivateMessage;
import com.project.demo.service.PrivateMessageService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *私密留言：(PrivateMessage)表控制层
 *
 */
@RestController
@RequestMapping("/private_message")
public class PrivateMessageController extends BaseController<PrivateMessage,PrivateMessageService> {

    /**
     *私密留言对象
     */
    @Autowired
    public PrivateMessageController(PrivateMessageService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
