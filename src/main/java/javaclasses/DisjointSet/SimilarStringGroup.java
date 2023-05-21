package javaclasses.DisjointSet;

public class SimilarStringGroup {
    //https://leetcode.com/problems/similar-string-groups/description/
    /*Two strings, X and Y, are considered similar if either they are identical or we can make
    them equivalent by swapping at most two letters (in distinct positions) within the string X.

For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts"
are similar, but "star" is not similar to "tars", "rats", or "arts".

Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.
Notice that "tars" and "arts" are in the same group even though they are not similar.  Formally,
each group is such that a word is in the group if and only if it is similar to at least one other
word in the group.

We are given a list strs of strings where every string in strs is an anagram of every other string
in strs. How many groups are there?



Example 1:

Input: strs = ["tars","rats","arts","star"]
Output: 2
Example 2:

Input: strs = ["omv","ovm"]
Output: 1


Constraints:

1 <= strs.length <= 300
1 <= strs[i].length <= 300
strs[i] consists of lowercase letters only.
All words in strs have the same length and are anagrams of each other.*/

    public int numSimilarGroups(String[] strs) {
        int len = strs.length;
        UnionFind uf = new UnionFind(len);

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (checkEqual(strs[i], strs[j]))
                    uf.Union(i, j);
            }
        }

        return uf.size;
    }

    public boolean checkEqual(String str1, String str2) {
        int mismatch = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (mismatch > 2) return false;
            if (str1.charAt(i) != str2.charAt(i))
                mismatch++;
        }

        return mismatch == 2 || mismatch == 0;
    }


    public class UnionFind {
        int size;
        int[] nums;
        int[] rank;

        UnionFind(int s) {
            nums = new int[s];
            rank = new int[s];
            for (int i = 0; i < nums.length; i++)
                nums[i] = i;
            this.size = s;
        }

        public void Union(int i, int j) {
            int pi = find(i);
            int pj = find(j);
            if (pi == pj) return;

            if (rank[pi] > rank[pj]) {
                nums[pj] = pi;
                rank[pi] += rank[pj];
            } else {
                nums[pi] = pj;
                rank[pj] += rank[pi];
            }

            size--;
        }

        public int find(int i) {
            while (i != nums[i]) {
                nums[i] = nums[nums[i]];
                i = nums[i];
            }

            return i;
        }
    }

    public static void main(String[] args) {
        SimilarStringGroup group = new SimilarStringGroup();
        String[] strs = {"tars", "rats", "arts", "star"};
        System.out.println(group.numSimilarGroups(strs));
    }
}
