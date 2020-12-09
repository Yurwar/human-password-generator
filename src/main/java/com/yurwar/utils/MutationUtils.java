package com.yurwar.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.yurwar.utils.GeneratorUtils.UPPERCASE_NUM_SYMBOLS;

public class MutationUtils {

    private static final double INITIAL_MUTATION_POSSIBILITY = 0.5;
    private static final double MUTATION_POSSIBILITY_DECREMENT = 0.1;
    private static final Random RANDOM = new Random();
    private static final List<String> letterReplacements = List.of("o->0", "a->@", "f->4", "e->3", "i->1", "g->9");
    private static final int DOB_YEAR_PREFIX_19 = 19;
    private static final int DOB_YEAR_PREFIX_20 = 20;
    private static final Map<String, String> NUM_TO_UPPERCASE_SYMBOL = createNumToUppercaseSymbol();

    private static final List<Function<String, String>> mutationFunctions = createMutationFunctions();

    public static String mutatePassword(final String password) {

        String result = password;
        double mutationPossibility = INITIAL_MUTATION_POSSIBILITY;

        for (var function : mutationFunctions) {

            if (RANDOM.nextDouble() < mutationPossibility) {

                result = function.apply(result);

                mutationPossibility -= MUTATION_POSSIBILITY_DECREMENT;
            }
        }

        return result;
    }

    private static List<Function<String, String>> createMutationFunctions() {

        return Stream.of(createReplacementFunctions(), createAdditionFunctions())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private static List<Function<String, String>> createAdditionFunctions() {

        return Stream.of(createDobBasedAdditionFunctions(), createRepeatBasedAdditionFunctions())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private static List<Function<String, String>> createDobBasedAdditionFunctions() {

        return Collections.singletonList(pwd -> pwd
                + (RANDOM.nextDouble() > 0.5 ? DOB_YEAR_PREFIX_19 : DOB_YEAR_PREFIX_20)
                + (RANDOM.nextInt() % 100));
    }

    private static List<Function<String, String>> createRepeatBasedAdditionFunctions() {

        return Collections.singletonList(pwd -> {

            int numberToAdd = RANDOM.nextInt() % 10;

            return pwd + IntStream.range(0, RANDOM.nextInt() % 10)
                    .mapToObj(i -> String.valueOf(numberToAdd))
                    .collect(Collectors.joining());
        });
    }

    private static List<Function<String, String>> createReplacementFunctions() {

        return Stream.of(createLetterReplacementFunctions(), createNumberReplacementFunctions())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private static List<Function<String, String>> createLetterReplacementFunctions() {

        return letterReplacements.stream()

                .flatMap(replacement -> {

                    var replacementArr = replacement.split("->");

                    return Stream.of(s -> s.replaceAll(replacementArr[0], replacementArr[1]),
                            (Function<String, String>) s -> s.replace(replacementArr[1], replacementArr[0]));
                })

                .collect(Collectors.toList());
    }

    private static List<Function<String, String>> createNumberReplacementFunctions() {

        return NUM_TO_UPPERCASE_SYMBOL.entrySet().stream()
                .map(replacement -> (Function<String, String>) s -> s.replace(replacement.getKey(), replacement.getValue()))
                .collect(Collectors.toList());
    }

    private static Map<String, String> createNumToUppercaseSymbol() {

        return IntStream.range(0, 10)
                .mapToObj(num -> Map.entry(String.valueOf(num), UPPERCASE_NUM_SYMBOLS.split("")[num]))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
