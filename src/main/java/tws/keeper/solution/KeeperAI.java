package tws.keeper.solution;

import tws.keeper.model.*;
import tws.keeper.model.Observable;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static tws.keeper.model.Action.*;

public class KeeperAI implements Keeper {

    private static final List<Action> availableActions = Arrays.asList(GO_DOWN, GO_LEFT, GO_RIGHT, GO_UP);

    /**
     * This Keeper Artificial Inteligence simply acts randomly
     *
     * @param maze
     * @return
     */
    public Action act(Observable maze) {
        Action nextAction = DO_NOTHING;

        if (!maze.isMazeCompleted()) {
            if (maze.getKeysFound() == maze.getTotalNumberOfKeys()) {
                if (maze.lookDown().equals(Cell.DOOR)) {
                    nextAction = GO_DOWN;
                } else if (maze.lookUp().equals(Cell.DOOR)) {
                    nextAction = GO_UP;
                } else if (maze.lookRight().equals(Cell.DOOR)) {
                    nextAction = GO_RIGHT;
                } else if (maze.lookLeft().equals(Cell.DOOR)) {
                    nextAction = GO_LEFT;
                }
            } else {
                if (maze.lookDown().equals(Cell.KEY)) {
                    nextAction = GO_DOWN;
                } else if (maze.lookUp().equals(Cell.KEY)) {
                    nextAction = GO_UP;
                } else if (maze.lookRight().equals(Cell.KEY)) {
                    nextAction = GO_RIGHT;
                } else if (maze.lookLeft().equals(Cell.KEY)) {
                    nextAction = GO_LEFT;
                } else {
                    List<Action> possibleDirections = new ArrayList<>();
                    if (!maze.lookDown().equals(Cell.WALL)) {
                        possibleDirections.add(GO_DOWN);
                    }

                    if (!maze.lookUp().equals(Cell.WALL)) {
                        possibleDirections.add(GO_UP);
                    }

                    if (!maze.lookRight().equals(Cell.WALL)) {
                        possibleDirections.add(GO_RIGHT);
                    }

                    if (!maze.lookLeft().equals(Cell.WALL)) {
                        possibleDirections.add(GO_LEFT);
                    }

                    nextAction = possibleDirections
                            .get(ThreadLocalRandom.current()
                                    .nextInt(possibleDirections.size()));
                }
            }
        }
        return nextAction;
    }

}