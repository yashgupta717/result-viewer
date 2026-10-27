package com.result.view.controller;

import com.result.view.dto.RequestResultForm;
import com.result.view.entity.Mark;
import com.result.view.entity.Student;
import com.result.view.repository.StudentRepo;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Controller
public class PageController {

    @GetMapping("/user-login")
    public String loginPage() {
        return "login_page";
    }

    private StudentRepo studentRepo;
    public PageController(StudentRepo studentRepo){
        this.studentRepo=studentRepo;
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/view-result")
    public String viewResultForm(Model model) {

        RequestResultForm requestResultForm = new RequestResultForm();
        model.addAttribute("requestResultForm" , requestResultForm);
        return "view_result_form";
    }

    @GetMapping("/help")
    public String help() {
        return "help";
    }


    @PostMapping("/view-result-action")
    public String viewResult(
            @Valid @ModelAttribute RequestResultForm requestResultForm,
            BindingResult bindingResult,
            Model model
    ){
        Optional<Student> optionalStudent = studentRepo.findByRollNumberAndDateOfBirth(requestResultForm.getRollNumber() , requestResultForm.getDateOfBirth());
        if(optionalStudent.isEmpty()){
            return "redirect:/view_result";
        }
        Student student = optionalStudent.get();
        List<Mark> marks = student.getMarks();

        AtomicReference<Double> totalMark = new AtomicReference<>(0.0);
        AtomicReference<Double> totalMax = new AtomicReference<>(0.0);
        marks.forEach(mark ->{
            totalMark.set(totalMark.get() + Double.parseDouble(mark.getMarks()));
            totalMax.set(totalMax.get() + Double.parseDouble(mark.getMaxMark()));
        });

        double percentage = (totalMark.get() / totalMax.get()) *100;



        if(bindingResult.hasErrors()){
            return "view_result_form";
        }


        boolean passed = percentage>50 ? true : false;

        model.addAttribute("student" , student);
        model.addAttribute("marks" , marks);
        model.addAttribute("percentage" , percentage);
        model.addAttribute("totalMark",totalMark);
        model.addAttribute("totalMax" , totalMax);
        model.addAttribute("passed" , passed);
        model.addAttribute("currentDate" , LocalDate.now().toString());

        return "view_result";
    }

}
