package com.yener.microlearning.dto.card;


import com.yener.microlearning.base.restApi.apiResponse.BaseApiResponse;

public class SearchCardResponseDTO extends BaseApiResponse {
    private CardDTO cardDTO;

    public CardDTO getCardDTO() {
        return cardDTO;
    }

    public void setCardDTO(CardDTO cardDTO) {
        this.cardDTO = cardDTO;
    }
}
