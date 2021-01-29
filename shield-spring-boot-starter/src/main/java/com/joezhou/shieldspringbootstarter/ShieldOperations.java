package com.joezhou.shieldspringbootstarter;

import lombok.Data;

/**
 * @author JoeZhou
 */
@Data
public class ShieldOperations {

    /**
     * a,b,c
     **/
    private String shieldWords;

    /**
     * "a blue apple" => "* *lue *pple"
     */
    public String shield(String word) {
        if (shieldWords != null) {
            for (String e : shieldWords.split(",")) {
                word = word.replace(e, "*");
            }
        }
        return word;
    }
}