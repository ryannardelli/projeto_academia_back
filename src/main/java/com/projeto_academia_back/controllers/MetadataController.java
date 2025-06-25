package com.projeto_academia_back.controllers;

import com.projeto_academia_back.enums.Objective;
import com.projeto_academia_back.models.ObjectiveDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/metadata")
public class MetadataController {
    @GetMapping("/objectives")
    public List<ObjectiveDto> listObjectives(){
        return Arrays.stream(Objective.values())
                .map(obj -> new ObjectiveDto(format(obj.name()), obj.name()))
                .collect(Collectors.toList());
    }

    private String format(String nomeEnum) {
        String formated = nomeEnum.replace("_", " ").toLowerCase();

        StringBuilder sb = new StringBuilder();
        boolean capitalizeNext = true;

        for (char c : formated.toCharArray()) {
            if (capitalizeNext && Character.isLetter(c)) {
                sb.append(Character.toUpperCase(c));
                capitalizeNext = false;
            } else {
                sb.append(c);
            }

            if (c == ' ') {
                capitalizeNext = true;
            }
        }
        return sb.toString();
    }
}
