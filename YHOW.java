/*
This is a simulation to test a drug for a company.
Author: Josiah Randleman
 */

public class YHOW
{
    public static void main(String[] args)throws Exception
    {
        Cell[][] MyCells=new Cell[10][10];// The MyCells array holds the Cell objects
        int tabint=1;//this is the color table iterator count
        int [][] MyHealth=new int [10][10];//this is the cell table holding the colors for each Chameleon entry

        int [] ccount=new int [10];
        int i,j, person = 1,year2 = 1, maxtab=10;//maxtab is the maximum number of times we will interate the table
        //double maxper=0.6;//maxper is the maximun percentage of the table which must be of one color before stopping

        int sumhealthy = 0;
        int sum2healthy = 0;
        double meanhealthy;
        double variancehealthy;

        int sumhealthy2 = 0;
        int sum2healthy2 = 0;
        double meanhealthy2;
        double variancehealthy2;

        for (i=0;i<=9;i++)
            for(j=0;j<=9;j++)
            {// create all chameleons in the MyCells and fill the MyHealth with their colors
                MyCells[i][j]= new Cell(i,j,10,10);
                MyHealth[i][j]=MyCells[i][j].getcondition2();
            }

        //now print the colors table
        printtableStart(MyHealth,tabint,9,9);
        counttable(MyHealth,ccount);
        printccount(ccount);

        for (i=0;i<=9;i++)
            for(j=0;j<=9;j++)
            {
                MyCells[i][j]= new Cell(i,j,10,10);
                MyHealth[i][j]=MyCells[i][j].getcondition();
            }
        //now print the colors table
        printtableSeed(MyHealth,tabint,9,9);
        counttable(MyHealth,ccount);
        printccount(ccount);

        while(tabint<=10)
        {for (i=0;i<=9;i++)
            for(j=0;j<=9;j++)
            {// update the colors for all chameleons
                MyCells[i][j].cellTendril(MyHealth, i, j);

            }
            for (i=0;i<=9;i++)
                for(j=0;j<=9;j++)
                {//now we can update the MyHealth and print it
                    MyHealth[i][j]=MyCells[i][j].getcondition();
                }
            if (tabint == 10){
                printtable(MyHealth,tabint,9,9);
                counttable(MyHealth,ccount);
                printccount(ccount);
            }

            tabint++;
        }//end of table interation

        System.out.println("\n\n\n");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("\n\n\n");

        // for the 10 individuals
        while(person <= 10){
            Cell[][] MyCells2=new Cell[10][10];// The MyCells array holds the Cell objects
            int holder = 1;//this is the color table iterator count
            int [][] MyHealth2=new int [10][10];//this is the cell table holding the colors for each Chameleon entry
            int [] ccount2=new int [10];
            int myi, myj;

            for (myi=0;myi<=9;myi++)
                for(myj=0;myj<=9;myj++)
                {// create all chameleons in the MyCells and fill the MyHealth with their colors
                    MyCells2[myi][myj]= new Cell(myi,myj,10,10);
                    MyHealth2[myi][myj]=MyCells2[myi][myj].getcondition();
                }

            while(holder<=10) {
                for (myi = 0; myi <= 9; myi++) {
                    for (myj = 0; myj <= 9; myj++) {// update the colors for all chameleons
                        MyCells2[myi][myj].cellTendril(MyHealth2, myi, myj);
                    }
                }

                for (myi = 0; myi <= 9; myi++) {
                    for (myj = 0; myj <= 9; myj++) {//now we can update the MyHealth and print it
                        MyHealth2[myi][myj] = MyCells2[myi][myj].getcondition();
                    }
                }

                if (holder == 10) {
                    printtablePerson(MyHealth2, holder, person, 9, 9);
                    counttable(MyHealth2, ccount2);
                    printccount(ccount2);
                    sumhealthy += ccount2[2];
                    sum2healthy += (ccount2[2]) * (ccount2[2]);
                }

                holder++;
            }//end of table interation
            person++;
        }

        meanhealthy = sumhealthy / 10.0;
        variancehealthy = sum2healthy / 10.0 - meanhealthy * meanhealthy;

        System.out.println("\nInfo:");
        System.out.println("\n The Mean number of muscle cells in a 10 year period for 10 males starting at age 50 that are in the healthy muscle cells (HM): " + meanhealthy);
        System.out.println("\n The Variance of muscle cells in a 10 year period for 10 males starting at age 50 that are in the healthy muscle cells (HM): " + variancehealthy);


        // for the 10 individuals for YOUTH CELLS
        while(year2 <= 10){
            Cell[][] MyCells3=new Cell[10][10];// The MyCells array holds the Cell objects
            int holder2 = 1;//this is the color table iterator count
            int [][] MyHealth3=new int [10][10];//this is the cell table holding the colors for each Chameleon entry
            int [] ccount3=new int [10];
            int myi, myj;

            for (myi=0;myi<=9;myi++)
                for(myj=0;myj<=9;myj++)
                {// create all chameleons in the MyCells and fill the MyHealth with their colors
                    MyCells3[myi][myj]= new Cell(myi,myj,10,10);
                    MyHealth3[myi][myj]=MyCells3[myi][myj].getcondition3();
                }

            while(holder2<=10) {
                for (myi = 0; myi <= 9; myi++) {
                    for (myj = 0; myj <= 9; myj++) {// update the colors for all chameleons
                        if(year2 == 1){
                            MyCells3[myi][myj].cellYouth(MyHealth3, myi, myj, 0);
                        } else {
                            MyCells3[myi][myj].cellYouth(MyHealth3, myi, myj, 1);
                        }
                    }
                }

                for (myi = 0; myi <= 9; myi++) {
                    for (myj = 0; myj <= 9; myj++) {//now we can update the MyHealth and print it
                        MyHealth3[myi][myj] = MyCells3[myi][myj].getcondition3();
                    }
                }
                if (holder2 == 10) {
                    printtablePersonYouth(MyHealth3, holder2, year2, 9, 9);
                    counttable(MyHealth3, ccount3);
                    printccount(ccount3);
                    sumhealthy2 += ccount3[2]+ccount3[3];
                    sum2healthy2 += (ccount3[2]+ccount3[3]) * (ccount3[2]+ccount3[3]);
                }

                holder2++;
            }//end of table interation
            year2++;
        }
        meanhealthy2 = sumhealthy2 / 10.0;
        variancehealthy2 = sum2healthy2 / 10.0 - meanhealthy2 * meanhealthy2;

        System.out.println("\nInfo:");
        System.out.println("\n The Mean number of muscle cells in a 10 year period for 10 males starting at age 50 that are in the healthy muscle cells (HM): " + meanhealthy2);
        System.out.println("\n The Variance of muscle cells in a 10 year period for 10 males starting at age 50 that are in the healthy muscle cells (HM): " + variancehealthy2);

    }// this is the end of main for now
    //functions go here
    public static void printtableStart(int [][]colortable,int tprint,int imax, int jmax)
    {//this function prints the colortable.  It is the table representing the current color of each Chameleon object\
        System.out.println("\n");
        System.out.println("    This is the Simulated Muscle Grid of Healthy Cells");
        // Print table header
        System.out.println("C1    C2    C3    C4    C5    C6    C7    C8    C9    C10");
        for(int i=0;i<=imax;i++)
        {
            for(int j=0;j<=jmax;j++)Cname(colortable[i][j]);
            //now skip a line
            System.out.println();
        }// next row of colors
    }//end of printtable

