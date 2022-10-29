package com.pratice.quizzApp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Questions")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String body;

    @OneToMany(targetEntity = Option.class,fetch =FetchType.EAGER,orphanRemoval = true,cascade = CascadeType.ALL)
    private Set<Option> options;

    @OneToOne
    @JoinColumn(name = "correct_option_id")
    private Option correctOption;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
