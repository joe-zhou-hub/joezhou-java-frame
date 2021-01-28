package com.joezhou.shieldspringbootstarter;

import org.junit.jupiter.api.Test;

/**
 * @author JoeZhou
 */
class ShieldOperationsTest {

    @Test
    void shield() {
        ShieldOperations shieldOperations = new ShieldOperations();
        String words = "a blue apple!";
        shieldOperations.setShieldWords("a,b,c");
        System.out.println(shieldOperations.shield(words));
    }
}