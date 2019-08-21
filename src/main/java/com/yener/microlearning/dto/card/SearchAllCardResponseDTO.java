package com.yener.microlearning.dto.card;


import com.yener.microlearning.base.restApi.apiResponse.BaseApiResponse;

import java.util.List;

public class SearchAllCardResponseDTO extends BaseApiResponse {
    private List<CardDTO> cardDTOList;


    public List<CardDTO> getCardDTOList() {
        return cardDTOList;
    }

    public void setCardDTOList(List<CardDTO> cardDTOList) {
        this.cardDTOList = cardDTOList;
    }
}
