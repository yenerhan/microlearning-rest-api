package com.yener.microlearning.service;

import com.yener.microlearning.base.enums.MessageTypeENUM;
import com.yener.microlearning.base.util.Message;
import com.yener.microlearning.base.util.Utils;
import com.yener.microlearning.dto.company.CompanyDTO;
import com.yener.microlearning.dto.company.DeleteCompanyResponseDTO;
import com.yener.microlearning.dto.company.SaveCompanyRequestDTO;
import com.yener.microlearning.dto.company.SaveCompanyResponseDTO;
import com.yener.microlearning.entity.Company;
import com.yener.microlearning.entity.MemberShip;
import com.yener.microlearning.repository.CompanyRepository;
import com.yener.microlearning.repository.MemberShipRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private MemberShipRepository memberShipRepository;

    @Autowired
    Utils utils;

    public List<CompanyDTO> getAllCompany() {
        List<Company> companyList = companyRepository.findAll();
        List<CompanyDTO> companyDTOList = new ArrayList<>();
        for (Company company : companyList) {
            CompanyDTO companyDTO = new CompanyDTO();
            BeanUtils.copyProperties(company, companyDTO);
            companyDTOList.add(companyDTO);
        }
        return companyDTOList;
    }

    public SaveCompanyResponseDTO saveCompany(SaveCompanyRequestDTO saveCompanyRequestDTO) {
        SaveCompanyResponseDTO saveCompanyResponseDTO = new SaveCompanyResponseDTO();
        if (companyRepository.findCompanyByCompanyName(saveCompanyRequestDTO.getCompanyName()) != null) {
            throw new RuntimeException("Bu İsimli şirket zaten var.");
        }
        String memberCode=utils.generateUserId(8);
        MemberShip isItNull=memberShipRepository.findByCode(memberCode);
        if (isItNull != null) {
            throw new RuntimeException("Bu Üyelik kodu zaten var.");
        }
        Company company = new Company();
        company.setCompanyName(saveCompanyRequestDTO.getCompanyName());
        company.setComment(saveCompanyRequestDTO.getComment());
        company.setActive(true);
        company = companyRepository.save(company);
        if (company.getCompanyId() != null) {
            MemberShip memberShip = new MemberShip();
            memberShip.setCompanyId(company.getCompanyId());
            memberShip.setCode(memberCode);
            Date today = new Date();
            memberShip.setStartOfDate(today);
            Calendar endOfDate = Calendar.getInstance();
            endOfDate.setTime(today);
            endOfDate.add(Calendar.YEAR, 1);
            memberShip.setEndOfDate(endOfDate.getTime());
            memberShip.setActive(true);
            memberShipRepository.save(memberShip);

            List<Message> messageList = new ArrayList();
            messageList.add(new Message(MessageTypeENUM.SUCCESS, "Kayıt Başarıyla Eklendi."));
            saveCompanyResponseDTO.setMessageList(messageList);
            saveCompanyResponseDTO.setCode(memberShip.getCode());
        }
        return saveCompanyResponseDTO;
    }

    public DeleteCompanyResponseDTO deleteCompany(Long companyId) {
        companyRepository.delete(companyId);
        DeleteCompanyResponseDTO deleteCompanyResponseDTO = new DeleteCompanyResponseDTO();
        List<Message> messageList = new ArrayList();
        messageList.add(new Message(MessageTypeENUM.SUCCESS, "Kayıt Başarıyla Silindi."));
        deleteCompanyResponseDTO.setMessageList(messageList);
        return deleteCompanyResponseDTO;
    }
}
