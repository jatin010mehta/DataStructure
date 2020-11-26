package DS;

import java.util.*;

public class LeetCode {
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    int totalPath = 0;
    public int uniquePathsIII(int[][] grid) {
        int totalZero = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]==0)
                    totalZero++;
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]==1)
                    dfsForUniquePath(grid,i,j,totalZero);
            }
        }
        return totalPath;
    }

    private void dfsForUniquePath(int[][] grid, int i, int j, int totalZero) {
        if (i<0||j<0||i>= grid.length||j>=grid[0].length||grid[i][j]==-1||(grid[i][j]==2&&totalZero!=0)||grid[i][j]==99)
            return;
        if (grid[i][j]==2&&totalZero==0)
            totalPath++;
        if (grid[i][j]==0)
            totalZero--;
        int temp = grid[i][j];
        grid[i][j]=99;
        dfsForUniquePath(grid,i+1,j,totalZero);
        dfsForUniquePath(grid,i-1,j,totalZero);
        dfsForUniquePath(grid,i,j+1,totalZero);
        dfsForUniquePath(grid,i,j-1,totalZero);
        grid[i][j]=temp;

    }

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length==0)
            return 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (dp[i][j]==0){
                    max=Math.max(max,dfsForLongestIncreasing(matrix,i,j,dp,Integer.MIN_VALUE));
                }
            }
        }
        return max;
    }

    private int dfsForLongestIncreasing(int[][] matrix, int i, int j, int[][] dp, int pre) {
        if (i<0||j<0||i>= matrix.length||j>= matrix[0].length)return 0;
        else if (pre>=matrix[i][j]) return 0;
        else if (dp[i][j]!=0)return dp[i][j];
        int path1 = dfsForLongestIncreasing(matrix,i+1,j,dp,matrix[i][j]);
        int path2 = dfsForLongestIncreasing(matrix,i-1,j,dp,matrix[i][j]);
        int path3 = dfsForLongestIncreasing(matrix,i,j+1,dp,matrix[i][j]);
        int path4 = dfsForLongestIncreasing(matrix,i,j-1,dp,matrix[i][j]);
        dp[i][j] =1+ Math.max(Math.max(path1,path2),Math.max(path3,path4));
        return dp[i][j];

    }

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> cord = new ArrayList();
        if(matrix.length==0)return cord;

        int row = matrix.length;
        int col = matrix[0].length;

        int[][] paci = new int[row][col];
        int[][] alt =  new int[row][col];


        //first row (pacific)
        for(int i=0;i<col;i++){
            dfs(matrix,0,i,paci,Integer.MIN_VALUE);
        }
        //first col (pacific)
        for(int i=0;i<row;i++){
            dfs(matrix,i,0,paci,Integer.MIN_VALUE);
        }
        //last row (altantic)
        for(int i=0;i<col;i++){
            dfs(matrix,row-1,i,alt,Integer.MIN_VALUE);
        }
        //last col (altantic)
        for(int i=0;i<row;i++){
            dfs(matrix,i,col-1,alt,Integer.MIN_VALUE);
        }

        for(int i=0;i<paci.length;i++){
            for(int j=0;j<paci[0].length;j++){
                if(paci[i][j]==1  && alt[i][j]==1){
                    List<Integer> c = new ArrayList();
                    c.add(i);c.add(j);
                    cord.add(c);
                }
            }
        }

        return cord;
    }

    void dfs(int[][] matrix , int row , int col , int[][] temp,int pre){

        if(row<0 || col<0 || row>matrix.length-1 || col>matrix[0].length-1)return;
        else if(pre>matrix[row][col])return;
        else if(temp[row][col]==1)return;

        temp[row][col] = 1;

        dfs(matrix,row+1,col,temp,matrix[row][col]);
        dfs(matrix,row-1,col,temp,matrix[row][col]);
        dfs(matrix,row,col-1,temp,matrix[row][col]);
        dfs(matrix,row,col+1,temp,matrix[row][col]);
    }
    public boolean hasAllCodes(String s, int k) {
        int count = 0;
        int start = 0;
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            count++;
            if (count==k){
                set.add(s.substring(start,start+k));
                start++;
                count--;
            }
        }
        return set.size()==Math.pow(2,k);
    }
    public int singleNumber(int[] nums) {
        HashSet<Integer> set = new HashSet();
        for(int n:nums){
            if(set.contains(n)){
                set.remove(n);
            }
            else
                set.add(n);
        }
        for(int n:set)
            return n;
        return -1;
    }
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],i);
        }

        for (int i = 0; i < nums.length; i++) {
            int compliment = target-nums[i];
            if (map.containsKey(compliment)&&map.get(compliment)!=i){
                return new int[]{i,map.get(compliment)};
            }
        }
        return new int[]{-1,-1};
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode temp = head;
        ListNode p = l1;
        ListNode q = l2;
        int carry = 0;
        while (p!=null||q!=null){
            int x = (p!=null)?p.val:0;
            int y = (q!=null)?q.val:0;
            int sum = x+y+carry;
            carry = sum/10;
            temp.next = new ListNode(sum%10);
            temp=temp.next;
            if(p!=null)
                p=p.next;
            if (q!=null)
                q=q.next;
        }
        if (carry>0)
            temp.next=new ListNode(carry);
        return head.next;
    }
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(0);
        ListNode temp = head;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(ListNode li:lists){
            while (li!=null){
                queue.add(li.val);
                li=li.next;
            }
        }
        while (!queue.isEmpty()){
            temp.next=new ListNode(queue.poll());
            temp=temp.next;
        }
        return head.next;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode temp = head;
        while (l1!=null&&l2!=null){
            if (l1.val< l2.val){
                temp.next = new ListNode(l1.val);
                l1=l1.next;
            }else {
                temp.next=new ListNode(l2.val);
                l2=l2.next;
            }
            temp=temp.next;
        }
        if (l1!=null)
            temp.next=l1;
        if (l2!=null)
            temp.next=l2;
        return head.next;
    }

