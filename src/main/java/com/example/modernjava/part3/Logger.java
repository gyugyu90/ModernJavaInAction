package com.example.modernjava.part3;

import java.util.function.Supplier;

class Logger {

    private static Logger instance;

    private static Level level;

    private Logger(Level level) {
        this.level = level;
    }

    public static Logger getInstance(Level level) {
        if (instance == null)
            instance = new Logger(level);
        return instance;
    }

    public void log() {
        System.out.println();
    }

    public void log(Level level, Supplier<String> msgSupplier) {
        if (instance.isLoggable(level)) {
            System.out.println(msgSupplier.get());
        }
    }

    public boolean isLoggable(Level level) {
        return this.level.getLevl() <= level.getLevl();
    }

    enum Level {
        DEBUG(0), FINER(1), WARNING(2), ERROR(3), ;

        private int levl;

        public int getLevl() {
            return levl;
        }

        Level(int levl) {
            this.levl = levl;
        }
    }
}
