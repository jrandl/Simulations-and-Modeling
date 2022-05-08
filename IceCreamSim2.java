/*
This is a simulator to calculate the business strategy of an Ice Cream Store.
Author: Josiah Randleman
 */

public class IceCreamSim2 {
    public static void main(String[] args) throws Exception {

        int week = 0,dm1, dm1g;
        // set the order for quarter 1
        demandANordered2 test = new demandANordered2();
        IceCream2 cone = new IceCream2();

        // set the order for quarter 2
        int dm1_2, dm1g_2;
        demandANordered2 test_2 = new demandANordered2();
        IceCream2 cone_2 = new IceCream2();

        // set the order for quarter 3
        int  dm1_3, dm1g_3;
        demandANordered2 test_3 = new demandANordered2();
        IceCream2 cone_3 = new IceCream2();
        // set the order for quarter 1
        int dm1_4, dm1g_4;
        demandANordered2 test_4 = new demandANordered2();
        IceCream2 cone_4 = new IceCream2();
        int dm2, dm2g;
        // set the order for quarter 1
        newpolicy new_test = new newpolicy();
        IceCream2 new_cone = new IceCream2();

        // set the order for quarter 2
        int new_dm1_2, new_dm1g_2;
        newpolicy new_test_2 = new newpolicy();
        IceCream2 new_cone_2 = new IceCream2();

        // set the order for quarter 3
        int  new_dm1_3, new_dm1g_3;
        newpolicy new_test_3 = new newpolicy();
        IceCream2 new_cone_3 = new IceCream2();

        // set the order for quarter 1
        int new_dm1_4, new_dm1g_4;
        newpolicy new_test_4 = new newpolicy();
        IceCream2 new_cone_4 = new IceCream2();

        // This is the time loop. One quarter is 13 weeks. There are 4 quarters in a year. In each quarter it takes
        // care of the demands for those weeks. 13*10=130. So 13 weeks is equivalent to 1 year pass for all four quarters.
        for(week=1;week<=130;week++) {
            // For quarter 1
            test.gallonsDelivered();
            dm1 = test.q1Demand(); // call to the q1 demand process generator
            dm1g = test.gallonsOrdered(1);

            test.gallonsOrdered(1);
            cone.setgallons(dm1g);
            cone.setdemand(dm1);
            cone.setSold(dm1g);
            cone.setTotalDemand();
            cone.setTotalSold();
            cone.setBase(cone.getTotalSold());
            cone.setProfit(cone.getTotalSold());
            cone.setTotalBase();
            cone.setTotalProfit();

            // For quarter 2
            test_2.gallonsDelivered();
            dm1_2 = test_2.q2Demand(); // call to the q2 demand process generator
            dm1g_2 = test_2.gallonsOrdered(2);

            test_2.gallonsOrdered(2);
            cone_2.setgallons(dm1g_2);
            cone_2.setdemand(dm1_2);
            cone_2.setSold(dm1g_2);
            cone_2.setTotalDemand();
            cone_2.setTotalSold();
            cone_2.setBase(cone_2.getTotalSold());
            cone_2.setProfit(cone_2.getTotalSold());
            cone_2.setTotalBase();
            cone_2.setTotalProfit();

            // For quarter 3
            test_3.gallonsDelivered();
            dm1_3 = test_3.q3Demand(); // call to the q3 demand process generator
            dm1g_3 = test_3.gallonsOrdered(3);

            test_3.gallonsOrdered(3);
            cone_3.setgallons(dm1g_3);
            cone_3.setdemand(dm1_3);
            cone_3.setSold(dm1g_3);
            cone_3.setTotalDemand();
            cone_3.setTotalSold();
            cone_3.setBase(cone_3.getTotalSold());
            cone_3.setProfit(cone_3.getTotalSold());
            cone_3.setTotalBase();
            cone_3.setTotalProfit();

            // For quarter 4
            test_4.gallonsDelivered();
            dm1_4 = test_4.q4Demand();  // call to the q4 demand process generator
            dm1g_4 = test_4.gallonsOrdered(4);

            test_4.gallonsOrdered(4);
            cone_4.setgallons(dm1g_4);
            cone_4.setdemand(dm1_4);
            cone_4.setSold(dm1g_4);
            cone_4.setTotalDemand();
            cone_4.setTotalSold();
            cone_4.setBase(cone_4.getTotalSold());
            cone_4.setProfit(cone_4.getTotalSold());
            cone_4.setTotalBase();
            cone_4.setTotalProfit();





            // For quarter 1
            new_test.gallonsDelivered();
            dm2 = new_test.q1Demand(); // call to the q1 demand process generator
            dm2g = new_test.gallonsOrdered(1);

            new_test.gallonsOrdered(1);
            new_cone.setgallons(dm2g);
            new_cone.setdemand(dm2);
            new_cone.setSold(dm2g);
            new_cone.setTotalDemand();
            new_cone.setTotalSold();
            new_cone.setBase(new_cone.getTotalSold());
            new_cone.setProfit(new_cone.getTotalSold());
            new_cone.setTotalBase();
            new_cone.setTotalProfit();

            // For quarter 2
            new_test_2.gallonsDelivered();
            new_dm1_2 = new_test_2.q2Demand(); // call to the q2 demand process generator
            new_dm1g_2 = new_test_2.gallonsOrdered(2);

            new_test_2.gallonsOrdered(2);
            new_cone_2.setgallons(new_dm1g_2);
            new_cone_2.setdemand(new_dm1_2);
            new_cone_2.setSold(new_dm1g_2);
            new_cone_2.setTotalDemand();
            new_cone_2.setTotalSold();
            new_cone_2.setBase(new_cone_2.getTotalSold());
            new_cone_2.setProfit(new_cone_2.getTotalSold());
            new_cone_2.setTotalBase();
            new_cone_2.setTotalProfit();

            // For quarter 3
            new_test_3.gallonsDelivered();
            new_dm1_3 = new_test_3.q3Demand(); // call to the q3 demand process generator
            new_dm1g_3 = new_test_3.gallonsOrdered(3);

            new_test_3.gallonsOrdered(3);
            new_cone_3.setgallons(new_dm1g_3);
            new_cone_3.setdemand(new_dm1_3);
            new_cone_3.setSold(new_dm1g_3);
            new_cone_3.setTotalDemand();
            new_cone_3.setTotalSold();
            new_cone_3.setBase(new_cone_3.getTotalSold());
            new_cone_3.setProfit(new_cone_3.getTotalSold());
            new_cone_3.setTotalBase();
            new_cone_3.setTotalProfit();

            // For quarter 4
            new_test_4.gallonsDelivered();
            new_dm1_4 = new_test_4.q4Demand();  // call to the q4 demand process generator
            new_dm1g_4 = new_test_4.gallonsOrdered(4);

            new_test_4.gallonsOrdered(4);
            new_cone_4.setgallons(new_dm1g_4);
            new_cone_4.setdemand(new_dm1_4);
            new_cone_4.setSold(new_dm1g_4);
            new_cone_4.setTotalDemand();
            new_cone_4.setTotalSold();
            new_cone_4.setBase(new_cone_4.getTotalSold());
            new_cone_4.setProfit(new_cone_4.getTotalSold());
            new_cone_4.setTotalBase();
            new_cone_4.setTotalProfit();
        }
        System.out.println("**************   Mother's Base Strategy   ***************");

        int cal;
        cal = (week-1);
        int cal_year = cal/10;

        // Get average and variance
        int average = (int) (cone.getTotalProfit()/cal_year);
        int variance= (int) (((cone.getTotalProfit()-average) * (cone.getTotalProfit()-average))/week);
        int stdev= (int) Math.sqrt(variance);

        // Print the results
        System.out.println("**************   Q1   ***************");
        System.out.println("Demand for Q1 is: " + cone.getTotalDemand() + " gallons");
        System.out.println("Sold for Q1 is: " + cone.getTotalSold() + " gallons");
        System.out.println("Total Gross Profit for Q1 is: $" + cone.getProfit());
        System.out.println("Base for Q1 is: $" + cone.getTotalBase());
        System.out.println("Total Net Profit for Q1 is: $" + cone.getTotalProfit());
        System.out.println("**************Statics for Q1 of Sales***************");
        System.out.println("average profit per quarter $"+ average);
        System.out.println("profit variance $"+variance+" profit st dev $"+stdev);
        System.out.println("\n");


        System.out.println("**************   Q2   ***************");
        // Get average and variance
        int average2 = (int) (cone_2.getTotalProfit()/cal_year);
        int variance2= (int) (((cone_2.getTotalProfit()-average2) * (cone_2.getTotalProfit()-average2))/week);
        int stdev2= (int) Math.sqrt(variance2);

        // Print the results
        System.out.println("Demand for Q2 is: " + cone_2.getTotalDemand() + " gallons");
        System.out.println("Sold for Q2 is: " + cone_2.getTotalSold() + " gallons");
        System.out.println("Total Gross Profit for Q2 is: $" + cone_2.getProfit());
        System.out.println("Base for Q2 is: $" + cone_2.getTotalBase());
        System.out.println("Total Net Profit for Q2 is: $" + cone_2.getTotalProfit());
        System.out.println("**************Statics for Q2 of Sales***************");
        System.out.println("average profit per quarter $"+ average2);
        System.out.println("profit variance $"+variance2+" profit st dev $"+stdev2);
        System.out.println("\n");


        System.out.println("**************   Q3   ***************");
        // Get average and variance
        int average3 = (int) (cone_3.getTotalProfit()/cal_year);
        int variance3= (int) (((cone_4.getTotalProfit()-average3) * (cone_4.getTotalProfit()-average3))/week);
        int stdev3= (int) Math.sqrt(variance3);

        // Print the results
        System.out.println("Demand for Q3 is: " + cone_3.getTotalDemand() + " gallons");
        System.out.println("Sold for Q3 is: " + cone_3.getTotalSold() + " gallons");
        System.out.println("Total Gross Profit for Q3 is: $" + cone_3.getProfit());
        System.out.println("Base for Q3 is: $" + cone_3.getTotalBase());
        System.out.println("Total Net Profit for Q3 is: $" + cone_3.getTotalProfit());
        System.out.println("**************Statics for Q3 of Sales***************");
        System.out.println("average profit per quarter $" + average3);
        System.out.println("profit variance $" + variance3 + " profit st dev $" + stdev3);
        System.out.println("\n");

        System.out.println("**************   Q4   ***************");
        // Get average and variance
        int average4 = (int) (cone_4.getTotalProfit()/cal_year);
        int variance4= (int) (((cone_4.getTotalProfit()-average4) * (cone_4.getTotalProfit()-average4))/week);
        int stdev4= (int) Math.sqrt(variance4);

        // Print the results
        System.out.println("Demand for Q4 is: " + cone_4.getTotalDemand() + " gallons");
        System.out.println("Sold for Q4 is: " + cone_4.getTotalSold() + " gallons");
        System.out.println("Total Gross Profit for Q4 is: $" + cone_4.getProfit());
        System.out.println("Base for Q4 is: $" + cone_4.getTotalBase());
        System.out.println("Total Net Profit for Q4 is: $" + cone_4.getTotalProfit());
        System.out.println("**************Statics for Q4 of Sales***************");
        System.out.println("average profit per quarter $" + average4);
        System.out.println("profit variance $" + cone_4.getProfitVariance() + " profit st dev $" + cone_4.getProfitStdev());
        System.out.println("\n");


        // Get average and variance
        int averageY = ((int) (cone.getTotalProfit() + cone_2.getTotalProfit() + cone_3.getTotalProfit() + cone_4.getTotalProfit())/cal_year);
        int varianceY = (int) (((cone.getTotalProfit()-averageY) * (cone.getTotalProfit()-averageY)) + ((cone_2.getTotalProfit() -averageY) * (cone_2.getTotalProfit()-averageY)) + ((cone_3.getTotalProfit()-averageY) * (cone_3.getTotalProfit()-averageY)) + ((cone_4.getTotalProfit() -averageY) * (cone_4.getTotalProfit()-averageY)))/week ;
        int stdevY= (int) Math.sqrt(varianceY);

        // Print the results
        System.out.println("**************   End of Calculations   ***************");
        System.out.println("All Time Profit For Mother’s Ice Cream Parlor") ;
        System.out.println(cone.getTotalProfit() + cone_2.getTotalProfit() + cone_3.getTotalProfit() + cone_4.getTotalProfit());
        System.out.println("Average Profit Per Year $" + averageY);
        System.out.println("profit variance $" + varianceY + " profit st dev $" + stdevY);
        System.out.println("profit variance of test $" + varianceY + " profit st dev $" + stdevY);
        //System.out.println(psumY);
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("**************************  New Strategy   *************************************");


        int new_cal;
        new_cal = (week-1);
        int new_cal_year = new_cal/10;

        // Get average and variance
        int new_average = (int) (new_cone.getTotalProfit()/new_cal_year);
        int new_variance= (int) (((new_cone.getTotalProfit()-new_average) * (new_cone.getTotalProfit()-new_average))/week);
        int new_stdev = (int) Math.sqrt(new_variance);

        // Print the results
        System.out.println("**************   Q1   ***************");
        System.out.println("Demand for Q1 is: " + new_cone.getTotalDemand() + " gallons");
        System.out.println("Sold for Q1 is: " + new_cone.getTotalSold() + " gallons");
        System.out.println("Total Gross Profit for Q1 is: $" + new_cone.getProfit());
        System.out.println("Base for Q1 is: $" + new_cone.getTotalBase());
        System.out.println("Total Net Profit for Q1 is: $" + new_cone.getTotalProfit());
        System.out.println("**************Statics for Q1 of Sales***************");
        System.out.println("average profit per quarter $"+ new_average);
        System.out.println("profit variance $"+new_variance+" profit st dev $"+ new_stdev);
        System.out.println("\n");


        System.out.println("**************   Q2   ***************");
        // Get average and variance
        int new_average2 = (int) (new_cone_2.getTotalProfit()/new_cal_year);
        int new_variance2= (int) (((new_cone_2.getTotalProfit()-new_average2) * (new_cone_2.getTotalProfit()-new_average2))/week);
        int new_stdev2= (int) Math.sqrt(new_variance2);

        // Print the results
        System.out.println("Demand for Q2 is: " + new_cone_2.getTotalDemand() + " gallons");
        System.out.println("Sold for Q2 is: " + new_cone_2.getTotalSold() + " gallons");
        System.out.println("Total Gross Profit for Q2 is: $" + new_cone_2.getProfit());
        System.out.println("Base for Q2 is: $" + new_cone_2.getTotalBase());
        System.out.println("Total Net Profit for Q2 is: $" + new_cone_2.getTotalProfit());
        System.out.println("**************Statics for Q2 of Sales***************");
        System.out.println("average profit per quarter $"+ new_average2);
        System.out.println("profit variance $"+new_variance2+" profit st dev $"+new_stdev2);
        System.out.println("\n");


        System.out.println("**************   Q3   ***************");
        // Get average and variance
        int new_average3 = (int) (new_cone_3.getTotalProfit()/new_cal_year);
        int new_variance3= (int) (((new_cone_4.getTotalProfit()-new_average3) * (new_cone_4.getTotalProfit()-new_average3))/week);
        int new_stdev3= (int) Math.sqrt(new_variance3);

        // Print the results
        System.out.println("Demand for Q3 is: " + new_cone_3.getTotalDemand() + " gallons");
        System.out.println("Sold for Q3 is: " + new_cone_3.getTotalSold() + " gallons");
        System.out.println("Total Gross Profit for Q3 is: $" + new_cone_3.getProfit());
        System.out.println("Base for Q3 is: $" + new_cone_3.getTotalBase());
        System.out.println("Total Net Profit for Q3 is: $" + new_cone_3.getTotalProfit());
        System.out.println("**************Statics for Q3 of Sales***************");
        System.out.println("average profit per quarter $" + new_average3);
        System.out.println("profit variance $" + new_variance3 + " profit st dev $" + new_stdev3);
        System.out.println("\n");

        System.out.println("**************   Q4   ***************");
        // Get average and variance
        int new_average4 = (int) (new_cone_4.getTotalProfit()/new_cal_year);
        int new_variance4= (int) (((new_cone_4.getTotalProfit()-new_average4) * (new_cone_4.getTotalProfit()-new_average4))/week);
        int new_stdev4= (int) Math.sqrt(new_variance4);

        // Print the results
        System.out.println("Demand for Q4 is: " + new_cone_4.getTotalDemand() + " gallons");
        System.out.println("Sold for Q4 is: " + new_cone_4.getTotalSold() + " gallons");
        System.out.println("Total Gross Profit for Q4 is: $" + new_cone_4.getProfit());
        System.out.println("Base for Q4 is: $" + new_cone_4.getTotalBase());
        System.out.println("Total Net Profit for Q4 is: $" + new_cone_4.getTotalProfit());
        System.out.println("**************Statics for Q4 of Sales***************");
        System.out.println("average profit per quarter $" + new_average4);
        System.out.println("profit variance $" + new_cone_4.getProfitVariance() + " profit st dev $" + new_cone_4.getProfitStdev());
        System.out.println("\n");
        // Print the results
        int new_averageY = ((int) (new_cone.getTotalProfit() + new_cone_2.getTotalProfit() + new_cone_3.getTotalProfit() + new_cone_4.getTotalProfit())/new_cal_year);
        int new_varianceY = (int) (((new_cone.getTotalProfit()-new_averageY) * (new_cone.getTotalProfit()-new_averageY)) + ((new_cone_2.getTotalProfit() -new_averageY) *
                (new_cone_2.getTotalProfit()-new_averageY)) + ((new_cone_3.getTotalProfit()-new_averageY) * (new_cone_3.getTotalProfit()-new_averageY)) +
                ((new_cone_4.getTotalProfit() -new_averageY) * (new_cone_4.getTotalProfit()-new_averageY)))/week ;
        int new_stdevY= (int) Math.sqrt(new_varianceY);
        System.out.println("**************   End of Calculations   ***************");
        System.out.println("All Time Profit For New Strategy") ;
        System.out.println(new_cone.getTotalProfit() + new_cone_2.getTotalProfit() + new_cone_3.getTotalProfit() + new_cone_4.getTotalProfit());
        System.out.println("Average Profit Per Year $" + new_averageY);
        System.out.println("profit variance $" + new_varianceY + " profit st dev $" + new_stdevY);
        //System.out.println("profit variance of test $" + new_varianceY + " profit st dev $" + new_stdevY);

        System.out.println("**************************************************************************");
        // Get average and variance
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("**************   End of Calculations   ***************");
        System.out.println("All Time Profit For Mother’s Ice Cream Parlor") ;
        System.out.println(cone.getTotalProfit() + cone_2.getTotalProfit() + cone_3.getTotalProfit() + cone_4.getTotalProfit());
        System.out.println("Average Profit Per Year $" + averageY);
        System.out.println("profit variance $" + varianceY + " profit st dev $" + stdevY);
        System.out.println("profit variance of test $" + varianceY + " profit st dev $" + stdevY);

        //System.out.println(psumY);

        System.out.println("\n");
        // Print the results
        System.out.println("**************   End of Calculations   ***************");
        System.out.println("All Time Profit For New Strategy") ;
        System.out.println(new_cone.getTotalProfit() + new_cone_2.getTotalProfit() + new_cone_3.getTotalProfit() + new_cone_4.getTotalProfit());
        System.out.println("Average Profit Per Year $" + new_averageY);
        System.out.println("profit variance $" + new_varianceY + " profit st dev $" + new_stdevY);
        System.out.println("\n");
        System.out.println("All Time Profit Increase For New Strategy") ;
        System.out.println((new_cone.getTotalProfit() + new_cone_2.getTotalProfit() + new_cone_3.getTotalProfit() + new_cone_4.getTotalProfit()) - (cone.getTotalProfit() + cone_2.getTotalProfit() + cone_3.getTotalProfit() + cone_4.getTotalProfit()));
        //System.out.println("profit variance of test $" + new_varianceY + " profit st dev $" + new_stdevY);
        System.out.println("**************************************************************************");
        //System.out.println(psumY);
    }  // end of main
}  // end of IceCreamSim

