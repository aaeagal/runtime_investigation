import java.util.Arrays;
public class minRotatedSortedArray {
    public static int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[start] <= nums[mid]) {
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return -1;
    }

    public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
    
        while (left < right) {
            int mid = left + (right - left) / 2;
    
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
    
        return nums[left];
    }

	public static int search2(int[] nums, int target) {
    	int left = 0;
    	int right = nums.length - 1;

    	while (left <= right) {
        	int mid = left + (right - left) / 2;

        	if (nums[mid] == target) {
            	return mid;
        	} else if (nums[left] <= nums[mid]) {
            	if (target >= nums[left] && target < nums[mid]) {
                	right = mid - 1;
            	} else {
                	left = mid + 1;
            	}
        	} else {
            	if (target > nums[mid] && target <= nums[right]) {
                	left = mid + 1;
            	} else {
                	right = mid - 1;
            	}
        	}
    	}

    	return -1;
	}

    public static int findMin4(int[] nums) {
      int left = 0;
      int right = nums.length - 1;
      
      while (left < right) {
        int mid = left + (right - left) / 2;
        
        if (nums[mid] > nums[right]) {
          left = mid + 1;
        } else {
          right = mid;
        }
      }
      
      return nums[left];
    }

    public static int findMin7(int[] nums) {
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }
        
        return nums[left];
    }

    // Main
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java minRotatedSortedArray methodName [array] [target]");
            return;
        }

        String methodName = args[0];
        int[] nums;
        int target;

        // Parse array and target from command-line arguments
        try {
            nums = Arrays.stream(args[1].split(","))
                         .mapToInt(Integer::parseInt)
                         .toArray();

            if (args.length > 2) {
                target = Integer.parseInt(args[2]);
            } else {
                target = -1; // Default target when not provided
            }
        } catch (NumberFormatException e) {
            System.out.println("Error parsing arguments: " + e.getMessage());
            return;
        }

        switch (methodName) {
            case "search":
                System.out.println(search(nums, target));
                break;
            case "findMin":
                System.out.println(findMin(nums));
                break;
            case "search2":
                System.out.println(search2(nums, target));
                break;
            case "findMin4":
                System.out.println(findMin4(nums));
                break;
            case "findMin7":
                System.out.println(findMin7(nums));
                break;
            default:
                System.out.println("Method not found: " + methodName);
        }
    }

}
