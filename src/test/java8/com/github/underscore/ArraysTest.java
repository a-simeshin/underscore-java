/*
 * The MIT License (MIT)
 *
 * Copyright 2015-2022 Valentyn Kolesnikov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.github.underscore;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * Underscore library unit test.
 *
 * @author Valentyn Kolesnikov
 */
class ArraysTest {

    /*
    _.first([5, 4, 3, 2, 1]);
    => 5
    _.first([5, 4, 3, 2, 1], 2);
    => [5, 4]
    */
    @Test
    void first() {
        final Integer result = Underscore.first(asList(5, 4, 3, 2, 1));
        assertEquals("5", result.toString());
        final Object resultChain = Underscore.chain(asList(5, 4, 3, 2, 1)).first().item();
        assertEquals("5", resultChain.toString());
        final Object resultChainTwo = Underscore.chain(asList(5, 4, 3, 2, 1)).first(2).value();
        assertEquals("[5, 4]", resultChainTwo.toString());
        final List<Integer> resultList = Underscore.first(asList(5, 4, 3, 2, 1), 2);
        assertEquals("[5, 4]", resultList.toString());
        final int resultInt = Underscore.first(new Integer[] {5, 4, 3, 2, 1});
        assertEquals(5, resultInt);
        final int resultPred = Underscore.first(asList(5, 4, 3, 2, 1), (Integer item) -> item % 2 == 0);
        assertEquals(4, resultPred);
        final int resultPredObj =
                new Underscore<>(asList(5, 4, 3, 2, 1)).first((Integer item) -> item % 2 == 0);
        assertEquals(4, resultPredObj);
    }

    @Test
    void firstOrNull() {
        final Integer result = Underscore.firstOrNull(asList(5, 4, 3, 2, 1));
        assertEquals("5", result.toString());
        final Integer resultObj = new Underscore<>(asList(5, 4, 3, 2, 1)).firstOrNull();
        assertEquals("5", resultObj.toString());
        final Integer resultChain = Underscore.chain(asList(5, 4, 3, 2, 1)).firstOrNull().item();
        assertEquals("5", resultChain.toString());
        assertNull(Underscore.firstOrNull(Collections.emptyList()));
        assertNull(new Underscore<>(Collections.<Integer>emptyList()).firstOrNull());
        final int resultPred =
                Underscore.firstOrNull(asList(5, 4, 3, 2, 1), (Integer item) -> item % 2 == 0);
        assertEquals(4, resultPred);
        final int resultPredChain =
                Underscore.chain(asList(5, 4, 3, 2, 1)).firstOrNull((Integer item) -> item % 2 == 0).item();
        assertEquals(4, resultPredChain);
        assertNull(
                Underscore.firstOrNull(Collections.emptyList(), (Integer item) -> item % 2 == 0));
        final int resultPredObj =
                new Underscore<>(asList(5, 4, 3, 2, 1)).firstOrNull((Integer item) -> item % 2 == 0);
        assertEquals(4, resultPredObj);
        assertNull(
                new Underscore<>(Collections.<Integer>emptyList())
                        .firstOrNull((Integer item) -> item % 2 == 0));
    }

    @Test
    @SuppressWarnings("unchecked")
    void firstEmpty() {
        assertThrows(NoSuchElementException.class, () -> Underscore.first(asList()));
    }

    /*
    _.head([5, 4, 3, 2, 1]);
    => 5
    _.head([5, 4, 3, 2, 1], 2);
    => [5, 4]
    */
    @Test
    void head() {
        final Integer result = Underscore.head(asList(5, 4, 3, 2, 1));
        assertEquals("5", result.toString());
        final Integer resultObj = new Underscore<>(asList(5, 4, 3, 2, 1)).head();
        assertEquals("5", resultObj.toString());
        final List<Integer> resultList = Underscore.head(asList(5, 4, 3, 2, 1), 2);
        assertEquals("[5, 4]", resultList.toString());
        final List<Integer> resultListObj = new Underscore<>(asList(5, 4, 3, 2, 1)).head(2);
        assertEquals("[5, 4]", resultListObj.toString());
        final int resultInt = Underscore.head(new Integer[] {5, 4, 3, 2, 1});
        assertEquals(5, resultInt);
    }

