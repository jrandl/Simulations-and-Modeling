/*
A simulation for the NORTHWEST LUMBER COMPANY to simulate tree growth over a 100-year span
Author: Josiah Randleman
 */

public class LumberSim {

    public static void main(String[] args){

        //Data for 1-year_2-old trees_2.
        double drought_year_1_drought[]={0.10,0.05,0.02};//fraction of one year_2 old trees_2 killed_2 by dorught
        double drought_year_1_ngrow[]={0.70,0.01,0.02};//fraction of one year_2 old trees_2 not growing by dorught
        double beetle_year_1_affected[]={0.10,0.05,0.0};//fraction of one year_2 old trees_2 affected by beetles
        double fire_year_1_vulnerable[]={0.15,.10,.05};//fraction of one year_2 old trees_2 that are vulnerable to fire
        //Data for 2-year_2-old trees_2.
        double drought_year_2_drought[]={0.10,0.05,0.03};//fraction of two year_2 old trees_2 killed_2 by drought
        double drought_year_2_ngrow[]={0.75,0.02,0.03};//fraction of two year_2 old trees_2 not growing by drought
        double beetle_year_2_affected[]={0.15,0.05,0.0};//fraction of two year_2 old trees_2 affected by beetles
        double fire_year_2_vulnerable[]={0.18,0.12,.07};//fraction of two year_2 old trees_2 that are vulnerable to fire
        //Data for 3-year_2-old trees_2.
        double drought_year_3_drought[]={0.30,0.05,0.03};//fraction of three year_2 old trees_2 killed_2 by drought
        double drought_year_3_ngrow[]={0.60,0.02,0.03};//fraction of three year_2 old trees_2 not growing by drought
        double beetle_year_3_affected[]={0.30,0.10,0.02};//fraction of three year_2 old trees_2 affected by beetles
        double fire_year_3_vulnerable[]={0.22,0.15,0.10};//fraction of three year_2 old trees_2 that are vulnerable to fire
        //Data for 4-year_2-old trees_2.
        double drought_year_4_drought[]={0.35,0.05,0.04};//fraction of four year_2 old trees_2 killed_2 by drought
        double drought_year_4_ngrow[]={0.65,0.01,0.04};//fraction of four year_2 old trees_2 not growing by drought
        double beetle_year_4_affected[]={0.30,0.10,0.02};//fraction of four year_2 old trees_2 affected by beetles
        double fire_year_4_vulnerable[]={0.30,0.20,0.15};//fraction of four year_2 old that trees_2 that are vulnerable to fire


        //*******************************Policy A Variables*****************************************************
        int year;
        // Create the trees, note that the number of trees are in 1,000,000 units
        tree onetree=new tree(2.0,0.0, drought_year_1_drought, drought_year_1_ngrow, beetle_year_1_affected, fire_year_1_vulnerable);
        tree twotree=new tree(1.5, 0.0,drought_year_2_drought, drought_year_2_ngrow, beetle_year_2_affected, fire_year_2_vulnerable);
        tree threetree=new tree(1.0,0.0, drought_year_3_drought, drought_year_3_ngrow, beetle_year_3_affected, fire_year_3_vulnerable);
        tree fourtree=new tree(0.5,0.0, drought_year_4_drought, drought_year_4_ngrow, beetle_year_4_affected, fire_year_4_vulnerable);

        double trees=0,killed = 0,grow = 0;
        double averageTrees=0,varianceTrees=0,standard_dev_Trees=0;
        double averageKilled=0,varianceKilled=0,standard_dev_Killed=0;
        double averageGrow=0,varianceGrow=0,standardGrow=0;
        double averageHarvested = 0;
        double varianceHarvestedTrees = 0;
        double standard_dev_HarvestedTrees = 0;

        // time step yearly for 100 years
        for(year=1;year<=100;year++) {
            int Rainfall = seedrain();   // Seed Rain

            //Storing the amount started with.
            double begin1 = onetree.getTree();
            double begin2 = twotree.getTree();
            double begin3 = threetree.getTree();
            double begin4 = fourtree.getTree();

            //Calculate the trees dying from rainfall.
            onetree.trees_killed_by_drought(Rainfall);
            twotree.trees_killed_by_drought(Rainfall);
            threetree.trees_killed_by_drought(Rainfall);
            fourtree.trees_killed_by_drought(Rainfall);

            //Calculate the trees dying from beetles.
            onetree.trees_killed_by_beetles(Rainfall);
            twotree.trees_killed_by_beetles(Rainfall);
            threetree.trees_killed_by_beetles(Rainfall);
            fourtree.trees_killed_by_beetles(Rainfall);

            //Calculate the trees dying by fires if there is one.
            if(Rainfall == 0) {
                onetree.trees_killed_by_fire(Rainfall);
                twotree.trees_killed_by_fire(Rainfall);
                threetree.trees_killed_by_fire(Rainfall);
                fourtree.trees_killed_by_fire(Rainfall);
            } //End of if.

            //Calculating total trees killed.
            double treeskilled1 = begin1-onetree.getTree();
            double treekilled2 = begin2-twotree.getTree();
            double treekilled3 = begin3-threetree.getTree();
            double treekilled4 = begin4-fourtree.getTree();

            //Collect statistics for number of trees in each age category.
            onetree.calcstats();
            twotree.calcstats();
            threetree.calcstats();
            fourtree.calcstats();
            //Calculating avg and variances.
            killed+=(treeskilled1+treekilled2+treekilled3+treekilled4);
            grow+=onetree.getGrow()+twotree.getGrow()+threetree.getGrow()+fourtree.getGrow();
            trees+=onetree.getTree()+twotree.getTree()+threetree.getTree()+fourtree.getTree();
            //Avg
            averageTrees=(trees/year);
            averageGrow=(grow/year);
            averageKilled=(killed/year);
            //Variance
            varianceTrees=(((trees*trees)/(year-averageTrees*averageTrees)));
            varianceGrow=(((grow*grow)/(year-averageGrow*averageGrow)));
            varianceKilled=(((killed*killed)/(year-averageKilled*averageKilled)));
            //Std Dev
            standard_dev_Trees=(Math.sqrt(varianceTrees));
            standard_dev_Killed=Math.sqrt(varianceKilled);
            standardGrow=Math.sqrt(varianceGrow);


            //Age all trees
            //Getting trees that grow!
            onetree.treeGrowth(Rainfall);
            twotree.treeGrowth(Rainfall);
            threetree.treeGrowth(Rainfall);
            fourtree.treeGrowth(Rainfall);
            //Aging the trees that grow and not aging the ones that don't
            onetree.setTree(onetree.getNotGrow());
            twotree.setTree(onetree.getGrow()+twotree.getNotGrow());
            threetree.setTree(twotree.getGrow()+threetree.getNotGrow());
            fourtree.setTree(threetree.getGrow()+fourtree.getNotGrow());

            //Harvest all 4-year-old stock.
            double harvest = fourtree.getGrow();
            double totalHarvested =0;
            totalHarvested+= harvest;

            averageHarvested = (totalHarvested / year);
            varianceHarvestedTrees = (((trees * trees) / (year - averageHarvested * averageHarvested)));
            standard_dev_HarvestedTrees=(Math.sqrt(varianceTrees));

            //Plant all vacant acres with trees.
            //Replace all harvested or killed trees.
            onetree.add_new_trees(harvest+treeskilled1+treekilled2+treekilled3+treekilled4);


        }// end of year loop
        //Calculating avg and variances.
        System.out.println("----------------------------------------------------------");
        System.out.println("Summary of Simulation with Policy A");
        System.out.println("Average trees that could grow: " + averageGrow*1000000);
        System.out.println("Average trees killed: " + averageKilled*1000000);
        System.out.println("Average number of trees: " + averageTrees*1000000);
        System.out.println("Variance Grow: " + varianceGrow*1000000);
        System.out.println("Variance Killed: " + varianceKilled*1000000);
        System.out.println("Variance Trees: " + varianceTrees*1000000);
        System.out.println("Standard Deviation Grow: " + standardGrow*1000000);
        System.out.println("Stdv Killed: " + standard_dev_Killed*1000000);
        System.out.println("Stdv Trees: " + standard_dev_Trees*1000000);
        System.out.println("Average Harvested: " + averageHarvested*1000000);
        System.out.println("Variance of Harvested Trees: " + varianceHarvestedTrees*1000000);
        System.out.println("Stand Deviation of Harvested Trees: " + standard_dev_HarvestedTrees*1000000);
        System.out.println("----------------------------------------------------------\n");

        //*******************************End of Policy A*****************************************************


        //*******************************Policy B Variables*****************************************************

        int year_2;
        // Create the tree actors, note that the number of trees_2 are in 1,000,000 units
        tree onetree_2=new tree(2.0,0.0, drought_year_1_drought, drought_year_1_ngrow, beetle_year_1_affected, fire_year_1_vulnerable);
        tree twotree_2=new tree(1.5, 0.0,drought_year_2_drought, drought_year_2_ngrow, beetle_year_2_affected, fire_year_2_vulnerable);
        tree threetree_2=new tree(1.0,0.0, drought_year_3_drought, drought_year_3_ngrow, beetle_year_3_affected, fire_year_3_vulnerable);
        tree fourtree_2=new tree(0.5,0.0, drought_year_4_drought, drought_year_4_ngrow, beetle_year_4_affected, fire_year_4_vulnerable);

        double trees_2=0,killed_2 = 0,grow_2 = 0;
        double averageTrees2_2=0,varianceTrees2_2=0,standard_dev_Trees2_2=0;
        double averageKilled2_2=0,varKilled2_2=0,standard_dev_Killed2_2=0;
        double averageGrow2_2=0,varianceGrow2_2=0,standard_dev_Grow2_2=0;
        double averageHarvested2 = 0;
        double varianceHarvestedTrees2 = 0;
        double standard_dev_HarvestedTrees2 = 0;


        // time step yearly for 100 years
        for(year_2=1;year_2<=100;year_2++) {
            int Rainfall_2 = rain();

            // determine the rain conditions for this year_2
            //amount=rain();
            //Storing the amount started with.
            double begin1_2 = onetree_2.getTree();
            double begin2_2 = twotree_2.getTree();
            double begin3_2 = threetree_2.getTree();
            double begin4_2 = fourtree_2.getTree();

            //Calculate the trees_2 dying from rainfall.
            onetree_2.trees_killed_by_drought(Rainfall_2);
            twotree_2.trees_killed_by_drought(Rainfall_2);
            threetree_2.trees_killed_by_drought(Rainfall_2);
            fourtree_2.trees_killed_by_drought(Rainfall_2);

            //Calculate the trees_2 dying from beetles.
            onetree_2.trees_killed_by_beetles(Rainfall_2);
            twotree_2.trees_killed_by_beetles(Rainfall_2);
            threetree_2.TreatedBeetlesTrees(Rainfall_2);     // Treat Half of three-year-olds
            fourtree_2.trees_killed_by_beetles(Rainfall_2);

            //Calculate the trees_2 dying by fires if there is one.
            if(Rainfall_2 == 0) {
                onetree_2.trees_killed_by_fire(Rainfall_2);
                twotree_2.trees_killed_by_fire(Rainfall_2);
                threetree_2.trees_killed_by_fire(Rainfall_2);
                fourtree_2.trees_killed_by_fire(Rainfall_2);
            } //End of if.

            //Calculating total trees_2 killed_2.
            double totalkilled1_2 = begin1_2-onetree_2.getTree();
            double totalkilled2_2 = begin2_2-twotree_2.getTree();
            double totalkilled3_2 = begin3_2-threetree_2.getTree();
            double totalkilled4_2 = begin4_2-fourtree_2.getTree();

            //Collect statistics for number of trees_2 in each age category.
            onetree_2.calcstats();
            twotree_2.calcstats();
            threetree_2.calcstats();
            fourtree_2.calcstats();

            //Calculating avg and variances.
            killed_2+=(totalkilled1_2+totalkilled2_2+totalkilled3_2+totalkilled4_2);
            grow_2+=onetree_2.getGrow()+twotree_2.getGrow()+threetree_2.getGrow()+fourtree_2.getGrow();
            trees_2+=onetree_2.getTree()+twotree_2.getTree()+threetree_2.getTree()+fourtree_2.getTree();
            //Avg
            averageTrees2_2=trees_2/year_2;
            averageGrow2_2=grow_2/year_2;
            averageKilled2_2=killed_2/year_2;
            //Variance
            varianceTrees2_2=(trees_2*trees_2)/(year_2-averageTrees2_2*averageTrees2_2);
            varianceGrow2_2=(grow_2*grow_2)/(year_2-averageGrow2_2*averageGrow2_2);
            varKilled2_2=(killed_2*killed_2)/(year_2-averageKilled2_2*averageKilled2_2);
            //Std Dev
            standard_dev_Trees2_2=Math.sqrt(varianceTrees2_2);
            standard_dev_Killed2_2=Math.sqrt(varKilled2_2);
            standard_dev_Grow2_2=Math.sqrt(varianceGrow2_2);


            //Age all trees_2; Remember that some trees_2 simply will not age because of rainfall.
            //Getting trees_2 that grow_2!
            onetree_2.treeGrowth(Rainfall_2);
            twotree_2.treeGrowth(Rainfall_2);
            threetree_2.treeGrowth(Rainfall_2);
            fourtree_2.treeGrowth(Rainfall_2);
            //Aging the trees_2 that grow_2 and not aging the ones that don't
            onetree_2.setTree(onetree_2.getNotGrow());
            twotree_2.setTree(onetree_2.getGrow()+twotree_2.getNotGrow());
            threetree_2.setTree(twotree_2.getGrow()+threetree_2.getNotGrow());
            fourtree_2.setTree(threetree_2.getGrow()+fourtree_2.getNotGrow());

            //Harvest all 4-year_2-old stock.
            double harvest_2 = fourtree_2.getGrow();
            double totalHarvested_2 =0;
            totalHarvested_2+= harvest_2;

            //Plant all vacant acres with trees_2.
            //Replace all harvested or killed_2 trees_2.
            onetree_2.add_new_trees(harvest_2+totalkilled1_2+totalkilled2_2+totalkilled3_2+totalkilled4_2);

            averageHarvested2 = (totalHarvested_2 / year_2);
            varianceHarvestedTrees2 = (((trees_2 * trees_2) / (year_2 - averageHarvested2 * averageHarvested2)));
            standard_dev_HarvestedTrees2=(Math.sqrt(varianceTrees2_2));


        }// end of year_2 loop

        System.out.println("----------------------------------------------------------");
        System.out.println("Summary of Simulation with Policy B");
        System.out.println("Average trees that could grow: " + averageGrow2_2*1000000);
        System.out.println("Average trees killed: " + averageKilled2_2 *1000000);
        System.out.println("Average number of trees: " + averageTrees2_2*1000000);
        System.out.println("Variance Grow: " + varianceGrow2_2*1000000);
        System.out.println("Variance Killed: " + varKilled2_2*1000000);
        System.out.println("Variance Trees: " + varianceTrees2_2*1000000);
        System.out.println("Standard Deviation Grow: " + standard_dev_Grow2_2*1000000);
        System.out.println("Standard Deviation Killed: " + standard_dev_Killed2_2*1000000);
        System.out.println("Standard Deviation Trees: " + standard_dev_Trees2_2*1000000);
        System.out.println("Average Harvested: " + averageHarvested2*1000000);
        System.out.println("Variance of Harvested Trees: " + varianceHarvestedTrees2*1000000);
        System.out.println("Stand Deviation of Harvested Trees: " + standard_dev_HarvestedTrees2*1000000);
        System.out.println("----------------------------------------------------------\n");

        //*******************************End of Policy B*****************************************************


        //*******************************Policy C Variables*****************************************************

        int year_3;
        // Create the tree actors, note that the number of trees_3 are in 1,000,000 units
        tree onetree_3=new tree(2.0,0.0, drought_year_1_drought, drought_year_1_ngrow, beetle_year_1_affected, fire_year_1_vulnerable);
        tree twotree_3=new tree(1.5, 0.0,drought_year_2_drought, drought_year_2_ngrow, beetle_year_2_affected, fire_year_2_vulnerable);
        tree threetree_3=new tree(1.0,0.0, drought_year_3_drought, drought_year_3_ngrow, beetle_year_3_affected, fire_year_3_vulnerable);
        tree fourtree_3=new tree(0.5,0.0, drought_year_4_drought, drought_year_4_ngrow, beetle_year_4_affected, fire_year_4_vulnerable);

        double trees_3=0,killed_3 = 0,grow_3 = 0;
        double avgerageTrees2_3=0,varianceTrees2_3=0,standard_dev_Trees2_3=0;
        double avgerageKilled2_3=0,varianceKilled2_3=0,standard_dev_Killed2_3=0;
        double avgerageGrow2_3=0,varianceGrow2_3=0,standard_dev_Grow2_3=0;
        double averageHarvested3 = 0;
        double varianceHarvestedTrees3 = 0;
        double standard_dev_HarvestedTrees3 = 0;

        // time step yearly for 100 years
        for(year_3=1;year_3<=100;year_3++) {
            int Rainfall_3 = rain();

            // determine the rain conditions for this year_3
            //amount=rain();
            //Storing the amount started with.
            double begin1_3 = onetree_3.getTree();
            double begin2_3 = twotree_3.getTree();
            double begin3_3 = threetree_3.getTree();
            double begin4_3 = fourtree_3.getTree();

            //Calculate the trees_3 dying from rainfall.
            onetree_3.trees_killed_by_drought(Rainfall_3);
            twotree_3.trees_killed_by_drought(Rainfall_3);
            threetree_3.trees_killed_by_drought(Rainfall_3);
            fourtree_3.trees_killed_by_drought(Rainfall_3);

            //Calculate the trees_3 dying from beetles.
            onetree_3.trees_killed_by_beetles(Rainfall_3);
            twotree_3.trees_killed_by_beetles(Rainfall_3);
            threetree_3.TreatHalf(Rainfall_3);     // Treat Half of three-year-olds
            fourtree_3.TreatHalf(Rainfall_3);      // Treat Half of four-year-olds

            //Calculate the trees_3 dying by fires if there is one.
            if(Rainfall_3 == 0) {
                onetree_3.trees_killed_by_fire(Rainfall_3);
                twotree_3.trees_killed_by_fire(Rainfall_3);
                threetree_3.trees_killed_by_fire(Rainfall_3);
                fourtree_3.trees_killed_by_fire(Rainfall_3);
            } //End of if.

            //Calculating total trees_3 killed_3.
            double totalkilled1_3 = begin1_3-onetree_3.getTree();
            double totalkilled2_3 = begin2_3-twotree_3.getTree();
            double totalkilled3_3 = begin3_3-threetree_3.getTree();
            double totalkilled4_3 = begin4_3-fourtree_3.getTree();

            //Collect statistics for number of trees_3 in each age category.
            onetree_3.calcstats();
            twotree_3.calcstats();
            threetree_3.calcstats();
            fourtree_3.calcstats();
            //Calculating avg and variances.
            killed_3+=(totalkilled1_3+totalkilled2_3+totalkilled3_3+totalkilled4_3);
            grow_3+=onetree_3.getGrow()+twotree_3.getGrow()+threetree_3.getGrow()+fourtree_3.getGrow();
            trees_3+=onetree_3.getTree()+twotree_3.getTree()+threetree_3.getTree()+fourtree_3.getTree();
            //Avg
            avgerageTrees2_3=trees_3/year_3;
            avgerageGrow2_3=grow_3/year_3;
            avgerageKilled2_3=killed_3/year_3;
            //Variance
            varianceTrees2_3=(trees_3*trees_3)/(year_3-avgerageTrees2_3*avgerageTrees2_3);
            varianceGrow2_3=(grow_3*grow_3)/(year_3-avgerageGrow2_3*avgerageGrow2_3);
            varianceKilled2_3=(killed_3*killed_3)/(year_3-avgerageKilled2_3*avgerageKilled2_3);
            //Std Dev
            standard_dev_Trees2_3=Math.sqrt(varianceTrees2_3);
            standard_dev_Killed2_3=Math.sqrt(varianceKilled2_3);
            standard_dev_Grow2_3=Math.sqrt(varianceGrow2_3);


            //Age all trees_3; Remember that some trees_3 simply will not age because of rainfall.
            //Getting trees_3 that grow_3!
            onetree_3.treeGrowth(Rainfall_3);
            twotree_3.treeGrowth(Rainfall_3);
            threetree_3.treeGrowth(Rainfall_3);
            fourtree_3.treeGrowth(Rainfall_3);
            //Aging the trees_3 that grow_3 and not aging the ones that don't
            onetree_3.setTree(onetree_3.getNotGrow());
            twotree_3.setTree(onetree_3.getGrow()+twotree_3.getNotGrow());
            threetree_3.setTree(twotree_3.getGrow()+threetree_3.getNotGrow());
            fourtree_3.setTree(threetree_3.getGrow()+fourtree_3.getNotGrow());

            //Harvest all 4-year_3-old stock.
            double harvest_3 = fourtree_3.getGrow();
            double totalHarvested_3 =0;
            totalHarvested_3+= harvest_3;

            //Plant all vacant acres with trees_3.
            //Replace all harvested or killed_3 trees_3.
            onetree_3.add_new_trees(harvest_3+totalkilled1_3+totalkilled2_3+totalkilled3_3+totalkilled4_3);

            averageHarvested3 = (totalHarvested_3 / year_3);
            varianceHarvestedTrees3 = (((trees_3 * trees_3) / (year_3 - averageHarvested3   * averageHarvested3)));
            standard_dev_HarvestedTrees3=(Math.sqrt(varianceTrees2_3));

        }// end of year_3 loop

        System.out.println("----------------------------------------------------------");
        System.out.println("Summary of Simulation with Policy C");
        System.out.println("Average trees that could grow: " + avgerageGrow2_3*1000000);
        System.out.println("Average trees killed: " + avgerageKilled2_3*1000000);
        System.out.println("Average number of trees: " + avgerageTrees2_3*1000000);
        System.out.println("Variance Grow: " + varianceGrow2_3*1000000);
        System.out.println("Variance Killed: " + varianceKilled2_3*1000000);
        System.out.println("Variance Trees: " + varianceTrees2_3*1000000);
        System.out.println("Standard Deviation Grow: " + standard_dev_Grow2_3*1000000);
        System.out.println("Standard Deviation Killed: " + standard_dev_Killed2_3*1000000);
        System.out.println("Standard Deviation Trees: " + standard_dev_Trees2_3*1000000);
        System.out.println("Average Harvested: " + averageHarvested3*1000000);
        System.out.println("Variance of Harvested Trees: " + varianceHarvestedTrees3*1000000);
        System.out.println("Stand Deviation of Harvested Trees: " + standard_dev_HarvestedTrees3*1000000);
        System.out.println("----------------------------------------------------------\n");

        System.out.println("Policy A average killed: " + averageKilled*1000000);
        System.out.println("Policy B average killed: " + averageKilled2_2*1000000);
        System.out.println("Policy C average killed: " + avgerageKilled2_3*1000000);




        //*******************************End of Policy C*****************************************************

    }// end of main


//******************************Random Processes***********************************************************************

