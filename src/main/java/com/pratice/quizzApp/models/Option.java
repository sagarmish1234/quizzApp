package com.pratice.quizzApp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="Options")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private  String body;

    public Option(String body){
        this.body = body;
    }


    public boolean checkStringEquality(String str){
        return body.equals(str);
    }
}
