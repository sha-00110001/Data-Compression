import java.security.KeyPair;
import java.util.Scanner;
import java.util.Vector;


class Triple {
	 
    // Pair attributes\ 
	public Double first;
	public Double second;
    public Character c;
    
 
    // Constructor to initialize pair
    public Triple(Character c, Double first, Double second)
    {
        // This keyword refers to current instance
        this.first = first;
        this.second = second;

        this.c = c;
        
    }
}

public class Main {

	public static void main(String[] args) {
		
		
        Scanner myObj5 = new Scanner(System.in); 
	    
		int choice = myObj5.nextInt();
		if(choice==1) {
			///Encoding
			Vector<Triple> v2 = new Vector<Triple>();
			Triple p1 =new Triple('A',0.0,0.8);
			v2.add(p1);
			Triple p2 =new Triple('B',0.8,0.82);
			v2.add(p2);
			Triple p3 =new Triple('C',0.82,1.0);
			v2.add(p3);

			double y=1e9;
			
			for(int i=0;i<v2.size();i++) {
				y=Math.min(y,(v2.get(i).second-v2.get(i).first));
			}
			
			
			//System.out.print(y);
			//System.out.println(00000);
            Scanner myObj3 = new Scanner(System.in); 
		    
			String s2 = myObj3.nextLine();
		    //ACBA

			//System.out.println(s2);
		    double lower=0.0,upper=0.0;
		    
		    String ans2="";
		    Boolean firstocc=true;
		    double lastlow=0.0,lastupp=0.0,lastr=lastupp-lastlow;

		    for(int j=0;j<s2.length();j++){
		    	
		        for(int i=0;i<v2.size();i++){
		            if(s2.charAt(j)==v2.get(i).c) {
		                if (firstocc) {
		                    lower = v2.get(i).first;
		                    upper = v2.get(i).second;
		                    lastr=upper-lower;
		                    firstocc = false;
		                } else {
		                    lower = lastlow + (lastr) * v2.get(i).first;
		                    upper = lastlow + (lastr) * v2.get(i).second;

		                }

		            }else 
		            	continue;

		            //110001100000

		            while (true)
		            {
		                if(lower<=0.5 && upper >0.5) break;
		                if(lower<=0.5 && upper<=0.5){
		                    lower=lower*2;
		                    upper=upper*2;
		                    ans2+='0';
		                    
		                }else if(lower>0.5 && upper>0.5){
		                    lower=(lower-0.5)*2;
		                    upper=(upper-0.5)*2;
		                    ans2+='1';
		                }
		                lastlow=lower;
		                lastupp=upper;
		                lastr=lastupp-lastlow;
		            }
		            break;

		        }
		    }





		   //System.out.println(ans2);
		    ans2+='1';
		    
		   int k=0;
		   Double po= Math.pow(2,0);
		    Double temp=1.0/ po;
		    while(temp>=y)
		    {

		        k++;
		        po= Math.pow(2,k);
		        temp=1.0/po;
		    }
		    
		   
		    for(int i=0;i<k-1;i++) {
		    	ans2+='0';
		    }
		    System.out.println(ans2);
			//////////
			

		}else {
			////////////DEcode
			
			
			Vector<Triple> v = new Vector<Triple>();
			Triple p1 =new Triple('A',0.0,0.8);
			v.add(p1);
			Triple p2 =new Triple('B',0.8,0.82);
			v.add(p2);
			Triple p3 =new Triple('C',0.82,1.0);
			v.add(p3);
			
			Scanner myObj6 = new Scanner(System.in); 
		    
			String s = myObj6.nextLine();
			
		    int skipped=0;
		    for(int i=s.length()-1;i>0;i--){
		        if(s.charAt(i)=='0')
		            skipped++;
		        else{
		            skipped++;
		            break;
		        }
		    }
		        //cout<<skip ee;

		        //110001100000
		    int kbits = s.length()-skipped;

		    
		    String ns=s.substring(0,kbits);
		    //110001
		    String rs = new StringBuffer(ns).reverse().toString();
			//100011
		    Double code=0.0,cnt=0.0;
		    for(int i=0;i<kbits;i++){
		        if(rs.charAt(i)=='1')
		            code+= Math.pow(2,i);
		    }
		    String ans="";
		    code=code/ Math.pow(2,kbits);

		    int index=0;
		    for(int i=0;i< v.size();i++){
		        if(code>=v.get(i).first&& code<=v.get(i).second)
		        {
		            ans+=v.get(i).c;
		            index=i;
		            break;
		        }
		    }
		    //cout<<ans ee;
		    
		    double lastlower=v.get(index).first,
		    		lastupper=v.get(index).second;
		    double lastrange=lastupper-lastlower;


		    while (true) {
		        if(kbits<1) break;

		            code= (code-lastlower)/lastrange;
		           // cout<<code ee;
		            
		            for(int i=0;i< v.size();i++){
		    	        if(code>=v.get(i).first&& code<=v.get(i).second)
		    	        {
		    	            ans+=v.get(i).c;
		    	            index=i;
		    	            break;
		    	        }
		    	    }
		            
		            //cout<<ans ee;
		            double lower=v.get(index).first,upper=v.get(index).second; //original
		            
		            double newlower=lastlower+lastrange*lower;
		            double newupper=lastlower+lastrange*upper;

		            while(true){
		                if((newlower<0.5 && newupper >0.5) || kbits<1) break;
		                
		                if(newlower<=0.5 && newupper<=0.5){
		                    newlower*=2; newupper*=2;
		                    kbits--;
		                }
		                if(newlower>0.5 && newupper>0.5){
		                    newlower=(newlower-0.5)*2; newupper=(newupper-0.5)*2;
		                    kbits--;
		                }
		            }
		            lastlower=newlower; lastupper=newupper;
		    }

		    ////100000 skipped
		    code= Math.pow(2,skipped-1)/ Math.pow(2,skipped);
		    ///search
		    for(int i=0;i< v.size();i++){
		        if(code>=v.get(i).first&& code<=v.get(i).second)
		        {
		            ans+=v.get(i).c;
		            index=i;
		            break;
		        }
		    }

		    

		    System.out.println(ans);
			///////////
		}
		
		///ACBA
		///110001100000
		
		
		

	}

}
