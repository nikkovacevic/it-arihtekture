package ita.ms.properties.controller;

import ita.ms.properties.dto.RequestDto;
import ita.ms.properties.dto.ResponseDto;
import ita.ms.properties.service.PropertyService;
import ita.ms.properties.model.Property;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("api/properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {this.propertyService = propertyService;}

    @PostMapping("/")
    @ResponseBody
    public ResponseDto getAll(@RequestBody RequestDto requestDto) {
        try {
            Page<Property> all = propertyService.getAll(
                    requestDto.getPageNumber(),
                    requestDto.getPageSize()
            );
            return new ResponseDto(
                    all.getTotalPages(),
                    all.getTotalElements(),
                    all.getContent().stream().collect(Collectors.toList())
            );
        } catch (Exception e) {
            throw new WebServerException(e.getMessage(), e);
        }
    }
}
