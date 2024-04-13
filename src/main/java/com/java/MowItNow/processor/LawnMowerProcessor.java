package com.java.MowItNow.processor;

import com.java.MowItNow.core.LawnMowerInstruction;
import com.java.MowItNow.core.MowerPosition;
import org.springframework.batch.item.ItemProcessor;

import javax.naming.Context;

public  class LawnMowerProcessor  implements ItemProcessor<LawnMowerInstruction, MowerPosition> {


    @Override
    public MowerPosition process(LawnMowerInstruction instruction ) throws Exception {

        LawnMowerInstruction lawn = new LawnMowerInstruction(instruction.getWidth(), instruction.getHeight());
        MowerPosition mower = instruction.getMower();

        for (char instructionChar : instruction.getInstructions().toCharArray()) {
            switch (instructionChar) {
                case 'G':
                    mower.turnLeft();
                    break;
                case 'D':
                    mower.turnRight();
                    break;
                case 'A':
                    mower.moveForward();
                    if (!mower.isWithinLawn(lawn)) {
                        // Handle case where mower goes outside the lawn (explained later)
                    }
                    break;
            }
        }

        return new MowerPosition(mower.getX(), mower.getY(), mower.getOrientation());
    }
    }

