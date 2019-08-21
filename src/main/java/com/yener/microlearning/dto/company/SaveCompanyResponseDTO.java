package com.yener.microlearning.dto.company;

import com.yener.microlearning.base.restApi.apiResponse.BaseApiResponse;

public class SaveCompanyResponseDTO extends BaseApiResponse {

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
