package com.example.zalik_rsd.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

@Entity
public class FarmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Кадастровий номер земельної ділянки не може бути порожнім")
    @Pattern(regexp = "\\d{10}:\\d{2}:\\d{3}:\\d{4}", message = "Кадастровий номер земельної ділянки має неправильний формат \n(Приклад 1234567890:12:123:1234)")
    private String cadastreNumber;

    @NotBlank(message = "Назва підприємства не може бути порожньою")
    @Size(min = 10, max = 40, message = "Назва підприємства має містити від 10 до 40 символів")
    private String enterpriseName;

    @NotNull(message = "Річний дохід по земельній ділянці не може бути порожнім")
    @Positive(message = "Річний дохід по земельній ділянці має бути більше 0")
    private double annualIncome;

    @NotNull(message = "Загальна площа земельної ділянки не може бути порожньою")
    @Positive(message = "Загальна площа земельної ділянки має бути більше 0")
    private double landArea;

    // Constructors, getters, setters, and other methods

    // Constructors

    public FarmEntity() {
    }

    public FarmEntity(String cadastreNumber, String enterpriseName, double annualIncome, double landArea) {
        this.cadastreNumber = cadastreNumber;
        this.enterpriseName = enterpriseName;
        this.annualIncome = annualIncome;
        this.landArea = landArea;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getcadastreNumber() {
        return cadastreNumber;
    }

    public void setcadastreNumber(String cadastreNumber) {
        this.cadastreNumber = cadastreNumber;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public double getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(double annualIncome) {
        this.annualIncome = annualIncome;
    }

    public double getLandArea() {
        return landArea;
    }

    public void setLandArea(double landArea) {
        this.landArea = landArea;
    }
}
