package br.fiap.ana561460.controller;

import br.fiap.ana561460.dto.BairroDTO;
import br.fiap.ana561460.dto.PainelItemDTO;
import br.fiap.ana561460.exception.RegraNegocioException;
import br.fiap.ana561460.service.BairroService;
import br.fiap.ana561460.service.PainelService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;


@Controller
@RequestMapping("/bairros")
public class BairroController {
    private final BairroService bairroService;
    private final PainelService painelService;

    public BairroController(BairroService bairroService, PainelService painelService) {
        this.bairroService = bairroService;
        this.painelService = painelService;
    }

    @GetMapping()
    public String listar(Model model) {
        model.addAttribute("bairros", bairroService.listar());
        return "bairros/lista";}

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("bairro", new BairroDTO());
        model.addAttribute("zonas", List.of("Centro", "Norte", "Sul", "Leste", "Oeste"));
        return "bairros/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("bairro") BairroDTO dto,
                         BindingResult result,
                         Model model) {

        if (result.hasErrors()) {
            model.addAttribute("zonas", List.of("Centro", "Norte", "Sul", "Leste", "Oeste"));
            return "bairros/form";
        }

        bairroService.salvar(dto);
        return "redirect:/bairros";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("bairro", bairroService.buscarPorId(id));
        model.addAttribute("zonas", List.of("Centro", "Norte", "Sul", "Leste", "Oeste"));
        return "bairros/form";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, Model model) {
        try {
            bairroService.excluir(id);
            return "redirect:/bairros";
        } catch (RegraNegocioException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("bairros", bairroService.listar());
            return "bairros/lista";
        }
    }

    @GetMapping("/{id}/painel")
    public String painel(@PathVariable Long id, Model model) {
        BairroDTO bairro = bairroService.buscarPorId(id);
        List<PainelItemDTO> itens = painelService.montarPainel(id);

        long normal  = itens.stream().filter(painel -> "NORMAL".equals(painel.getUltimoStatus())).count();
        long atencao = itens.stream().filter(painel -> "ATENCAO".equals(painel.getUltimoStatus())).count();
        long alerta  = itens.stream().filter(painel -> "ALERTA".equals(painel.getUltimoStatus())).count();

        model.addAttribute("bairro", bairro);
        model.addAttribute("itens", itens);
        model.addAttribute("normal", normal);
        model.addAttribute("atencao", atencao);
        model.addAttribute("alerta", alerta);
        return "bairros/painel";
    }
}



