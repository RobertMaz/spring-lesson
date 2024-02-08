package ru.mtsbank.demofintech;

public class Greeter {

    private final GreetingConfig greetingConfig;

    private String prefix;

    public Greeter(String prefix, GreetingConfig greetingConfig) {
        this.prefix = prefix;
        this.greetingConfig = greetingConfig;
    }

    public void morning(){
        String morningMessage = greetingConfig.get("morningMessage");
        System.out.println(prefix + " " + greetingConfig.get("userName") + " " + morningMessage);
    }
}
