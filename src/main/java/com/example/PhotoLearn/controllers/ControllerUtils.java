package com.example.PhotoLearn.controllers;

import com.example.PhotoLearn.dto.TutorialDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ControllerUtils {

    public static List<Integer> getPagerSequence(Page<TutorialDto> page) {
        List<Integer> pagerSequence;

        // format pager if there's more that 7 pages.
        if (page.getTotalPages() > 7) {
            // formatting when the slider is to the left.
            if (page.getNumber() < 4) {
                pagerSequence = getSequenceOf(0, 4);
                pagerSequence.add(-1);
                pagerSequence.add(page.getTotalPages() - 1);
            }
            // formatting when the slider is to the right.
            else if (page.getNumber()  > page.getTotalPages() - 5) {
                pagerSequence = getSequenceOf(page.getTotalPages() - 5, page.getTotalPages() - 1);
                pagerSequence.add(0, 0);
                pagerSequence.add(1, -1);
            }
            // formatting when the slider is in the middle of the sequence.
            else {
                pagerSequence = getSequenceOf(page.getNumber() - 2, page.getNumber() + 2);
                // left part formatting.
                pagerSequence.add(0, 0);
                pagerSequence.add(1, -1);
                //right part formatting.
                pagerSequence.add(-1);
                pagerSequence.add(page.getTotalPages() - 1);
            }
        }
        else {
            pagerSequence = getSequenceOf(0, page.getTotalPages() - 1);
        }

        return pagerSequence;
    }

    private static List<Integer> getSequenceOf(int start, int end) {
        return IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }
}