    /*
    _.singleOrNull([5, 4, 3, 2, 1]);
    => null
    _.singleOrNull([5]);
    => 5
    */
    @Test
    void singleOrNull() {
        Underscore<Integer> uWithMoreElement = new Underscore<Integer>(asList(1, 2, 3));
        Underscore<Integer> uWithOneElement = new Underscore<Integer>(asList(1));

        final Integer result1 = Underscore.singleOrNull(asList(1, 2, 3, 4));
        assertEquals(result1, null);
        final int result2 = Underscore.singleOrNull(asList(1));
        assertEquals(result2, 1);
        final Integer result3 = Underscore.singleOrNull(new ArrayList<Integer>());
        assertEquals(result3, null);
        final Integer result4 = Underscore.singleOrNull(asList(1, 2, 3), number -> number % 2 == 1);
        assertEquals(result4, null);
        final int result5 = Underscore.singleOrNull(asList(1, 2, 3), number -> number % 2 == 0);
        assertEquals(result5, 2);
        final Integer result6 = Underscore.singleOrNull(asList(1, 2, 3), number -> number == 5);
        assertEquals(result6, null);
        final Integer result7 = uWithMoreElement.singleOrNull();
        assertEquals(result7, null);
        final Integer result8 = uWithOneElement.singleOrNull();
        assertEquals(result8, Integer.valueOf(1));
        final Integer result9 = uWithMoreElement.singleOrNull(item -> item % 2 == 0);
        assertEquals(result9, Integer.valueOf(2));
        final Integer result10 = uWithMoreElement.singleOrNull(item -> item % 2 == 1);
        assertEquals(result10, null);
    }

    /*
    _.rest([5, 4, 3, 2, 1]);
    => [4, 3, 2, 1]
    _.rest([5, 4, 3, 2, 1], 2);
    => [3, 2, 1]
    */
    @Test
    @SuppressWarnings("unchecked")
    void rest() {
        final List<Integer> result = Underscore.rest(asList(5, 4, 3, 2, 1));
        assertEquals("[4, 3, 2, 1]", result.toString());
        final List<Integer> resultChain = Underscore.chain(asList(5, 4, 3, 2, 1)).rest().value();
        assertEquals("[4, 3, 2, 1]", resultChain.toString());
        final List<Integer> result2 = Underscore.rest(asList(5, 4, 3, 2, 1), 2);
        assertEquals("[3, 2, 1]", result2.toString());
        final List<Integer> result2Chain = Underscore.chain(asList(5, 4, 3, 2, 1)).rest(2).value();
        assertEquals("[3, 2, 1]", result2Chain.toString());
        final Object[] resultArray = Underscore.rest(new Integer[] {5, 4, 3, 2, 1});
        assertEquals("[4, 3, 2, 1]", asList(resultArray).toString());
        final Object[] resultArray2 = Underscore.rest(new Integer[] {5, 4, 3, 2, 1}, 2);
        assertEquals("[3, 2, 1]", asList(resultArray2).toString());
    }

    /*
    _.chunk(['a', 'b', 'c', 'd'], 2);
    // → [['a', 'b'], ['c', 'd']]

    _.chunk(['a', 'b', 'c', 'd'], 3);
    // → [['a', 'b', 'c'], ['d']]
    */
    @Test
    @SuppressWarnings("unchecked")
    void chunk() {
        assertEquals("[[a, b], [c, d]]", Underscore.chunk(asList("a", "b", "c", "d"), 2).toString());
        assertEquals("[[a, b], [c, d]]", new Underscore(asList("a", "b", "c", "d")).chunk(2).toString());
        assertEquals(
                "[[a, b], [c, d]]",
                Underscore.chain(asList("a", "b", "c", "d")).chunk(2).value().toString());
        assertEquals("[[a, b, c], [d]]", Underscore.chunk(asList("a", "b", "c", "d"), 3).toString());
    }

    /*
    _.tail([5, 4, 3, 2, 1]);
    => [4, 3, 2, 1]
    _.tail([5, 4, 3, 2, 1], 2);
    => [3, 2, 1]
    */
    @Test
    void tail() {
        final List<Integer> result = Underscore.tail(asList(5, 4, 3, 2, 1));
        assertEquals("[4, 3, 2, 1]", result.toString());
        final List<Integer> result2 = Underscore.tail(asList(5, 4, 3, 2, 1), 2);
        assertEquals("[3, 2, 1]", result2.toString());
        final Object[] resultArray = Underscore.tail(new Integer[] {5, 4, 3, 2, 1});
        assertEquals("[4, 3, 2, 1]", asList(resultArray).toString());
        final List<Integer> resultArrayObj = new Underscore<>(asList(5, 4, 3, 2, 1)).tail();
        assertEquals("[4, 3, 2, 1]", resultArrayObj.toString());
        final Object[] resultArray2 = Underscore.tail(new Integer[] {5, 4, 3, 2, 1}, 2);
        assertEquals("[3, 2, 1]", asList(resultArray2).toString());
        final List<Integer> resultArray2Obj = new Underscore<>(asList(5, 4, 3, 2, 1)).tail(2);
        assertEquals("[3, 2, 1]", resultArray2Obj.toString());
    }

