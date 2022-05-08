import java.lang.*;

public class NewsBoySim
{
    public static void main(String[] args)throws Exception
    {

        int i, day;
        // Inventory pans=new Inventory(15.75, 10);
        cstats statics=new cstats(); //Initiate statistics
        newsboy joe=new newsboy(); // initiate Joe
        dmdproc wantpaper=new dmdproc(); //initiate wantpaper process
        int dmd;
        double sales, money;
        //now start a loop to test joe's behavior for 5 days.  Have demand constant at 11
        for (day=1;day<=1000;day++)
        {// get the demand for today
            dmd=wantpaper.dmdtoday();
            //give Joe the demand for today
            joe.setdemand(dmd);
            // record the statistics for this day
            statics.setprofit(joe.getprofit());
            // order papers for tomorrow
            joe.order();
        }; //end of timing loop
        System.out.println("sold "+joe.getsold()+" ordered "+joe.getordered());
        System.out.println("profit "+joe.getprofit());
        System.out.println("**************Statics for 1000 Days of Sales***************");
        System.out.println("**************  Ordering Policy A  ***************");
        System.out.println("average profit per day "+statics.getaverage());
        System.out.println("profit variance "+statics.getvar()+" profit st dev "+statics.getstdev());
        System.out.println("average paper sold per day "+joe.gettotalsold()/day);
        System.out.println("sold variance "+joe.getSoldvariance() + " sold st dev "+joe.soldStdev());
        System.out.println("average paper demand per day "+joe.getDemandtotal()/day);
        System.out.println("demand variance "+joe.getDemandvariance() + " demand st dev "+joe.demandStdev());
        System.out.println("count "+statics.getcount()+"\n");




        cstats statics2=new cstats(); //Initiate statistics
        newsboy2 joe2=new newsboy2(); // initiate Joe
        dmdproc wantpaper2=new dmdproc(); //initiate wantpaper process
        int dmd2, day2;
        double sales2, money2;
        //now start a loop to test joe's behavior for 5 days.  Have demand constant at 11
        for (day2=1;day2<=1000;day2++)
        {// get the demand for today
            dmd2=wantpaper2.dmdtoday();
            //give Joe the demand for today
            joe2.setdemand(dmd2);
            // record the statistics for this day
            statics2.setprofit(joe2.getprofit());
            // order papers for tomorrow
            joe2.order2();
        }; //end of timing loop
        System.out.println("sold "+joe2.getsold()+" ordered "+joe2.getordered());
        System.out.println("profit "+joe2.getprofit());
        System.out.println("**************Statics for 1000 Days of Sales***************");
        System.out.println("**************  Ordering Policy B  ***************");
        System.out.println("average profit per day "+statics2.getaverage());
        System.out.println("profit variance "+statics2.getvar()+" profit st dev "+statics2.getstdev());
        System.out.println("average paper sold per day "+joe2.gettotalsold()/day2);
        System.out.println("sold variance "+joe2.getSoldvariance() + " sold st dev "+joe2.soldStdev());
        System.out.println("average paper demand per day "+joe2.getDemandtotal()/day2);
        System.out.println("demand variance "+joe2.getDemandvariance() + " demand st dev "+joe2.demandStdev());
        System.out.println("count "+statics2.getcount()+"\n");



        cstats statics3=new cstats(); //Initiate statistics
        newsboy3 joe3=new newsboy3(); // initiate Joe
        dmdproc wantpaper3=new dmdproc(); //initiate wantpaper process
        int dmd3, day3;
        double sales3, money3;
        //now start a loop to test joe's behavior for 5 days.  Have demand constant at 11
        for (day3=1;day3<=1000;day3++)
        {// get the demand for today
            dmd3=wantpaper3.dmdtoday();
            //give Joe the demand for today
            joe3.setdemand(dmd3);
            // record the statistics for this day
            statics3.setprofit(joe3.getprofit());
            // order papers for tomorrow
            joe3.order3();
        }; //end of timing loop
        System.out.println("sold "+joe3.getsold()+" ordered "+joe3.getordered());
        System.out.println("profit "+joe3.getprofit());
        System.out.println("**************Statics for 1000 Days of Sales***************");
        System.out.println("**************  Ordering Policy C  ***************");
        System.out.println("average profit per day "+statics3.getaverage());
        System.out.println("profit variance "+statics3.getvar()+" profit st dev "+statics3.getstdev());
        System.out.println("average paper sold per day "+joe3.gettotalsold()/day3);
        System.out.println("sold variance "+joe3.getSoldvariance() + " sold st dev "+joe3.soldStdev());
        System.out.println("average paper demand per day "+joe3.getDemandtotal()/day);
        System.out.println("demand variance "+joe3.getDemandvariance() + " demand st dev "+joe3.demandStdev());
        System.out.println("count "+statics3.getcount());


    }// end of main
}//end of newsboysim
class newsboy{
    private int demand;// this is the demand for the day
    private int ordered;// this is the amount ordered for today
    private int bought;//this is the amount bought for today
    private int sold;// this is the amount sold for today
    private int returned;// this is the returned for the day
    private double profit; // profit
    private int total; // average sold
    private int demandtotal; // demand total
    private double soldtotal; // sold total
    private double count;  //count
    private double soldsquare; // sold squared
    private double soldaverage; // sold average
    private double soldvariance; // variance of sold
    private double soldstdev; // st dev of sold
    private double demandsquare; // demand squared
    private double demandaverage;  // demand average
    private double demandvariance; // demand variance
    private double demandstdev; // demand st dev
    // now for the behaviors of the newsboy
    public newsboy()
    {// this is the constructor for the newsboy
        // set all values to 0 and start him with 10 papers
        demand=0;
        ordered=10;
        bought=0;
        sold=0;
        profit=0.0;
        returned = 0;
        total = 0;
        demandtotal = 0;
        soldtotal = 0;
        count = 0;
        demandsquare = 0;
        demandaverage = 0;
        demandvariance = 0;
        demandstdev = 0;
    }//end of newsboy constructor
    public int order()
    {// this is a private policy function for how many the newsboy will order daily
        int x;
        x=16; // order 16 papers daily
        ordered=x;
        return x;
    }
    private void behavior()
    {// this is the behavior of the newsboy.
        count ++;
        // recieve the papers ordered yesterday
        bought=ordered;
        //System.out.println ("nboybehavior bought "+bought+" demand "+demand + " sold " + sold);
        // calculate the papers sold
        if(demand>=bought)sold=bought;
        else
            sold=demand;
        if(bought>sold) {
            //System.out.println("Extra Paper");
            //System.out.println(bought-sold);
            int y = 0;
            y = (bought-sold);
            returned += y;
            //System.out.println(returned);
        }
        // calculate profit
        profit=((1.00*sold)-(0.35*bought))+(returned*.05);
        // order for tomorrow
        ordered=order();
        total += sold;

        // calculate the sold variance and st dev
        soldtotal += sold;
        soldsquare += sold * sold;
        soldaverage = soldtotal/count;
        soldvariance=soldsquare/count-soldaverage*soldaverage;
        soldstdev= Math.sqrt(soldvariance);

        // calculate the demand variance and st dev
        demandtotal += demand;
        demandsquare += demand * demand;
        demandaverage = demandtotal/count;
        demandvariance=demandsquare/count-demandaverage*demandaverage;
        demandstdev= Math.sqrt(demandvariance);

        //System.out.println(" bought "+bought+" ordered "+ordered+" demand "+demand+" sold "+sold);
    }// this is the end of the behavior of the newsboy
    public void setdemand(int x)
    {// we will give the newsboy a demand and then let him behave as appropriate
        demand=x;
        //given the demand for the day, activate the behavior of the newsboy object
        behavior();
    }//end of setdemand
    //********************************Now Create the Utility functions to Interrograte the News Boy Objecct
    public double getSoldvariance() {return soldvariance; }
    public double soldStdev() {return soldstdev; }
    public double getDemandvariance() {return demandvariance; }
    public double demandStdev() {return demandstdev; }
    public int gettotalsold() {
        return total;
    }
    public int getDemandtotal(){return demandtotal; }
    public double getprofit(){return profit;}
    public int getsold(){return sold;}
    public  int  getordered(){return ordered;}
}// end of newsboy class

