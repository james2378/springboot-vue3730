package com.project.demo.controller;

import com.project.demo.entity.SystemMaintenance;
import com.project.demo.service.SystemMaintenanceService;
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
 *系统维护：(SystemMaintenance)表控制层
 *
 */
@RestController
@RequestMapping("/system_maintenance")
public class SystemMaintenanceController extends BaseController<SystemMaintenance,SystemMaintenanceService> {

    /**
     *系统维护对象
     */
    @Autowired
    public SystemMaintenanceController(SystemMaintenanceService service) {
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
