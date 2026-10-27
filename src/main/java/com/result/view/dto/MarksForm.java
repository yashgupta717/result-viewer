package com.result.view.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MarksForm {


    private String subjectName;
    private String marks;
    private String maxMark;
    private String feedback;
    private String grade;
}