    /*
    _.drop([5, 4, 3, 2, 1]);
    => [4, 3, 2, 1]
    _.drop([5, 4, 3, 2, 1], 2);
    => [3, 2, 1]
    */
    @Test
    void drop() {
        final List<Integer> result = Underscore.drop(asList(5, 4, 3, 2, 1));
        assertEquals("[4, 3, 2, 1]", result.toString());
        final List<Integer> result2 = Underscore.drop(asList(5, 4, 3, 2, 1), 2);
        assertEquals("[3, 2, 1]", result2.toString());
        final Object[] resultArray = Underscore.drop(new Integer[] {5, 4, 3, 2, 1});
        assertEquals("[4, 3, 2, 1]", asList(resultArray).toString());
        final Object[] resultArray2 = Underscore.drop(new Integer[] {5, 4, 3, 2, 1}, 2);
        assertEquals("[3, 2, 1]", asList(resultArray2).toString());
    }

    /*
    _.initial([5, 4, 3, 2, 1]);
    => [5, 4, 3, 2]
    _.initial([5, 4, 3, 2, 1], 2);
    => [5, 4, 3]
    */
    @Test
    @SuppressWarnings("unchecked")
    void initial() {
        final List<Integer> result = Underscore.initial(asList(5, 4, 3, 2, 1));
        assertEquals("[5, 4, 3, 2]", result.toString());
        final List<Integer> resultChain = Underscore.chain(asList(5, 4, 3, 2, 1)).initial().value();
        assertEquals("[5, 4, 3, 2]", resultChain.toString());
        final List<Integer> resultList = Underscore.initial(asList(5, 4, 3, 2, 1), 2);
        assertEquals("[5, 4, 3]", resultList.toString());
        final List<Integer> resultListChain = Underscore.chain(asList(5, 4, 3, 2, 1)).initial(2).value();
        assertEquals("[5, 4, 3]", resultListChain.toString());
        final Integer[] resultArray = Underscore.initial(new Integer[] {5, 4, 3, 2, 1});
        assertEquals("[5, 4, 3, 2]", asList(resultArray).toString());
        final Integer[] resultListArray = Underscore.initial(new Integer[] {5, 4, 3, 2, 1}, 2);
        assertEquals("[5, 4, 3]", asList(resultListArray).toString());
        List<Integer> res = new Underscore(asList(1, 2, 3, 4, 5)).initial();
        assertEquals(asList(1, 2, 3, 4), res, "initial one item did not work");
        res = new Underscore(asList(1, 2, 3, 4, 5)).initial(3);
        assertEquals(asList(1, 2), res, "initial multi item did not wok");
    }

    /*
    _.last([5, 4, 3, 2, 1]);
    => 1
    */
    @Test
    @SuppressWarnings("unchecked")
    void last() {
        final Integer result = Underscore.last(asList(5, 4, 3, 2, 1));
        assertEquals("1", result.toString());
        final List<Integer> resultTwo = Underscore.last(asList(5, 4, 3, 2, 1), 2);
        assertEquals("[2, 1]", resultTwo.toString());
        final Object resultChain = Underscore.chain(asList(5, 4, 3, 2, 1)).last().item();
        assertEquals("1", resultChain.toString());
        final Object resultChainTwo = Underscore.chain(asList(5, 4, 3, 2, 1)).last(2).value();
        assertEquals("[2, 1]", resultChainTwo.toString());
        final Integer resultArray = Underscore.last(new Integer[] {5, 4, 3, 2, 1});
        assertEquals("1", resultArray.toString());
        Integer res = new Underscore<>(asList(1, 2, 3, 4, 5)).last();
        assertEquals(5, res.intValue(), "last one item did not work");
        List<Integer> resList = new Underscore(asList(1, 2, 3, 4, 5)).last(3);
        assertEquals(asList(3, 4, 5), resList, "last multi item did not wok");
        final int resultPred = Underscore.last(asList(5, 4, 3, 2, 1), (Integer item) -> item % 2 == 0);
        assertEquals(2, resultPred);
        final int resultPredObj =
                new Underscore<>(asList(5, 4, 3, 2, 1)).last((Integer item) -> item % 2 == 0);
        assertEquals(2, resultPredObj);
    }

