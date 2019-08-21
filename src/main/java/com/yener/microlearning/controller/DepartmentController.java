package com.yener.microlearning.controller;

import com.yener.microlearning.dto.department.*;
import com.yener.microlearning.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value = "/getAllDeparmentByCompanyId", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public
    @ResponseBody
    List<DepartmentDTO> getAllDeparmentByCompanyId() {
        return departmentService.getAllDeparmentByCompanyId();
    }

    @RequestMapping(value = "/saveDepartment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public
    @ResponseBody
    SaveDepartmentResponseDTO saveDepartment(@RequestBody SaveDepartmentRequestDTO saveDepartmentRequestDTO) {
        return departmentService.saveDeparment(saveDepartmentRequestDTO);
    }


    @RequestMapping(value = "/deleteDepartment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public
    @ResponseBody
    DeleteDepartmentResponseDTO deleteDepartment(@RequestBody DeleteDepartmentRequestDTO deleteDepartmentRequestDTO) {
        DeleteDepartmentResponseDTO deleteDepartmentResponseDTO = departmentService.deleteDeparment(deleteDepartmentRequestDTO.getDepartmentId());
        return deleteDepartmentResponseDTO;
    }
}
