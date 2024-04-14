package com.java.MowItNow.processor;

import com.java.MowItNow.core.LawnMowerInstruction;
import com.java.MowItNow.core.MowerPosition;
import org.springframework.batch.item.ItemProcessor;

public  class LawnMowerProcessor  implements ItemProcessor<LawnMowerInstruction, MowerPosition> {


    @Override
    public MowerPosition process(LawnMowerInstruction instruction  )   {

        int width = instruction.getWidth();
        int height = instruction.getHeight();
        LawnMowerInstruction lawn = new LawnMowerInstruction(width, height);
        MowerPosition mower = new MowerPosition(instruction.getMower().getX(), instruction.getMower().getY(), instruction.getMower().getOrientation());

        for (char instructionChar : instruction.getInstructions().toCharArray()) {
            switch (instructionChar) {
                case 'G':
                    mower.turnLeft();
                    break;
                case 'D':
                    mower.turnRight();
                    break;
                case 'A':
                    int newX = mower.getX() + mower.getDirectionX();
                    int newY = mower.getY() + mower.getDirectionY();

                    // Validate new position within lawn boundaries
                    if (lawn.isValidPosition(newX, newY)) {
                        mower.setX(newX);
                        mower.setY(newY);
                    }
                    break;
            }
        }

        return new MowerPosition(mower.getX(), mower.getY(), mower.getOrientation());
    }

}