// This handles the demand and the orders for the business
class demandANordered2 {
    private double demand;
    private double sold;
    private double error;
    private double ordered;
    private double gallons;
    private double holder;

    public demandANordered2()
    {// this is the constructor for demand
        demand=0;
        error=0;
    }// end of demandANordered

    // This handles the gallons ordered for each quarter
    public int gallonsOrdered(int x) {
        if(x==1) {
            gallons=50;
        }else if(x==2){
            gallons=60;
        }else if(x==3){
            gallons=60;
        } else {
            gallons=50;
        }
        return (int) gallons;
    } // end of gallonsOrdered

    // This is the error of frequency generator
    // This adds into account human error
    public int gallonsDelivered() {
        int x;
        x=(int)(Math.random()*100);
        if(x<=10) { // 5 less than ordered
            error = -5;
        }
        else if(x<=30){ // 10 gallons more
            error = 10;
        } else {  // if no error set error to zero
            error = 0;
        }
        return (int) error;
        // End of getting the frequency of occurrence
    } // end of gallonsDelivered

    //this is the process generator for the demand in quarter 1
    public int q1Demand()
    {
        int y;
        y=(int)(Math.random()*100);

        if(y<=30) {
            demand = 40;
        } else if(y<=50){
            demand = 50;
        } else if(y<=80){
            demand = 60;
        } else if(y<=90){
            demand = 70;
        } else {
            demand = 80;
        }
        holder = (int) ((int) demand + error);
        return (int) holder;
    }// end of q1

