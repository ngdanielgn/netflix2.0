package jdbc;

public class MovieSort {
	public class Sorter
	{
	    private static Comparator<Product> COMPARATOR = new Comparator<Product>()
	    {
	    // This is where the sorting happens.
	        public int compare(Product o1, Product o2)
	        {
	            return o1.getPrice() - o2.getPrice();
	        }
	    };
}
