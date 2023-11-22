package utilz;

public class Constants {
    public static class WindowSize {
        public static final int WINDOW_SIZE_X = 1100;
        public static final int WINDOW_SIZE_Y = 600;
    }

    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class PlayerConstants {
        public static final int PLAYER_X_SPAWN = 50;
        public static final int PLAYER_Y_SPAWN = 450;
        
        public static int SCORE = 0;
    }   

    public static class EnemyConstants {
        public static final int NINJA_BIRD = 0;
        public static final int NINJA_BIRD_WIDTH_DEFAULT = 100;
        public static final int NINJA_BIRD_HEIGHT_DEFAULT = 100;
        public static final int NINJA_BIRD_X_SPAWN = 1000;
        public static final int NINJA_BIRD_Y_SPAWN = 100;

        public static final int NINJA_BIRD_DRAW_OFFSET_X = 10;
        public static final int NINJA_BIRD_DRAW_OFFSET_Y = 10;

        public static int GetMaxHealth(int enemy_type) {
            switch(enemy_type) {
                case NINJA_BIRD:
                    return 60;
                default:
                    return 50;
            }
        }

        public static int GetEnemyDmg(int enemy_type) {
            switch(enemy_type) {
                case NINJA_BIRD:
                    return 25;
                default:
                    return 0;
            }
        }
    }
    
}
