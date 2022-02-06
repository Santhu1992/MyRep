package com.snatch.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.snatch.dto.UserDTO;
import com.snatch.exception.UserException;

import java.io.IOException;

public interface UserService {

    public UserDTO createUser(UserDTO userDTO) throws UserException;
    public UserDTO updateUser(String userId, UserDTO userDTO) throws UserException, IOException, JsonPatchException;
    public String deleteUser(String userId) throws UserException;
    public UserDTO getUser(String userId) throws UserException;
    public UserDTO getUserByEmail(String email) throws UserException;
}