    //this is the process generator for the demand in quarter 2
    public int q2Demand()
    {
        int y;
        y=(int)(Math.random()*100);
        if(y<=15) {
            demand = 40;
        } else if(y<=55){
            demand = 50;
        } else if(y<=80){
            demand = 60;
        } else if(y<=90){
            demand = 70;
        } else {
            demand = 80;
        }
        holder = (int) ((int) demand + error);
        return (int) holder;
    }// end of q2

    //this is the process generator for the demand in quarter 3
    public int q3Demand()
    {
        int y;
        y=(int)(Math.random()*100);
        if(y<=5) {
            demand = 40;
        } else if(y<=15){
            demand = 50;
        } else if(y<=45){
            demand = 60;
        } else if(y<=85){
            demand = 70;
        } else {
            demand = 80;
        }
        holder = (int) ((int) demand + error);
        return (int) holder;
    }// end of q3

    //this is the process generator for the demand in quarter 4
    public int q4Demand()
    {
        int y;
        y=(int)(Math.random()*100);
        if(y<=40) {
            demand = 40;
        } else if(y<=80){
            demand = 50;
        } else if(y<=90){
            demand = 60;
        } else if(y<=95){
            demand = 70;
        } else {
            demand = 80;
        }
        holder = (int) ((int) demand + error);
        return (int) holder;
    }// end of q4
} // end of demandANordered


