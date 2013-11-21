package ru.spbau.mit.dbmsau.pages;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.spbau.mit.dbmsau.BaseTest;

import java.util.Iterator;

public class PagesListTest extends BaseTest {
    private static final int HEAD_PAGE_ID = 100;

    @Test
    public void testList() throws Exception {
        PagesList list = new PagesList(HEAD_PAGE_ID, context);
        list.initList();

        assertNull(list.peek());
        assertNull(list.pop());

        list.put(1000);
        assertThat(list.pop(), is(1000));

        for (int i = 1000; i < 5000; i++) {
            list.put(i);
        }

        for (int i = 1000; i < 5000; i++) {
            assertThat(list.peek(), is(i));
            assertThat(list.pop(), is(i));
        }

        assertNull(list.peek());
        assertNull(list.pop());
    }

    @Test
    public void testIterator() throws Exception {
        PagesList list = new PagesList(HEAD_PAGE_ID, context);
        list.initList();

        assertNull(list.peek());
        assertNull(list.pop());

        for (int i = 1000; i < 5000; i++) {
            list.put(i);
        }

        iterateList(list);
        iterateList(list);

        for (int i = 1000; i < 5000; i++) {
            assertThat(list.peek(), is(i));
            assertThat(list.pop(), is(i));
        }

        assertNull(list.peek());
        assertNull(list.pop());
    }

    private void iterateList(PagesList list) {
        Iterator<Page> it = list.iterator();

        for (int i = 1000; i < 5000; i++) {
            assertTrue(it.hasNext());
            Page next = it.next();

            assertThat(next.getId(), is(i));
        }
    }
}
