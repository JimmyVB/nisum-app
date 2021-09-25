package com.exam.nisum.app.controllers;

import com.exam.nisum.app.entity.User;
import com.exam.nisum.app.services.IUserService;
import com.exam.nisum.app.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private IUserService userService;

    private final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @PostMapping("/registro")
    public ResponseEntity<?> create(@RequestHeader HttpHeaders headers, @Valid @RequestBody User user, BindingResult result){

        String token = headers.getFirst(HttpHeaders.AUTHORIZATION);
        Map<String, Object> response = new HashMap<>();
        User userNew = null;

        boolean mailExists = userService.findEmail(user);

        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {

            if(Util.isValid(user.getEmail())){
                if(mailExists){
                    response.put("mensaje", "Error: El correo ya fue registrado ");
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
                }else if (!Util.isPasswordCorrect(user.getPassword())){
                    response.put("mensaje", "Error: La clave debe tener el siguiente formato: Una\n" +
                            "Mayuscula, letras minúsculas, y dos numeros.");
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
                } else{
                    user.setToken(token.substring(7, token.length()));
                    userNew = userService.save(user);
                }
            }else{
                response.put("mensaje", "Error: Escriba un correo válido ");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert a la BD ");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente ha sido creado con exito!.");
        response.put("cliente", userNew);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
}