class newpolicy {
    private double demand;
    private double sold;
    private double error;
    private double ordered;
    private double gallons;
    private double holder;

    public newpolicy()
    {// this is the constructor for demand
        demand=0;
        error=0;
        gallons = 50;
    }// end of demandANordered

    // This is the error of frequency generator
    // This adds into account human error
    public int gallonsDelivered() {
        int x;
        x=(int)(Math.random()*100);
        if(x<=10) { // 5 less than ordered
            error = -5;
        }
        else if(x<=30){ // 10 gallons more
            error = 10;
        } else {  // if no error set error to zero
            error = 0;
        }
        return (int) error;
        // End of getting the frequency of occurrence
    } // end of gallonsDelivered

    // This handles the gallons ordered for each quarter
    // x will hold the quarter that is selected. So 1 will be for quarter 1.
    public int gallonsOrdered(int x) {
        // For quarter 1
        if(x==1) {
            if(demand > gallons) {
                gallons=demand;
            } else {
                gallons= demand + 15;
            }
            // For quarter 2
        }else if(x==2){
            if(demand > gallons) {
                gallons=demand;
            } else {
                gallons= demand + 15;
            }
            // For quarter 3
        }else if(x==3){
            if(demand > gallons) {
                gallons=demand;
            } else {
                gallons= demand + 15;
            }
            // For quarter 4
        } else {
            if(demand > gallons) {
                gallons=demand;
            } else {
                gallons= demand + 15;
            }
        }
        return (int) gallons;
    } // end of gallonsOrdered