class newsboy2{
    private int demand;// this is the demand for the day
    private int ordered;// this is the amount ordered for today
    private int bought;//this is the amount bought for today
    private int sold;// this is the amount sold for today
    private int returned;// this is the returned for the day
    private double profit; // profit
    private int total; // average sold
    private int demandtotal; // demand total
    private double soldtotal; // sold total
    private double count;  //count
    private double soldsquare; // sold squared
    private double soldaverage; // sold average
    private double soldvariance; // variance of sold
    private double soldstdev; // st dev of sold
    private double demandsquare; // demand squared
    private double demandaverage;  // demand average
    private double demandvariance; // demand variance
    private double demandstdev; // demand st dev
    // now for the behaviors of the newsboy
    public newsboy2()
    {// this is the constructor for the newsboy
        // set all values to 0 and start him with 10 papers
        demand=0;
        ordered=10;
        bought=0;
        sold=0;
        profit=0.0;
        returned = 0;
        total = 0;
        demandtotal = 0;
        soldtotal = 0;
        count = 0;
        demandsquare = 0;
        demandaverage = 0;
        demandvariance = 0;
        demandstdev = 0;
    }//end of newsboy constructor
    public int order2()
    {// this is a private policy function for how many the newsboy will order daily
        int x;
        x = demand;
        ordered = x;
        return x;
    }
    private void behavior()
    {// this is the behavior of the newsboy.
        count ++;
        // recieve the papers ordered yesterday
        bought=ordered;
        //System.out.println ("nboybehavior bought "+bought+" demand "+demand + " sold " + sold);
        // calculate the papers sold
        if(demand>=bought)sold=bought;
        else
            sold=demand;
        if(bought>sold) {
            //System.out.println("Extra Paper");
            //System.out.println(bought-sold);
            int y = 0;
            y = (bought-sold);
            returned += y;
            //System.out.println(returned);
        }
        // calculate profit
        profit=((1.00*sold)-(0.35*bought))+(returned*.05);
        // order for tomorrow
        ordered=order2();
        total += sold;


        // calculate the sold variance and st dev
        soldtotal += sold;
        soldsquare += sold * sold;
        soldaverage = soldtotal/count;
        soldvariance=soldsquare/count-soldaverage*soldaverage;
        soldstdev= Math.sqrt(soldvariance);

        // calculate the demand variance and st dev
        demandtotal += demand;
        demandsquare += demand * demand;
        demandaverage = demandtotal/count;
        demandvariance=demandsquare/count-demandaverage*demandaverage;
        demandstdev= Math.sqrt(demandvariance);

        //System.out.println(" bought "+bought+" ordered "+ordered+" demand "+demand+" sold "+sold);
    }// this is the end of the behavior of the newsboy
    public void setdemand(int x)
    {// we will give the newsboy a demand and then let him behave as appropriate
        demand=x;
        //given the demand for the day, activate the behavior of the newsboy object
        behavior();
    }//end of setdemand
    //********************************Now Create the Utility functions to Interrograte the News Boy Objecct
    public double getSoldvariance() {return soldvariance; }
    public double soldStdev() {return soldstdev; }
    public double getDemandvariance() {return demandvariance; }
    public double demandStdev() {return demandstdev; }
    public int gettotalsold() {
        return total;
    }
    public int getDemandtotal(){return demandtotal; }
    public double getprofit(){return profit;}
    public int getsold(){return sold;}
    public  int  getordered(){return ordered;}
}// end of newsboy class

