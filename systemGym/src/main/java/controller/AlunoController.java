package controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import entity.Aluno;


@Controller
public class AlunoController {

    @GetMapping("/admin/aluno")
    public ModelAndView form(Aluno aluno) {
        ModelAndView mv = new ModelAndView("admin/formAluno");
        mv.addObject("aluno", aluno);
        return mv;
    }
    
}