    //this is the process generator for the demand in quarter 1
    public int q1Demand()
    {
        int y;
        y=(int)(Math.random()*100);

        if(y<=30) {
            demand = 40;
        } else if(y<=50){
            demand = 50;
        } else if(y<=80){
            demand = 60;
        } else if(y<=90){
            demand = 70;
        } else {
            demand = 80;
        }
        holder = (int) ((int) demand + error);
        return (int) holder;
    }// end of q1

    //this is the process generator for the demand in quarter 2
    public int q2Demand()
    {
        int y;
        y=(int)(Math.random()*100);
        if(y<=15) {
            demand = 40;
        } else if(y<=55){
            demand = 50;
        } else if(y<=80){
            demand = 60;
        } else if(y<=90){
            demand = 70;
        } else {
            demand = 80;
        }
        holder = (int) ((int) demand + error);
        return (int) holder;
    }// end of q2

    //this is the process generator for the demand in quarter 3
    public int q3Demand()
    {
        int y;
        y=(int)(Math.random()*100);
        if(y<=5) {
            demand = 40;
        } else if(y<=15){
            demand = 50;
        } else if(y<=45){
            demand = 60;
        } else if(y<=85){
            demand = 70;
        } else {
            demand = 80;
        }
        holder = (int) ((int) demand + error);
        return (int) holder;
    }// end of q3

