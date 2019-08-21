package com.yener.microlearning.service;

import com.yener.microlearning.base.enums.MessageTypeENUM;
import com.yener.microlearning.base.util.Message;
import com.yener.microlearning.dto.user.*;
import com.yener.microlearning.entity.MemberShip;
import com.yener.microlearning.entity.Role;
import com.yener.microlearning.entity.User;
import com.yener.microlearning.enums.RoleEnum;
import com.yener.microlearning.repository.MemberShipRepository;
import com.yener.microlearning.repository.RoleRepository;
import com.yener.microlearning.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private MemberShipRepository memberShipRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserResponseDTO searchUser(SearchUserRequestDTO searchUserRequestDTO) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        User user = userRepository.findUserByUserId(searchUserRequestDTO.getUserId());

        if (user == null) {
            throw new UsernameNotFoundException(searchUserRequestDTO.getUserId());
        }

        BeanUtils.copyProperties(user, userResponseDTO);
        return userResponseDTO;
    }

    public UserResponseDTO createUser(SaveUserRequestDTO saveUserRequestDTO) {

        if (userRepository.findUserByEmail(saveUserRequestDTO.getEmail()) != null) {
            throw new RuntimeException("Bu Email adresli kullanıcı zaten var.");
        }
        MemberShip memberShip = memberShipRepository.findByCode(saveUserRequestDTO.getCode());
        if (memberShip == null) {
            throw new RuntimeException("Şirket kodunuz bulunamadı.");
        }
        User user = new User();
        BeanUtils.copyProperties(saveUserRequestDTO, user);
        user.setEncryptedPassword("test");
        user.setEmailVerificationStatus(Boolean.FALSE);
        user.setEncryptedPassword(bCryptPasswordEncoder.encode(saveUserRequestDTO.getPassword()));
        user.setCompanyId(memberShip.getCompanyId());
        Role adminRole = roleRepository.findByRoleName(RoleEnum.USER.name());
        user.setRoles(Arrays.asList(adminRole));
        user = userRepository.save(user);
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        BeanUtils.copyProperties(user, userResponseDTO);
        return userResponseDTO;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CurrentUser currentUser = null;
        List authorities = new ArrayList();

        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        //todo role setlenecek

        currentUser = new CurrentUser(user.getEmail(), user.getEncryptedPassword(),
                true, true, true, true, authorities,
                user.getUserId(), user.getFirstName(), user.getLastName(), user.getDepartmentId(), user.getCompanyId());
        return currentUser;
    }


    public UserResponseDTO updateUser(UpdateUserRequestDTO updateUserRequestDTO) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        User user = userRepository.findUserByUserId(updateUserRequestDTO.getUserId());

        if (user == null) {
            throw new UsernameNotFoundException(updateUserRequestDTO.getUserId());
        }
        user.setFirstName(updateUserRequestDTO.getFirstName());
        user.setLastName(updateUserRequestDTO.getLastName());

        user = userRepository.save(user);
        BeanUtils.copyProperties(user, userResponseDTO);
        return userResponseDTO;
    }

    public DeleteUserResponseDTO deleteUser(DeleteUserRequestDTO deleteUserRequestDTO) {
        DeleteUserResponseDTO deleteUserResponseDTO = new DeleteUserResponseDTO();
        List<Message> messageList = new ArrayList();
        User user = userRepository.findUserByUserId(deleteUserRequestDTO.getUserId());
        if (user == null) {
            throw new UsernameNotFoundException(deleteUserRequestDTO.getUserId());
        }
        userRepository.delete(user);
        messageList.add(new Message(MessageTypeENUM.SUCCESS, "Silme İşlemi Başarılı"));
        deleteUserResponseDTO.setMessageList(messageList);
        return deleteUserResponseDTO;
    }
}
