package com.example.pz2_6_v1.service;

import com.example.pz2_6_v1.modal.Medicine;
import com.example.pz2_6_v1.modal.Order;
import com.example.pz2_6_v1.repository.MedicineRepository;
import com.example.pz2_6_v1.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class MedicineDataUpdateService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Scheduled(cron = "0 1 0 * * *") // Виконується щоденно о 00:01 годині
    public void updateMedicineData() {
        List<Medicine> medicines = medicineRepository.findAll();
        LocalDate today = LocalDate.now();

        for (Medicine medicine : medicines) {
            List<Order> orders = orderRepository.findByMedicine(medicine);

            LocalDate arrivalDate = medicine.getArrivalDate();
            if (arrivalDate != null && arrivalDate.isBefore(today)) {
                medicine.setExpiryDate(today.plusYears(2)); // Приклад: Термін придатності 2 роки
                medicineRepository.save(medicine);
            }

            if (!orders.isEmpty()) {
                int totalDays = 0;
                for (Order order : orders) {
                    LocalDate deliveryDate = order.getOrderDate();
                    if (deliveryDate != null && deliveryDate.isBefore(today)) {
                        totalDays += ChronoUnit.DAYS.between(deliveryDate, arrivalDate);
                    }
                }
                double averageDeliveryDays = totalDays / orders.size();
                // Зберегти середній термін реалізації ліків у файл або базу даних
            }
        }
    }
}

