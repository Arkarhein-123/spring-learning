package com.example.demo.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NameService {

    private Map<String , List<String>> names = Map.of(
            "Arkar", List.of("Energico","Perfecto"),
            "Hein",List.of("Fanstico","Andantio")
    );

    @PreAuthorize("#name == authentication.principal.username")
    public List<String> getSecretNames(String name){
        return names.get(name);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public String getName(){
        return "Fantastico";
    }


}
