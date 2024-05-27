package tech.ffs.urlshortner.service;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UrlServiceDiffblueTest {
    @Autowired
    private UrlService urlService;

    /**
     * Method under test: {@link UrlService#getFullUrl(String)}
     */
    @Test
    void testGetFullUrl() {
        // Arrange, Act and Assert
        assertFalse(urlService.getFullUrl("https://example.org/example").isPresent());
    }
}
