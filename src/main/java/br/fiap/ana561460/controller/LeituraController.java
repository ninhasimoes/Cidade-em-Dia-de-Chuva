package br.fiap.ana561460.controller;

import br.fiap.ana561460.dto.LeituraDTO;
import br.fiap.ana561460.service.LeituraService;
import br.fiap.ana561460.service.TrechoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/leituras")
public class LeituraController {

    private final LeituraService leituraService;
    private final TrechoService trechoService;

    public LeituraController(LeituraService leituraService, TrechoService trechoService) {
        this.leituraService = leituraService;
        this.trechoService = trechoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("leituras", leituraService.listar());
        return "leituras/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("leitura", new LeituraDTO());
        model.addAttribute("trechos", trechoService.listar());
        return "leituras/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("leitura", leituraService.buscarPorId(id));
        model.addAttribute("trechos", trechoService.listar());
        return "leituras/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("leitura") LeituraDTO dto,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("trechos", trechoService.listar());
            return "leituras/form";
        }
        leituraService.salvar(dto);
        return "redirect:/leituras";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        leituraService.excluir(id);
        return "redirect:/leituras";
    }




}
