package com.yener.microlearning.service;

import com.yener.microlearning.base.enums.MessageTypeENUM;
import com.yener.microlearning.base.util.Message;
import com.yener.microlearning.dto.department.DeleteDepartmentResponseDTO;
import com.yener.microlearning.dto.department.DepartmentDTO;
import com.yener.microlearning.dto.department.SaveDepartmentRequestDTO;
import com.yener.microlearning.dto.department.SaveDepartmentResponseDTO;
import com.yener.microlearning.dto.user.CurrentUser;
import com.yener.microlearning.entity.Company;
import com.yener.microlearning.entity.Department;
import com.yener.microlearning.repository.CompanyRepository;
import com.yener.microlearning.repository.DepartmentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private DepartmentRepository departmentRepository;


    public List<DepartmentDTO> getAllDeparmentByCompanyId() {
        CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Department> departmentList = departmentRepository.findByCompanyCompanyId(currentUser.getCompanyId());
        List<DepartmentDTO> departmentDTOList = new ArrayList<>();
        for (Department department : departmentList) {
            DepartmentDTO departmentDTO = new DepartmentDTO();
            BeanUtils.copyProperties(department, departmentDTO);
            departmentDTOList.add(departmentDTO);
        }
        return departmentDTOList;
    }

    public SaveDepartmentResponseDTO saveDeparment(SaveDepartmentRequestDTO saveDepartmentRequestDTO) {
        SaveDepartmentResponseDTO saveDepartmentResponseDTO = new SaveDepartmentResponseDTO();
        Department department = new Department();
        CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Company company = companyRepository.findByCompanyId(currentUser.getCompanyId());
        if (company == null) {
            throw new RuntimeException("Bu İsimli şirket bulunamadı.");
        }
        department.setCompany(company);
        department.setDepartmentName(saveDepartmentRequestDTO.getDepartmentName());
        department.setComment(saveDepartmentRequestDTO.getComment());
        department.setActive(true);
        department = departmentRepository.save(department);
        if (department.getDepartmentId() != null) {
            List<Message> messageList = new ArrayList();
            messageList.add(new Message(MessageTypeENUM.SUCCESS, "Kayıt Başarıyla Eklendi."));
            saveDepartmentResponseDTO.setMessageList(messageList);
        }
        return saveDepartmentResponseDTO;
    }

    public DeleteDepartmentResponseDTO deleteDeparment(Long departmentId) {
        departmentRepository.delete(departmentId);
        DeleteDepartmentResponseDTO deleteDepartmentResponseDTO = new DeleteDepartmentResponseDTO();
        List<Message> messageList = new ArrayList();
        messageList.add(new Message(MessageTypeENUM.SUCCESS, "Kayıt Başarıyla Silindi."));
        deleteDepartmentResponseDTO.setMessageList(messageList);
        return deleteDepartmentResponseDTO;
    }
}