    //this is the process generator for the demand in quarter 4
    public int q4Demand()
    {
        int y;
        y=(int)(Math.random()*100);
        if(y<=40) {
            demand = 40;
        } else if(y<=80){
            demand = 50;
        } else if(y<=90){
            demand = 60;
        } else if(y<=95){
            demand = 70;
        } else {
            demand = 80;
        }
        holder = (int) ((int) demand + error);
        return (int) holder;
    }// end of q4
} // end of demandANordered


class IceCream2 {
    private double base = 7.00;
    private double profit = 10.00;
    private double demand;
    private double totalProfit;
    private double totalDemand;
    private double totalBase;
    private double totalSold;
    private double sold;
    private double gallons;

    private int ordered;// this is the amount ordered for today
    private int bought;//this is the amount bought for today
    private int returned;// this is the returned for the day
    private int total; // average sold
    private int profittotoal; // demand total
    private double soldtotal; // sold total
    private double count;  //count
    private double soldsquare; // sold squared
    private double soldaverage; // sold average
    private double soldvariance; // variance of sold
    private double soldstdev; // st dev of sold
    private double profitsquared; // demand squared
    private double profitaverage;  // demand average
    private double profitvariance; // demand variance
    private double profitstdev; // demand st dev
    public IceCream2() {
        demand=0;
        ordered=10;
        bought=0;
        sold=0;
        profit=0.0;
        returned = 0;
        total = 0;
        profittotoal = 0;
        soldtotal = 0;
        count = 0;
        profitsquared = 0;
        profitaverage = 0;
        profitvariance = 0;
        profitstdev = 0;
    }

