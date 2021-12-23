package guru.springframework.sfgdi.config;

import com.springframework.pets.PetService;
import com.springframework.pets.PetServiceFactory;
import guru.springframework.sfgdi.datasource.FakeDataSource;
import guru.springframework.sfgdi.repositories.EnglishGreetingRepository;
import guru.springframework.sfgdi.repositories.EnglishGreetingRepositoryImpl;
import guru.springframework.sfgdi.services.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Service;

/**
 * Created by jt on 2/20/21.
 */
@Configuration
@EnableConfigurationProperties(ConstructorConfig.class)
public class GreetingServiceConfig {

    @Bean
    FakeDataSource fakeDataSource(ConstructorConfig constructorConfig){
        FakeDataSource fakeDataSource=new FakeDataSource();
        fakeDataSource.setUserName(constructorConfig.getUserName());
        fakeDataSource.setPassword(constructorConfig.getPassword());
        fakeDataSource.setJdbcUrl(constructorConfig.getJdbcUrl());

        return fakeDataSource;
    }

    @Bean
    PetServiceFactory petServiceFactory(){
        return  new PetServiceFactory();
    }

    @Profile({"dog", "default"})
    @Bean

    PetService dogPetService(PetServiceFactory petServiceFactory)
    {
        return  petServiceFactory.getPetService("dog");
    }

    @Profile("cat")
    @Bean
    PetService catPetService(PetServiceFactory petServiceFactory){
        return  petServiceFactory.getPetService("cat");
    }

    @Profile({"ES", "default"})
    @Bean("i18nService")
    I18NSpanishService i18NSpanishService(){
        return new I18NSpanishService();
    }

    @Bean
    EnglishGreetingRepository englishGreetingRepository(){
        return  new EnglishGreetingRepositoryImpl();
    }


    @Profile("EN")
    @Bean
    I18nEnglishGreetingService i18nService(EnglishGreetingRepository englishGreetingRepository){
        return new I18nEnglishGreetingService(englishGreetingRepository);
    }

    @Primary
    @Bean
    PrimaryGreetingService primaryGreetingService(){
        return new PrimaryGreetingService();
    }

    @Bean
    ConstructorGreetingService constructorGreetingService(){
        return new ConstructorGreetingService();
    }

    @Bean
    PropertyInjectedGreetingService propertyInjectedGreetingService(){
        return new PropertyInjectedGreetingService();
    }

    @Bean
    SetterInjectedGreetingService setterInjectedGreetingService(){
        return new SetterInjectedGreetingService();
    }
}
