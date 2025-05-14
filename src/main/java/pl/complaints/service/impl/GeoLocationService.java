package pl.complaints.service.impl;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;

@Service
public class GeoLocationService {

    private DatabaseReader databaseReader;

    @PostConstruct
    public void init() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("GeoLite2-Country.mmdb");
        if (inputStream == null) {
            throw new IOException("File GeoLite2-Country.mmdb not found in resources/");
        }
        this.databaseReader = new DatabaseReader.Builder(inputStream).build();
    }

    public String getCountryFromRequest(HttpServletRequest request) {
        try {
            String ip = getClientIp(request);
            if (ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")) {
                return "Localhost";
            }

            InetAddress ipAddress = InetAddress.getByName(ip);
            CountryResponse response = databaseReader.country(ipAddress);
            return response.getCountry().getName();

        } catch (IOException | GeoIp2Exception e) {
            return "Unknown";
        }
    }

    private String getClientIp(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}
