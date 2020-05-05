package com.example.demo.controllers;

import com.example.demo.models.Student;
import com.example.demo.repositories.IStudentRepository;
import com.example.demo.repositories.InMemoryStudentRepositoryImpl;
import com.example.demo.repositories.StudentRepositoryImplMySQL;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    private IStudentRepository studentRepository;

    public StudentController() {
        studentRepository = new StudentRepositoryImplMySQL();
    }

    @GetMapping("/student")
    public String index(Model model){
        model.addAttribute("students" , studentRepository.readAll());
        return "/student/overview";
    }

    @GetMapping("/student/edit/{id}")
    public String editStudents(@PathVariable int id, Model viewModel){
        viewModel.addAttribute("student", studentRepository.read(id));
        return "/student/edit";
    }

    @PostMapping("/student/editted")
    public String editStudents(Student student){
        System.out.println(student);
        Student studentToEdit = student;
        boolean found = false;

        for (Student student2 : studentRepository.readAll()){
            if (student2.getId() == student.getId()){
                studentToEdit = student2;
                found = true;
                break;
            }
        }
        if (!found){
            //incase student can't be foudn in above loop
            return "redirect:/student";
        }
        System.out.println(studentToEdit);
        if (!student.getFirstName().equals("")){
            studentToEdit.setFirstName(student.getFirstName());
        }
        if (!student.getLastName().equals("")){
            studentToEdit.setLastName(student.getLastName());
        }
        if (student.getEnrollmentDate() != null){
            studentToEdit.setEnrollmentDate(student.getEnrollmentDate());
        }
        if (!student.getCpr().equals("")){
            studentToEdit.setCpr(student.getCpr());
        }
        System.out.println(studentToEdit);
        studentRepository.update(studentToEdit);
        return "redirect:/student";
    }

    @GetMapping("student/enroll")
    public String enrollStudent(){
        return "/student/enroll";
    }

    @PostMapping("/student/enrolled")
    public String enrolledStudent(@ModelAttribute Student student){

        //student.setId(studentRepository.getNumberOfStudents()+1);
        System.out.println(student);

        studentRepository.create(student);
        return "redirect:/student/";
    }

    @PostMapping("/student")
    @ResponseBody
    public String getStudentByParameter(@RequestParam int id) {
        Student stu = studentRepository.read(id);
        return "The name is: " + stu.getFirstName() + " and the cpr is " + stu.getCpr();
    }
    //forskellen mellem nedenstående og ovenstående metode er primært deres retur type.
    //den ene retunerer en html fil, som får sendt data vira viewModel, mens ovenstående er sært.
    //hvis nedenstående skal gøre det samme, skal den også være en responsebody!

    @RequestMapping(value="/student/details/{id}",method = RequestMethod.GET)
    public String getStudentIdDetails(@PathVariable int id, Model viewModel){
        Student theStudent = studentRepository.read(id);
        viewModel.addAttribute("student",theStudent);
        return "/student/detail";
    }

    @RequestMapping(value = "/student/delete/{id}", method = RequestMethod.GET)
    public String deleteStudent(@PathVariable int id){
        studentRepository.delete(id);
        return "redirect:/student/";
    }
}
