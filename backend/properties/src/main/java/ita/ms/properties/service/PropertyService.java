package ita.ms.properties.service;

import ita.ms.properties.model.Property;
import ita.ms.properties.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PropertyService {
    private final PropertyRepository propertyRepository;

    public Page<Property> getAll(int page, int size) {
        try {
            return propertyRepository.findAll(PageRequest.of(page, size));
        } catch (Exception e) {
            System.out.println("Error getting list of all property records");
            throw e;
        }
    }
}
