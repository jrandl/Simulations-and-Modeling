/*
This is a traffic sim to calculate the amount of traffic and the wait time of an intersection
Author: Josiah Randleman

 */
import java.text.DecimalFormat;
import java.util.*;
import java.lang.*;

public class TrafficSim
{
    public static void main(String[] args)throws Exception
    {
        double  Bigtime=0.0; // this is the simulation main clock in seconds
        double  Endtime=100.0;// we will test the queue structues for 100 seconds.
        double  Eventtime;// this is the event time
        double deltime;
        double balktime=5.0;
        //Now create an event manager EventQue2 and a Que Simulation Manager QueMgr
        GenericManagerTraffic<TrafficEvent> EventQue=new GenericManagerTraffic<TrafficEvent>();
        //Now create an a queue of folks waiting for service.
        GenericManagerTraffic<Car> MyQueue=new GenericManagerTraffic<Car>();
        int MyBalkid=0;//this is the unique balk id
        int numinque;
        int numinevent;// number of events in the event queue
        int totalthrusystem=0, totalthruline=0, totalthrufac=0, totalthrusystem_NorthCar = 0,totalthrusystem_WestCar = 0,  totalthrusystem_WestCarGoingNorth = 0, totalthrusystem_WestCarGoingSouth = 0;
        double totaltimeinline=0.0,totaltimeinlineNSGoingN=0.0,totaltimeinlineNSGoingWest=0.0,totaltimeinlineEWGoingN=0.0,totaltimeinlineEWGoingSouth=0.0,    totaltimeinsystem=0.0,totaltimeinservers=0.0,totaltimeinservers2=0.0,ttil, ttis;
        double waittotalNS = 0, waittotalEW = 0;
        double totalwaittotalNS = 0, totalwaittotalEW = 0;
        double totaltimeinline2=0.0,totaltimeinsystem2=0.0;
        int MyBCust;//this is the customer id from a balk event
        int totalbalked=0, NorthCarWaitTime=0, WestCarbalkedWaitTime=0, WestCarGoingNorthBalked=0, WestCarGoingSouthBalked=0;
        int totalcarsNorth=0, totalcarsWest=0;
        boolean busy1=false;//this is server 1 it is empty
        boolean busy2=true;// this is server 2 it is empty
        boolean GreenNS = false;
        boolean GreenEW = false;
        Car served1;
        Car served2;
        Car newcust;
        Car workcust;

        served1=new Car(-9, 0);//this is the customer in server1
        served2=new Car(-9, 0);//this is the customer in server2
        newcust=new Car(-9, 0);//this is a work customer object for those entereing the queue
        workcust=new Car(-9, 0);//this is a work customer object for those coming from the queue

        //Patient served1_2=new Patient(-9,1);//this is the customer in server1
        //Patient served2_2=new Patient(-9);//this is the customer in server2
        double deltimeserv = 0;//this is the service time
        double timearrive, deltimearv;//this is the timearrival

        // now create the last event of the simulation
        TrafficEvent workevent=new TrafficEvent(8,60000,0); // 1,000 hours

        //add the event in the queue
        numinevent=EventQue.addinorder(workevent);
        //now add the arrival for the first customer
        deltimearv=TimetoArriveorServe(4); // 120 / 60 = 2 so 120 per hour or 30 per 15 minutes
        //the event time is current time plus the delta time
        Eventtime=Bigtime+deltimearv;
        //System.out.println("the first customer arrives at "+Eventtime2);
        //Creater the event for the first customer to arrive.
        workevent=new TrafficEvent(1,Eventtime,0);
        //Store this event on the queue
        numinevent=EventQue.addinorder(workevent);
        //now start processing the events.
        // get the first event off of the event queue
        workevent=EventQue.getvalue(0);
        while(workevent.getEtype()!=8)
        //for(int ixx=1;ixx<=15;ixx++)
        {//this is a valid event. Get ready to update the time
            //System.out.println("This is the time:   "+workevent.getTime());
            deltime=workevent.getTime()-Bigtime;
            // now update everybody with this deltime2
            ttil= UpdateCustomer(MyQueue,deltime);
            totaltimeinline+=ttil;
            totaltimeinline2+=ttil*ttil;
            // now update everybody in the servers
            ttis= UpdateServers(served1,busy1,served2,busy2,deltime);
            // now get the event type and process it. First update the time
            totaltimeinservers+=ttis;
            totaltimeinservers2+=ttis*ttis;
            Bigtime=workevent.getTime();
            //if((Bigtime2>=500.00)&&(Bigtime2<=600.00))System.out.println("++++++++++++++++++ THE TIME IS NOW++++++++++++++++ "+Bigtime2);
            // get the number in the Customer Queue at this time
            numinque=MyQueue.getmcount();
            int x= (int)(Math.random()*100);
            int y= (int)(Math.random()*100);
            switch (workevent.getEtype())
            {case 1://customer arrives at the facility.
                int cycle_per_hour = 12;
                double minutes = workevent.getTime();
                double hours = (workevent.getTime()/60);
                double total_cycles = (cycle_per_hour * hours);

                /*
                You need to count the amount of cars that have to wait when the light is red. When it is red you have to
                count the time for the cars that had to wait.
                 */

                // generate the object for the customer
                //if servers busy put the customer generate a customer enters line event
                //if servers not busy, generate a customer object and put the the customer in the server
                if((busy1==false)&&(numinque<=0))
                {//server 1 is not busy and there is no one in the customer que, put the customer in the server 1
                    // create the customer object
                    if (workevent.getTime() <= (48000)) {  // this is the light cycle. For 60,000 minutes the light on EW will be green for 24,000 minutes * 2 48,000
                        GreenEW = true;
                        GreenNS = false;
                    } else {
                        GreenNS = true;
                        GreenEW = false;
                    }

                    /*
                    This is for the north and west-bound traffic
                     */
                    if(GreenNS) {
                        if (x <= 25) {  // Car on NS Turning Left
                            /*
                            This is for the car on NS turning Left onto EW road. There is a 50% chance that there will
                            be traffic. If there is traffic then wait for the next cycle.
                            This is currently wrong.
                             */
                            if (y<=50){
                                newcust = new Car(-9, 2); // 2 is west
                                deltimeserv = TimetoArriveorServe(7.5);  // 8 seconds
                            }

                        } else {  // Car Going North
                            newcust = new Car(-9, 1);
                            deltimeserv = TimetoArriveorServe(15);  // 4 seconds
                        }

                    }

                    /*
                    This is for the EW traffic
                     */
                    if(GreenEW) {
                        if (x <= 30) {  // Car on EW turning Left going North
                            newcust = new Car(-9, 4); // Car Turning Left Going North
                            deltimeserv = TimetoArriveorServe(7.5);  // 8 seconds
                        } else {  // Car on EW turning Right going South
                            newcust = new Car(-9, 3);  // Car Turning Right Going South
                            deltimeserv = TimetoArriveorServe(12);  // 5 seconds
                        }
                    }


                    //set the arrival time for this customer
                    newcust.SetArrive(Bigtime);
                    //put this customer in server 1
                    busy1=true;
                    served1=newcust;
                    // generate the finished server event for this customer
                    Eventtime=deltimeserv+Bigtime;
                    workevent=new TrafficEvent(5,Eventtime,-9);
                    //put this event in the event queue
                    numinevent=EventQue.addinorder(workevent);
                }

                else
                if((busy1==true)&&(busy2==false)&&(numinque<=0))
                {//server 2 is open and there is no one in the customer que, send the customer to server 2
                    if (workevent.getTime() <= (48000)) {  // this is the light cycle. For 60,000 minutes the light on EW will be green for 24,000 minutes
                        GreenEW = true;
                        GreenNS = false;
                    } else {
                        GreenNS = true;
                        GreenEW = false;
                    }
                     /*
                    This is for the north and west-bound traffic
                     */
                    if(GreenNS) {
                        if (x <= 25) {  // Car on NS Turning Left
                            newcust = new Car(-9, 2); // 2 is west
                            deltimeserv = TimetoArriveorServe(7.5);  // 8 seconds
                        } else {  // Car Going North
                            newcust = new Car(-9, 1);
                            deltimeserv = TimetoArriveorServe(15);  // 4 seconds
                        }
                    }

                    /*
                    This is for the EW traffic
                     */
                    if(GreenEW) {
                        if (x <= 30) {  // Car on EW turning Left going North
                            newcust = new Car(-9, 4); // Car Turning Left Going North
                            deltimeserv = TimetoArriveorServe(7.5);  // 8 seconds
                        } else {  // Car on EW turning Right going South
                            newcust = new Car(-9, 3);  // Car Turning Right Going South
                            deltimeserv = TimetoArriveorServe(12);  // 5 seconds
                        }
                    }

                    // set the arrival time for this customer
                    newcust.SetArrive(Bigtime);
                    //put this customer in server 2
                    busy2=true;
                    served2=newcust;
                    // generate the finished server event for this customer

                    Eventtime=deltimeserv+Bigtime;
                    workevent=new TrafficEvent(6,Eventtime,-9);
                    //put this event in the event queue
                    numinevent=EventQue.addinorder(workevent);
                }//done in server 2
                else
                if((!GreenNS))  // if Light on NS Road is Red
                {//both servers are busy put the customer in line
                    //first generate the customer, note this customer must have a unique ID
                    //if((Bigtime2>=500.00)&&(Bigtime2<=600.00))System.out.println("customer goes into the line");
                    MyBalkid++;
                    waittotalNS++;
                    totalwaittotalNS+=waittotalNS;

                    if(x<=25) { // This is for NS Road turning Left onto West bound
                        if(y<=50){ // if there is traffic the car must wait 4 minutes till it turns green again
                            newcust=new Car(MyBalkid, 2);
                            balktime=Bigtime + 4;  // 2 minutes
                            double ttil3= UpdateCustomer(MyQueue,4); // red for 4 minutes
                            totaltimeinlineNSGoingWest+=ttil3;

                        } else { // if there is no traffic then don't create a balktime
                            continue;

                        }

                    } else {   // This is for NS Road going north
                        newcust=new Car(MyBalkid, 1);
                        balktime=Bigtime + 2; // 2 minutes
                        double ttil4= UpdateCustomer(MyQueue,2);
                        totaltimeinlineNSGoingN+=ttil4;

                    }

                    // set the arrival time for this customer
                    newcust.SetArrive(Bigtime);
                    // now put this customer in line at end
                    MyQueue.addatend(newcust);

                    // now create the event
                    workevent=new TrafficEvent(7,balktime,MyBalkid);
                    // add the event to the event queue
                    numinevent=EventQue.addinorder(workevent);
                }//the customer is in the line

                if((!GreenEW))  // if light on EW Road is red
                {//both servers are busy put the customer in line
                    //first generate the customer, note this customer must have a unique ID
                    //if((Bigtime2>=500.00)&&(Bigtime2<=600.00))System.out.println("customer goes into the line");
                    MyBalkid++;
                    waittotalEW++;
                    totalwaittotalEW+=waittotalEW;

                    if(x<=30) { // for car on ew turning left going north.
                        newcust=new Car(MyBalkid, 2);
                        balktime=Bigtime + 3; // 3 minutes
                        double ttil2= UpdateCustomer(MyQueue,3);
                        totaltimeinlineEWGoingN+=ttil2;
                    } else {
                        newcust=new Car(MyBalkid, 1);
                        balktime=Bigtime + 3; //  3 minutes
                        double ttil3= UpdateCustomer(MyQueue,3);
                        totaltimeinlineEWGoingSouth+=ttil3;

                    }

                    // set the arrival time for this customer
                    newcust.SetArrive(Bigtime);
                    // now put this customer in line at end
                    MyQueue.addatend(newcust);

                    // now create the event
                    workevent=new TrafficEvent(7,balktime,MyBalkid);
                    // add the event to the event queue
                    numinevent=EventQue.addinorder(workevent);
                }//the customer is in the line


                //generate the event for the next customer to arrive
                deltimearv=TimetoArriveorServe(4);
                //the event time is current time plus the delta time
                Eventtime=Bigtime+deltimearv;
                //if((Bigtime2>=500.00)&&(Bigtime2<=600.00))System.out.println("the next customer arrives at "+Eventtime2);
                //Creater the event for the first customer to arrive.
                workevent=new TrafficEvent(1,Eventtime,0);
                //Store this event on the queue
                numinevent=EventQue.addinorder(workevent);
                break;
                case 2://customer enters the line at the facility
                    //generate the balk event for this customer and put them in line
                    //System.out.println("this is event 2, we have incorporated it in the arrival event if we are here we are in trouble");
                    break;
                case 3:// customer enters service bay 1
                    // decrement the number in line
                    //generate completion time and departure event for this customer
                    // set this server to busy
                    numinque=MyQueue.getmcount();
                    if((!busy1)&&(numinque>0))
                    {// the customer can enter bay 1 get the customer from the front line
                        //if((Bigtime2>=500.00)&&(Bigtime2<=600.00)) System.out.println("first customer in line enters server 1");
                        workcust=MyQueue.getvalue(0);
                        MyBCust=workcust.GetMyBalk();//get the customer balk event
                        PurgeEvent(EventQue,MyBCust);//purge the event from the queue
                        totalthruline++;//this customer just came out of line
                        // delete this customer from the queue and put them in the server.
                        MyQueue.removem(0);
                        //put this customer in server 1
                        busy1=true;
                        served1=workcust;
                        // generate the finished server event for this customer
                        /*
                        if (served1.GetType() == 1) {
                            totalcarsNorth++;
                        } else if (served1.GetType() == 2) {
                            totalcarsWest++;
                        }
                        */
                        if(GreenNS) {
                            if(x<=25) {
                                deltimeserv=TimetoArriveorServe(7.5);  // 8 seconds

                            } else {
                                deltimeserv=TimetoArriveorServe(15); // 4 seconds
                            }
                        }
                        if(GreenEW) {
                            if(x<=30) {
                                deltimeserv=TimetoArriveorServe(7.5);  // 8 seconds

                            } else {  // this is for the bleeding patient
                                deltimeserv=TimetoArriveorServe(12);  //  5 seconds
                            }
                        }


                        Eventtime=deltimeserv+Bigtime;
                        workevent=new TrafficEvent(5,Eventtime,-9);
                        //put this event in the event queue
                        numinevent=EventQue.addinorder(workevent);
                    }//end of enter service bay 1
                    else
                    {// either we are busy and have had an event collision or there is none in the line
                        System.out.println(numinque);
                        System.out.println("in event 3 customer enters service bay 1 unable to process event");
                    }
                    break;
                case 4: // customer enters service bay 2
                    //decrement the number in line
                    //generate completion time and departure event for this customr
                    // set this server to busy
                    busy2=true;
                    numinque=MyQueue.getmcount();
                    if((!busy2)&&(numinque>0))
                    {// the customer can enter bay 1 get the customer from the front line
                        //if((Bigtime2>=500.00)&&(Bigtime2<=600.00))System.out.println("customer enters service2 from line");
                        workcust=MyQueue.getvalue(0);
                        MyBCust=workcust.GetMyBalk();//get the customer balk event
                        PurgeEvent(EventQue,MyBCust);//purge the event from the queue
                        totalthruline++;//this customer just came out of line
                        // delete this customer from the queue and put them in the server.
                        MyQueue.removem(0);
                        //put this customer in server 2
                        busy2=true;
                        served2=workcust;
                        // generate the finished server event for this customer
                        if (served1.GetType() == 1) {
                            totalcarsNorth++;
                        } else if (served1.GetType() == 2) {
                            totalcarsWest++;
                        }

                        if(GreenNS) {
                            if(x<=25) {
                                deltimeserv=TimetoArriveorServe(7.5);  // 8 seconds

                            } else {  // this is for the bleeding patient
                                deltimeserv=TimetoArriveorServe(15); // 4 seconds
                            }
                        }
                        if(GreenEW) {
                            if(x<=30) {
                                deltimeserv=TimetoArriveorServe(7.5);  // 8 seconds

                            } else {  // this is for the bleeding patient
                                deltimeserv=TimetoArriveorServe(12);  //  5 seconds
                            }
                        }

                        Eventtime=deltimeserv+Bigtime;
                        workevent=new TrafficEvent(6,Eventtime,-9);
                        //put this event in the event queue
                        numinevent=EventQue.addinorder(workevent);
                    }//end of enter service bay 2
                    else
                    {// either we are busy and have had an event collision or there is noone in the line
                        System.out.println("in event 4 customer enters service bay 2 unable to process event");
                    }
                    break;
                case 5: // customer leaves service bay 1
                    // update the number of customers through the system
                    //set the server to not busy
                    //if there are people in line generate an enter service bay 1 event
                    busy1=false;
                    totalthrusystem++;
                    numinque=MyQueue.getmcount();
                    if (served1.GetType() == 1) {
                        totalthrusystem_NorthCar++;
                    } else if (served1.GetType() == 2) {
                        totalthrusystem_WestCar++;
                    }
                    else if (served1.GetType() == 3) {
                        totalthrusystem_WestCarGoingSouth++;
                    }
                    else if (served1.GetType() == 4) {
                        totalthrusystem_WestCarGoingNorth++;
                    }
                    //if((Bigtime2>=500.00)&&(Bigtime2<=600.00))System.out.println("customer leaves server1 numinque2"+numinque2);
                    if(numinque>0)
                    {//there are customers in the line, generate a customer enter service bay 1 now at Bigtime2
                        //NOTE PROBLEMS WITH COLLISION EVENTS
                        workevent=new TrafficEvent(3,Bigtime+0.01,-9);
                        //put this event in the event queue
                        numinevent=EventQue.addinorder(workevent);
                    }
                    break;
                case 6://customer leaves service bay 2
                    //set the server to not busy
                    //if there are people in line generate an enter service bay 2 event
                    busy2=true;
                    totalthrusystem++;

                    numinque=MyQueue.getmcount();
                    //if((Bigtime2>=500.00)&&(Bigtime2<=600.00))System.out.println("customer leaves servere2");
                    if(numinque>0)
                    {//there are customers in the line, generate a customer enter service bay 2 now at Bigtime2
                        workevent=new TrafficEvent(4,Bigtime+.01,-9);
                        //put this event in the event queue
                        numinevent=EventQue.addinorder(workevent);
                    }
                    break;
                case 7:// customer balks and leaves the waiting line
                    //delete the customer from the line
                    // delete the customer from the queue
                    MyBCust=workevent.getMyCust();
                    //get this customer out of line
                    OutaHere(MyQueue,MyBCust);
                    //add this to the customers that have gone through the system
                    totalbalked++;
                    totalthruline++;

                    if (served1.GetType() == 1) {
                        NorthCarWaitTime++;
                        totalcarsNorth++;
                    } else if (served1.GetType() == 2) {
                        WestCarbalkedWaitTime++;
                        totalcarsWest++;
                    }
                    else if (served1.GetType() == 3) {
                        WestCarGoingSouthBalked++;

                    }
                    else if (served1.GetType() == 4) {
                        WestCarGoingNorthBalked++;

                    }

                    break;
                case 8://this is the shutdown event
                    System.out.println(" this event is type 8 and we are in the switch statement TROUBLE!");
                    continue;
                default:
                    System.out.println("this is a bad event type"+workevent.getEtype()+" at time "+workevent.getTime());
            }//this is the end of the switch statement
            // this event is processed delete it from the eventqueue
            EventQue.removem(0);
            // now get the next event
            //System.out.println(served1_2.GetType());
            //if((Bigtime2>=500.00)&&(Bigtime2<=600.00))System.out.println("*************************the time is "+Bigtime2+"****************************");
            workevent=EventQue.getvalue(0);
            //  System.out.println("The current customer queue is");
            //PrintCustQue(MyQueue2);
            //System.out.println("The current event queue is");
            //PrintEventQue(EventQue2);
        }//end of simulation loop
        //Now for the Statics
        /*
        This is calculating the traffic of cars on the NS road. This is when the traffic light is green infinitely.
         */

        System.out.println("\n\n");
        System.out.println("Printing the Statistics for this Run");
        System.out.println("\n");
        System.out.println("Total of Cars Heading North from NS Road(75%): " + totalthrusystem_NorthCar);
        System.out.println("Total of Cars Heading West from NS Road (25%): " + totalthrusystem_WestCar);
        System.out.println("Total of Cars On NS Road: " + (totalthrusystem_NorthCar+totalthrusystem_WestCar));
        System.out.println("\n");
        System.out.println("Total of Cars Heading North from EW Road (30%): " + totalthrusystem_WestCarGoingNorth);
        System.out.println("Total of Cars Heading South from EW Road (70%): " + totalthrusystem_WestCarGoingSouth);
        System.out.println("Total of Cars On EW Road: " + (totalthrusystem_WestCarGoingNorth+totalthrusystem_WestCarGoingSouth));
        LightCycle(workevent.getTime());
        System.out.println("Total Time in Line: " + totaltimeinline);
        System.out.println("Total Time in Line For Car Waiting on Road NS Going North: " + totaltimeinlineNSGoingN);
        System.out.println("Total Time in Line For Car Waiting on Road NS Going West: " + totaltimeinlineNSGoingWest);
        System.out.println("Total Time in Line For Car Waiting on Road EW Going North: " + totaltimeinlineEWGoingN);
        System.out.println("Total Time in Line For Car Waiting on Road EW Going South: " + totaltimeinlineEWGoingSouth);
        System.out.println("\n");
        System.out.println("Total Time in Line For Car Waiting on Road NS: " + (totaltimeinlineNSGoingN+totaltimeinlineNSGoingWest));
        System.out.println("Total Time in Line For Car Waiting on Road EW: " + (totaltimeinlineEWGoingN+totaltimeinlineEWGoingSouth));
        System.out.println("\n");
        DecimalFormat df = new DecimalFormat("0.000");
        System.out.println("-------------------------------------------------------------");
        System.out.println("Average Time in Line For Car Waiting on Road NS: " + df.format(((totalthrusystem_NorthCar+totalthrusystem_WestCar)/(totaltimeinlineNSGoingN+totaltimeinlineNSGoingWest))) + " minutes");
        System.out.println("Average Time in Line For Car Waiting on Road EW: " +df.format(((totalthrusystem_WestCarGoingNorth+totalthrusystem_WestCarGoingSouth)/(totaltimeinlineEWGoingN+totaltimeinlineEWGoingSouth)))+ " minutes");
        System.out.println("\n");
        System.out.println("Total Cars in Line For Car Waiting on Road NS: " + waittotalNS);
        System.out.println("Total Cars in Line For Car Waiting on Road EW: " + waittotalEW);
        System.out.println("\n");
        System.out.println("Average Cars in Line For Car Waiting on Road NS: " + Math.round(totalwaittotalNS/waittotalNS));
        System.out.println("Average Cars in Line For Car Waiting on Road EW: " + Math.round(totalwaittotalEW/waittotalEW));
        //System.out.println("Average Cars in Line For Car Waiting on Road NS: " + df.format(((totalthrusystem_NorthCar+totalthrusystem_WestCar)/2)));
        //System.out.println("Average Cars in Line For Car Waiting on Road EW: " + df.format(((totalthrusystem_WestCarGoingNorth+totalthrusystem_WestCarGoingSouth)/2)));
        System.out.println("-------------------------------------------------------------");
        System.out.println("\n");
        System.out.println("End of Traffic Sim");

    }// end of main