    public static void printtableSeed(int [][]colortable,int tprint,int imax, int jmax)
    {//this function prints the colortable.  It is the table representing the current color of each Chameleon object\
        System.out.println("\n");
        System.out.println("    This is the Simulated Muscle Grid Seeded with 5% of DMD Cells");
        // Print table header
        System.out.println("C1    C2    C3    C4    C5    C6    C7    C8    C9    C10");
        for(int i=0;i<=imax;i++)
        {
            for(int j=0;j<=jmax;j++)Cname(colortable[i][j]);
            //now skip a line
            System.out.println();
        }// next row of colors
    }//end of printtable

    public static void printtable(int [][]MyHealth,int tprint,int imax, int jmax)
    {//this function prints the MyHealth.  It is the table representing the current color of each Chameleon object\
        System.out.println("\n");
        System.out.println("        This is the "+tprint+" Interaction of the Cell Table For The Individual Male");
        // Print table header
        System.out.println("C1    C2    C3    C4    C5    C6    C7    C8    C9    C10");
        for(int i=0;i<=imax;i++) {
            for(int j=0;j<=jmax;j++) {
                Cname(MyHealth[i][j]);
            }
            System.out.println();
        }// next row of colors
    }//end of printtable

    public static void printtablePerson(int [][]MyHealth,int tprint,int person, int imax, int jmax)
    {//this function prints the MyHealth.  It is the table representing the current color of each Chameleon object\
        System.out.println("\n");
        System.out.println("           This is the "+tprint+" Interaction of the Cell Table for Person " + person);
        // Print table header
        System.out.println("C1    C2    C3    C4    C5    C6    C7    C8    C9    C10");
        for(int i=0;i<=imax;i++) {
            for(int j=0;j<=jmax;j++) {
                Cname(MyHealth[i][j]);
            }
            System.out.println();
        }// next row of colors
    }//end of printtable

    public static void printtablePersonYouth(int [][]MyHealth,int tprint,int person, int imax, int jmax)
    {//this function prints the MyHealth.  It is the table representing the current color of each Chameleon object\
        System.out.println("\n");
        System.out.println("    This is the "+tprint+" Interaction of the Youth Injection Cell Table for Person " + person);
        // Print table header
        System.out.println("C1    C2    C3    C4    C5    C6    C7    C8    C9    C10");
        for(int i=0;i<=imax;i++) {
            for(int j=0;j<=jmax;j++) {
                Cname(MyHealth[i][j]);
            }
            System.out.println();
        }// next row of colors
    }//end of printtable

    public static void Cname (int cnum)
    {// this prints the name of the colors
        if(cnum==1) {
            System.out.print("MW    ");
        }
        else if(cnum==2) {
            System.out.print("HM    ");
        } else if(cnum==3) {
            System.out.print("HM    ");
        }
        return;
    }//end of Cname
    public static void printccount(int []ccount)
    {//this function prints the color count
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("           MW     HM   ");
        System.out.print("\nTotal:");
        for(int i=1;i<=3;i++) {
            if (i == 1) {
                System.out.print("     " + ccount[i]);
            } if (i == 2) {
                System.out.print("     " + (ccount[2] + ccount[3]));
            }
        }
        System.out.println("\n-----------------------------------------------------------------------------");
    }//end of printcount
    public static void counttable(int [][]MyHealth,int []ccount)
    {//this function updates ccount by counting the colors in the table.
        for(int i=0;i<=9;i++)ccount[i]=0;
        for(int irow=0;irow<=9;irow++)
            for(int jcol=0;jcol<=9;jcol++)
            {  ccount[MyHealth[irow][jcol]]++;}
    }//end of counttable
} //this is the end of Agentbasedexample


