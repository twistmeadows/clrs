package org.example.geometry.entities;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangguang
 * @date 2023-04-20 0:05
 * description:
 */
@Data
public class Point implements Serializable{

    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Point of(int x, int y){
        return new Point(x, y);
    }


    public static int crossProduct(Point p1, Point p2){
        return p1.x*p2.y - p1.y * p2.x;
    }
}