    public static void LightCycle(double x){ // x = 6,000 minutes 12 Light Cycles per hour
        int cycle_per_hour = 12;
        double minutes = x;
        double hours = (x/60);
        double total_cycles = (cycle_per_hour * hours);
        System.out.println("\n"); // should be 6,000 minutes
        System.out.println(minutes + " minutes"); // should be 6,000 minutes
        System.out.println( hours + " hours" ) ;
        System.out.println(total_cycles + " light cycles for " + hours + " hours" ) ;
        System.out.println( cycle_per_hour + " light cycles per hour") ;
        System.out.println( total_cycles * 3 + " minutes that NS is green") ;
        System.out.println( total_cycles * 2 + " minutes that EW is green") ;
        System.out.println("\n");
    }
    private static int getPoissonRandom(double mean) {
        Random r = new Random();
        double L = Math.exp(-mean);
        int k = 0;
        double p = 1.0;
        do {
            p = p * r.nextDouble();
            k++;
        } while (p > L);
        return k - 1;
    }

    public static void OutaHere(GenericManagerTraffic<Car> CustLine, int Balkid)
    {// this function removes a balking customer from the Queue line CustLine.  It traverces the line, finds the customer with balkid
        // and removes them
        int i,numinline,CBalkid;

        Car workcust=new Car(-9, 0);
        //prepare to trasverse the customer line
        numinline=CustLine.getmcount();
        workcust=CustLine.getvalue(0);
        CBalkid=workcust.GetMyBalk();
        i=0;
        while((CBalkid!=Balkid)&&(i<=(numinline-1)))
        {
            workcust=CustLine.getvalue(i);
            CBalkid=workcust.GetMyBalk();
            // System.out.println("in OutaHere checking customer"+i+"with balkid"+CBalkid+ "against balkid "+Balkid);
            i++;
        }
        // removing customer i from the line
        //System.out.println("removing Customer with id"+ CBalkid+ "against balkid"+Balkid);
        if(i==0){CustLine.removem(0);//we are removing the first customer in line
            //       System.out.println("removing the first customer in line");
        }
        else
        if((CBalkid==Balkid)&&(i>0))CustLine.removem(i-1);
        return;
    }//end of OutaHere
    public static void PurgeEvent(GenericManagerTraffic<TrafficEvent> EventQue, int Balkid)
    {// this function removes a balking event from the Event Queue  It traverces the Event Que, finds the event with Balkid
        // and removes it
        int i,numinqueue,EBalkid;
        TrafficEvent workevent=new TrafficEvent(1,1.0,1);
        //prepare to trasverse the event queue
        numinqueue=EventQue.getmcount();
        workevent=EventQue.getvalue(0);
        EBalkid=workevent.getMyCust();
        i=0;
        while((EBalkid!=Balkid)&&(i<=(numinqueue-1)))
        {
            workevent=EventQue.getvalue(i);
            EBalkid=workevent.getMyCust();
            //  System.out.println("in PurgeEvent checking Event"+i+"with EBalkid"+EBalkid+ "against balkid "+Balkid);
            i++;
        }
        // removing customer i from the line
        //  System.out.println("removing Event with id"+ EBalkid+ "against balkid"+Balkid);
        if(EBalkid==Balkid)EventQue.removem(i-1);
        return;
    }//end of PurgeEvent
    public static void PrintCustQue(GenericManagerTraffic<Car> MyQueue)
    {int numinqueue;
        /*
        This is wrong but this method is never called!!!!!!!!
         */
        Car workcust=new Car(123, 1);
        numinqueue=MyQueue.getmcount()-1;
        for(int i=0;i<=numinqueue;i++)
        { workcust=MyQueue.getvalue(i);
            // System.out.println("this is the "+i+"person in line");
            // System.out.println("balk id is "+workcust.GetMyBalk());
            //System.out.println("timeArrive is"+workcust.GettimeArrive());
        }//end of for write loop
        return;
    }//End of PrintCustQue
    public static void PrintEventQue(GenericManagerTraffic<TrafficEvent> EventQue)
    {int numinevent;
        TrafficEvent workevent=new TrafficEvent(1,1.0,1);
        numinevent=EventQue.getmcount()-1;
        // System.out.println("printing event queue there are"+(numinevent+1)+"in event queue");
        for(int i=0;i<=numinevent;i++)
        {workevent=EventQue.getvalue(i);
            // System.out.println("his is the "+i+"event in the queue");
            //   System.out.println("balk id is "+workevent.getMyCust());
            //System.out.println("event type is"+workevent.getEtype());
            //System.out.println("event time is "+workevent.getTime());
        }//end of for
    }// end of PrintEventQue
    public static double TimetoArriveorServe(double rate)
    {//this is the ramdom process to determine the time to arrive or the service time.  rate is the arrival or service rate.
        double deltime;
        double bigx;;
        bigx=Math.random();
        if(bigx>0.9)bigx=Math.random();
        deltime=-Math.log(1.0-bigx)/rate;
        // System.out.println("in time to arrive with rate "+rate+" the del time is "+deltime+" bigx is "+bigx);
        return deltime;
    }//this is the end of the random process generator for deltime
    public static double UpdateCustomer(GenericManagerTraffic<Car> custline, double deltime)
    {// this function adds up all the time spent for a customer in line for this deltime
        double linetime=0.0;
        int custinline;
        custinline=custline.getmcount();
        if(custinline==0)
            return linetime;
        else
            return linetime=deltime*custinline;
    }//end of UpdateCustomer
    public static double UpdateServers(Car s1, boolean b1, Car s2, boolean b2, double deltime)
    {// this function updates the time to customers in the servers
        double servetime=0.0;
        if(b1&&b2)return servetime=2*deltime;
        else
        if(b1||b2)servetime=deltime;
        return servetime;
    }//end of UpdateServers
}//end of Multiserverqueingwitharraylist
class Car implements Comparable{
    /* This is Customer class.  It stores the time the customer gets Nline, time gets Nserver and time Nsystem;  It also keeps an id to the
    Balk event associated with this customer.*/
    protected double timeNline;
    protected double timeNserver;
    protected double timeNsystem;
    protected double timeArrive;
    protected int mynum;
    protected int type;
    protected int MyBalk;//this is the unique identifier of my balking event
    public Car(int x, int y)
    { // create the customer object.
        timeNline=timeNserver=timeNsystem=0;
        mynum=x;
        MyBalk=x;
        type=y;
    }
    public int compareTo(Object o){//the Customer class must have a comparable if we are to use in the queue manager
        //return Integer.compare(((Patient) o).GetType(), GetType());
        if(GetType()>((Car)o).GetType())return 1;
        else
        if(GetType()<((Car)o).GetType())return -1;
        else return 0;
        /*
           if(GetType()>((Patient)o).GetType())return 1;
        else
        if(GetType()<((Patient)o).GetType())return -1;
        else return 0;



        if(GetNline()>((Patient)o).GetNline())return 1;// if time a > time b return 1
        else
        if(GetNline()<((Patient)o).GetNline())return -1;//if time a < time b return -1
        else return 0;
        */

    }//end of compareTo

