class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int n = nums.length;
        int MAX_GCD = 50000; // 100000/2 
        // int size = MAX_GCD+1;
        // long[] freq = new long[size];

        // for(int i=0; i<n; i++) {
        //     for(int j=i+1; j<n; j++) {
        //         freq[gcd(nums[i], nums[j])]++;
        //     }
        // }
        int maxVal = 0;
for (int x : nums) {
    maxVal = Math.max(maxVal, x);
}

// count[value] = how many times this value appears in nums
int[] count = new int[maxVal + 1];

for (int x : nums) {
    count[x]++;
}

// freq[g] = number of pairs whose GCD is exactly g
long[] freq = new long[maxVal + 1];

    // Process from bigger gcd to smaller gcd
for (int g = maxVal; g >= 1; g--) {
    long divisibleCount = 0;

    // Count how many numbers are divisible by g
    for (int multiple = g; multiple <= maxVal; multiple += g) {
        divisibleCount += count[multiple];
    }

    // Total pairs where both numbers are divisible by g
    long pairs = divisibleCount * (divisibleCount - 1) / 2;

    // Remove pairs whose GCD is bigger multiple of g
    for (int multiple = 2 * g; multiple <= maxVal; multiple += g) {
        pairs -= freq[multiple];
    }

    // Remaining pairs have exact GCD = g
    freq[g] = pairs;
}

        int m = queries.length;
        int[] answer = new int[m];

        long[] prefixSum = new long[maxVal+1];
        for(int i=1; i<=maxVal; i++) {
            prefixSum[i] = prefixSum[i-1] + freq[i];
        }

        for(int i=0; i<m; i++) {
            answer[i] = binarySearch(prefixSum, queries[i]+1);
        } 

        return answer;

    }

    private int binarySearch(long[] arr, long target) {
        int left = 1;
        int right = arr.length-1;
        int res = -1;

        while(left<=right) {
            int mid = left + (right-left)/2;

            if(arr[mid] >= target) {
                res = mid;
                right = mid-1;
            } else {
                left = mid+1;
            }
        }

        return res;
    }

    private int gcd(int a, int b) {
        while(b!=0) {
            int rem = a%b;
            a = b;
            b = rem;
        }

        return a;
    }
}