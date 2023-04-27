package com.samirbenbouker.ninjatalent.config;

import com.samirbenbouker.ninjatalent.model.Address;
import com.samirbenbouker.ninjatalent.model.User;
import com.samirbenbouker.ninjatalent.repository.AddressRepository;
import com.samirbenbouker.ninjatalent.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, AddressRepository addressRepository) {
        return args -> {
            Address address = new Address(
                    "c/ Barraquer",
                    "Sant Sadurni d Anoia",
                    "Barcelona",
                    "Spain",
                    "08770"
            );

            Address address1 = new Address(
                    "c/ Orfeo Catala",
                    "Santa Coloma de Queralt",
                    "Tarragona",
                    "Spain",
                    "43420"
            );

            addressRepository.saveAll(List.of(address, address1));

            User samir = new User(
                    "Samir",
                    "samir@gmail.com",
                    LocalDateTime.of(2001, Month.FEBRUARY, 28, 0, 0, 0),
                    address
            );


            User pedro = new User(
                    "Pedro",
                    "pedro@gmail.com",
                    LocalDateTime.of(2006, Month.DECEMBER, 12, 0, 0, 0),
                    address
            );

            User sara = new User(
                    "Sara",
                    "sara@gmail.com",
                    LocalDateTime.of(1999, Month.JULY, 27, 0, 0, 0),
                    address1
            );

            userRepository.saveAll(List.of(samir, pedro, sara));
        };
    }
}
