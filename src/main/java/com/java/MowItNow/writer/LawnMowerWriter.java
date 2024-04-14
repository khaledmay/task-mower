package com.java.MowItNow.writer;

import com.java.MowItNow.core.LawnMowerInstruction;
import com.java.MowItNow.core.MowerPosition;


import java.util.List;

public class LawnMowerWriter {

    public void writeFinalPositions(List<MowerPosition> finalPositions) {
        for (MowerPosition position : finalPositions) {
            System.out.println(position.toString()); // Use toString() from MowerPosition
        }
    }
}