class Cell {
    // This is the Cell class
    protected int myi;// this is the i location of me in the illusion
    protected int myj;// this is the j location of me in the illusion
    protected int lastchange;// this is the interation since my last change
    protected int mycycle;//chameleons can change under three cycles in 1 cycle, 2 cycle or 3 cycle
    protected int nrows;// rows of the illusion go from 0 to nrows
    protected int ncols;// cols of the illusion go from 0 to ncols
    protected int tdir; // tendril direction if DMD  1=up, 2=down, 3=back, 4=front
    protected int ccond;
    protected int ccond2;
    protected int ccond3;
    int runs;
    int c;
    int b;


    public Cell(int i, int j, int n, int m)
    {// this is the constructor for a Chameleon object it sets my location and the origional
        myi=i;
        myj=j;
        nrows=n-1;//rows in the illusion for later checks
        ncols=m-1;//columns in the illusion for late checks
        ccond = cellChange();
        ccond3 = cellChange();
        ccond2 = 3;
        lastchange=1;
        tdir = 1;
        runs = 1;
        b = 1;

    }//End of constructor Chameleon
    int getcondition() {return ccond;};
    int getcondition2() {return ccond2;};
    int getcondition3() {return ccond3;};

    public int cellChange()
    {//this is the process generator for the cell change
        int x; // this is the random variante U(0-100)
        int health;
        x=(int)(Math.random()*100);
        if (x<=5)health=1; // 5%
        else
            health=2;
        return health;
    }// end of function cellChange