    private void behavior()
    {// this is the behavior of the newsboy.
        count ++;
        // calculate the demand variance and st dev
        profittotoal += totalProfit;
        profitsquared += totalProfit * totalProfit;
        profitaverage = profittotoal /count;
        profitvariance = profitsquared /count- profitaverage * profitaverage;
        profitstdev = Math.sqrt(profitvariance);

        //System.out.println(" bought "+bought+" ordered "+ordered+" demand "+demand+" sold "+sold);
    }// this is the end of the behavior of the newsboy

    public double getProfitVariance() {return profitvariance; }
    public double getProfitStdev() {return profitstdev; }

    // This sets the demand
    public void setdemand(int x)
    {
        demand=x;
    }//end of setdemand

    // This sets the demand for gallons
    public void setgallons(int x)
    {
        gallons=x;
    }//end of setgallons

    // This sets the demand for sold
    public void setSold(int x)
    {
        sold=x;
    }//end of setSold

    // This gets the profit
    public double getProfit() {
        return profit;
    } // end of profit

    // the sets the profit
    public void setProfit(double x) {
        profit = (x*10.00);
    } // end of setProfit

    // This sets the base
    public void setBase(double x) {
        base = (x*7.00);
    } // end of setBase

    // This sets the total demanded
    public void setTotalDemand() {
        totalDemand += demand;
    } // end of setTotalDemand

    // This sets the total base
    public void setTotalBase() {
        totalBase = base;
    } // end of setTotalBase

    // This gets the total base
    public double getTotalBase(){
        return totalBase;
    } // end of getTotalBase

    // This gets the total demand
    public double getTotalDemand(){
        return totalDemand;
    } // end of getTotalDemand

    // This sets the total profit
    public void setTotalProfit() {
        totalProfit = profit - totalBase;
        behavior();
    }  // end of setTotalProfit

    // This gets the total profit
    public double getTotalProfit(){
        return totalProfit;
    } // end of getTotalProfit

    // This gets the total sold
    public double getTotalSold(){
        return totalSold;
    } // end of getTotalSold

    // This sets the total sold
    public void setTotalSold() {
        if(gallons>demand) {
            totalSold+=demand;
        } else {
            totalSold+=sold;
        }
    } // end of setTotalSold
} // end of IceCream
