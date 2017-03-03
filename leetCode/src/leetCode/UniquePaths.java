package leetCode;

public class UniquePaths {
	
	public static int uniquePath(int m, int n){
		int count = 0;
		if(n==0 || m==0){
			return 0;
		}
		if(n==1 || m==1){
			return 1;
		}
		int[][] arr = new int[m][n];
		for(int i=0; i<m; i++){
			arr[i][0] = 1;
		}
		for(int j=0; j<n; j++){
			arr[0][j] = 1;
		}
		for(int i=1; i<m; i++){
			for(int j=1; j<n; j++){
				arr[i][j] = arr[i-1][j] + arr[i][j-1];
			}
		}
		count = arr[m-1][n-1];
		arr = null;
		return count;
	}
	
	public static int uniquePaths(int m, int n){
		double index = 1;
		double count = 1;
		double temp = 1;
		if(m==0 || n==0){
			return 0;
		}
		if(m==1 || n==1){
			return 1;
		}
		index = m<n?(m-1):(n-1);
		for(double i=1.0; i<=index; i++){
			count *= (m+n-1-i);
			temp *= i;
		}
		int result = (int) (count/temp);
		return result;
	}
	
	public static int uniquePathsWithObstacles(int[][] obstacleGrid){
		int m = obstacleGrid.length;//行
		if(m==0){
			return 0;
		}
		int n = obstacleGrid[0].length;//列
		if(m==1 && n==1){
			if(obstacleGrid[0][0]==1){
				return 0;
			}
			return 1;
		}
		int[][] arr = new int[m][n];
		for(int i=0; i<m; i++){
			if(obstacleGrid[i][0]!=1){
				arr[i][0] = 1;
			}else{
				break;
			}
		}
		for(int j=0; j<n; j++){
			if(obstacleGrid[0][j]!=1){
				arr[0][j] = 1;
			}else{
				break;
			}
		}
		for(int i=1; i<m; i++){
			for(int j=1; j<n; j++){
				if(obstacleGrid[i][j]!=1){
					arr[i][j] = arr[i-1][j] + arr[i][j-1];
				}
			}
		}
		int count = arr[m-1][n-1];
		arr = null;
		return count;
	}
	public static void main(String[] args){
		int[][] arr = {{1,2,3},{3,4,5}};
		int m = arr.length;
		System.out.println(m);
		System.out.println(uniquePaths(9,8));
	}

}
