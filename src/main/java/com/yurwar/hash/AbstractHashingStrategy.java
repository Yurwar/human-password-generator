package com.yurwar.hash;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractHashingStrategy {
    public List<String> hash(List<String> passwords) {
        return passwords.stream().map(this::hash).collect(Collectors.toList());
    }

    protected abstract String hash(String password);
}
