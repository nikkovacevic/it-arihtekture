package ita.ms.properties.service;

import ita.ms.properties.model.Property;
import ita.ms.properties.repository.PropertyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PropertyServiceTest {

    @Mock
    private PropertyRepository propertyRepository;

    @InjectMocks
    private PropertyService propertyService;

    @Test
    void testGetAll() {
        List<Property> mockProperties = new ArrayList<>();
        mockProperties.add(new Property(1L, "link1", "title1", "location1", "price1", "image1"));
        mockProperties.add(new Property(2L, "link2", "title2", "location2", "price2", "image2"));
        mockProperties.add(new Property(3L, "link3", "title3", "location3", "price3", "image3"));

        Page<Property> mockPage = new PageImpl<>(mockProperties);



        when(propertyRepository.findAll(PageRequest.of(0, 3))).thenReturn(mockPage);

        Page<Property> results = propertyService.getAll(0, 3);

        assertEquals(mockPage, results);
    }
}