class newsboy3{
    private int demand;// this is the demand for the day
    private int ordered;// this is the amount ordered for today
    private int bought;//this is the amount bought for today
    private int sold;// this is the amount sold for today
    private int returned;// this is the returned for the day
    private double profit; // profit
    private int total; // average sold
    private int demandtotal; // demand total
    private double soldtotal; // sold total
    private double count;  //count
    private double soldsquare; // sold squared
    private double soldaverage; // sold average
    private double soldvariance; // variance of sold
    private double soldstdev; // st dev of sold
    private double demandsquare; // demand squared
    private double demandaverage;  // demand average
    private double demandvariance; // demand variance
    private double demandstdev; // demand st dev
    // now for the behaviors of the newsboy
    public newsboy3()
    {// this is the constructor for the newsboy
        // set all values to 0 and start him with 10 papers
        demand=0;
        ordered=10;
        bought=0;
        sold=0;
        profit=0.0;
        returned = 0;
        total = 0;
        demandtotal = 0;
        soldtotal = 0;
        count = 0;
        demandsquare = 0;
        demandaverage = 0;
        demandvariance = 0;
        demandstdev = 0;
    }//end of newsboy constructor
    public int order3()
    {// this is a private policy function for how many the newsboy will order daily
        int x;
        x = demand -1;
        ordered = x;
        return x;
    }
    private void behavior()
    {// this is the behavior of the newsboy.
        count ++;
        // recieve the papers ordered yesterday
        bought=ordered;
        //System.out.println ("nboybehavior bought "+bought+" demand "+demand + " sold " + sold);
        // calculate the papers sold
        if(demand>=bought)sold=bought;
        else
            sold=demand;
        if(bought>sold) {
            //System.out.println("Extra Paper");
            //System.out.println(bought-sold);
            int y = 0;
            y = (bought-sold);
            returned += y;
            //System.out.println(returned);
        }
        // calculate profit
        profit=((1.00*sold)-(0.35*bought))+(returned*.05);
        // order for tomorrow
        ordered=order3();
        total += sold;


        // calculate the sold variance and st dev
        soldtotal += sold;
        soldsquare += sold * sold;
        soldaverage = soldtotal/count;
        soldvariance=soldsquare/count-soldaverage*soldaverage;
        soldstdev= Math.sqrt(soldvariance);

        // calculate the demand variance and st dev
        demandtotal += demand;
        demandsquare += demand * demand;
        demandaverage = demandtotal/count;
        demandvariance=demandsquare/count-demandaverage*demandaverage;
        demandstdev= Math.sqrt(demandvariance);

        //System.out.println(" bought "+bought+" ordered "+ordered+" demand "+demand+" sold "+sold);
    }// this is the end of the behavior of the newsboy
    public void setdemand(int x)
    {// we will give the newsboy a demand and then let him behave as appropriate
        demand=x;
        //given the demand for the day, activate the behavior of the newsboy object
        behavior();
    }//end of setdemand
    //********************************Now Create the Utility functions to Interrograte the News Boy Objecct
    public double getSoldvariance() {return soldvariance; }
    public double soldStdev() {return soldstdev; }
    public double getDemandvariance() {return demandvariance; }
    public double demandStdev() {return demandstdev; }
    public int gettotalsold() {
        return total;
    }
    public int getDemandtotal(){return demandtotal; }
    public double getprofit(){return profit;}
    public int getsold(){return sold;}
    public  int  getordered(){return ordered;}
}// end of newsboy class
//**********************Now  Setup the Calculator Class*********************************
class cstats {
    private double profit;//profit for today
    private double psum;//sum of profit for all days
    private double psum2;//sum squaared of profit
    private double average;//average profit
    private double stdev;//standard deviation
    private double variance;// variance
    private int count;//
    public cstats()
    {//constructor for cstats
        profit=psum=psum2=average=stdev=variance=0; count=0;
    }
    public void setprofit(double x)
    {// this function  sets profit and calculates the stats for the day
        profit=x;
        psum+=profit;
        psum2+=profit*profit;
        count++;
        average=psum/count;
        variance=psum2/count-average*average;
        stdev=Math.sqrt(variance);
        return;
    }// end of setprofit
    //  Utility functions to return values from cstats
    public double getprofit() {return profit;}
    public double getaverage(){return average;}
    public double getvar(){return variance;}
    public double getstdev(){return stdev;}
    public int getcount(){return count;}
}// end of class cstats
class dmdproc
{//this is the process generator for the demand
    private int demand;
    public dmdproc()
    {// this is the conctructor for dmdproc
        demand=0;
    }
    public int dmdtoday()
    {//this is the process generator for the demand today
        //the demand for papers considered with percents on a daily basis is
        // 10-10%, 11-20%,12-30%, 13-10%, 14-10%, 15-20%
        // System.out.println(" x and demand"+x+"  "+demand);

        double value1 = ((1.0/12.0)*100);
        double value2 = ((2.0/12.0)*100);
        double value3 = ((7.0/12.0)*100);
        double value4 = ((9.0/12.0)*100);
        double value5 = ((11.0/12.0)*100);

        int x; // this is the random variante U(0-100)
        x=(int)(Math.random()*100);

        if(x<=value1) {
            demand = 15;
        } else {
            if(x<=value2){
                demand = 16;
            } else {
                if(x<=value3) {
                    demand = 17;
                } else {
                    if (x <= value4) {
                        demand = 18;
                    } else {
                        if (x <= value5) {
                            demand = 19;
                        } else {
                            demand = 20;
                        }
                    }
                }
            }
        }

        return demand;
    }// end of dmdtoday
}//end of class dmdproc