    public void cellYouth(int[][] MyHealth, int myi, int myj, int value) {
        int x;
        int trajectory;
        x = (int) (Math.random() * 100);

        if (x <= 15) {
            trajectory = 1; // going up
        } else if (x <= 30) {
            trajectory = 2; // going down
        } else if (x <= 65) {
            trajectory = 3; // going left
        } else {
            trajectory = 4; // going right
        } // end of else

        if ((myi == 0) && (myj == 0)) {
            // in the upper left corner
            if (trajectory == 1) { // up
                ccond3 = MyHealth[0][0];
            } else if (trajectory == 2) { // down
                if (MyHealth[0][0] == 1) {
                    MyHealth[1][0] = 1;
                    ccond3 = MyHealth[1][0];
                } // end of if
            } else if (trajectory == 3) { // left
                ccond3 = MyHealth[0][0];
            } else { // right
                if (MyHealth[0][0] == 1) {
                    MyHealth[0][1] = 1;
                    ccond3 = MyHealth[1][0];
                } // end of if
            } // end of else
        } else if ((myi == 0) && (myj == ncols - 1)) {
            // in the upper right corner
            if (trajectory == 1) { // up
                ccond3 = MyHealth[0][ncols-1];
            } else if (trajectory == 2) { // down
                if (MyHealth[0][ncols-1] == 1) {
                    MyHealth[1][ncols-1] = 1;
                    ccond3 = MyHealth[1][ncols-1];
                } // end of if
            } else if (trajectory == 3) { // left
                if (MyHealth[0][ncols-1] == 1) {
                    MyHealth[0][ncols-2] = 1;
                    ccond3 = MyHealth[0][ncols-2];
                } // end of if
            } else { // right
                ccond3 = MyHealth[0][ncols-1];
            } // end of else
        } else if ((myi == nrows - 1) && (myj == 0)) {
            // in the lower left corner
            if (trajectory == 1) { // up
                if (MyHealth[nrows-1][0] == 1) {
                    MyHealth[nrows-2][0] = 1;
                    ccond3 = MyHealth[nrows-2][0];
                } // end of if
            } else if (trajectory == 2) { // down
                ccond3 = MyHealth[nrows-1][0];
            } else if (trajectory == 3) { // left
                ccond3 = MyHealth[nrows-1][0];
            } else { // right
                if (MyHealth[nrows-1][0] == 1) {
                    MyHealth[nrows-1][1] = 1;
                    ccond3 = MyHealth[nrows-1][1];
                } // end of if
            } // end of else
        } else if ((myi == nrows - 1) && (myj == ncols - 1)) {
            // in the lower right corner
            if (trajectory == 1) { // up
                if (MyHealth[nrows-1][ncols-1] == 1) {
                    MyHealth[nrows-2][ncols-1] = 1;
                    ccond3 = MyHealth[nrows-2][ncols-1];
                } // end of if
            } else if (trajectory == 2) { // down
                ccond3 = MyHealth[nrows-1][ncols-1];
            } else if (trajectory == 3) { // left
                if (MyHealth[nrows-1][ncols-1] == 1) {
                    MyHealth[nrows-1][ncols-2] = 1;
                    ccond3 = MyHealth[nrows-1][ncols-2];
                } // end of if
            } else { // right
                ccond3 = MyHealth[nrows-1][ncols-1];
            } // end of else
        } else if ((myi == 0) && (myj > 0) && (myj <= ncols - 1)) {
            // check the upper row.
            if (trajectory == 1) { // up
                ccond3 = MyHealth[0][myj];
            } else if (trajectory == 2) { // down
                if (MyHealth[0][myj] == 1) {
                    MyHealth[1][myj] = 1;
                    ccond3 = MyHealth[1][myj];
                } // end of if
            } else if (trajectory == 3) { // left
                if (MyHealth[0][myj] == 1) {
                    MyHealth[0][myj-1] = 1;
                    ccond3 = MyHealth[0][myj-1];
                } // end of if
            } else { // right
                if (MyHealth[0][myj] == 1) {
                    MyHealth[0][myj+1] = 1;
                    ccond3 = MyHealth[0][myj+1];
                } // end of if
            } // end of else
        } else if (myi == (nrows) && (myj > 0) && (myj < ncols)) {
            // check the bottom row
            if (trajectory == 1) { // up
                if (MyHealth[nrows][myj] == 1) {
                    MyHealth[nrows-1][myj] = 1;
                    ccond3 = MyHealth[nrows-1][myj];
                } // end of if
            } else if (trajectory == 2) { // down
                ccond3 = MyHealth[nrows][myj];
            } else if (trajectory == 3) { // left
                if (MyHealth[nrows][myj] == 1) {
                    MyHealth[nrows][myj-1] = 1;
                    ccond3 = MyHealth[nrows][myj-1];
                } // end of if
            } else { // right
                if (MyHealth[nrows][myj] == 1) {
                    MyHealth[nrows][myj+1] = 1;
                    ccond3 = MyHealth[nrows][myj+1];
                } // end of if
            } // end of else
        } else if ((myj == 0) && (myi > 0) && (myi < nrows - 1)) {
            // check the left column
            if (trajectory == 1) { // up
                if (MyHealth[myi][0] == 1) {
                    MyHealth[myi-1][0] = 1;
                    ccond3 = MyHealth[myi-1][0];
                } // end of if
            } else if (trajectory == 2) { // down
                if (MyHealth[myi][0] == 1) {
                    MyHealth[myi+1][0] = 1;
                    ccond3 = MyHealth[myi+1][0];
                } // end of if
            } else if (trajectory == 3) { // left
                ccond3 = MyHealth[myi][0];
            } else { // right
                if (MyHealth[myi][0] == 1) {
                    MyHealth[myi][1] = 1;
                    ccond3 = MyHealth[myi][1];
                } // end of if
            } // end of else
        } else if ((myj == (ncols - 1)) && (myi > 0) && (myi < nrows - 1)) {
            // check the right column
            if (trajectory == 1) { // up
                if (MyHealth[myi][ncols-1] == 1) {
                    MyHealth[myi-1][ncols-1] = 1;
                    ccond3 = MyHealth[myi-1][ncols-1];
                } // end of if
            } else if (trajectory == 2) { // down
                if (MyHealth[myi][ncols-1] == 1) {
                    MyHealth[myi+1][ncols-1] = 1;
                    ccond3 = MyHealth[myi+1][ncols-1];
                } // end of if
            } else if (trajectory == 3) { // left
                ccond3 = MyHealth[myi][ncols-1];
            } else { // right
                if (MyHealth[myi][ncols-1] == 1) {
                    MyHealth[myi][ncols] = 1;
                    ccond3 = MyHealth[myi][ncols];
                } // end of if
            } // end of else
        } else if ((myi > 0) && (myi < nrows - 1) && (myj > 0) && (myj < ncols - 1)) {
            // check in the middle
            if (trajectory == 1) { // up
                if (MyHealth[myi][myj] == 1) {
                    MyHealth[myi-1][myj] = 1;
                    ccond3 = MyHealth[myi-1][myj];
                } // end of if
            } else if (trajectory == 2) { // down
                if (MyHealth[myi][myj] == 1) {
                    MyHealth[myi+1][myj] = 1;
                    ccond3 = MyHealth[myi+1][myj];
                } // end of if
            } else if (trajectory == 3) { // left
                if (MyHealth[myi][myj] == 1) {
                    MyHealth[myi][myj-1] = 1;
                    ccond3 = MyHealth[myi][myj-1];
                } // end of if
            } else { // right
                if (MyHealth[myi][myj] == 1) {
                    MyHealth[myi][myj+1] = 1;
                    ccond3 = MyHealth[myi][myj+1];
                } // end of if
            } // end of else
        }
        // this immediately cures the DMD to a HM cell
        if (value == 0){
            c = 5;
        } else if (value == 1) {
            c = 3;
        }
        while(runs<=c) {
            if ((myi == 0) && (myj == 0)) {
                // we are in the upper left corner
                if (trajectory == 1) { // up
                    ccond3 = MyHealth[0][0];
                } else if (trajectory == 2) { // down
                    if (MyHealth[0][0] == 1) {
                        MyHealth[1][0] = 3;
                        ccond3 = MyHealth[1][0];
                    }
                } else if (trajectory == 3) { // left
                    ccond3 = MyHealth[0][0];
                } else { // right
                    if (MyHealth[0][0] == 1) {
                        MyHealth[0][1] = 3;
                        ccond3 = MyHealth[1][0];
                    }
                } // end of else
            } else if ((myi == 0) && (myj == ncols - 1)) {
                // in the upper right corner
                if (trajectory == 1) { // up
                    ccond3 = MyHealth[0][ncols-1];
                } else if (trajectory == 2) { // down
                    if (MyHealth[0][ncols-1] == 1) {
                        MyHealth[1][ncols-1] = 3;
                        ccond3 = MyHealth[1][ncols-1];
                    } // end of if
                } else if (trajectory == 3) { // left
                    if (MyHealth[0][ncols-1] == 1) {
                        MyHealth[0][ncols-2] = 3;
                        ccond3 = MyHealth[0][ncols-2];
                    } // end of if
                } else { // right
                    ccond3 = MyHealth[0][ncols-1];
                } // end of else
            } else if ((myi == nrows - 1) && (myj == 0)) {
                // in the lower left corner
                if (trajectory == 1) { // up
                    if (MyHealth[nrows-1][0] == 1) {
                        MyHealth[nrows-2][0] = 3;
                        ccond3 = MyHealth[nrows-2][0];
                    } // end of if
                } else if (trajectory == 2) { // down
                    ccond3 = MyHealth[nrows-1][0];
                } else if (trajectory == 3) { // left
                    ccond3 = MyHealth[nrows-1][0];
                } else { // right
                    if (MyHealth[nrows-1][0] == 1) {
                        MyHealth[nrows-1][1] = 3;
                        ccond3 = MyHealth[nrows-1][1];
                    } // end of if
                } // end of else
            } else if ((myi == nrows - 1) && (myj == ncols - 1)) {
                // in the lower right corner
                if (trajectory == 1) { // up
                    if (MyHealth[nrows-1][ncols-1] == 1) {
                        MyHealth[nrows-2][ncols-1] = 3;
                        ccond3 = MyHealth[nrows-2][ncols-1];
                    } // end of if
                } else if (trajectory == 2) { // down
                    ccond3 = MyHealth[nrows-1][ncols-1];
                } else if (trajectory == 3) { // left
                    if (MyHealth[nrows-1][ncols-1] == 1) {
                        MyHealth[nrows-1][ncols-2] = 3;
                        ccond3 = MyHealth[nrows-1][ncols-2];
                    } // end of if
                } else { // right
                    ccond3 = MyHealth[nrows-1][ncols-1];
                } // end of else
            } else if ((myi == 0) && (myj > 0) && (myj <= ncols - 1)) {
                // check the upper row.
                if (trajectory == 1) { // up
                    ccond3 = MyHealth[0][myj];
                } else if (trajectory == 2) { // down
                    if (MyHealth[0][myj] == 1) {
                        MyHealth[1][myj] = 3;
                        ccond3 = MyHealth[1][myj];
                    } // end of if
                } else if (trajectory == 3) { // left
                    if (MyHealth[0][myj] == 1) {
                        MyHealth[0][myj-1] = 3;
                        ccond3 = MyHealth[0][myj-1];
                    } // end of if
                } else { // right
                    if (MyHealth[0][myj] == 1) {
                        MyHealth[0][myj+1] = 3;
                        ccond3 = MyHealth[0][myj+1];
                    } // end of if
                } // end of else
            } else if (myi == (nrows) && (myj > 0) && (myj < ncols)) {
                // check the bottom row
                if (trajectory == 1) { // up
                    if (MyHealth[nrows][myj] == 1) {
                        MyHealth[nrows-1][myj] = 3;
                        ccond3 = MyHealth[nrows-1][myj];
                    } // end of if
                } else if (trajectory == 2) { // down
                    ccond3 = MyHealth[nrows][myj];
                } else if (trajectory == 3) { // left
                    if (MyHealth[nrows][myj] == 1) {
                        MyHealth[nrows][myj-1] = 3;
                        ccond3 = MyHealth[nrows][myj-1];
                    } // end of if
                } else { // right
                    if (MyHealth[nrows][myj] == 1) {
                        MyHealth[nrows][myj+1] = 3;
                        ccond3 = MyHealth[nrows][myj+1];
                    } // end of if
                } // end of else
            } else if ((myj == 0) && (myi > 0) && (myi < nrows - 1)) {
                // check the left column
                if (trajectory == 1) { // up
                    if (MyHealth[myi][0] == 1) {
                        MyHealth[myi-1][0] = 3;
                        ccond3 = MyHealth[myi-1][0];
                    } // end of if
                } else if (trajectory == 2) { // down
                    if (MyHealth[myi][0] == 1) {
                        MyHealth[myi+1][0] = 3;
                        ccond3 = MyHealth[myi+1][0];
                    } // end of if
                } else if (trajectory == 3) { // left
                    ccond3 = MyHealth[myi][0];
                } else { // right
                    if (MyHealth[myi][0] == 1) {
                        MyHealth[myi][1] = 3;
                        ccond3 = MyHealth[myi][1];
                    } // end of if
                } // end of else
            } else if ((myj == (ncols - 1)) && (myi > 0) && (myi < nrows - 1)) {
                // check the right column
                if (trajectory == 1) { // up
                    if (MyHealth[myi][ncols-1] == 1) {
                        MyHealth[myi-1][ncols-1] = 3;
                        ccond3 = MyHealth[myi-1][ncols-1];
                    } // end of if
                } else if (trajectory == 2) { // down
                    if (MyHealth[myi][ncols-1] == 1) {
                        MyHealth[myi+1][ncols-1] = 3;
                        ccond3 = MyHealth[myi+1][ncols-1];
                    } // end of if
                } else if (trajectory == 3) { // left
                    ccond3 = MyHealth[myi][ncols-1];
                } else { // right
                    if (MyHealth[myi][ncols-1] == 1) {
                        MyHealth[myi][ncols] = 3;
                        ccond3 = MyHealth[myi][ncols];
                    } // end of if
                } // end of else
            } else if ((myi > 0) && (myi < nrows - 1) && (myj > 0) && (myj < ncols - 1)) {
                // check the middle
                if (trajectory == 1) { // up
                    if (MyHealth[myi][myj] == 1) {
                        MyHealth[myi-1][myj] = 3;
                        ccond3 = MyHealth[myi-1][myj];
                    } // end of if
                } else if (trajectory == 2) { // down
                    if (MyHealth[myi][myj] == 1) {
                        MyHealth[myi+1][myj] = 3;
                        ccond3 = MyHealth[myi+1][myj];
                    } // end of if
                } else if (trajectory == 3) { // left
                    if (MyHealth[myi][myj] == 1) {
                        MyHealth[myi][myj-1] = 3;
                        ccond3 = MyHealth[myi][myj-1];
                    } // end of if
                } else { // right
                    if (MyHealth[myi][myj] == 1) {
                        MyHealth[myi][myj+1] = 3;
                        ccond3 = MyHealth[myi][myj+1];
                    } // end of if
                } // end of else
            }
            runs++;
        }
        // Youth Cells impact two cells
        int a = 0;
        int z = 0;
        int health;
        while(a<b){
            x = (int) (Math.random() * 100);
            if (x <= 85) {
                health = 1; // alive cell
            }  else {
                health = 2; // dead cell
            }
            if(health == 1){
                while(z<2){
                    if ((myi == 0) && (myj == 0)) {
                        // check the upper left corner
                        if (trajectory == 1) { // up
                            ccond3 = MyHealth[0][0];
                        } else if (trajectory == 2) { // down
                            if (MyHealth[0][0] == 3) {
                                MyHealth[1][0] = 3;
                                ccond3 = MyHealth[1][0];
                            } // end of if
                        } else if (trajectory == 3) { // left
                            ccond3 = MyHealth[0][0];
                        } else { // right
                            if (MyHealth[0][0] == 3) {
                                MyHealth[0][1] = 3;
                                ccond3 = MyHealth[1][0];
                            } // end of if
                        } // end of else
                    } else if ((myi == 0) && (myj == ncols - 1)) {
                        // check the upper right corner
                        if (trajectory == 1) { // up
                            ccond3 = MyHealth[0][ncols-1];
                        } else if (trajectory == 2) { // down
                            if (MyHealth[0][ncols-1] == 3) {
                                MyHealth[1][ncols-1] = 3;
                                ccond3 = MyHealth[1][ncols-1];
                            } // end of if
                        } else if (trajectory == 3) { // left
                            if (MyHealth[0][ncols-1] == 3) {
                                MyHealth[0][ncols-2] = 3;
                                ccond3 = MyHealth[0][ncols-2];
                            } // end of if
                        } else { // right
                            ccond3 = MyHealth[0][ncols-1];
                        } // end of else
                    } else if ((myi == nrows - 1) && (myj == 0)) {
                        // check the lower left corner
                        if (trajectory == 1) { // up
                            if (MyHealth[nrows-1][0] == 3) {
                                MyHealth[nrows-2][0] = 3;
                                ccond3 = MyHealth[nrows-2][0];
                            } // end of if
                        } else if (trajectory == 2) { // down
                            ccond3 = MyHealth[nrows-1][0];
                        } else if (trajectory == 3) { // left
                            ccond3 = MyHealth[nrows-1][0];
                        } else { // right
                            if (MyHealth[nrows-1][0] == 3) {
                                MyHealth[nrows-1][1] = 3;
                                ccond3 = MyHealth[nrows-1][1];
                            } // end of if
                        } // end of else
                    } else if ((myi == nrows - 1) && (myj == ncols - 1)) {
                        // check the lower right corner
                        if (trajectory == 1) { // up
                            if (MyHealth[nrows-1][ncols-1] == 3) {
                                MyHealth[nrows-2][ncols-1] = 3;
                                ccond3 = MyHealth[nrows-2][ncols-1];
                            } // end of if
                        } else if (trajectory == 2) { // down
                            ccond3 = MyHealth[nrows-1][ncols-1];
                        } else if (trajectory == 3) { // left
                            if (MyHealth[nrows-1][ncols-1] == 3) {
                                MyHealth[nrows-1][ncols-2] = 3;
                                ccond3 = MyHealth[nrows-1][ncols-2];
                            } // end of if
                        } else { // right
                            ccond3 = MyHealth[nrows-1][ncols-1];
                        } // end of else
                    } else if ((myi == 0) && (myj > 0) && (myj <= ncols - 1)) {
                        // check the upper row
                        if (trajectory == 1) { // up
                            ccond3 = MyHealth[0][myj];
                        } else if (trajectory == 2) { // down
                            if (MyHealth[0][myj] == 3) {
                                MyHealth[1][myj] = 3;
                                ccond3 = MyHealth[1][myj];
                            } // end of if
                        } else if (trajectory == 3) { // left
                            if (MyHealth[0][myj] == 3) {
                                MyHealth[0][myj-1] = 3;
                                ccond3 = MyHealth[0][myj-1];
                            } // end of if
                        } else { // right
                            if (MyHealth[0][myj] == 3) {
                                MyHealth[0][myj+1] = 3;
                                ccond3 = MyHealth[0][myj+1];
                            } // end of if
                        } // end of else
                    } else if (myi == (nrows) && (myj > 0) && (myj < ncols)) {
                        // check the bottom row
                        if (trajectory == 1) { // up
                            if (MyHealth[nrows][myj] == 3) {
                                MyHealth[nrows-1][myj] = 3;
                                ccond3 = MyHealth[nrows-1][myj];
                            } // end of if
                        } else if (trajectory == 2) { // down
                            ccond3 = MyHealth[nrows][myj];
                        } else if (trajectory == 3) { // left
                            if (MyHealth[nrows][myj] == 3) {
                                MyHealth[nrows][myj-1] = 3;
                                ccond3 = MyHealth[nrows][myj-1];
                            } // end of if
                        } else { // right
                            if (MyHealth[nrows][myj] == 3) {
                                MyHealth[nrows][myj+1] = 3;
                                ccond3 = MyHealth[nrows][myj+1];
                            } // end of if
                        } // end of else
                    } else if ((myj == 0) && (myi > 0) && (myi < nrows - 1)) {
                        // check the left column
                        if (trajectory == 1) { // up
                            if (MyHealth[myi][0] == 3) {
                                MyHealth[myi-1][0] = 3;
                                ccond3 = MyHealth[myi-1][0];
                            } // end of if
                        } else if (trajectory == 2) { // down
                            if (MyHealth[myi][0] == 3) {
                                MyHealth[myi+1][0] = 3;
                                ccond3 = MyHealth[myi+1][0];
                            } // end of if
                        } else if (trajectory == 3) { // left
                            ccond3 = MyHealth[myi][0];
                        } else { // right
                            if (MyHealth[myi][0] == 3) {
                                MyHealth[myi][1] = 3;
                                ccond3 = MyHealth[myi][1];
                            } // end of if
                        } // end of else
                    } else if ((myj == (ncols - 1)) && (myi > 0) && (myi < nrows - 1)) {
                        // check the right column
                        if (trajectory == 1) { // up
                            if (MyHealth[myi][ncols-1] == 3) {
                                MyHealth[myi-1][ncols-1] = 3;
                                ccond3 = MyHealth[myi-1][ncols-1];
                            } // end of if // end of if
                        } else if (trajectory == 2) { // down
                            if (MyHealth[myi][ncols-1] == 3) {
                                MyHealth[myi+1][ncols-1] = 3;
                                ccond3 = MyHealth[myi+1][ncols-1];
                            } // end of if
                        } else if (trajectory == 3) { // left
                            ccond3 = MyHealth[myi][ncols-1];
                        } else { // right
                            if (MyHealth[myi][ncols-1] == 3) {
                                MyHealth[myi][ncols] = 3;
                                ccond3 = MyHealth[myi][ncols];
                            } // end of if
                        } // end of else
                    } else if ((myi > 0) && (myi < nrows - 1) && (myj > 0) && (myj < ncols - 1)) {
                        // check the middle
                        if (trajectory == 1) { // up
                            if (MyHealth[myi][myj] == 3) {
                                MyHealth[myi-1][myj] = 3;
                                ccond3 = MyHealth[myi-1][myj];
                            } // end of if
                        } else if (trajectory == 2) { // down
                            if (MyHealth[myi][myj] == 3) {
                                MyHealth[myi+1][myj] = 3;
                                ccond3 = MyHealth[myi+1][myj];
                            } // end of if
                        } else if (trajectory == 3) { // left
                            if (MyHealth[myi][myj] == 3) {
                                MyHealth[myi][myj-1] = 3;
                                ccond3 = MyHealth[myi][myj-1];
                            } // end of if
                        } else { // right
                            if (MyHealth[myi][myj] == 3) {
                                MyHealth[myi][myj+1] = 3;
                                ccond3 = MyHealth[myi][myj+1];
                            } // end of if
                        } // end of else
                    }
                    z++;
                }
            }
            a++;
        }
        return;
    } // end

