package com.example.pz2_6_v1.controller;

import com.example.pz2_6_v1.modal.*;
import com.example.pz2_6_v1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class MedicineController {

    @Autowired
    private MedicineRepository medicineRepository;

    @GetMapping("/medicine/form")
    public String showMedicineForm() {
        return "medicine_form";
    }

    @PostMapping("/medicine/add")
    public String addMedicine(@RequestParam("name") String name,
                              @RequestParam("expiryDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate expiryDate,
                              @RequestParam("arrivalDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate arrivalDate) {
        Medicine medicine = new Medicine();
        medicine.setName(name);
        medicine.setExpiryDate(expiryDate);
        medicine.setArrivalDate(arrivalDate);
        medicineRepository.save(medicine);

        return "redirect:/medicine/form";
    }

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/order/form")
    public String showOrderForm(Model model) {
        model.addAttribute("order", new Order());
        return "order_form";
    }

    @PostMapping("/order/add")
    public String addOrder(@ModelAttribute("order") Order order) {
        orderRepository.save(order);
        return "redirect:/medicine/delivery";
    }

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping("/doctor/form")
    public String showDoctorForm(Model model) {
        model.addAttribute("doctor");
        return "doctor_form";
    }

    @PostMapping("/doctor/add")
    public String addDoctor(@ModelAttribute("doctor") Doctor doctor) {
        doctorRepository.save(doctor);
        return "redirect:/medicine/delivery";
    }

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/patient/form")
    public String showPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient_form";
    }

    @PostMapping("/patient/add")
    public String addPatient(@ModelAttribute("patient") Patient patient) {
        patientRepository.save(patient);
        return "redirect:/medicine/delivery";
    }

    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @GetMapping("/diagnosis/form")
    public String showDiagnosisForm(Model model) {
        model.addAttribute("diagnosis", new Diagnosis());
        return "diagnosis_form";
    }

    @PostMapping("/diagnosis/add")
    public String addDiagnosis(@ModelAttribute("diagnosis") Diagnosis diagnosis) {
        diagnosisRepository.save(diagnosis);
        return "redirect:/medicine/delivery";
    }

}

