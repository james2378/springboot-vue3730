package com.project.demo.controller;

import com.project.demo.entity.PublicMessage;
import com.project.demo.service.PublicMessageService;
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
 *公开留言：(PublicMessage)表控制层
 *
 */
@RestController
@RequestMapping("/public_message")
public class PublicMessageController extends BaseController<PublicMessage,PublicMessageService> {

    /**
     *公开留言对象
     */
    @Autowired
    public PublicMessageController(PublicMessageService service) {
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
