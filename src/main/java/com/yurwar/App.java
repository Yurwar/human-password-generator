package com.yurwar;

import com.yurwar.generator.DefaultPasswordGenerator;
import com.yurwar.generator.PasswordGenerator;

public class App {
    public static void main(String[] args) {
        PasswordGenerator generator = new DefaultPasswordGenerator();
        generator.generate();
    }
}