    public void cellTendril(int[][] MyHealth, int myi, int myj) {
        int x;
        int trajectory;
        x = (int) (Math.random() * 100);
        if (x <= 15) {
            trajectory = 1; // going up
        } else if (x <= 30) {
            trajectory = 2; // going down
        } else if (x <= 65) {
            trajectory = 3; // going left
        } else {
            trajectory = 4; // going right
        } // end of else

        if ((myi == 0) && (myj == 0)) {
            // check the upper left corner
            if (trajectory == 1) { // up
                ccond = MyHealth[0][0];
            } else if (trajectory == 2) { // down
                if (MyHealth[0][0] == 1) {
                    MyHealth[1][0] = 1;
                    ccond = MyHealth[1][0];
                } // end of if
            } else if (trajectory == 3) { // left
                ccond = MyHealth[0][0];
            } else { // right
                if (MyHealth[0][0] == 1) {
                    MyHealth[0][1] = 1;
                    ccond = MyHealth[1][0];
                } // end of if
            }// end of else
        } else if ((myi == 0) && (myj == ncols - 1)) {
            // check the upper right corner
            if (trajectory == 1) { // up
                ccond = MyHealth[0][ncols-1];
            } else if (trajectory == 2) { // down
                if (MyHealth[0][ncols-1] == 1) {
                    MyHealth[1][ncols-1] = 1;
                    ccond = MyHealth[1][ncols-1];
                } // end of if
            } else if (trajectory == 3) { // left
                if (MyHealth[0][ncols-1] == 1) {
                    MyHealth[0][ncols-2] = 1;
                    ccond = MyHealth[0][ncols-2];
                } // end of if
            } else { // right
                ccond = MyHealth[0][ncols-1];
            } // end of else
        } else if ((myi == nrows - 1) && (myj == 0)) {
            // check the lower left corner
            if (trajectory == 1) { // up
                if (MyHealth[nrows-1][0] == 1) {
                    MyHealth[nrows-2][0] = 1;
                    ccond = MyHealth[nrows-2][0];
                } // end of if
            } else if (trajectory == 2) { // down
                ccond = MyHealth[nrows-1][0];
            } else if (trajectory == 3) { // left
                ccond = MyHealth[nrows-1][0];
            } else { // right
                if (MyHealth[nrows-1][0] == 1) {
                    MyHealth[nrows-1][1] = 1;
                    ccond = MyHealth[nrows-1][1];
                } // end of if
            } // end of else
        } else if ((myi == nrows - 1) && (myj == ncols - 1)) {
            // check the lower right corner
            if (trajectory == 1) { // up
                if (MyHealth[nrows-1][ncols-1] == 1) {
                    MyHealth[nrows-2][ncols-1] = 1;
                    ccond = MyHealth[nrows-2][ncols-1];
                } // end of if
            } else if (trajectory == 2) { // down
                ccond = MyHealth[nrows-1][ncols-1];
            } else if (trajectory == 3) { // left
                if (MyHealth[nrows-1][ncols-1] == 1) {
                    MyHealth[nrows-1][ncols-2] = 1;
                    ccond = MyHealth[nrows-1][ncols-2];
                } // end of else if
            } else { // right
                ccond = MyHealth[nrows-1][ncols-1];
            } // end of else
        } else if ((myi == 0) && (myj > 0) && (myj <= ncols - 1)) {
            // check the upper row
            if (trajectory == 1) { // up
                ccond = MyHealth[0][myj];
            } else if (trajectory == 2) { // down
                if (MyHealth[0][myj] == 1) {
                    MyHealth[1][myj] = 1;
                    ccond = MyHealth[1][myj];
                } // end of else if
            } else if (trajectory == 3) { // left
                if (MyHealth[0][myj] == 1) {
                    MyHealth[0][myj-1] = 1;
                    ccond = MyHealth[0][myj-1];
                } // end of if
            } else { // right
                if (MyHealth[0][myj] == 1) {
                    MyHealth[0][myj+1] = 1;
                    ccond = MyHealth[0][myj+1];
                } // end of if
            } // end of else
        } else if (myi == (nrows) && (myj > 0) && (myj < ncols)) {
            // check the bottom row
            if (trajectory == 1) { // up
                if (MyHealth[nrows][myj] == 1) {
                    MyHealth[nrows-1][myj] = 1;
                    ccond = MyHealth[nrows-1][myj];
                } // end of if
            } else if (trajectory == 2) { // down
                ccond = MyHealth[nrows][myj];
            } else if (trajectory == 3) { // left
                if (MyHealth[nrows][myj] == 1) {
                    MyHealth[nrows][myj-1] = 1;
                    ccond = MyHealth[nrows][myj-1];
                } // end of if
            } else { // right
                if (MyHealth[nrows][myj] == 1) {
                    MyHealth[nrows][myj+1] = 1;
                    ccond = MyHealth[nrows][myj+1];
                } // end of if
            } // end of else
        } else if ((myj == 0) && (myi > 0) && (myi < nrows - 1)) {
            // check the left column
            if (trajectory == 1) { // up
                if (MyHealth[myi][0] == 1) {
                    MyHealth[myi-1][0] = 1;
                    ccond = MyHealth[myi-1][0];
                } // end of if
            } else if (trajectory == 2) { // down
                if (MyHealth[myi][0] == 1) {
                    MyHealth[myi+1][0] = 1;
                    ccond = MyHealth[myi+1][0];
                } // end of if
            } else if (trajectory == 3) { // left
                ccond = MyHealth[myi][0];
            } else { // right
                if (MyHealth[myi][0] == 1) {
                    MyHealth[myi][1] = 1;
                    ccond = MyHealth[myi][1];
                }// end of if
            } // end of else
        } else if ((myj == (ncols - 1)) && (myi > 0) && (myi < nrows - 1)) {
            // check the right column
            if (trajectory == 1) { // up
                if (MyHealth[myi][ncols-1] == 1) {
                    MyHealth[myi-1][ncols-1] = 1;
                    ccond = MyHealth[myi-1][ncols-1];
                } // end of if
            } else if (trajectory == 2) { // down
                if (MyHealth[myi][ncols-1] == 1) {
                    MyHealth[myi+1][ncols-1] = 1;
                    ccond = MyHealth[myi+1][ncols-1];
                } // end of if
            } else if (trajectory == 3) { // left
                ccond = MyHealth[myi][ncols-1];
            } else { // right
                if (MyHealth[myi][ncols-1] == 1) {
                    MyHealth[myi][ncols] = 1;
                    ccond = MyHealth[myi][ncols];
                }  // end of if
            } // end of else
        } else if ((myi > 0) && (myi < nrows - 1) && (myj > 0) && (myj < ncols - 1)) {
            // check the middle
            if (trajectory == 1) { // up
                if (MyHealth[myi][myj] == 1) {
                    MyHealth[myi-1][myj] = 1;
                    ccond = MyHealth[myi-1][myj];
                } // end of if
            } else if (trajectory == 2) { // down
                if (MyHealth[myi][myj] == 1) {
                    MyHealth[myi+1][myj] = 1;
                    ccond = MyHealth[myi+1][myj];
                }// end of else if
            } else if (trajectory == 3) { // left
                if (MyHealth[myi][myj] == 1) {
                    MyHealth[myi][myj-1] = 1;
                    ccond = MyHealth[myi][myj-1];
                }// end of else if
            } else { // right
                if (MyHealth[myi][myj] == 1) {
                    MyHealth[myi][myj+1] = 1;
                    ccond = MyHealth[myi][myj+1];
                } // end of if
            } // end of else
        }
        return;
    } // end of function cellTendril
} //end of class Cell

