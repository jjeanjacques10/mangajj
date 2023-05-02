package com.mangajj.mangacontrol.adapter.web;

import com.mangajj.mangacontrol.adapter.persistence.entity.UserEntity;
import com.mangajj.mangacontrol.adapter.web.dto.RegisterDTO;
import com.mangajj.mangacontrol.adapter.web.dto.ResponseDTO;
import com.mangajj.mangacontrol.adapter.web.dto.UserDTO;
import com.mangajj.mangacontrol.application.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<ResponseDTO> getUserByEmail(@RequestParam String email) {
        UserEntity user = userService.getUserByEmail(email);
        return ResponseEntity.ok(ResponseDTO.builder()
                .data(mapper.map(user, UserDTO.class))
                .build());
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> createUser(@RequestBody RegisterDTO register) {
        UserEntity user = mapper.map(register, UserEntity.class);

        UserEntity userCreated = userService.save(user);
        return ResponseEntity.created(URI.create("/" + userCreated.getId()))
                .body(ResponseDTO.builder()
                        .data(mapper.map(userCreated, UserDTO.class))
                        .build());
    }
}
