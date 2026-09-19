package com.streaming.userservice.util;

public class LoginManager {

    private static volatile LoginManager instance;
    private int activeSessionsCount;

    private LoginManager() {
        this.activeSessionsCount = 0;
    }

    public static LoginManager getInstance() {
        if (instance == null) {
            synchronized (LoginManager.class) {
                if (instance == null) {
                    instance = new LoginManager();
                }
            }
        }
        return instance;
    }

    public synchronized void registerLogin(String username) {
        this.activeSessionsCount++;
        System.out.println("[Singleton LoginManager] Usuario conectado: " + username + " | Sesiones activas: " + activeSessionsCount);
    }

    public int getActiveSessionsCount() {
        return activeSessionsCount;
    }
}
