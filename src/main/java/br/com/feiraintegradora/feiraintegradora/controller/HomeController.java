package br.com.feiraintegradora.feiraintegradora.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import br.com.feiraintegradora.feiraintegradora.model.Feira;
import br.com.feiraintegradora.feiraintegradora.repository.FeiraRepository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import br.com.feiraintegradora.feiraintegradora.model.Feirante;
import br.com.feiraintegradora.feiraintegradora.repository.FeiranteRepository;
import br.com.feiraintegradora.feiraintegradora.model.Inscricao;
import br.com.feiraintegradora.feiraintegradora.repository.InscricaoRepository;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController{
    
    private final FeiraRepository feiraRepository;
    private final InscricaoRepository inscricaoRepository; 
    private final FeiranteRepository feiranteRepository;
    
    @GetMapping("/")
    public String paginaInicial(){
        return "index";
    }
    
    @GetMapping("/cadastro-feira")
    public String paginaCadastroFeira(){
        return "cadastro-feira";
    }
    
    public HomeController(FeiraRepository feiraRepository, FeiranteRepository feiranteRepository, InscricaoRepository inscricaoRepository){
        this.feiraRepository = feiraRepository;
        this.feiranteRepository = feiranteRepository;
        this.inscricaoRepository = inscricaoRepository;
    }
    @PostMapping("/cadastrar-feira")
    public String cadastrarFeira(@ModelAttribute Feira feira){
        System.out.println("ENTROU NO METODO CDASTRAR FEIRA");
        feiraRepository.save(feira);
        return "redirect:/";
    }
    
    @GetMapping("/feiras")
    public String listarFeiras(Model model){
        System.out.println("ENTROU NO METODO LISTAR FEIRA");
        model.addAttribute("feiras", feiraRepository.findAll());
        return "lista-feiras";       
    }
    
    @GetMapping("/cadastro-feirante")
    public String paginaCadastroFeirante(){
        return "cadastro-feirante";
    }
    
    @PostMapping("/cadastrar-feirante")
    public String cadastrarFeirante(@ModelAttribute Feirante feirante){
        System.out.println("ENTROU NO METODO CADASTRAR FEIRANTE");
        feirante.setStatusAprovacao("PENDENTE");
        feiranteRepository.save(feirante);
        return "redirect:/";
    }
    
    @GetMapping("/feirantes")
    public String listarFeirantes(Model model) {
        System.out.println("ENTROU NO METODO LISTAR FEIRANTE");
        model.addAttribute("feirantes", feiranteRepository.findAll());
        return "lista-feirantes";
    }
    
    @GetMapping("/cadastro-inscricao")
    public String paginaCadastroInscricao(Model model){
        System.out.println("ENTROU NO METODO CADASTRO INSCRICAO");
        model.addAttribute("feirantes", feiranteRepository.findAll());
        model.addAttribute("feiras", feiraRepository.findAll());
        return "cadastro-inscricao";
    }
    
    @PostMapping("/cadastrar-inscricao")
    public String cadastrarInscircao(@RequestParam("feiranteId")int feiranteId, @RequestParam("feiraId") int feiraId){ 
        Feirante feirante = feiranteRepository.findById(feiranteId).orElseThrow();
        Feira feira = feiraRepository.findById(feiraId).orElseThrow();
        Inscricao inscricao = new Inscricao();
        inscricao.setFeirante(feirante);
        inscricao.setFeira(feira);
        inscricao.setStatusAprovacao("PENDENTE");
        inscricaoRepository.save(inscricao);
        return "redirect:/";   
    }
    
}