    @Test
    void lastOrNull() {
        final Integer result = Underscore.lastOrNull(asList(5, 4, 3, 2, 1));
        assertEquals("1", result.toString());
        final Integer resultObj = new Underscore<>(asList(5, 4, 3, 2, 1)).lastOrNull();
        assertEquals("1", resultObj.toString());
        final Integer resultChain = Underscore.chain(asList(5, 4, 3, 2, 1)).lastOrNull().item();
        assertEquals("1", resultChain.toString());
        assertNull(Underscore.lastOrNull(Collections.emptyList()));
        assertNull(new Underscore<>(Collections.<Integer>emptyList()).lastOrNull());
        final int resultPred = Underscore.lastOrNull(asList(5, 4, 3, 2, 1), (Integer item) -> item % 2 == 0);
        assertEquals(2, resultPred);
        final int resultPredChain =
                Underscore.chain(asList(5, 4, 3, 2, 1)).lastOrNull((Integer item) -> item % 2 == 0).item();
        assertEquals(2, resultPredChain);
        assertNull(Underscore.lastOrNull(Collections.<Integer>emptyList(), (Integer item) -> item % 2 == 0));
        final int resultPredObj =
                new Underscore<>(asList(5, 4, 3, 2, 1)).lastOrNull((Integer item) -> item % 2 == 0);
        assertEquals(2, resultPredObj);
        assertNull(
                new Underscore<>(Collections.<Integer>emptyList())
                        .lastOrNull((Integer item) -> item % 2 == 0));
    }
    /*
    _.compact([0, 1, false, 2, '', 3]);
    => [1, 2, 3]
    */
    @Test
    @SuppressWarnings("unchecked")
    void compact() {
        final List<?> result = Underscore.compact(asList(0, 1, false, 2, "", 3));
        assertEquals("[1, 2, 3]", result.toString());
        final List<?> result2 = Underscore.compact(asList(0, 1, false, 2, "", 3), 1);
        assertEquals("[0, false, 2, , 3]", result2.toString());
        final List<?> result3 = Underscore.compact(asList(0, 1, null, 2, "", 3));
        assertEquals("[1, 2, 3]", result3.toString());
        final List<?> resultChain = Underscore.chain(asList(0, 1, false, 2, "", 3)).compact().value();
        assertEquals("[1, 2, 3]", resultChain.toString());
        final List<?> result2Chain = Underscore.chain(asList(0, 1, false, 2, "", 3)).compact(1).value();
        assertEquals("[0, false, 2, , 3]", result2Chain.toString());
        final List<?> result4 = new Underscore(asList(0, 1, false, 2, "", 3)).compact();
        assertEquals("[1, 2, 3]", result4.toString());
        final List<?> result5 = new Underscore(asList(0, 1, false, 2, "", 3)).compact(1);
        assertEquals("[0, false, 2, , 3]", result5.toString());
        final List<?> result6 = new Underscore(asList(0, 1, null, 2, "", 3)).compact(1);
        assertEquals("[0, null, 2, , 3]", result6.toString());
        final List<?> result7 = new Underscore(asList(0, 1, null, 2, "", 3)).compact((Integer) null);
        assertEquals("[0, 1, 2, , 3]", result7.toString());
        final Object[] resultArray = Underscore.compact(new Object[] {0, 1, false, 2, "", 3});
        assertEquals("[1, 2, 3]", asList(resultArray).toString());
        final Object[] resultArray2 = Underscore.compact(new Object[] {0, 1, false, 2, "", 3}, 1);
        assertEquals("[0, false, 2, , 3]", asList(resultArray2).toString());
    }

