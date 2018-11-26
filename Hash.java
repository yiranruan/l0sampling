import java.math.BigInteger;

// Hash.java
// Hash class
// awirth for COMP90056
// Aug 2017,8 -- version 2

public class Hash{
	private long p = (long)Math.pow(2, 61)-1; //smaller than 2^30
	private long a,b;	
	private long range;
	
	public Hash(long range){
		a = StdRandom.uniform((long) (p-1))+1;
		b = StdRandom.uniform((long) (p+1));
		this.range = range;
	}
	public long hash(int key){
		BigInteger prod = BigInteger.valueOf(1);
		prod = prod.multiply(BigInteger.valueOf(a));
		prod = prod.multiply(BigInteger.valueOf(key));
		prod = prod.add(BigInteger.valueOf(b));
		prod = prod.mod(BigInteger.valueOf(p));
		prod = prod.mod(BigInteger.valueOf(range));
		
		return prod.longValueExact();
	}
	
	public long getN() { return this.range; }

}
