package com.result.view.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestResultForm {
    @NotBlank(message = "enter roll number !!")
    private String rollNumber;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDate dateOfBirth;
}