    /*
    _.flatten([1, [2], [3, [[4]]]]);
    => [1, 2, 3, 4];
    */
    @Test
    @SuppressWarnings("unchecked")
    void flatten() {
        final List<Integer> result = Underscore.flatten(asList(1, asList(2, asList(3, asList(asList(4))))));
        assertEquals("[1, 2, 3, 4]", result.toString());
        final List<Integer> result2 =
                Underscore.flatten(asList(1, asList(2, asList(3, asList(asList(4))))), true);
        assertEquals("[1, 2, [3, [[4]]]]", result2.toString());
        final List<Integer> result3 =
                Underscore.flatten(asList(1, asList(2, asList(3, asList(asList(4))))), false);
        assertEquals("[1, 2, 3, 4]", result3.toString());
        final List<Integer> resultObj =
                new Underscore(asList(1, asList(2, asList(3, asList(asList(4)))))).flatten();
        assertEquals("[1, 2, 3, 4]", resultObj.toString());
        final List<Integer> resultObj2 =
                new Underscore(asList(1, asList(2, asList(3, asList(asList(4)))))).flatten(true);
        assertEquals("[1, 2, [3, [[4]]]]", resultObj2.toString());
        final List<Integer> resultObj3 =
                new Underscore(asList(1, asList(2, asList(3, asList(asList(4)))))).flatten(false);
        assertEquals("[1, 2, 3, 4]", resultObj3.toString());
    }

    /*
    _.without([1, 2, 1, 0, 3, 1, 4], 0, 1);
    => [2, 3, 4]
    */
    @Test
    void without() {
        final List<Integer> result = Underscore.without(asList(1, 2, 1, 0, 3, 1, 4), 0, 1);
        assertEquals("[2, 3, 4]", result.toString());
        final List<Integer> result2 = Underscore.without(asList(1, 2, 1, 0, 3, 1, 4), 1);
        assertEquals("[2, 0, 3, 4]", result2.toString());
        final List<Integer> result3 =
                Underscore.without(asList(null, 2, null, 0, 3, null, 4), (Integer) null);
        assertEquals("[2, 0, 3, 4]", result3.toString());
        final Object[] resultArray = Underscore.without(new Integer[] {1, 2, 1, 0, 3, 1, 4}, 0, 1);
        assertEquals("[2, 3, 4]", asList(resultArray).toString());
        final Object[] resultArray2 = Underscore.without(new Integer[] {1, 2, 1, 0, 3, 1, 4}, 1);
        assertEquals("[2, 0, 3, 4]", asList(resultArray2).toString());
    }

    /*
    _.sortedIndex([10, 20, 30, 40, 50], 35);
    => 3
    */
    @Test
    void sortedIndex() {
        final Integer result = Underscore.sortedIndex(asList(10, 20, 30, 40, 50), 35);
        assertEquals(3, result.intValue());
        final Integer result2 = Underscore.sortedIndex(new Integer[] {10, 20, 30, 40, 50}, 35);
        assertEquals(3, result2.intValue());
        final Integer result3 = Underscore.sortedIndex(asList(10, 20, 30, 40, 50), 60);
        assertEquals(-1, result3.intValue());
    }

    @Test
    void sortedIndex2() {
        class Person implements Comparable<Person> {
            public final String name;
            public final Integer age;

            public Person(final String name, final Integer age) {
                this.name = name;
                this.age = age;
            }

            @Override
            public int compareTo(Person person) {
                return person.age - this.age;
            }

            @Override
            public String toString() {
                return name + ", " + age;
            }
        }
        final int result =
                Underscore.<Person>sortedIndex(
                        asList(
                                new Person("moe", 40),
                                new Person("moe", 50),
                                new Person("curly", 60)),
                        new Person("moe", 50),
                        "age");
        assertEquals(1, result);
        final int result2 =
                Underscore.<Person>sortedIndex(
                        asList(
                                new Person("moe", 40),
                                new Person("moe", 50),
                                new Person("curly", 60)),
                        new Person("moe", 70),
                        "age");
        assertEquals(-1, result2);
        final int resultArray =
                Underscore.<Person>sortedIndex(
                        new Person[] {
                            new Person("moe", 40), new Person("moe", 50), new Person("curly", 60)
                        },
                        new Person("moe", 50),
                        "age");
        assertEquals(1, resultArray);
    }

    @Test
    void sortedIndex2Error() {
        class Person implements Comparable<Person> {
            @Override
            public int compareTo(Person person) {
                return 0;
            }
        }
        assertThrows(IllegalArgumentException.class,
                () -> Underscore.<Person>sortedIndex(asList(new Person()), new Person(), "age"));
    }