//
//               [1],
//             [1,1],
//             [1,2,1], [1,1,1]
//             [1,3,3,1],
//             [1,4,6,4,1]
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> output = new ArrayList<>();
        List<Integer> row = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            row.add(0,1);
            for (int j = 1; j <row.size()-1 ; j++) {
                row.set(j,row.get(j)+row.get(j+1));
            }
            output.add(new ArrayList<>(row));
        }
        return output;
    }

    public int[] twoSumII(int[] numbers, int target) {
        int aPointer = 0;
        int bPointer = numbers.length-1;
        while (aPointer<bPointer){
            int sum = numbers[aPointer]+numbers[bPointer];
            if (sum<target)
                aPointer++;
            else if (sum>target)
                bPointer--;
            else if (sum==target)
                return new int[]{aPointer+1,bPointer+1};
        }
        return new int[]{-1,-1};
    }
    ListNode firstNode;
    public boolean isPalindrome(ListNode head) {
        firstNode = head;
        return checkPalindrome(head);
    }

    private boolean checkPalindrome(ListNode current) {
        if (current!=null){
            if (!checkPalindrome(current.next)) return false;
            if (firstNode.val!=current.val) return false;
            firstNode=firstNode.next;
        }
        return true;
    }
    public String removeDuplicates(String S) {
        Stack<Character> stack = new Stack<>();
        for (char ch :S.toCharArray()) {
            if (stack.isEmpty()||stack.peek()!=ch)
                stack.push(ch);
            else
                stack.pop();

        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
            sb.append(stack.pop());
        return sb.reverse().toString();
    }
   //yffbbbbnnnnffbgffffgbbbbgssssgthyyyy
   //111234
   //012345
    public String removeDuplicates(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        int[] count = new int[sb.length()];
        int i = 0;
        while (i<sb.length()){
            if (i==0||sb.charAt(i)!=sb.charAt(i-1))
                count[i]=1;
            else {
                count[i]=count[i-1]+1;
                if (count[i]==k){
                    sb.delete(i-k+1,i+1);
                    i=i-k;
                }
            }
            i++;
        }
       return sb.toString();
    }

    //"lee(t(c)o)de)"
    //"a)b(c)d"
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> index = new Stack<>();
        Stack<Character> charecter = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length();i++){
            if (s.charAt(i)=='('){
                index.push(i);
                charecter.push('(');
            }else if (s.charAt(i)==')'){
                if (!charecter.isEmpty()&&charecter.peek()=='('){
                    index.pop();
                    charecter.pop();
                }
                else {
                    index.push(i);

                }
            }
        }
      HashSet<Integer> set = new HashSet<>();
        while (!index.isEmpty()){
            set.add(index.pop());
        }
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(i))
                set.remove(i);
            else
                sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public int minInsertions(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                dp[i][j]=-1;
            }
        }
        return minInsertion(s,0,s.length()-1,dp);
    }

    private int minInsertion(String s, int i, int j, int[][] dp) {
        if (i>=j) return 0;
        if (dp[i][j]!=-1)
            return dp[i][j];
        if (s.charAt(i)==s.charAt(j)){
            dp[i][j]=minInsertion(s,i+1,j-1,dp);
            return dp[i][j];
        }else {
            int dec1 = 1+minInsertion(s,i+1,j,dp);
            int dec2 = 1+minInsertion(s,i,j-1,dp);
            dp[i][j] = Math.min(dec1,dec2);
            return dp[i][j];
        }
    }
}
