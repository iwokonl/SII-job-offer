package org.example.zadanko.mapper;

import org.example.zadanko.dto.CreatedBox.CreatedBoxResponseDto;
import org.example.zadanko.model.Box;

public class BoxMapper {

    public static CreatedBoxResponseDto BoxToCratedBoxDto(Box box) {
        return new CreatedBoxResponseDto(
                box.getId()
        );
    }
}
