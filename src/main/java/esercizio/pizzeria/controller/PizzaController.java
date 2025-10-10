package esercizio.pizzeria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import esercizio.pizzeria.model.Pizza;
import esercizio.pizzeria.repository.PizzaRepository;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/")
public class PizzaController {

    @Autowired
    private PizzaRepository repository;

    @GetMapping("")
    public String filter(@RequestParam(name="keyword", required=false) String keyword, Model model){
        List<Pizza> result = null;
        if(keyword == null || keyword.isBlank()){
            result = repository.findAll();
        } else{
            result = repository.findByNameContainingIgnoreCase(keyword);
        }
        
        model.addAttribute("list", result);
        
        return "index";
    }

    @GetMapping("/details/{id}")
    public String detail(@PathVariable(name="id") Integer id, Model model){
        Optional<Pizza> optionalPizza = repository.findById(id);
        if(optionalPizza.isPresent()){
            model.addAttribute("pizza", optionalPizza.get());
            model.addAttribute("empty", false);
        } else {
            model.addAttribute("empty", true);
        }

        return "details";
    }

    @GetMapping("/insert")
    public String showInsertForm(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "insert";
    }

    @PostMapping("/insert")
    public String insert(@Valid @ModelAttribute("pizza") Pizza formPizza, 
                        BindingResult bindingResult, 
                        Model model) {
        if(formPizza.getPrice() == 0){
            bindingResult.addError(new ObjectError("price", "This field cannot be equal to 0"));
        }

        if(formPizza.getPrepTime() == 0){
            bindingResult.addError(new ObjectError("prepTime", "This field cannot be equal to 0"));
        }
        
        if(bindingResult.hasErrors()){
            return "/insert";
        }

        repository.save(formPizza);
        return "redirect: index";
    }    
}
