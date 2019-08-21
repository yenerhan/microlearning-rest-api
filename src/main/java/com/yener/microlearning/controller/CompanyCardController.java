package com.yener.microlearning.controller;

import com.yener.microlearning.dto.companycard.DeleteCompanyCardRequestDTO;
import com.yener.microlearning.dto.companycard.DeleteCompanyCardResponseDTO;
import com.yener.microlearning.dto.companycard.SaveCompanyCardRequestDTO;
import com.yener.microlearning.dto.companycard.SaveCompanyCardResponseDTO;
import com.yener.microlearning.service.CompanyCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/companycard")
public class CompanyCardController {

    @Autowired
    CompanyCardService companyCardService;

    @RequestMapping(value = "/saveCompanyCard", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public
    @ResponseBody
    SaveCompanyCardResponseDTO saveCompanyCard(@RequestBody SaveCompanyCardRequestDTO saveCompanyCardRequestDTO) {
        return companyCardService.saveCompanyCard(saveCompanyCardRequestDTO);
    }


    @RequestMapping(value = "/deleteCompanyCard", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public
    @ResponseBody
    DeleteCompanyCardResponseDTO deleteCompanyCard(@RequestBody DeleteCompanyCardRequestDTO deleteCompanyCardRequestDTO) {
        DeleteCompanyCardResponseDTO deleteCompanyCardResponseDTO = companyCardService.deleteCompanyCard(deleteCompanyCardRequestDTO.getCompanyCardId());
        return deleteCompanyCardResponseDTO;
    }
}
