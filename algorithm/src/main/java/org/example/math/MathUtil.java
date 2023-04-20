package org.example.math;

import org.example.geometry.entities.Point;

/**
 * @author wangguang
 * @date 2023-04-20 9:50
 * description:
 */
public final class MathUtil {

    public static int square(int x){
        return x*x;
    }

    public static int getDistanceSquare(Point p1, Point p2){
        return square(p1.x - p2.x) + square(p1.y - p2.y);
    }

}
