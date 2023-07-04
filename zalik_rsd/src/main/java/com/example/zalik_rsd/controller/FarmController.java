package com.example.zalik_rsd.controller;

import com.example.zalik_rsd.modal.FarmEntity;
import com.example.zalik_rsd.repository.FarmRepository;
import com.example.zalik_rsd.service.GeneratePDF;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class FarmController {

    @Autowired
    private final FarmRepository farmRepository;

    private GeneratePDF generatePDF = new GeneratePDF();

    @Autowired
    public FarmController(FarmRepository farmRepository) {
        this.farmRepository = farmRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<FarmEntity> farms = farmRepository.findAll();
        model.addAttribute("farms", farms);
        return "index";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("farm", new FarmEntity());
        return "create";
    }

    @PostMapping("/add")
    public String addFarm(@Valid @ModelAttribute("farm") FarmEntity farm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldErrors().get(0);
            String errorMessage = fieldError.getDefaultMessage();

//            System.out.println("Ошибка валидации: " + errorMessage);
//            System.out.println("Каскадний номер: " + farm.getcadastreNumber()); //1234567890:12:345:6789
            model.addAttribute("errorMessage", errorMessage);
            return "create";
        }



        farmRepository.save(farm);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        FarmEntity farm = farmRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid farm Id: " + id));


        model.addAttribute("farm", farm);
        return "update";
    }

    @PostMapping("/edit/{id}")
    public String updateFarm(@PathVariable("id") long id, @Valid @ModelAttribute("farm") FarmEntity farm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            farm.setId(id); // Ensure that the ID is set for error handling
            FieldError fieldError = bindingResult.getFieldErrors().get(0);
            String errorMessage = fieldError.getDefaultMessage();

//            System.out.println("Ошибка валидации: " + errorMessage);
//            System.out.println("Каскадний номер: " + farm.getcadastreNumber()); //1234567890:12:345:6789
            model.addAttribute("errorMessage", errorMessage);
            return "update";
        }


        farmRepository.save(farm);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteFarm(@PathVariable("id") long id) {
        FarmEntity farm = farmRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid farm Id: " + id));

        farmRepository.delete(farm);
        return "redirect:/";
    }

    @GetMapping("/download/pdf")
    public String getPDFFile(HttpServletRequest request, HttpServletResponse response) {
        try {
            generatePDF.doDownload(request, response, farmRepository.findAll());} catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/";
    }

    @PostMapping("/filter")
    public String filterByEnterpriseName(@RequestParam("companyNameFilter") String enterpriseNameFilter, Model model) {
        if (enterpriseNameFilter.equals("")){
            List<FarmEntity> farms = farmRepository.findAll();
            model.addAttribute("farms", farms);
            return "index";
        }
        List<FarmEntity> filteredFarms = farmRepository.findByEnterpriseName(enterpriseNameFilter);
        model.addAttribute("farms", filteredFarms);
        return "index";
    }

}