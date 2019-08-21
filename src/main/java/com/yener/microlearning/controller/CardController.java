package com.yener.microlearning.controller;

import com.yener.microlearning.dto.card.*;
import com.yener.microlearning.dto.company.*;
import com.yener.microlearning.service.CardService;
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
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;

    @RequestMapping(value = "/getAllCard", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public
    @ResponseBody
    SearchAllCardResponseDTO getAllCard() {
        return cardService.getAllCardByCompanyId();
    }

    @RequestMapping(value = "/getAllUserCard", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public
    @ResponseBody
    SearchAllCardResponseDTO getAllUserCard() {
        return cardService.getAllUserCard();
    }

    @RequestMapping(value = "/getCardByCardId", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public
    @ResponseBody
    SearchCardResponseDTO getCardByCardId(@RequestBody SearchCardRequestDTO searchCardRequestDTO) {
        return cardService.getCardByCardId(searchCardRequestDTO);
    }

    @RequestMapping(value = "/saveCard", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public
    @ResponseBody
    SaveCardResponseDTO saveCard(@RequestBody SaveCardRequestDTO saveCardRequestDTO) {
        return cardService.saveCard(saveCardRequestDTO);
    }


    @RequestMapping(value = "/deleteCard", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public
    @ResponseBody
    DeleteCardResponseDTO deleteCard(@RequestBody DeleteCardRequestDTO deleteCardRequestDTO) {
        return cardService.deleteCard(deleteCardRequestDTO.getCardId());
    }
}
