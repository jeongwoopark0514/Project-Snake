package game;

/**
 * Enumeration of directions: up, down, left and right.
 */
public enum Directions {
    UP {
        @Override
        public Directions opposite() {
            return DOWN;
        }
    },
    DOWN {
        @Override
        public Directions opposite() {
            return UP;
        }
    },
    LEFT {
        @Override
        public Directions opposite() {
            return RIGHT;
        }
    },
    RIGHT {
        @Override
        public Directions opposite() {
            return LEFT;
        }
    };

    public abstract Directions opposite();
}

