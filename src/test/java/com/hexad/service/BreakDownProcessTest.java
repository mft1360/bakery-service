package com.hexad.service;

import com.hexad.exception.BusinessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author R.fatthi
 */
@RunWith(MockitoJUnitRunner.class)
public class BreakDownProcessTest {

    @Test
    public void breakDownByOrder8_IsOk() {
        int[] packs = {5, 3};
        int order = 8;
        List<String> breakdown = BreakDownProcess.breakDownProcessPack(order, packs);
        int calculateBreakdown = BreakDownProcess.calculate(breakdown);
        assertEquals("should be 8", 8, calculateBreakdown);
    }

    @Test(expected = BusinessException.class)
    public void shouldThrowException_when_OrderIs7() {
        int[] packs = {3, 5};
        int order = 7;
        List<String> breakdown = BreakDownProcess.breakDownProcessPack(order, packs);
        int calculateBreakdown = BreakDownProcess.calculate(breakdown);
        assertEquals("should be 7", 7, calculateBreakdown);
    }

    @Test
    public void breakDownByOrder15_IsOk() {
        int[] packs = {2, 5, 8};
        int order = 15;
        List<String> breakdown = BreakDownProcess.breakDownProcessPack(order, packs);
        int calculateBreakdown = BreakDownProcess.calculate(breakdown);
        assertEquals("should be 15", 15, calculateBreakdown);
    }

    @Test
    public void breakDownByOrder14_IsOk() {
        int[] packs = {3, 5, 9};
        int order = 14;
        List<String> breakdown = BreakDownProcess.breakDownProcessPack(order, packs);
        int calculateBreakdown = BreakDownProcess.calculate(breakdown);
        assertEquals("should be 14", 14, calculateBreakdown);
    }

    @Test
    public void breakDownByOrder58_IsOk() {
        int[] packs = {2, 3, 4, 9};
        int order = 58;
        List<String> breakdown = BreakDownProcess.breakDownProcessPack(order, packs);
        int calculateBreakdown = BreakDownProcess.calculate(breakdown);
        assertEquals("should be 58", 58, calculateBreakdown);
    }

    @Test(expected = BusinessException.class)
    public void shouldThrowException_when_OrderIs2() {
        int[] packs = {3, 5, 9};
        int order = 2;
        List<String> breakdown = BreakDownProcess.breakDownProcessPack(order, packs);
        int calculateBreakdown = BreakDownProcess.calculate(breakdown);
        assertEquals("should be 2", 2, calculateBreakdown);
    }

    @Test(expected = BusinessException.class)
    public void shouldThrowException_when_OrderIs1() {
        int[] packs = {3, 5, 9};
        int order = 1;
        List<String> breakdown = BreakDownProcess.breakDownProcessPack(order, packs);
        int calculateBreakdown = BreakDownProcess.calculate(breakdown);
        assertEquals("should be 1", 1, calculateBreakdown);
    }

    @Test
    public void breakDownByOrder150_IsOk() {
        int[] packs = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int order = 150;
        List<String> breakdown = BreakDownProcess.breakDownProcessPack(order, packs);
        int calculateBreakdown = BreakDownProcess.calculate(breakdown);
        assertEquals("should be 150", 150, calculateBreakdown);
    }

    @Test
    public void breakDownByOrder44_IsOk() {
        int[] packs = {3, 5, 9};
        int order = 44;
        List<String> breakdown = BreakDownProcess.breakDownProcessPack(order, packs);
        assertEquals("should be 3", 3, breakdown.size());
    }

    @Test
    public void breakDownByOrder45_IsOk() {
        int[] packs = {3, 5, 9};
        int order = 45;
        List<String> breakdown = BreakDownProcess.breakDownProcessPack(order, packs);
        assertEquals("should be 5x9", "5x9", breakdown.get(0));
    }

}
