
public class OneSparseRec {

    private long phi;
    private long iota;
    // there are more attributes...
    // fill in 
    private long p = 1073741789;
    private long q;
    private long left;

    public OneSparseRec() {
       // fill in
    	this.phi = 0;
    	this.iota = 0;
    	this.q = StdRandom.uniform(p);
    	this.left = 0;
    }

    public void add(long index, long value) {
        // fill in
    	left += value * power(q, index,p);
    	phi += value;
    	iota += value * index;
    }

    public boolean isOneSparse() {
        // fill in
    	if (phi == 0) {
    		return false;
    	} else if (left - phi * power(q, iota/phi,p) == 0) {
    		return true;
		}
        return false;
    }
    
    private static long power(long x, long y, long p) { 
    	long res = 1;      
    	x = x % p; 
        
    	while (y > 0) { 
    		if((y & 1)==1) {
    			res = (res * x) % p;
    		}
    		y = y >> 1;
    		x = (x * x) % p;
    	} 
        return res;
    }
    
    public String oneSparseTest() {
        // fill in
    	if (!isOneSparse()) {
			if (phi == 0&&iota==0) {
				return "zero";
			}
			return "more";
		}
        return iota/phi+" "+phi;
    }

    // getters
    public long getPhi() {
        return this.phi;
    }

    public long getIota() {
        return this.iota;
    }

    // this might help
    @Override public boolean equals(Object otherObj) {
        if (!(otherObj instanceof OneSparseRec)) return false;
        else {
            OneSparseRec oner = (OneSparseRec) otherObj;
            return this.getIota() == oner.getIota()
                    && this.getPhi() == oner.getPhi();
        }
    }

    @Override public String toString() {
        if(this.isOneSparse()) {
            return "index = " + iota/phi + ",value = " + phi;
        } else if(phi == 0) {
            return "empty " + phi + " " + iota ;
        } else return "multiple " + "index = " + iota/phi +
                ",value = " + phi + this.isOneSparse();
    }


}
