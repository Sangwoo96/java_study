public class Stack {
    private int[] nums;

    Stack(){
        nums = new int[0];
    }

    public void push(int data){
        int[] tmp = new int[nums.length+1];
        System.arraycopy(nums, 0, tmp, 0, nums.length);
        tmp[nums.length] = data;
        nums = null;
        nums = tmp;
    }

    public int pop(){
        int result = 0;
        int[] tmp = new int[nums.length-1];
        System.arraycopy(nums, 0, tmp, 0, nums.length-1);
        result = nums[nums.length-1];
        nums = null;
        nums = tmp;
        return result;
    }

    public int size(){
        return nums.length;
    }
    public String printStack(){
        String str = "";
        for(int num : nums){
            str += num + " ";
        }
        return str;
    }
}
