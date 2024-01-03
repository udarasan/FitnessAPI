package com.perspicaz.jim.modules.trainer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.perspicaz.jim.modules.userProfile.UserProfile;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trainer")
@Getter
@Setter
@ToString
public class Trainer  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private UserProfile profile;



}