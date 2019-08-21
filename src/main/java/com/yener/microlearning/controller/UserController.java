package com.yener.microlearning.controller;

import com.yener.microlearning.dto.user.*;
import com.yener.microlearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/searchUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public
    @ResponseBody
    UserResponseDTO searchUser(@RequestBody SearchUserRequestDTO searchUserRequestDTO) {
        return userService.searchUser(searchUserRequestDTO);
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public
    @ResponseBody
    UserResponseDTO createUser(@RequestBody SaveUserRequestDTO saveUserRequestDTO) {
        return userService.createUser(saveUserRequestDTO);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public
    @ResponseBody
    UserResponseDTO updateUser(@RequestBody UpdateUserRequestDTO updateUserRequestDTO) {
        return userService.updateUser(updateUserRequestDTO);
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public
    @ResponseBody
    DeleteUserResponseDTO deleteUser(@RequestBody DeleteUserRequestDTO deleteUserRequestDTO) {
        return userService.deleteUser(deleteUserRequestDTO);
    }
}
