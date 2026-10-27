package com.result.view.controller;

import com.result.view.dto.StudentForm;
import com.result.view.entity.Student;
import com.result.view.repository.StudentRepo;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.result.view.entity.Mark;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private StudentRepo studentRepo;

    private ModelMapper modelMapper;

    public AdminController(StudentRepo studentRepo, ModelMapper modelMapper) {
        this.studentRepo = studentRepo;
        this.modelMapper = modelMapper;
    }
    @PostMapping("/result-page")
    public String redirectHandler(){
        return "redirect:/admin/add-result";
    }
    @GetMapping("/add-result")
    public String addResultForm(Principal principal, Model model){
        StudentForm  studentForm = new StudentForm();
        String name = principal.getName();
        List<String> stdOption=new ArrayList<>();
        stdOption.add("Class 1");
        stdOption.add("Class 2");
        stdOption.add("Class 3");
        stdOption.add("Class 4");
        stdOption.add("Class 5");
        stdOption.add("Class 6");
        stdOption.add("Class 7");
        stdOption.add("Class 8");
        stdOption.add("Class 9");
        stdOption.add("Class 10");
        stdOption.add("Class 11");
        stdOption.add("Class 12");

        model.addAttribute("studentForm",studentForm);
        model.addAttribute("stdOption",stdOption);
        model.addAttribute("name" , name);
        return "admin/add_result";
    }

    @RequestMapping(value = "/add-result-action" , method = RequestMethod.POST)
    public String processAddResulForm(
            @Valid @ModelAttribute StudentForm studentForm,
            BindingResult bindingResult,
            Model  model
    ){
        if(bindingResult.hasErrors()){

            List<String> stdOption=new ArrayList<>();
            stdOption.add("Class 1");
            stdOption.add("Class 2");
            stdOption.add("Class 3");
            stdOption.add("Class 4");
            stdOption.add("Class 5");
            stdOption.add("Class 6");
            stdOption.add("Class 7");
            stdOption.add("Class 8");
            stdOption.add("Class 9");
            stdOption.add("Class 10");
            stdOption.add("Class 11");
            stdOption.add("Class 12");

//            model.addAttribute("studentForm",studentForm);
            model.addAttribute("stdOption",stdOption);

            return "admin/add_result";
        }
        Student student = modelMapper.map(studentForm , Student.class);

        List<Mark> updatedList=student.getMarks().stream().map(mark -> {
            mark.setStudent(student);
            return mark;
        }).toList();

        student.setMarks(updatedList);


        student.setId(UUID.randomUUID().toString());
        Student savedStudent = studentRepo.save(student);
        return "redirect:/admin/add-result?message=Data added successfully";
    }
}
