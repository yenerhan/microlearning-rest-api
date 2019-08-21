package com.yener.microlearning.controller;

import com.yener.microlearning.dto.company.*;
import com.yener.microlearning.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @RequestMapping(value = "/getAllCompany", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public
    @ResponseBody
    List<CompanyDTO> getAllCompany() {
        return companyService.getAllCompany();
    }

    @RequestMapping(value = "/saveCompany", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public
    @ResponseBody
    SaveCompanyResponseDTO saveCompany(@RequestBody SaveCompanyRequestDTO saveCompanyRequestDTO) {
        return companyService.saveCompany(saveCompanyRequestDTO);
    }


    @RequestMapping(value = "/deleteCompany", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public
    @ResponseBody
    DeleteCompanyResponseDTO deleteCompany(@RequestBody DeleteCompanyRequestDTO deleteCompanyRequestDTO) {
        DeleteCompanyResponseDTO deleteCompanyResponseDTO = companyService.deleteCompany(deleteCompanyRequestDTO.getCompanyId());
        return deleteCompanyResponseDTO;
    }
}
