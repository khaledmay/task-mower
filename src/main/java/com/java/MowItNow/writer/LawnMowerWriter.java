package com.java.MowItNow.writer;

import com.java.MowItNow.core.MowerPosition;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class LawnMowerWriter implements ItemWriter<MowerPosition> {

    @Override
    public void write(Chunk<? extends MowerPosition> items) throws Exception {
        for (MowerPosition position : items) {
            System.out.println(position.getX() + " " + position.getY() + " " + position.getOrientation());
        }
    }

    public void writeFinalPositions(List<MowerPosition> finalPositions) {
        for (MowerPosition position : finalPositions) {
            System.out.println(position.toString()); // Use toString() from MowerPosition
        }
    }
}