    public static int rain() {// This function is a process generator for the rain with no cloud seeding.
        // 0 is drought, 1 = moderate, 2 = rain

        int x, inches, amount;
        x=(int)(Math.random()*100);
        if(x<=1) {
            inches=1;
            amount=0;
        }else if(x<=6){
            inches=2;
            amount=0;
        }else if(x<=11){
            inches=3;
            amount=0;
        }else if(x<=14){
            inches=4;
            amount=1;
        }else if(x<=24){
            inches=5;
            amount=1;
        }else if(x<=39){
            inches=6;
            amount=1;
        }else if(x<=59){
            inches=7;
            amount=1;
        }else if(x<=73){
            inches=8;
            amount=1;
        }else if(x<=83){
            inches=9;
            amount=1;
        }else if(x<=93){
            inches=10;
            amount=1;
        }else if(x<=98){
            inches=11;
            amount=2;
        } else {
            inches=12;
            amount=2;
        }

        return amount;
    }// end of rain


    public static int seedrain() {// This function is a process generator for the rain with cloud seeding.
        // 0 is drought, 1 = moderate, 2 = rain

        int x, inches, amount;
        x=(int)(Math.random()*100);
        if(x<=1) {
            inches=1;
            amount=0;
        }else if(x<=2){
            inches=2;
            amount=0;
        }else if(x<=3){
            inches=3;
            amount=0;
        }else if(x<=5){
            inches=4;
            amount=1;
        }else if(x<=15){
            inches=5;
            amount=1;
        }else if(x<=25){
            inches=6;
            amount=1;
        }else if(x<=45){
            inches=7;
            amount=1;
        }else if(x<=55){
            inches=8;
            amount=1;
        }else if(x<=65){
            inches=9;
            amount=1;
        }else if(x<=75){
            inches=10;
            amount=1;
        }else if(x<=90){
            inches=11;
            amount=2;
        } else {
            inches=12;
            amount=2;
        }

        return amount;
    }// end of seedrain

} // end lumber_runme