    public void SetType(int y){
        type=y;
    }
    public void SetArrive(double x){
        //the time we arrive is set at directly from x
        timeArrive=x;}
    public void SetNline(double x){
        //note that we add the value of x it is the del time
        timeNline+=x;}
    public void SetNserver(double x){
        //note that we add the value of x it is the del time
        timeNserver+=x;}
    public void SetNsystem(){timeNsystem=timeNline+timeNserver;}
    public void SetBalk(int x){
        //  x is the balk event
        MyBalk=x;
    }// end of SetBalk
    public int GetType(){return type;}
    public double GettimeArrive(){return timeArrive;}
    public double GetNline(){return timeNline;}
    public double GetNserver(){return timeNserver;}
    public double GetNsystem(){return timeNsystem;}
    public int GetMyBalk(){return MyBalk;}
}//end of Customer Class
class TrafficEvent implements Comparable {
    /* This is the event class.  Events hold an event type, an event time and in the case of a balk event, a pointer to customer
     when the event is a balk event.*/
    protected int x;// event type
    protected double time;// this is the time of the event
    protected int MyCust;//it this a balk event, this a unique identifier of the balking customer;
    protected int etype;//this is the event type
    public TrafficEvent(int etype, double etime, int balkcust)
    { x=etype;
        time=etime;
        if(x==7)
        {// this is a balk event
            MyCust=balkcust;
        }
        else
        {// this is not a balk event
            MyCust=-9;
        }
    }//end of Event constructor
    public int compareTo(Object o){
        if(getTime()>((TrafficEvent)o).getTime())return 1;// if time a > time b return 1
        else
        if(getTime()<((TrafficEvent)o).getTime())return -1;//if time a < time b return -1
        else return 0;
    }//end of compareTo
    public double getTime(){return time;}
    public int getEtype(){return x;}
    public int getMyCust(){return MyCust;}
}//end of class event
class GenericManagerTraffic<T extends Comparable>{// NOTE THAT YOU MUST ADD THIS COMPARABLE TO USE
    //                                       COMPARETO FUNCTON THAT COMES ALONG WITH T
    protected ArrayList<T> mylist= new ArrayList<T>();
    protected int mcount;
    public GenericManagerTraffic()
    {// this is the generic constructor
        mcount=0;//mcount is the next available value in array myarray
    }
    public int addatend(T x)//this places values at the end of myarray
    { mylist.add(mcount++,x);
        return mcount;}
    public int getmcount(){return mcount;}
    public int addinorder(T x)
    {int i;
        // System.out.println(" in addinorder and adding an object with mcount"+mcount);
        // this places the object from smaller to larger
        if((mcount==0)||((x.compareTo(mylist.get(0)))==-1)||(x.compareTo(mylist.get(0))==0))
        {//this is less than or equal to the first entry
            mylist.add(0,x);
        }
        else
        if((x.compareTo(mylist.get(mcount-1))==1)||(x.compareTo(mylist.get(mcount-1))==0))
        {// x is greater than the last entry
            mylist.add(mcount,x);
        }
        else
        {// this object is greater than the first and less than the last
            i=0;
            while((i<mcount)&&(x.compareTo(mylist.get(i))==1))i++;
            mylist.add(i,x);
        }
        // add one to mcount
        mcount++;
        // for(i=0;i<=mcount-1;i++)System.out.println("in mylist at"+i+"value is "+mylist.get(i));
        // System.out.println("leaving addinorder mcount is "+mcount);
        return mcount;
    }// end of add in order
    public int addatfront(T x)
    {// add this object at the front of the list
        mylist.add(0,x);
        mcount++;
        return mcount;
    }
    public T getvalue(int i)//this gets values from myarray
    { if (i<mcount)return mylist.get(i);
    else
    {//System.out.println("in getvalue trying to get a value "+i+" when the value of mcount is "+mcount);
        return mylist.get(0);
    }
    }//end of getvalue
    public void ManageAndSort()       {/* This is a generic sort.  It will sort anything that the manager manages BUT the objects
          being sorted must support the compareTo function*/
        //this method will sort an array of Flat objects based on their CompareTo function
        T xsave, ysave,a,b;
        int isw=1,xlast=mylist.size();
        while (isw==1)
        {isw=0;
            for(int i=0;i<=xlast-2;i++)
            {a=mylist.get(i);
                b=mylist.get(i+1);
                switch (a.compareTo(b))
                {
                    case 1://the objects in array x are in the right order
                        break;
                    case -1:// objects out of order, they must be changed.
                        xsave=mylist.get(i);
                        ysave=mylist.get(i+1);
                        mylist.remove(i);
                        mylist.add(i,ysave);
                        mylist.remove(i+1);
                        mylist.add(i+1,xsave);
                        // mylist.add(i,mylist.get(i+1));
                        //mylist.add(i+1,xsave);
                        isw=1;
                        break;
                    default://objects are equal no chanbe
                }//end of switch
            }//end of for
        }//end of while
    }// ManageandSort
    public void removem(int i)
    {//This removes the i'th value from the list
        if((i>=0)&&(i<=mcount-1))
        {mylist.remove(i);
            mcount--;
        }
        return;
    }//end of removem
}  //end of GenericManager