    /*
    _.uniq([1, 2, 1, 3, 1, 4]);
    => [1, 2, 3, 4]
    */
    @Test
    @SuppressWarnings("unchecked")
    void uniq() {
        final List<Integer> result = Underscore.uniq(asList(1, 2, 1, 3, 1, 4));
        assertEquals("[1, 2, 3, 4]", result.toString());
        final Object[] resultArray = Underscore.uniq(new Integer[] {1, 2, 1, 3, 1, 4});
        assertEquals("[1, 2, 3, 4]", asList(resultArray).toString());
        class Person {
            public final String name;
            public final Integer age;

            public Person(final String name, final Integer age) {
                this.name = name;
                this.age = age;
            }

            public String toString() {
                return name + ", " + age;
            }
        }
        final Collection<Person> resultObject =
                Underscore.uniq(
                        asList(
                                new Person("moe", 40),
                                new Person("moe", 50),
                                new Person("curly", 60)),
                        (Person person) -> person.name);
        assertEquals("[moe, 50, curly, 60]", resultObject.toString());
        final List<Person> resultObjectChain =
                Underscore.chain(
                                asList(
                                        new Person("moe", 40),
                                        new Person("moe", 50),
                                        new Person("curly", 60)))
                        .uniq((Person person) -> person.name)
                        .value();
        assertEquals("[moe, 50, curly, 60]", resultObjectChain.toString());
        assertEquals(
                "[1, 2, 3, 4, 5]", Underscore.chain(asList(1, 2, 3, 3, 4, 5)).uniq().value().toString());
        final Object[] resultObjectArray =
                Underscore.uniq(
                        asList(
                                        new Person("moe", 40),
                                        new Person("moe", 50),
                                        new Person("curly", 60))
                                .toArray(new Person[] {}),
                        (Person person) -> person.name);
        assertEquals("[moe, 50, curly, 60]", asList(resultObjectArray).toString());
    }

    /*
    _.distinct([1, 2, 1, 3, 1, 4]);
    => [1, 2, 3, 4]
    */
    @Test
    @SuppressWarnings("unchecked")
    void distinct() {
        final List<Integer> result = Underscore.distinct(asList(1, 2, 1, 3, 1, 4));
        assertEquals("[1, 2, 3, 4]", result.toString());
        final Object[] resultArray = Underscore.distinct(new Integer[] {1, 2, 1, 3, 1, 4});
        assertEquals("[1, 2, 3, 4]", asList(resultArray).toString());
        class Person {
            public final String name;
            public final Integer age;

            public Person(final String name, final Integer age) {
                this.name = name;
                this.age = age;
            }

            @Override
            public String toString() {
                return name + ", " + age;
            }
        }
        final Collection<Person> resultObject =
                Underscore.distinctBy(
                        asList(
                                new Person("moe", 40),
                                new Person("moe", 50),
                                new Person("curly", 60)),
                        (Person person) -> person.name);
        assertEquals("[moe, 50, curly, 60]", resultObject.toString());
        final List<String> resultObjectChain =
                Underscore.chain(
                                asList(
                                        new Person("moe", 40),
                                        new Person("moe", 50),
                                        new Person("curly", 60)))
                        .distinctBy((Person person) -> person.name)
                        .value();
        assertEquals("[moe, 50, curly, 60]", resultObjectChain.toString());
        assertEquals(
                "[1, 2, 3, 4, 5]", Underscore.chain(asList(1, 2, 3, 3, 4, 5)).distinct().value().toString());
        final Object[] resultObjectArray =
                Underscore.distinctBy(
                        asList(
                                        new Person("moe", 40),
                                        new Person("moe", 50),
                                        new Person("curly", 60))
                                .toArray(new Person[] {}),
                        (Person person) -> person.name);
        assertEquals("[moe, 50, curly, 60]", asList(resultObjectArray).toString());
    }

    /*
    _.intersection([1, 2, 3], [101, 2, 1, 10], [2, 1]);
    => [1, 2]
    */
    @Test
    @SuppressWarnings("unchecked")
    void intersection() {
        final List<Integer> result =
                Underscore.intersection(asList(1, 2, 3), asList(101, 2, 1, 10), asList(2, 1));
        assertEquals("[1, 2]", result.toString());
        final List<Integer> resultObj =
                new Underscore(asList(1, 2, 3)).intersectionWith(asList(101, 2, 1, 10), asList(2, 1));
        assertEquals("[1, 2]", resultObj.toString());
        final List<Integer> resultChain =
                Underscore.chain(asList(1, 2, 3)).intersection(asList(101, 2, 1, 10), asList(2, 1)).value();
        assertEquals("[1, 2]", resultChain.toString());
        final Object[] resultArray =
                Underscore.intersection(new Integer[] {1, 2, 3}, new Integer[] {101, 2, 1, 10});
        assertEquals("[1, 2]", asList(resultArray).toString());
    }

