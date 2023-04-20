package org.example.geometry;

import lombok.extern.slf4j.Slf4j;
import org.example.geometry.entities.Point;
import org.example.math.MathUtil;
import org.junit.Test;

import java.security.SecureRandom;
import java.util.*;

/**
 * @author wangguang
 * @date 2023-04-20 0:01
 * description: 平面上N个点, 求最短距离
 */

@Slf4j
public class MinimumDistanceOfPoints {

    public int getMinimumDistanceSquare(Point[] points) {
        long l = System.currentTimeMillis();
        if (points == null || points.length < 2) {
            throw new IllegalArgumentException("points should contains at least 2 items");
        }

        Set<Point> distinctPoints = new HashSet<>(points.length);
        Collections.addAll(distinctPoints, points);
        if (distinctPoints.size() != points.length) {
            //存在两点重合
            return 0;
        }

        Point[] xSort = distinctPoints.toArray(new Point[0]);
        Arrays.sort(xSort, (p1, p2) -> {
            if (p1.x != p2.x) {
                return p1.x - p2.x;
            }
            return p1.y - p2.y;
        });
        System.out.println("getMinimumDistanceSquare use :" + (System.currentTimeMillis() - l));

        return merge(xSort, 0, xSort.length - 1);
    }

    private int merge(Point[] xSort, int left, int right) {
        if (left == right) {
            return Integer.MAX_VALUE;
        }
        if (left == right - 1) {
            return MathUtil.getDistanceSquare(xSort[left], xSort[right]);
        }

        int mid = (left + right) >>> 1;

        int leftRes = merge(xSort, left, mid);
        int rightRes = merge(xSort, mid + 1, right);

        int min = Math.min(leftRes, rightRes);
        int d = xSort[mid].x;
        List<Point> list = new ArrayList<>();
        for (int t = mid; t > -1; t--) {
            if (d - xSort[t].x >= min) {
                break;
            } else {
                list.add(xSort[t]);
            }
        }
        for (int t = mid + 1; t < xSort.length; t++) {
            if (xSort[t].x - d >= min) {
                break;
            } else {
                list.add(xSort[t]);
            }
        }

        list.sort((p1, p2) -> {
            if (p1.y != p2.y) {
                return p1.y - p2.y;
            }
            return p1.x - p2.x;
        });
        int size = list.size();

        for (int i = 0; i < size - 1; i++) {
            boolean b = list.get(i).x <= d;
            for (int j = 1; i + j < size && j < 8; j++) {
                min = Math.min(min, MathUtil.getDistanceSquare(list.get(i), list.get(i + j)));
            }
        }
        return min;
    }

    @Test
    public void test_01(){
        SecureRandom random = new SecureRandom();
        Point[] points = new Point[20000];
        for(int i=0;i<20000;i++){
            points[i] = new Point(random.nextInt(10000), random.nextInt(10000));
        }
        System.out.println(getMinimumDistanceSquare(points));
        System.out.println(getByForce(points));
    }

    public int getByForce(Point[] points){
        long l = System.currentTimeMillis();
        int min = Integer.MAX_VALUE;
        int len = points.length;
        for(int i=0;i<len-1;i++){
            for(int j=i+1;j<len;j++){
                min = Math.min(MathUtil.getDistanceSquare(points[i], points[j]), min);
            }
        }
        System.out.println("getByForce use :" + (System.currentTimeMillis() - l));
        return min;
    }


}
