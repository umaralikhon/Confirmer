package com.umaralikhon.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@Valid
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @Size(min = 2, max=255, message = "Field should contain at least 2 characters!")
    @NotNull(message = "Field can't be empty!")
    @NotBlank(message = "Field can't be empty!")
    private String name;

    @Column(name = "user_email", unique = true)
    @Email(message = "There is not email!",
            regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    private String email;

    @Column(name = "password")
    @Size(min = 8, message = "Password should contain at least 8 characters")
    private String password;

    @Column(name = "st_confirm")
    private boolean isConfirmed = false;

    @Column(name = "step")
    private String step;

    @Column(name = "tg_id")
    private String tgId;

    @Column(name = "stcode")
    private String stCode = String.valueOf(CodeGenerator.rnd());

    @Column(name = "bt_code")
    private String btCode; // = String.valueOf(CodeGenerator.rnd());

    public User(String stCode, String email){
        this.email = email;
        this.stCode =  stCode;
    }
}
