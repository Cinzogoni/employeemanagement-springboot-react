package com.javaguides.emsbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DeleteEmployeeHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime deletionTime;

    public DeleteEmployeeHistory(Employee employee) {
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastname();
        this.email = employee.getEmail();
        this.deletionTime = LocalDateTime.now();
    }
}
