package com.result.view.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentForm {

    @NotBlank(message = "Name is required !!")
    private String name;
    @NotBlank(message = "Name is required !!")
    private String rollNumber;
//    @NotBlank(message = "Email is required !!")
    @Email(message = "Invalid email !!")
    private String email;
    @NotBlank(message = "address is required !!")
    private String addrress;
    @NotBlank(message = "School name is required !!")
    private String schoolName;
    private String photoName;
//    @NotBlank(message = "Dob is required !!")
    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDate dateOfBirth;
    @NotBlank(message = "Standard is required !!")
    private String standard;
    @NotBlank(message = "father name is required !!")
    private String fatherName;
    @NotBlank(message = "gender is required !!")
    private String gender;

    private List<MarksForm> marks = new ArrayList<>();
}
