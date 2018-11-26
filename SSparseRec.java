
import java.util.HashMap;


public class SSparseRec {

    private int r, s;

    // selected from pairwise independent family of hash functions
    private Hash[] hashFamily;
    private OneSparseRec[][] net;
    private boolean isMore;
    long more_count = 0;
    long row;
    HashMap<Integer,Integer> indexMap;
    long m;
    boolean isZero;

    public SSparseRec(int r, int sparsity){
        this.r = r;
        this.s = sparsity;

        initialise();
    }

    private void initialise() {
        // fill in
    	hashFamily = new Hash[r];
    	for (int i = 0; i < r; i++) {
    		hashFamily[i] = new Hash(2*s);
    	}
    	net = new OneSparseRec[r][2*s];
    	for (int i = 0; i < r; i++) {
			for (int j = 0; j < 2*s; j++) {
				net[i][j] = new OneSparseRec();
			}
		}
    	indexMap = new HashMap<Integer,Integer>();
    	m = 0;
    	
    }

    public void add(int index, int value) {
       // fill in
    	for(int i = 0; i < r; i++) {
    		net[i][(int) hashFamily[i].hash(index)].add(index, value);
    		
    	}
    	m += value;
    }

    public boolean isSSparse() {
        // fill in
    	isMore = false;
    	isZero = false;
    	long value = 0;
    	for (int i = 0; i < r; i++) {
    		for (int j = 0; j < 2*s; j++) {
    			if (net[i][j].isOneSparse()) {
    				indexMap.put((int) (net[i][j].getIota()/net[i][j].getPhi()),
							(int) (net[i][j].getPhi()));
				}
    		}
    	}
    	for (HashMap.Entry<Integer,Integer> entry : indexMap.entrySet()) {
    	 
    	    value+=entry.getValue();
    	 
    	}
    	if (value == m) {
    		if (m == 0) {
    			indexMap.clear();
    			isZero = true;
				return false;
			}
			return true;
		}else if (m == 0) {
			isZero = true;
			indexMap.clear();
	    	return false;
		}
    	indexMap.clear();
    	isMore = true;
    	return false;
    }

    public String sparseRecTest() {
        // fill in
    	String s = "fail";
    	
    	boolean is = isSSparse();
    	if (!is) {
    		if (isMore) {
        		s = "more";
    		} else if (isZero) {
    			s = "zero";
    		}
		} else {
			s = "";
			HashMap<Integer,Integer> map = recover();
	    	for (HashMap.Entry<Integer,Integer> entry : map.entrySet()) {
	    		
	    	    s+=entry.getKey() + " " + entry.getValue()+"\n";
	    	 
	    	}
		}
    	return s;
    }

    public HashMap<Integer,Integer> recover() {
       // fill in
    	HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
    	if (!isSSparse()) {
			return null;
		}
    	map = indexMap;
        return map;
    }

    // this might help
    @Override public String toString() {
        StringBuilder sb = new StringBuilder("SSparseRecoveryEstimator{array=[");
        long numCols = 2*s;

        for (int i=0; i<r; i++)
            for (int j=0; j<numCols; j++)
                sb.append(String.format("(%d,%d)=%s, ", i, j, net[i][j].toString()));
        sb.append("]}");

        return sb.toString();
    }




}
