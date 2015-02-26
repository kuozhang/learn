package learn.dsaa.dp;

/**
 * @author Kuo Zhang
 * 
 * 动态规划：最长递增子序列，Longest Increasing Subsequence
 * 
 * 严格：必须保持元素是递增的
 * 非严格：元素相同可以放在序列里，保持非减趋势
 * 
 * 不要求序列在数组中的位置是连续的
 * Output: 每个元素的最长递增自序列的长度
 * 
 * // TODO, output the sequence for each item 
 */
public class LIS
{

	public static int[] lis( int[] src )
	{
		if( src.length == 0 )
		{
			return null;
		}

		int len = src.length;

		int[] retval = new int[len]; 

		retval[0] = 1;

		for( int i = 1; i < len; i++ )
		{
			int lenOfLIS = 1;

			for( int j = i - 1; j >= 0; j-- )
			{
			    // if( src[j] < src[i] && lenOfLIS < retval[j] + 1 ) strict
				if( src[j] <= src[i] && lenOfLIS < retval[j] + 1 ) // non-strict
				{
					lenOfLIS = retval[j] + 1;
				}
			}

			retval[i] = lenOfLIS;
		}

		return retval;
	}

	public static void main( String[] args )
	{
		int[] valuesForTest =
			{ 11, 12, 13, 14, 15, 6, 8, 7, 9, 5, 1, 3, 20, 2, 4, 0, 16, 17, 19, 18, 10, 0, 5, 15, 10, 20 };

		int[] lis = lis( valuesForTest ); 

//      // System.out.println("The length of Longest Increasing Subsequence is: " + lis.length );

		printArray( lis );
	}

	private static void printArray( int[] lis2 )
	{
		for( int val : lis2 )
		{
			System.out.print( val + " " );
		}
	}
}
