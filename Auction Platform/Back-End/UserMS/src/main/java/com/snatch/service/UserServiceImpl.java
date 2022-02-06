package com.snatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.snatch.dto.UserDTO;
import com.snatch.entity.User;
import com.snatch.exception.ExceptionConstants;
import com.snatch.exception.UserException;
import com.snatch.repo.UserRepo;
import com.snatch.util.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@PropertySource(value = "messages.properties")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    Environment env;

    @Autowired
    UserMapper userMapper;



    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Override
    public UserDTO createUser(UserDTO userDTO) throws UserException {
        Optional<User> user = findByEmail(userDTO.getEmail());
        if(user.isEmpty()){
            logger.info("Creating new user");
            return UserDTO.prepareUserDTO(saveUser(userDTO));
        }
        throw  new UserException(env.getProperty(ExceptionConstants.USER_ALREADY_EXISTS.getType()));
    }

    private User saveUser(UserDTO userDTO) {
        return userRepo.saveAndFlush(UserDTO.prepareUserEntity(userDTO));
    }

    private Optional<User> findByEmail(String email){
        return userRepo.findByEmail(email);
    }

    @Override
    public UserDTO updateUser(String userId, UserDTO userDTO) throws UserException, IOException, JsonPatchException {
        logger.info("user dto {}",userDTO);
        User user = findById(userId);
//        UserDTO userDTOPatched = applyPatchToUser(userPatch,userDTO);
        userMapper.updateUserFromDto(userDTO,user);
        return UserDTO.prepareUserDTO( userRepo.save(user));
    }

//    private UserDTO applyPatchToUser(JsonPatch userPatch, UserDTO userDTO) throws JsonPatchException, JsonProcessingException {
//        JsonNode patched = userPatch.apply(objectMapper.convertValue(userDTO, JsonNode.class));
//        return objectMapper.treeToValue(patched,UserDTO.class);
//    }

    @Override
    public String deleteUser(String userId) throws UserException {
        User user = findById(userId);
        userRepo.delete(user);
        return "User deleted successfully";
    }

    @Override
    public UserDTO getUser(String userId) throws UserException {
        return UserDTO.prepareUserDTO(findById(userId));
    }

    private User findById(String userId) throws UserException {
        logger.info("Retrieving details from db");
        return (userRepo.findById(userId).
                orElseThrow(()-> new UserException(env.getProperty(ExceptionConstants.USER_NOT_FOUND.getType()))));
    }

    @Override
    public UserDTO getUserByEmail(String email) throws UserException {
        return UserDTO.prepareUserDTO(findByEmail(email).orElseThrow(()->new UserException(ExceptionConstants.USER_NOT_FOUND.getType())));
    }
}
