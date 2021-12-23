package guru.springframework.sfgdi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Configuration;

import java.beans.ConstructorProperties;

@ConstructorBinding
@ConfigurationProperties("guru1")
public class ConstructorConfig {
    private final String userName;
    private final String password;
    private final String  jdbcUrl;

    public ConstructorConfig(String userName, String password, String jdbcUrl) {
        this.userName = userName;
        this.password = password;
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }
}
