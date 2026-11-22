
package br.com.feiraintegradora.feiraintegradora.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import br.com.feiraintegradora.feiraintegradora.model.Feira;
import br.com.feiraintegradora.feiraintegradora.model.Feirante;
import br.com.feiraintegradora.feiraintegradora.model.Inscricao;

import br.com.feiraintegradora.feiraintegradora.repository.FeiraRepository;
import br.com.feiraintegradora.feiraintegradora.repository.FeiranteRepository;
import br.com.feiraintegradora.feiraintegradora.repository.InscricaoRepository;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

@Controller
public class HomeController {

    private final FeiraRepository feiraRepository;
    private final FeiranteRepository feiranteRepository;
    private final InscricaoRepository inscricaoRepository;
    private final GerenciadorDeInscricao gerenciadorDeInscricao;

    public HomeController(FeiraRepository feiraRepository,FeiranteRepository feiranteRepository,InscricaoRepository inscricaoRepository,GerenciadorDeInscricao gerenciadorDeInscricao) {

        this.feiraRepository = feiraRepository;
        this.feiranteRepository = feiranteRepository;
        this.inscricaoRepository = inscricaoRepository;
        this.gerenciadorDeInscricao = gerenciadorDeInscricao;
    }

    @GetMapping("/")
    public String inicio() {
        return "index";
    }
    
        
    @GetMapping("/painel-prefeitura")
    public String painelPrefeitura() {
        return "painel-prefeitura";
    }
    
    @GetMapping("/login-prefeitura")
    public String loginPrefeitura() {
        return "login-prefeitura";
    }

    @PostMapping("/login-prefeitura")
    public String autenticarPrefeitura(@RequestParam String email,@RequestParam String senha,Model model) {

    if (email.equals("prefeitura@feira.com") && senha.equals("123456")) {
        return "redirect:/painel-prefeitura";
    }
        model.addAttribute("erro", "E-mail ou senha incorretos.");
        return "login-prefeitura";
    }
    
    @GetMapping("/login-feirante")
    public String loginFeirante() {
        return "login-feirante";
    }

    @PostMapping("/login-feirante")
    public String autenticarFeirante(@RequestParam String email, @RequestParam String senha, Model model) {

    if (email.equals("feirante@feira.com")&& senha.equals("123456")) {
        return "redirect:/painel-feirante";
    }
    model.addAttribute("erro", "E-mail ou senha incorretos.");
    return "login-feirante";
    }
    
    @PostMapping("/aprovar-feirante")
    public String aprovarFeirante(@RequestParam int id) {
        Feirante feirante = feiranteRepository.findById(id).orElseThrow();
        feirante.setStatusAprovacao("APROVADO");
        feiranteRepository.save(feirante);
        return "redirect:/feirantes";
    }

    @PostMapping("/rejeitar-feirante")
    public String rejeitarFeirante(@RequestParam int id) {
        Feirante feirante = feiranteRepository.findById(id).orElseThrow();
        feirante.setStatusAprovacao("REJEITADO");
        feiranteRepository.save(feirante);
        return "redirect:/feirantes";
    }

    @GetMapping("/cadastro-feira")
    public String paginaCadastroFeira() {
        return "cadastro-feira";
    }

    @PostMapping("/cadastrar-feira")
    public String cadastrarFeira(@ModelAttribute Feira feira) {
        feiraRepository.save(feira);
        return "redirect:/";
    }

    @GetMapping("/feiras")
    public String listarFeiras(Model model) {
        model.addAttribute("feiras", feiraRepository.findAll());
        return "lista-feiras";
    }

    @GetMapping("/cadastro-feirante")
    public String paginaCadastroFeirante(Model model) {
        model.addAttribute("feirante", new Feirante());
        return "cadastro-feirante";
    }

    @PostMapping("/cadastrar-feirante")
    public String cadastrarFeirante(@ModelAttribute Feirante feirante, Model model) {

    if (feiranteRepository.existsByEmail(feirante.getEmail())) {
        model.addAttribute("erro", "Este e-mail já está cadastrado.");
        return "cadastro-feirante";
    }

    if (feiranteRepository.existsByCpfCnpj(feirante.getCpfCnpj())) {
        model.addAttribute("erro", "Este CPF/CNPJ já está cadastrado.");
        return "cadastro-feirante";
    }

    feirante.setStatusAprovacao("PENDENTE");
    feiranteRepository.save(feirante);
    return "redirect:/";
    }

    @GetMapping("/feirantes")
    public String listarFeirantes(Model model) { model.addAttribute("feirantes", feiranteRepository.findAll());
        return "lista-feirantes";
    }

    @GetMapping("/cadastro-inscricao")
    public String paginaCadastroInscricao(Model model) {model.addAttribute("feirantes", feiranteRepository.findAll());model.addAttribute("feiras", feiraRepository.findAll());
        return "cadastro-inscricao";
    }

    @PostMapping("/cadastrar-inscricao")
    public String cadastrarInscricao(@RequestParam("feiranteId") int feiranteId,@RequestParam("feiraId") int feiraId) {

        Feirante feirante = feiranteRepository.findById(feiranteId).orElseThrow();
        Feira feira = feiraRepository.findById(feiraId).orElseThrow();

        Inscricao inscricao = new Inscricao();
        inscricao.setFeirante(feirante);
        inscricao.setFeira(feira);
        inscricao.setStatusAprovacao("PENDENTE");

        gerenciadorDeInscricao.adicionarInscricao(inscricao);

        return "redirect:/";
    }

    @GetMapping("/mapa")
    public String abrirMapa(Model model) {

        List<Feira> feiras = feiraRepository.findAll();
        Map<Integer, List<Inscricao>> inscricoesPorFeira = new LinkedHashMap<>();
        for (Feira feira : feiras) {
            List<Inscricao> inscricoes = inscricaoRepository.findAllByFeira_IdAndStatusAprovacaoIgnoreCase(feira.getId(), "APROVADA");
            inscricoesPorFeira.put(feira.getId(), inscricoes);
        }
        model.addAttribute("feiras", feiras);
        model.addAttribute("inscricoesPorFeira", inscricoesPorFeira);

        return "mapa";
    }
}
