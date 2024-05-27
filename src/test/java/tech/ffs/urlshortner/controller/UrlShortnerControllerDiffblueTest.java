package tech.ffs.urlshortner.controller;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tech.ffs.urlshortner.entities.UrlEntity;
import tech.ffs.urlshortner.repository.UrlRepository;
import tech.ffs.urlshortner.service.UrlService;

@ContextConfiguration(classes = {UrlShortnerController.class, UrlService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class UrlShortnerControllerDiffblueTest {
    @MockBean
    private UrlRepository urlRepository;

    @Autowired
    private UrlShortnerController urlShortnerController;

    /**
     * Method under test: {@link UrlShortnerController#redirect(String)}
     */
    @Test
    void testRedirect() throws Exception {
        // Arrange
        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setExpireAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        urlEntity.setFullUrl("https://example.org/example");
        urlEntity.setId("https://example.org/example");
        Optional<UrlEntity> ofResult = Optional.of(urlEntity);
        when(urlRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/{id}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(urlShortnerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("https://example.org/example"));
    }

    /**
     * Method under test: {@link UrlShortnerController#redirect(String)}
     */
    @Test
    void testRedirect2() throws Exception {
        // Arrange
        Optional<UrlEntity> emptyResult = Optional.empty();
        when(urlRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/{id}", "42");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(urlShortnerController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
