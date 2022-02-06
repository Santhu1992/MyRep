package com.snatch.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.snatch.dto.UserDTO;
import com.snatch.exception.UserException;
import com.snatch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) throws UserException {
        return  new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }

    @GetMapping(path = "{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("userId") String userId) throws UserException {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @PatchMapping(path = "{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("userId") String userId, @RequestBody UserDTO userDTO) throws UserException, IOException, JsonPatchException {

        return ResponseEntity.ok(userService.updateUser(userId,userDTO));
    }

    @DeleteMapping(path = "{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") String userId) throws UserException {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

}
