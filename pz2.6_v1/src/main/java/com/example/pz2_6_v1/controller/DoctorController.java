package com.example.pz2_6_v1.controller;

import org.springframework.ui.Model;
import com.example.pz2_6_v1.modal.Medicine;
import com.example.pz2_6_v1.modal.Order;
import com.example.pz2_6_v1.repository.MedicineRepository;
import com.example.pz2_6_v1.repository.OrderRepository;
import com.example.pz2_6_v1.service.MedicineDataUpdateService;
import org.antlr.v4.runtime.tree.pattern.ParseTreePattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
public class DoctorController {

    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private MedicineDataUpdateService medicineDataUpdateService;

    @GetMapping("/medicine/expiry")
    public String getMedicineExpiry(Model model) {
        List<Medicine> medicines = medicineRepository.findAll();
        model.addAttribute("medicines", medicines);
        return "medicine_expiry";
    }

    @GetMapping("/medicine/delivery")
    public String getMedicineDelivery(Model model) {
        // Отримати середній термін реалізації ліків
        double averageDeliveryDays = calculateAverageDeliveryDays();

        // Передати дані моделі для відображення на веб-сторінці
        model.addAttribute("averageDeliveryDays", averageDeliveryDays);

        return "medicine_delivery";
    }

    private double calculateAverageDeliveryDays() {
        List<Order> orders = orderRepository.findAll();
        LocalDate today = LocalDate.now();

        int totalDays = 0;
        int count = 0;

        for (Order order : orders) {
            LocalDate deliveryDate = order.getOrderDate();
            LocalDate arrivalDate = order.getMedicine().getArrivalDate();

            if (deliveryDate != null && deliveryDate.isBefore(today) && arrivalDate != null) {
                int days = (int) ChronoUnit.DAYS.between(deliveryDate, arrivalDate);
                totalDays += days;
                count++;
            }
        }

        if (count > 0) {
            return (double) totalDays / count;
        } else {
            return 0.0;
        }
    }
}

