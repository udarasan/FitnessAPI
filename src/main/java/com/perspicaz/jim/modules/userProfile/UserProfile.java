package com.perspicaz.jim.modules.userProfile;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.perspicaz.jim.common.enums.UserRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "userProfile")
@Getter
@Setter
@ToString
public class UserProfile  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "role", length = 50)
    private UserRole role;

    @Column(name = "password", length = 20)
    private String password;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate addedDate;

}