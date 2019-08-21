package com.yener.microlearning.service;

import com.yener.microlearning.base.enums.MessageTypeENUM;
import com.yener.microlearning.base.util.Message;
import com.yener.microlearning.dto.card.*;
import com.yener.microlearning.dto.user.CurrentUser;
import com.yener.microlearning.entity.Card;
import com.yener.microlearning.entity.CompanyCard;
import com.yener.microlearning.entity.Content;
import com.yener.microlearning.entity.MemberShip;
import com.yener.microlearning.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private CompanyCardRepository companyCardRepository;

    @Autowired
    private MemberShipRepository memberShipRepository;

    @Autowired
    UserRepository userRepository;


    public SearchAllCardResponseDTO getAllCardByCompanyId() {
        SearchAllCardResponseDTO searchAllCardResponseDTO = new SearchAllCardResponseDTO();
        CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!controlEndOfMemberShip(memberShipRepository.findByCompanyId(currentUser.getCompanyId()))){
            List<Message> messageList = new ArrayList();
            messageList.add(new Message(MessageTypeENUM.INFO, "Üyeliğiniz sona ermiştir."));
            searchAllCardResponseDTO.setMessageList(messageList);
            return searchAllCardResponseDTO;
        }

        List<Card> cardList = cardRepository.findByCompanyId(currentUser.getCompanyId());
        if (cardList != null) {
            List<CardDTO> cardDTOList = new ArrayList<>();
            for (Card card : cardList) {
                CardDTO cardDTO = new CardDTO();
                cardDTO.setCardId(card.getCardId());
                cardDTO.setCardName(card.getCardName());
                cardDTO.setComment(card.getComment());
                cardDTO.setCardPhoto(card.getCardPhoto());
                cardDTO.setActive(card.getActive());
                cardDTOList.add(cardDTO);
            }
            searchAllCardResponseDTO.setCardDTOList(cardDTOList);
        }
        return searchAllCardResponseDTO;
    }

    public SearchAllCardResponseDTO getAllUserCard() {
        SearchAllCardResponseDTO searchAllCardResponseDTO = new SearchAllCardResponseDTO();
        CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!controlEndOfMemberShip(memberShipRepository.findByCompanyId(currentUser.getCompanyId()))){
            List<Message> messageList = new ArrayList();
            messageList.add(new Message(MessageTypeENUM.INFO, "Üyeliğiniz sona ermiştir."));
            searchAllCardResponseDTO.setMessageList(messageList);
            return searchAllCardResponseDTO;
        }

        List<CompanyCard> companyCardList = companyCardRepository.findByCompanyIdOrDepartmentId(currentUser.getCompanyId(), currentUser.getDepartmentId());
        if(companyCardList!=null){
            searchAllCardResponseDTO.setCardDTOList(convertFromCardListToCardDTOList(companyCardList));
        }
        return searchAllCardResponseDTO;
    }

    private List<CardDTO> convertFromCardListToCardDTOList(List<CompanyCard> companyCardList) {
        List<CardDTO> cardDTOList = new ArrayList<>();
        for (CompanyCard companyCard : companyCardList) {
            CardDTO cardDTO = new CardDTO();
            cardDTO.setCardId(companyCard.getCard().getCardId());
            cardDTO.setCardName(companyCard.getCard().getCardName());
            cardDTO.setCardPhoto(companyCard.getCard().getCardPhoto());
            cardDTO.setComment(companyCard.getCard().getComment());
            cardDTO.setActive(companyCard.getCard().getActive());
            cardDTOList.add(cardDTO);
        }
        return cardDTOList;
    }

    public SearchCardResponseDTO getCardByCardId(SearchCardRequestDTO searchCardRequestDTO) {
        Card card = cardRepository.findByCardId(searchCardRequestDTO.getCardId());
        SearchCardResponseDTO searchCardResponseDTO = new SearchCardResponseDTO();
        if (card != null) {
            searchCardResponseDTO.setCardDTO(convertFromCardToCardDTO(card));
        } else {
            List<Message> messageList = new ArrayList();
            messageList.add(new Message(MessageTypeENUM.INFO, "İlgili eğitim bulunamadı."));
            searchCardResponseDTO.setMessageList(messageList);

        }
        return searchCardResponseDTO;
    }

    private CardDTO convertFromCardToCardDTO(Card card) {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setCardId(card.getCardId());
        cardDTO.setCardName(card.getCardName());
        cardDTO.setCardPhoto(card.getCardPhoto());
        cardDTO.setComment(card.getComment());
        List<ContentDTO> contentDTOList = new ArrayList<>();
        for (Content content : card.getContentList()) {
            ContentDTO contentDTO = new ContentDTO();
            contentDTO.setContentId(content.getContentId());
            contentDTO.setText(content.getText());
            contentDTO.setPhoto(content.getPhoto());
            contentDTO.setActive(content.getActive());
            contentDTOList.add(contentDTO);
        }
        cardDTO.setContentDTOList(contentDTOList);
        return cardDTO;
    }

    private Boolean controlEndOfMemberShip(MemberShip memberShip) {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 23);
        today.set(Calendar.MINUTE, 59);
        today.set(Calendar.SECOND, 59);

        if (memberShip != null && memberShip.getEndOfDate().before(today.getTime())) {
            return false;
        }
        return true;
    }

    public SaveCardResponseDTO saveCard(SaveCardRequestDTO saveCardRequestDTO) {
        SaveCardResponseDTO saveCardResponseDTO = new SaveCardResponseDTO();
        if (saveCardRequestDTO.getCardDTO() != null && saveCardRequestDTO.getCardDTO().getContentDTOList() != null) {

            Card card = new Card();
            card.setCardName(saveCardRequestDTO.getCardDTO().getCardName());
            card.setComment(saveCardRequestDTO.getCardDTO().getComment());
            card.setCardPhoto(getImage());
            card.setActive(saveCardRequestDTO.getCardDTO().getActive());
            CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            card.setCompanyId(currentUser.getCompanyId());
            card = cardRepository.save(card);

            Set<Content> contentList = new HashSet<>();
            for (ContentDTO contentDTO : saveCardRequestDTO.getCardDTO().getContentDTOList()) {
                Content content = new Content();
                content.setPhoto(getImage());
                content.setText(contentDTO.getText());
                content.setActive(contentDTO.getActive());
                content.setCard(card);
                contentList.add(content);
            }
            contentRepository.save(contentList);

            if (card.getCardId() != null) {
                List<Message> messageList = new ArrayList();
                messageList.add(new Message(MessageTypeENUM.SUCCESS, "Kayıt Başarıyla Eklendi."));
                saveCardResponseDTO.setMessageList(messageList);
            }
        }
        return saveCardResponseDTO;
    }

    public static byte[] getImage() {
        File file = new File("c:\\image\\image1.jpg");
        if (file.exists()) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "jpg", byteOutStream);
                return byteOutStream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public DeleteCardResponseDTO deleteCard(Long cardId) {
        cardRepository.delete(cardId);
        DeleteCardResponseDTO deleteCardResponseDTO = new DeleteCardResponseDTO();
        List<Message> messageList = new ArrayList();
        messageList.add(new Message(MessageTypeENUM.SUCCESS, "Kayıt Başarıyla Silindi."));
        deleteCardResponseDTO.setMessageList(messageList);
        return deleteCardResponseDTO;
    }
}
