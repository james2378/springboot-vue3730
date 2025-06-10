package com.project.demo.controller;

import com.project.demo.entity.VaccinationAppointment;
import com.project.demo.service.VaccinationAppointmentService;
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
 *接种预约：(VaccinationAppointment)表控制层
 *
 */
@RestController
@RequestMapping("/vaccination_appointment")
public class VaccinationAppointmentController extends BaseController<VaccinationAppointment,VaccinationAppointmentService> {

    /**
     *接种预约对象
     */
    @Autowired
    public VaccinationAppointmentController(VaccinationAppointmentService service) {
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
