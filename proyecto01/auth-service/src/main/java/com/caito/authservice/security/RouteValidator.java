package com.caito.authservice.security;

import com.caito.authservice.dto.RequestDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
@ConfigurationProperties(prefix = "admin-paths")
@Getter
@Setter
public class RouteValidator {
    private List<RequestDTO> paths;
    public boolean isAdmin(RequestDTO requestDTO){
        return paths.stream().anyMatch(path ->
                Pattern.matches(path.getUri(), requestDTO.getUri()) && path.getMethod().equals(
                        requestDTO.getMethod()));
    }
}
