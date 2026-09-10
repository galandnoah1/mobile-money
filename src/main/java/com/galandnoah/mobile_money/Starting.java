package com.galandnoah.mobile_money;

import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Starting implements CommandLineRunner {
    @Override
    public void run(String @NonNull ... args) throws Exception {
        System.out.println("Mobile Money");
    }
}
