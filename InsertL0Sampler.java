
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class InsertL0Sampler implements Sampler<Integer> {
    // you will need to add additional class attribute
    // size of universe
    private long n;
    private long minHash = (long) Double.POSITIVE_INFINITY;
    // selected from k-wise independent family of hash functions with k>=s/2
    private Hash hash;
    private int minIndex;

    public InsertL0Sampler(long n) {
        this.n = n;
        // fill in
        hash = new Hash(n*n);
    }

    public void add(Integer index, int value) {
        // fill in
    	long h = hash.hash(index);
    	
    	if (minHash > h) {
    		minHash = h;
    		minIndex = index;
    	} 
    }

    public Integer output() {
        // fill in
    	return minIndex;
    }

}
