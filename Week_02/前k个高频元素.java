class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        //创建一个优先队列，里面的每个元素是一个长度为2的数组([num, count])
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });

        for (Map.Entry<Integer, Integer> mEntry : map.entrySet()) {
            int num = mEntry.getKey();
            int count = mEntry.getValue();
            if (queue.size() < k) {
                queue.offer(new int[]{num, count});
            } else {
                int[] top = queue.peek();
                if (top[1] < count) {
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            int[] top = queue.poll();
            result[i] = top[0];
        }
        return result;
    }
}