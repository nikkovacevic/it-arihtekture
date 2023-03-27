package ita.ms.properties.dto;

import ita.ms.properties.model.Property;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private int pageCount;
    private long resultCount;
    private List<Property> results;
}
