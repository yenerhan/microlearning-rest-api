package com.yener.microlearning.service;

import com.yener.microlearning.base.util.Utils;
import com.yener.microlearning.repository.MemberShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberShipService {

    @Autowired
    private MemberShipRepository memberShipRepository;

    @Autowired
    Utils utils;


}
