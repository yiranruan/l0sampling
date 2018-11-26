
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class DynamicL0Sampler implements Sampler<Integer> {
    // you may need to add additional class attributes

    // size of universe
    private int n;

    private int sparsity;
    
    // struct of sparse rec. structures
    private SSparseRec [] recovery;
    Hash[] hashFamily;
    SSparseRec[] s;
    int j;
    int count = 0;
    int delta;
    
    // selected from k-wise independent family of hash functions with k>=s/2
    private Hash hash;

    public DynamicL0Sampler(int n) {
    	delta = 100;
    	sparsity = (int) (12 * Math.log(delta));
        this.n = n;
        hash = new Hash(n*n);
        j = (int) Math.ceil(Math.log(n)/Math.log(2));
    	s = new SSparseRec[j];
    	hashFamily = new Hash[j];
    	for(int i = 0; i < j; i++) {
    		s[i] = new SSparseRec(5, sparsity);
    		hashFamily[i] = new Hash(n*n);
    	}
        // fill in
    }

    public void add(Integer index, int value) {
        // fill in
    	for(int i = 0; i < j; i++) {
    		if(hashFamily[i].hash(index.intValue())>n*n*(Math.pow(2, -i))) {//need we change the hash for each level?
    			s[i].add(index.intValue(), 0);
    		} else {
				s[i].add(index.intValue(), value);
			}
    	}
    	
    }

    public Integer output() {
        // fill in
    	HashMap<Integer,Integer> map = null;
    	int i;
    	long minHash = (long) Double.POSITIVE_INFINITY;
    	Integer index = -1;
    	boolean isSSparseExist = false;
    	for(i = 0; i < j; i++) {
    		if(s[i].isSSparse()) {
    			map = s[i].recover();
    			isSSparseExist = true;
    			break;
    		}
    	}
    	if (isSSparseExist) {
        	hash = new Hash(n*n);
        	for (HashMap.Entry<Integer,Integer> entry : map.entrySet()) {
        		if (hash.hash(entry.getKey()) < minHash) {
    				minHash = hash.hash(entry.getKey());
    				index = entry.getKey();
    			}
        	}
		}
        return index;
    }

    // more difficult: return a vector index and its count-value
    public Pair<Integer,Integer> outputVector() {
        // you do not have to fill in

        return null;
    }

    // this may help
    @Override public String toString() {
        StringBuilder sb = new StringBuilder("L0_samp: s-sparse strucs: %n");
        for(int i=0; i<recovery.length; i++) {
            sb.append("struc: " + i + " " + recovery[i] + "%n");
        }
        return sb.toString();
    }

}
