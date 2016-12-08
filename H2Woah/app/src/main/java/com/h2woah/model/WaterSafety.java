package com.h2woah.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Aman on 12/8/16.
 */

public enum WaterSafety {
    SAFE, TREATABLE, UNSAFE;

    public static Collection<WaterSafety> toList() {
        List<WaterSafety> list = new ArrayList<WaterSafety>();

        Collections.addAll(list, values());
        return list;
    }

    public static WaterSafety stringToSafety(String s) {
        switch(s) {
            case "SAFE":
                return WaterSafety.SAFE;
            case "TREATABLE":
                return WaterSafety.TREATABLE;
            case "UNSAFE":
                return WaterSafety.UNSAFE;
        }
        return WaterSafety.UNSAFE;
    }
}