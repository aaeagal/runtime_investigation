public class Solution {
    public int getArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int area1 = (ax2 - ax1) * (ay2 - ay1);
        int area2 = (bx2 - bx1) * (by2 - by1);
        int intersectionWidth = Math.min(ax2, bx2) - Math.max(ax1, bx1);
        int intersectionHeight = Math.min(ay2, by2) - Math.max(ay1, by1);
        int intersectionArea = Math.max(intersectionWidth, 0) * Math.max(intersectionHeight, 0);
        return area1 + area2 - intersectionArea;
    }

    public int getTotalArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int area1 = (ax2 - ax1) * (ay2 - ay1);
        int area2 = (bx2 - bx1) * (by2 - by1);
        return area1 + area2;
    }
    public double getTotalArea3(double ax1, double ay1, double ax2, double ay2, double bx1, double by1, double bx2, double by2) {
        double area1 = (ax2 - ax1) * (ay2 - ay1);
        double area2 = (bx2 - bx1) * (by2 - by1);
        return area1 + area2;
    }

    public static int calculateAre4a(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int area1 = Math.abs(ax2 - ax1) * Math.abs(ay2 - ay1);
        int area2 = Math.abs(bx2 - bx1) * Math.abs(by2 - by1);
        int intersectionArea = Math.max(Math.min(ax2, bx2) - Math.max(ax1, bx1), 0) * Math.max(Math.min(ay2, by2) - Math.max(ay1, by1), 0);
        return area1 + area2 - intersectionArea;
    }


    public int getAre7a(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int area1 = (ax2 - ax1) * (ay2 - ay1);
        int area2 = (bx2 - bx1) * (by2 - by1);
        int intersectionWidth = Math.min(ax2, bx2) - Math.max(ax1, bx1);
        int intersectionHeight = Math.min(ay2, by2) - Math.max(ay1, by1);
        int intersectionArea = Math.max(intersectionWidth, 0) * Math.max(intersectionHeight, 0);
        return area1 + area2 - intersectionArea;
    }
}
