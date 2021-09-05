package javaclasses.ArrayList;

import java.util.ArrayList;
import java.util.List;

public class RangeModule {
    //Leetcode 715
    List<int[]> ranges;

    public RangeModule() {
        ranges = new ArrayList();
    }

    public void addRange(int left, int right) {
        if (ranges.isEmpty()) {
            int[] range = {left, right};
            ranges.add(range);
        } else {
            //check for overlap
            for (int i = 0; i < ranges.size(); i++) {
                int[] arr = ranges.get(i);
                if (arr[0] <= left && arr[1] >= right) {
                    arr[0] = left;
                    arr[1] = right;
                    ranges.set(i, arr);
                } else {
                    //No overlap
                    int[] range = {left, right};
                    ranges.add(range);
                }
            }
        }
    }

    public boolean queryRange(int left, int right) {
        for (int i = 0; i < ranges.size(); i++) {
            int[] arr = ranges.get(i);
            if (arr[0] <= left && arr[1] >= right) {
                //range found
                return true;
            }
        }
        return false;
    }

    public void removeRange(int left, int right) {
        for (int i = 0; i < ranges.size(); i++) {
            int[] arr = ranges.get(i);
            if (arr[0] < left && arr[1] > right) {
                //range found
                ranges.remove(arr);
                //Create range
                int l1 = arr[0];
                int r1 = left;
                int[] range = {l1, r1};
                ranges.add(range);
                int l2 = right;
                int r2 = arr[1];
                int[] range2 = {l2, r2};
                ranges.add(range2);
                break;
            }
        }
    }

    public static void main(String[] args) {
        RangeModule module = new RangeModule();
        /*module.addRange(10, 20);
        module.removeRange(14, 16);
        module.queryRange(10, 14);
        module.queryRange(13, 15);
        module.queryRange(16, 17);*/
        module.queryRange(4, 7);
        module.addRange(3, 5);
        module.addRange(1, 2);
        module.removeRange(1, 2);
        module.removeRange(5, 9);
        module.queryRange(6, 7);
        module.addRange(5, 6);
        module.queryRange(1, 3);
        module.removeRange(1, 8);
    }
}
