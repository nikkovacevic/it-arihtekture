package ita.ms.properties.controller;

import ita.ms.properties.dto.RequestDto;
import ita.ms.properties.dto.ResponseDto;
import ita.ms.properties.service.PropertyService;
import ita.ms.properties.model.Property;
import ita.ms.properties.utils.JwtUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;

@RestController
@RequestMapping("api/properties")
public class PropertyController {

    private static final Logger logger = LogManager.getLogger(PropertyController.class);
    private final PropertyService propertyService;

    private final JwtUtil jwt;

    public PropertyController(PropertyService propertyService, JwtUtil jwt) {this.propertyService = propertyService;
        this.jwt = jwt;
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseDto getAll(@RequestBody RequestDto requestDto) {
        try {
            logger.info("Getting all properties...");
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
            logger.error("Error getting all properties...");
            throw e;
        }
    }

    @GetMapping("/test")
    public String test(@RequestBody String authorizationHeader) {
        if (jwt.validateToken(authorizationHeader)) {
            return "test";
        } else {
            return "unauthorized";
        }
    }

    @GetMapping("/test2")
    public String test2() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://users:8080/api/users/test";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        System.out.println("response prosim");
        System.out.println(response.getStatusCode());
        String data = response.getBody();
        System.out.println(data);
        return data;
    }
}