    /*
    _.union([1, 2, 3], [101, 2, 1, 10], [2, 1]);
    => [1, 2, 3, 101, 10]
    */
    @Test
    @SuppressWarnings("unchecked")
    void union() {
        final List<Integer> result = Underscore.union(asList(1, 2, 3), asList(101, 2, 1, 10), asList(2, 1));
        assertEquals("[1, 2, 3, 101, 10]", result.toString());
        final List<Integer> resultObj =
                new Underscore(asList(1, 2, 3)).unionWith(asList(101, 2, 1, 10), asList(2, 1));
        assertEquals("[1, 2, 3, 101, 10]", resultObj.toString());
        final List<Integer> resultChain =
                Underscore.chain(asList(1, 2, 3)).union(asList(101, 2, 1, 10), asList(2, 1)).value();
        assertEquals("[1, 2, 3, 101, 10]", resultChain.toString());
        final Object[] resultArray =
                Underscore.union(new Integer[] {1, 2, 3}, new Integer[] {101, 2, 1, 10});
        assertEquals("[1, 2, 3, 101, 10]", asList(resultArray).toString());
    }

    /*
    _.difference([1, 2, 3, 4, 5], [5, 2, 10]);
    => [1, 3, 4]
    */
    @Test
    @SuppressWarnings("unchecked")
    void difference() {
        final List<Integer> result = Underscore.difference(asList(1, 2, 3, 4, 5), asList(5, 2, 10));
        assertEquals("[1, 3, 4]", result.toString());
        final List<Integer> resultObj =
                new Underscore(asList(1, 2, 3, 4, 5)).differenceWith(asList(5, 2, 10));
        assertEquals("[1, 3, 4]", resultObj.toString());
        final List<Integer> resultChain =
                Underscore.chain(asList(1, 2, 3, 4, 5)).difference(asList(5, 2, 10)).value();
        assertEquals("[1, 3, 4]", resultChain.toString());
        final List<Integer> resultList =
                Underscore.difference(asList(1, 2, 3, 4, 5), asList(5, 2, 10), asList(8, 4));
        assertEquals("[1, 3]", resultList.toString());
        final Object[] resultArray =
                Underscore.difference(new Integer[] {1, 2, 3, 4, 5}, new Integer[] {5, 2, 10});
        assertEquals("[1, 3, 4]", asList(resultArray).toString());
        final Object[] resultArray2 =
                Underscore.difference(
                        new Integer[] {1, 2, 3, 4, 5},
                        new Integer[] {5, 2, 10},
                        new Integer[] {8, 4});
        assertEquals("[1, 3]", asList(resultArray2).toString());
    }

    /*
    _.zip(['moe', 'larry', 'curly'], [30, 40, 50], [true, false, false]);
    => [["moe", 30, true], ["larry", 40, false], ["curly", 50, false]]
    */
    @Test
    @SuppressWarnings("unchecked")
    void zip() {
        final List<List<String>> result =
                Underscore.zip(
                        asList("moe", "larry", "curly"),
                        asList("30", "40", "50"),
                        asList("true", "false", "false"));
        assertEquals(
                "[[moe, 30, true], [larry, 40, false], [curly, 50, false]]", result.toString());
    }

    /*
    _.unzip(["moe", 30, true], ["larry", 40, false], ["curly", 50, false]);
    => [['moe', 'larry', 'curly'], [30, 40, 50], [true, false, false]]
    */
    @Test
    @SuppressWarnings("unchecked")
    void unzip() {
        final List<List<String>> result =
                Underscore.unzip(
                        asList("moe", "30", "true"),
                        asList("larry", "40", "false"),
                        asList("curly", "50", "false"));
        assertEquals(
                "[[moe, larry, curly], [30, 40, 50], [true, false, false]]", result.toString());
    }

