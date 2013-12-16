package com.scoredev.scores;

import java.security.BasicPermission;

public final class HighScorePermission extends BasicPermission {

    public HighScorePermission(String name) {
        super(name);
    }
    
    public HighScorePermission(String name, String actions) {
        super(name, actions);
    }
    
}
