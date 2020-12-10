package com.yurwar.hash;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class AbstractHashingStrategy {

    public List<String> hash(List<String> passwords) {

        return IntStream.range(0, passwords.size())
                .peek(index -> {
                    if (index % 10000 == 0) {
                        System.out.println("Index =" + index);
                    }
                })
                .mapToObj(index -> hash(passwords.get(index)))
                .collect(Collectors.toList());
//        return passwords.stream().map(this::hash).collect(Collectors.toList());
    }

    protected abstract String hash(String password);

    public abstract String getName();
}