class tree {

    private final double[] droughtlist;  // holds the trees
    private final double[] drought_not_growing_list;  // holds the not grown tree
    private final double[] beetles_list; // holds the beetles
    private final double[] fire_list;  // holds the fire
    private double tree;// the current number of trees
    private double treated; //The current number of trees sprayed with beetle immunity for this group.
    private double grow; //The current number of trees that will grow due to rainfall amounts.
    private double notGrow; //The current number of trees that will not grow due to rainfall amounts.
    private double sumtree; //the sum of trees for this group
    private double sumtree2;//the square of the sum of trees for this group
    private double drought[]={0.0,0.0,0.0};//the percent of trees killed by drought in this group
    private double drought_not_growing[]={0.0,0.0,0.0};//the percent of trees not killed and not growing by drought in this group
    private double beetlekill[]={0.0,0.0,0.0};// the percent of trees killed by beetles in this group
    private double firekill[]={0.0,0.0,0.0};//the percent of trees killed by fire in this group


    public void calcstats() {// this calculates the sum of the trees
        sumtree+=tree;
        sumtree2 += (tree); // (tree)
    }// end of calcstats

    //**Functions for the fish Class**

    public tree(double tree, double treated, double[] rainless, double[] rainless_not_growing, double[] beetles_killed, double[] fire_killed) {
        /* Constructor for trees. This constructor
        initializes the origional number of trees
        4 (million) and then the number that will die from various
        causes */

        int i;

        // set the number of trees origionally in this group
        this.tree=tree;
        this.treated=treated;
        this.droughtlist =rainless;
        this.drought_not_growing_list =rainless_not_growing;
        this.beetles_list =beetles_killed;
        this.fire_list =fire_killed;

        // now set the percentage of survivors
        for (i=0;i<=2;i++){
            drought[i]=rainless[i];
            drought_not_growing[i]=rainless_not_growing[i];
            beetlekill[i]=beetles_killed[i];
            firekill[i]=fire_killed[i];
        }

        // now set the statistics for this yer group
        sumtree=sumtree2=0.0;
    }//end of constructor

