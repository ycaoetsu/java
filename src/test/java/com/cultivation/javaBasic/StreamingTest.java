package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.AnimeCharacter;
import com.cultivation.javaBasic.util.KeyValuePair;
import com.cultivation.javaBasic.util.ValueHolder;
import org.junit.jupiter.api.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.*;

import static org.junit.jupiter.api.Assertions.*;

class StreamingTest {
    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_turn_collection_into_stream() {
        List<String> words = Arrays.asList("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<String> wordStream = words.stream();
        // --end-->
        {
            assertIterableEquals(
                words,
                wordStream.collect(Collectors.toList())
            );
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_turn_array_into_stream() {
        String[] words = {"a", "quick", "brown", "fox", "jumps", "over"};

        // TODO: please modify the following code to pass the test
        // <--start
//        DoubleStream
        Stream<String> wordStream = Arrays.stream(words);
        // --end-->
        {
            assertArrayEquals(
                words,
                wordStream.toArray(String[]::new)
            );
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_generate_empty_stream() {
        // TODO: please modify the following code to pass the test
        // <--start
        Stream<String> emptyWordStream = Stream.empty();
        // --end-->
        {
            assertEquals(0, emptyWordStream.count());
        }
    }
    @Test
    void should_original_stream_equal_to_filter_stream() {
        IntStream original = IntStream.of(1, 10);
        IntStream filter = original.filter(num -> num > 5);

        boolean flag = false;

        assertSame(original, filter);
    }

//    @Test
////    void should_not_close_stream_after_filter_stream() {
////        IntStream stream = IntStream.of(1, 10).filter(num -> num > 10);
////        boolean flag = false;
////        stream.onClose(() ->
////    }
    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_generate_infinite_stream_with_same_items() {
        // TODO: please modify the following code to pass the test
        // <--start
        Stream<String> infiniteEchos = Stream.generate(() -> "Echo");
        // --end-->
        {
            assertEquals("Echo", infiniteEchos.skip(10000).findFirst().get());
        }
    }

    @Test
    void should_run_times() {
        int[] count = new int[1];
        Stream<String> infiniteCall = Stream.iterate("a", str -> {
            count[0]++;
            return str;
        });
        Stream<String> anotherStream = infiniteCall.skip(10000);
        assertTrue(count[0] == 0);
        Optional<String> result = anotherStream.findFirst();
        assertTrue(count[0] == 10000);
    }

    @Test
    void should_be_multiple_times() {
        int[] count = new int[1];
        Stream<Integer> stream = Stream.iterate(0, num -> {
            count[0]++;
            return num + 1;
        });
        Optional<Integer> anotherStream = stream.skip(10000).findFirst();
        assertTrue(count[0] == 10000);
    }
    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_generate_infinite_stream_of_sequence() {
        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Integer> infiniteSequence = Stream.iterate(0, num -> num + 1);
        // --end-->
        {
            assertEquals(10000, infiniteSequence.skip(10000).findFirst().get().intValue());
        }
    }

    @SuppressWarnings({"TryFinallyCanBeTryWithResources", "unused", "ConstantConditions"})
    @Test
    void should_be_able_to_filter_stream() {
        Stream<String> wordStream = Stream.of("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please write code to filter word whose length is greater than 4
        // <--start
        Stream<String> filtered = wordStream.filter(item -> item.length() > 4);
        // --end-->
        {
            assertArrayEquals(new String[]{"quick", "brown", "jumps"}, filtered.toArray(String[]::new));
        }
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_map_stream() {//map 内置函数,what is a function instance
        Stream<String> wordStream = Stream.of("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please write code to filter word whose length is greater than 4
        // <--start
        Stream<String> filtered = wordStream.map((item) -> item.toUpperCase()); //map.(String :: toUpperCase);
        // --end-->
        {
            assertArrayEquals(
                new String[]{"A", "QUICK", "BROWN", "FOX", "JUMPS", "OVER"},
                filtered.toArray(String[]::new));
        }
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_map_item_to_a_new_type() {
        Stream<String> nameStream = Stream.of("Naruto", "Kisuke", "Tomoya");

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<AnimeCharacter> characters = nameStream.map(AnimeCharacter::new);
        // --end-->
        {
            AnimeCharacter[] actual = characters.toArray(AnimeCharacter[]::new);
            assertArrayEquals(
                new AnimeCharacter[] {
                    new AnimeCharacter("Naruto"),
                    new AnimeCharacter("Kisuke"),
                    new AnimeCharacter("Tomoya")
                },
                actual);
        }
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_flatten_stream_of_streams() {//?
        Stream<Stream<Character>> stream = Stream.of("abc", "def").map(StreamingTest::letters);
        Stream<Character> newStream = stream.flatMap(item -> item);
        Character[] result = newStream.toArray(Character[]::new);
        assertArrayEquals(new Character[]{'a', 'b', 'c', 'd', 'e', 'f'}, result);
        assertEquals(Character.class, result.getClass());
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_create_sequence_of_specified_size() {
        Stream<Integer> infiniteSequence = Stream.iterate(0, i -> i + 1);

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Integer> finiteStream = infiniteSequence.limit(10);
        // --end-->
        {
            assertArrayEquals(
                new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                finiteStream.toArray(Integer[]::new)
            );
        }
    }
    @Test
    void should_run_ten_times() {
        int[] count = new int[1];
        Stream<Integer> sequence = Stream.iterate(0, integer -> {
            count[0]++;
            return integer + 1;
        });
        sequence.limit(10).toArray(Integer[]::new);
        assertEquals(9, count[0]);
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_concat_streams() {
        Stream<Character> helloStream = Stream.of('H', 'e', 'l', 'l', 'o');
        Stream<Character> worldStream = Stream.of('W', 'o', 'r', 'l', 'd');

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Character> concatStream = Stream.concat(helloStream, worldStream);
        // --end-->
        {
            assertArrayEquals(
                letters("HelloWorld").toArray(Character[]::new),
                concatStream.toArray(Character[]::new)
            );
        }
    }

    @Test
    void should_sorted_separatedly_two_streams() {
        Stream<Character> helloStream = Stream.of('h', 'e', 'a');
        Stream<Character> worldStream = Stream.of('W', 'd', 'c');

        Stream<Character> concatStream = Stream.concat(helloStream.sorted(), worldStream.sorted());

        assertArrayEquals(letters("aehWcd").toArray(Character[]::new), concatStream.toArray(Character[]::new));
//        assertArrayEquals(letters("acdehW").toArray(), concatStream.toArray(Character[]::new));
    }

    @SuppressWarnings({"SpellCheckingInspection", "unused", "ConstantConditions"})
    @Test
    void should_get_unique_items() {
        Stream<Character> characterStream = letters("aquickbrownfox");

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Character> distinct = characterStream.distinct();
        // --end-->
        {
            Character[] characters = distinct.sorted().toArray(Character[]::new);

            assertArrayEquals(
                new Character[] {'a', 'b', 'c', 'f', 'i', 'k', 'n', 'o', 'q', 'r', 'u', 'w', 'x'},
                characters
            );
        }
    }

    @Test
    void should_hook_stream_generation() {
        ValueHolder<Integer> holder = new ValueHolder<>();
        holder.setValue(0);

        Stream<Integer> hookStream = Stream
            .iterate(0, i -> i + 1) //0 - 19
            .limit(20)
            .filter(v -> v % 2 == 0) //0 2 4 6 - 18
            .peek(v -> holder.setValue(holder.getValue() + v));//

        hookStream.forEach(i -> {});

        // TODO: please modify the following code to pass the test
        // <--start
        final int expected = 90;
        // --end-->

        assertEquals(expected, holder.getValue().intValue());
    }

    @Test
    void should_modify_stream_peek() {
        ValueHolder<Integer> holder = new ValueHolder<>();
        holder.setValue(0);

        Stream<Integer> stream = Stream.iterate(0, num -> num + 1).peek(num -> num++);
//        assertArrayEquals();
    }
//intermediate lazy


    @SuppressWarnings({"ConstantConditions", "unchecked", "OptionalAssignedToNull"})
    @Test
    void should_throws_if_get_value_of_empty_optional() {
        // TODO: please create an empty optional and specify the concrete exception type.
        // <--start
        Optional<String> empty = Optional.empty();
        Class errorType = NoSuchElementException.class;
        // --end-->

        assertThrows(errorType, empty::get);
    }

    @Test
    void should_provide_a_default_value_using_or_else() {
        Optional<String> empty = Optional.empty();
        Optional<String> nonEmpty = Optional.of("great");

        String emptyResult = getValue(empty, "default value");
        String nonEmptyResult = getValue(nonEmpty, "default value");

        assertEquals("default value", emptyResult);
        assertEquals("great", nonEmptyResult);
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_throw_for_empty_optional() {
        Optional<String> empty = Optional.empty();

        // TODO: In the `Runnable` object. Please throw IllegalStateException when `empty` is not present.
        // <--start
        Runnable emptyRunnable = () -> {
            if (!empty.isPresent()) {
                throw new IllegalStateException();
            }
        };
        // --end-->

        assertThrows(IllegalStateException.class, emptyRunnable::run);
    }

    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "ConstantConditions"})
    @Test
    void should_process_value_if_present() {
        Optional<String> optional = Optional.of("word");
        List<String> result = new ArrayList<>();

        // TODO: please add the upper-cased value to result if `optional` is present in `Consumer<Optional<String>>`
        // TODO: implementation.
        // <--start
        Consumer<Optional<String>> optionalConsumer = p -> {
            optional.get().toUpperCase();
        };
        result.add(optional.get().toUpperCase());
        // --end-->

        optionalConsumer.accept(optional);

        assertEquals("WORD", result.get(0));
    }

    @SuppressWarnings({"ConstantConditions", "MismatchedQueryAndUpdateOfCollection"})
    @Test
    void should_map_value_if_present() {
        Optional<String> optional = Optional.of("word");
        Optional<String> empty = Optional.empty();

        List<String> result = new ArrayList<>();

        // TODO: The `Function<Optional<String>, Optional<Boolean>>` will map `Optional<String>` to `Optional<Boolean>`
        // TODO: please add the upper-cased value to `result` list if optional is present. Then return the boolean
        // TODO: mapping result of `result.add`.
        // <--start
        Function<Optional<String>, Optional<Boolean>> mapping = null; //(item -> {
//            if (item.isPresent()) {
//                result.stream().map(String :: toUpperCase).map(item -> (Optional<Boolean>)item);
//            }
//            return result.
//        };
        // --end-->

        Optional<Boolean> mappingResult = mapping.apply(optional);
        Optional<Boolean> emptyMappingResult = mapping.apply(empty);

        assertTrue(mappingResult.orElseThrow(IllegalStateException::new));
        assertEquals("WORD", result.get(0));
        assertFalse(emptyMappingResult.isPresent());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_flat_map_optional_value_do_chain_method() {
        Stream<YieldOptional> emptyStream = Stream.of(1, 2, 3)
            .filter(i -> i > 4)
            .map(i -> new YieldOptional());
        Stream<YieldOptional> nonEmptyStream = Stream.of(1, 2, 3)
            .filter(i -> i > 1)
            .map(i -> new YieldOptional());

        // TODO: The `findFirstAndGet` interface will find first item in stream. If it can be found, map it with
        // TODO: `YieldOptional.get` method. Otherwise, returns empty Optional.
        // <--start
        Function<Stream<YieldOptional>, Optional<String>> findFirstAndGet = null;
        // --end-->

        Optional<String> emptyStreamResult = findFirstAndGet.apply(emptyStream);
        Optional<String> nonEmptyStreamResult = findFirstAndGet.apply(nonEmptyStream);

        assertFalse(emptyStreamResult.isPresent());
        assertTrue(nonEmptyStreamResult.isPresent());
        assertEquals("Hello", nonEmptyStreamResult.get());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_collect_result() {
        Stream<String> stream = Stream.of("Hello", "What", "is", "your", "name");
//        stream.collect(Collectors.toList())
        // TODO: please implement toList collector using `stream.collect`. You cannot use existing `toList` collector.
        // <--start
        ArrayList<String> list = null; //stream.collect(item -> {});
        // --end-->

        assertEquals(ArrayList.class, list.getClass());
        assertIterableEquals(
            Arrays.asList("Hello", "What", "is", "your", "name"),
            list
        );
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_collect_to_map() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
            new KeyValuePair<>("Harry", 2002),
            new KeyValuePair<>("Bob", 2014),
            new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: please implement toMap collector using `stream.collect`. You cannot use existing `toMap` collector.
        // <--start
        HashMap<String, Integer> map = null;
        // --end-->

        assertEquals(2, map.size());
        assertTrue(map.containsKey("Harry"));
        assertEquals(2033, map.get("Harry").intValue());
        assertTrue(map.containsKey("Bob"));
        assertEquals(2014, map.get("Bob").intValue());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_collect_to_group() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
            new KeyValuePair<>("Harry", 2002),
            new KeyValuePair<>("Bob", 2014),
            new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. You cannot use existing `groupingBy` collector.
        // <--start
        HashMap<String, List<Integer>> map = null;
        // --end-->

        assertEquals(2, map.size());
        assertIterableEquals(Arrays.asList(2002, 2033), map.get("Harry"));
        assertIterableEquals(Collections.singletonList(2014), map.get("Bob"));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_collect_to_group_continued() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
            new KeyValuePair<>("Harry", 2002),
            new KeyValuePair<>("Bob", 2014),
            new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. This time please use `Collectors.groupingBy`
        // <--start
        Map<String, List<Integer>> map = null;
        // --end-->

        assertEquals(2, map.size());
        assertIterableEquals(Arrays.asList(2002, 2033), map.get("Harry"));
        assertIterableEquals(Collections.singletonList(2014), map.get("Bob"));
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_calculate_number_in_each_group() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
            new KeyValuePair<>("Harry", 2002),
            new KeyValuePair<>("Bob", 2014),
            new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. You should use `Collectors.groupingBy` and
        // TODO: downstream collector.
        // <--start
        Map<String, Long> map = null;
        // --end-->

        assertEquals(2, map.size());
        assertEquals(2, map.get("Harry").longValue());
        assertEquals(1, map.get("Bob").longValue());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_calculate_sum_of_each_group() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
            new KeyValuePair<>("Harry", 2002),
            new KeyValuePair<>("Bob", 2014),
            new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. You should use `Collectors.groupingBy` and
        // TODO: downstream collector.
        // <--start
        Map<String, Integer> map = null;
        // --end-->

        assertEquals(2, map.size());
        assertEquals(4035, map.get("Harry").intValue());
        assertEquals(2014, map.get("Bob").intValue());
    }

    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "OptionalAssignedToNull"})
    @Test
    void should_calculate_sum_using_reduce() {
        List<Integer> numbers = new ArrayList<>();
        Stream
            .iterate(1, i -> i + 1)
            .limit(100)
            .forEach(numbers::add);

        // TODO: please modify the following code to pass the test
        // <--start
        Optional<Integer> reduced = null;
        // --end-->

        //noinspection ConstantConditions
        assertEquals(5050, reduced.get().intValue());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_calculate_total_character_lengths() {
        List<String> words = Arrays.asList("The", "future", "is", "ours");

        // TODO: please calculate the total number of characters using `reduce`.
        // <--start
        Integer total = null;
        // --end-->

        assertEquals(15, total.intValue());
    }

    @SuppressWarnings({"SameParameterValue", "OptionalUsedAsFieldOrParameterType"})
    private static <T> T getValue(Optional<T> optional, T defaultValue) {
        // TODO: please implement the following method to pass the test
        // <--start
        if (optional == Optional.empty()) return defaultValue;
        else return optional.get();
//        throw new NotImplementedException();
        // --end-->
    }

    private static Stream<Character> letters(String text) {
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < text.length(); ++i) {
            characters.add(text.charAt(i));
        }
        return characters.stream();
    }
}

class YieldOptional {
    Optional<String> get() {
        return Optional.of("Hello");
    }
}

/*
 * - Can you use `collect` method to implement `joining(String delimiter)` method?
 * - What can you do using primitive types with streams?
 */