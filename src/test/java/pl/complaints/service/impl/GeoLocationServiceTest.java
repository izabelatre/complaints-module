package pl.complaints.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GeoLocationServiceTest {

    @Autowired
    private GeoLocationService geoLocationService;

    @MockitoBean
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        when(request.getHeader("X-Forwarded-For")).thenReturn("85.214.132.117");
    }

    @Test
    void shouldReturnCorrectCountryForKnownIp() {
        String country = geoLocationService.getCountryFromRequest(request);
        assertEquals("Germany", country);
    }

    @Test
    void shouldReturnLocalhostForLoopbackIp() {
        when(request.getHeader("X-Forwarded-For")).thenReturn(null);
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");

        String country = geoLocationService.getCountryFromRequest(request);
        assertEquals("Localhost", country);
    }
}