    public void treeGrowth(int weatherType) {
        //The amount of trees that will grow.
        grow=tree-(tree* droughtlist[weatherType]);
        notGrow=tree* droughtlist[weatherType];
    }

    public void trees_killed_by_drought(int weatherType) {
        //Amount of trees dying to drought.
        tree=tree-(tree* drought_not_growing_list[weatherType]);
    }

    public void trees_killed_by_beetles(int weatherType) {
        //Amount of non-treated trees.
        double untreated = tree - (tree*treated);
        //The non-treated trees dying to beetles.
        tree=tree-(untreated* beetles_list[weatherType]);
    }

    public void TreatedBeetlesTrees(int weatherType) {
        tree=tree-(tree* beetles_list[weatherType]);

    }

    public void TreatHalf(int weatherType) {
        //Amount of all trees
        double half = tree - ((tree/2));
        //The half are treated
        tree=tree-(half* beetles_list[weatherType]);
    }

    public void trees_killed_by_fire(int weatherType) {
        //Amount of trees dying to fire.
        tree=tree-(tree* fire_list[weatherType]);
    }

    public void add_new_trees(double num) {
        tree+=num;
    }

    //**Utility Functions for Data Access
    public double getTree(){return tree;}
    public void setTree(double trees) {this.tree = tree;}
    public double getSumtree(){return sumtree;}
    public double getSumtree2(){return sumtree2;}
    public double getGrow() {return grow;}
    public double getNotGrow() {return notGrow;}

}// end of class tree