    /*
    _.object(['moe', 'larry', 'curly'], [30, 40, 50]);
    => {moe: 30, larry: 40, curly: 50}
    */
    @Test
    void object() {
        final List<Tuple<String, String>> result =
                Underscore.object(asList("moe", "larry", "curly"), asList("30", "40", "50"));
        assertEquals("[(moe, 30), (larry, 40), (curly, 50)]", result.toString());
    }

    /*
    _.indexOf([1, 2, 3], 2);
    => 1
    */
    @Test
    void indexOf() {
        final Integer result = Underscore.indexOf(asList(1, 2, 3), 2);
        assertEquals(1, result.intValue());
        final Integer resultArray = Underscore.indexOf(new Integer[] {1, 2, 3}, 2);
        assertEquals(1, resultArray.intValue());
    }

    /*
    _.lastIndexOf([1, 2, 3, 1, 2, 3], 2);
    => 4
    */
    @Test
    void lastIndexOf() {
        final Integer result = Underscore.lastIndexOf(asList(1, 2, 3, 1, 2, 3), 2);
        assertEquals(4, result.intValue());
        final Integer resultArray = Underscore.lastIndexOf(new Integer[] {1, 2, 3, 1, 2, 3}, 2);
        assertEquals(4, resultArray.intValue());
    }

    /*
    _.findIndex([1, 2, 3], function(item) {return item % 2  === 0; });
    => 1
    */
    @Test
    void findIndex() {
        final Integer result = Underscore.findIndex(asList(1, 2, 3), (Integer item) -> item % 2 == 0);
        assertEquals(1, result.intValue());
        final Integer resultNotFound = Underscore.findIndex(asList(1, 2, 3), (Integer item) -> item > 3);
        assertEquals(-1, resultNotFound.intValue());
        final Integer resultArray =
                Underscore.findIndex(new Integer[] {1, 2, 3}, (Integer item) -> item % 2 == 0);
        assertEquals(1, resultArray.intValue());
    }

    /*
    _.findLastIndex([1, 2, 3, 4, 5], function(item) {return item % 2  === 0; });
    => 3
    */
    @Test
    void findLastIndex() {
        final Integer result =
                Underscore.findLastIndex(asList(1, 2, 3, 4, 5), (Integer item) -> item % 2 == 0);
        assertEquals(3, result.intValue());
        final Integer resultNotFound =
                Underscore.findLastIndex(asList(1, 2, 3, 4, 5), (Integer item) -> item > 5);
        assertEquals(-1, resultNotFound.intValue());
        final Integer resultArray =
                Underscore.findLastIndex(new Integer[] {1, 2, 3, 4, 5}, (Integer item) -> item % 2 == 0);
        assertEquals(3, resultArray.intValue());
    }

    /*
    _.range(10);
    => [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    _.range(1, 11);
    => [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    _.range(0, 30, 5);
    => [0, 5, 10, 15, 20, 25]
    _.range(0, -10, -1);
    => [0, -1, -2, -3, -4, -5, -6, -7, -8, -9]
    _.range(0);
    => []
    */
    @Test
    void range() {
        final List<Integer> result = Underscore.range(10);
        assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", result.toString());
        final List<Integer> resultChain = Underscore.chain("").range(10).value();
        assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", resultChain.toString());
        final List<Integer> result2 = Underscore.range(1, 11);
        assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", result2.toString());
        final List<Integer> result2Chain = Underscore.chain("").range(1, 11).value();
        assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", result2Chain.toString());
        final List<Integer> result3 = Underscore.range(0, 30, 5);
        assertEquals("[0, 5, 10, 15, 20, 25]", result3.toString());
        final List<Integer> result3Chain = Underscore.chain("").range(0, 30, 5).value();
        assertEquals("[0, 5, 10, 15, 20, 25]", result3Chain.toString());
        final List<Integer> result4 = Underscore.range(0, -10, -1);
        assertEquals("[0, -1, -2, -3, -4, -5, -6, -7, -8, -9]", result4.toString());
        final List<Integer> result5 = Underscore.range(0);
        assertEquals("[]", result5.toString());
    }

    /*
    _.lastIndex([1, 2, 3, 4, 5]);
    => 4
    */
    @Test
    void lastIndex() {
        assertEquals(4, Underscore.lastIndex(asList(1, 2, 3, 4, 5)));
        assertEquals(4, Underscore.lastIndex(new Integer[] {1, 2, 3, 4, 5}));
        assertEquals(4, Underscore.lastIndex(new int[] {1, 2, 3, 4, 5}));
    }
}
