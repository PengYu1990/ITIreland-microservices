package works.itireland.apigw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class ApiGWApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(ApiGWApp.class, args);
    }
}