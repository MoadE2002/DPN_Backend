package com.Dpnetworks.dp.dto;

public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private String phonenumber;
    private String address;

    public UserDTO(Long id, String email, String name, String phonenumber, String address) {
        this.id = id;
        this.email = email;
        this.username = name;
        this.phonenumber = phonenumber;
        this.address = address;
    }



}
