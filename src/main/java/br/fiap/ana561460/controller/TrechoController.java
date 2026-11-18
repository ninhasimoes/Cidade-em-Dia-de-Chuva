package br.fiap.ana561460.controller;

import br.fiap.ana561460.dto.TrechoDTO;
import br.fiap.ana561460.service.BairroService;
import br.fiap.ana561460.service.TrechoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/trechos")
public class TrechoController {

    private final TrechoService trechoService;
    private final BairroService bairroService;

    public TrechoController(TrechoService trechoService, BairroService bairroService) {
        this.trechoService = trechoService;
        this.bairroService = bairroService;
    }

    @GetMapping()
    public String listar(Model model) {
        model.addAttribute("trechos", trechoService.listar());
        return "trechos/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("trecho", new TrechoDTO());
        model.addAttribute("bairros", bairroService.listar());
        return "trechos/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("trecho", trechoService.buscarPorId(id));
        model.addAttribute("bairros", bairroService.listar());
        return "trechos/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("trecho") TrechoDTO dto,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("bairros", bairroService.listar());
            return "trechos/form";
        }
        trechoService.salvar(dto);
        return "redirect:/trechos";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        trechoService.excluir(id);
        return "redirect:/trechos";
    }
}
