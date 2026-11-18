package br.fiap.ana561460.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
    public class HomeController {

        @GetMapping("/")
        public String home() {
            return "home";
        }
    }

