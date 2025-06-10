package com.project.demo.controller;

import com.project.demo.entity.ConsultationTelephone;
import com.project.demo.service.ConsultationTelephoneService;
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
 *咨询电话：(ConsultationTelephone)表控制层
 *
 */
@RestController
@RequestMapping("/consultation_telephone")
public class ConsultationTelephoneController extends BaseController<ConsultationTelephone,ConsultationTelephoneService> {

    /**
     *咨询电话对象
     */
    @Autowired
    public ConsultationTelephoneController(ConsultationTelephoneService service) {
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
