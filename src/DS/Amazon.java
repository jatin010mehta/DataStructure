package DS;
import java.util.*;
public class Amazon {
    public static class PairInt{
        private int first;
        private int second;

        public PairInt(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString() {
            return "first=" + first + ", second=" + second;
        }
    }
    public List<PairInt> nearestVegetarianRestaurant(int totalRestaurants, List<PairInt> allLocations, int k) {
        // WRITE YOUR CODE HERE
        if (k==0)
            return new ArrayList<PairInt>();
        if (totalRestaurants<=k)
            return allLocations;
        List<PairInt> output = new ArrayList();
        PriorityQueue<PairInt> queue = new PriorityQueue<>((a,b)->(b.first*b.first+b.second*b.second)-(a.first*a.first+a.second+a.second));
        for (PairInt pair :allLocations) {
            queue.add(pair);
            if (queue.size()>k)
                queue.poll();
        }

        while (!queue.isEmpty()){
            output.add(queue.poll());
        }
        Collections.reverse(output);
        return output;


    }

    public List<PairInt> aircraftUtilization(int deviceCapacity, List<PairInt> foregroundAppList, List<PairInt> backgroundAppList){
        List<PairInt> res = new ArrayList<>();
        int len1 = foregroundAppList.size(), len2 = backgroundAppList.size();
        if(len1 == 0 || len2 == 0) return res;
        Collections.sort(foregroundAppList,(a,b)->a.second-b.second);
        Collections.sort(backgroundAppList,(a,b)->a.second-b.second);
        int left = 0, right = len2 - 1, maxVal = -1;
        HashMap<Integer, List<PairInt>> map = new HashMap<>();
        while(left < len1 && right >= 0){
            int sum = foregroundAppList.get(left).second + backgroundAppList.get(right).second;
            if(sum > deviceCapacity){ --right; continue;}
            if(sum >= maxVal){
                int r = right;
                map.putIfAbsent(sum, new ArrayList<>());
                while(r >= 0 && backgroundAppList.get(r).second == backgroundAppList.get(right).second){
                    PairInt pair = new PairInt(foregroundAppList.get(left).first,backgroundAppList.get(r).first);
                    map.get(sum).add(pair); --r;
                }
                maxVal = sum;

            }
            ++left;
        }
        return map.get(maxVal);
    }
}
