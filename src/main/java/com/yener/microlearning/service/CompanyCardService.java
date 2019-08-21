package com.yener.microlearning.service;

import com.yener.microlearning.base.enums.MessageTypeENUM;
import com.yener.microlearning.base.util.Message;
import com.yener.microlearning.dto.companycard.DeleteCompanyCardResponseDTO;
import com.yener.microlearning.dto.companycard.SaveCompanyCardRequestDTO;
import com.yener.microlearning.dto.companycard.SaveCompanyCardResponseDTO;
import com.yener.microlearning.entity.Card;
import com.yener.microlearning.entity.CompanyCard;
import com.yener.microlearning.repository.CardRepository;
import com.yener.microlearning.repository.CompanyCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyCardService {

    @Autowired
    private CompanyCardRepository companyCardRepository;

    @Autowired
    private CardRepository cardRepository;

    public SaveCompanyCardResponseDTO saveCompanyCard(SaveCompanyCardRequestDTO saveCompanyCardRequestDTO) {
        SaveCompanyCardResponseDTO saveCompanyCardResponseDTO = new SaveCompanyCardResponseDTO();

        CompanyCard companyCard = new CompanyCard();
        companyCard.setCompanyId(saveCompanyCardRequestDTO.getCompanyId());
        companyCard.setDepartmentId(saveCompanyCardRequestDTO.getDepartmentId());
        Card card=cardRepository.findByCardId(saveCompanyCardRequestDTO.getCardId());
        companyCard.setCard(card);
        companyCard.setActive(true);
        companyCard = companyCardRepository.save(companyCard);

        if (companyCard.getCompanyCardId() != null) {
            List<Message> messageList = new ArrayList();
            messageList.add(new Message(MessageTypeENUM.SUCCESS, "Kayıt Başarıyla Eklendi."));
            saveCompanyCardResponseDTO.setMessageList(messageList);
        }

        return saveCompanyCardResponseDTO;
    }

    public DeleteCompanyCardResponseDTO deleteCompanyCard(Long companycardId) {
        companyCardRepository.delete(companycardId);
        DeleteCompanyCardResponseDTO deleteCompanyCardResponseDTO = new DeleteCompanyCardResponseDTO();
        List<Message> messageList = new ArrayList();
        messageList.add(new Message(MessageTypeENUM.SUCCESS, "Kayıt Başarıyla Silindi."));
        deleteCompanyCardResponseDTO.setMessageList(messageList);
        return deleteCompanyCardResponseDTO;
    }
}
