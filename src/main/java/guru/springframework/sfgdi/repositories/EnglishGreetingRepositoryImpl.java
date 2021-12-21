package guru.springframework.sfgdi.repositories;

public class EnglishGreetingRepositoryImpl implements EnglishGreetingRepository{
    @Override
    public String SayGreeting() {
        return "Hello World - EN- From Repo";
    }
}
