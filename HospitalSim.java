/*This is a simulation program written in Java. It represents a multiserver queing situation.  Customers arrive at a service facility which has two
service bays.  If either bay is empty customers move immediately into that bay for service.  If both bays are full, customers move into line.  After
customers have spent a random time in line, they may balk and leave the line.  The simulation has the following events;
Event Type       Description
                Customer Arrives at the facility
                Customer Enters line at the facility
                Customer Enters service bay 1
                Customer Enters service bay 2
                Customer Leaves service bay 1
                Customer Leaves service bay 2
                Customer balks and leaves waiting line
                Simulation Shut down
The simulation has two main structures;
The event queue, a set of ordered (time lower to higher) of linked list event objects
The waiting line, a set of linked (by a linked list) objects representing the customers

Author: Josiah Randleman

*/
import java.util.*;
import java.lang.*;

public class HospitalSim
{
    public static void main(String[] args)throws Exception
    {
        double  Bigtime=0.0; // this is the simulation main clock in seconds
        double  Endtime=100.0;// we will test the queue structues for 100 seconds.
        double  Eventtime;// this is the event time
        double deltime;
        double balktime=5.0;
        //Now create an event manager EventQue2 and a Que Simulation Manager QueMgr
        GenericManagerPatient<Event2> EventQue=new GenericManagerPatient<Event2>();
        //Now create an a queue of folks waiting for service.
        GenericManagerPatient<Patient> MyQueue=new GenericManagerPatient<Patient>();
        int MyBalkid=0;//this is the unique balk id
        int numinque;
        int numinevent;// number of events in the event queue
        int totalthrusystem=0, totalthruline=0, totalthrufac=0, totalthrusystem_heart = 0,totalthrusystem_gastro = 0, totalthrusystem_bleeding = 0 ;
        double totaltimeinline=0.0,totaltimeinsystem=0.0,totaltimeinservers=0.0,totaltimeinservers2=0.0,ttil, ttis;
        double totaltimeinline2=0.0,totaltimeinsystem2=0.0;
        int MyBCust;//this is the customer id from a balk event
        int totalbalked=0, heartbalked=0, gastrobalked=0, bleedingbalked=0;//the number of customers that balked
        int totalheart=0, totalgastro=0, totalbleeding=0;
        boolean busy1=false;//this is server 1 it is empty
        boolean busy2=true;// this is server 2 it is empty
        Patient served1;
        Patient served2;
        Patient newcust;
        Patient workcust;

        served1=new Patient(-9, 0);//this is the customer in server1
        served2=new Patient(-9, 0);//this is the customer in server2
        newcust=new Patient(-9, 0);//this is a work customer object for those entereing the queue
        workcust=new Patient(-9, 0);//this is a work customer object for those coming from the queue

        //Patient served1_2=new Patient(-9,1);//this is the customer in server1
        //Patient served2_2=new Patient(-9);//this is the customer in server2
        double deltimeserv;//this is the service time
        double timearrive, deltimearv;//this is the timearrival

        // now create the last event of the simulation
        Event2 workevent=new Event2(8,6000.0,0);
        //add the event in the queue
        numinevent=EventQue.addinorder(workevent);
        //now add the arrival for the first customer
        deltimearv=TimetoArriveorServe(0.05);//customers arrive at the rate of 5/20 min
        //the event time is current time plus the delta time
        Eventtime=Bigtime+deltimearv;
        //System.out.println("the first customer arrives at "+Eventtime2);
        //Creater the event for the first customer to arrive.
        workevent=new Event2(1,Eventtime,0);
        //Store this event on the queue
        numinevent=EventQue.addinorder(workevent);
        //now start processing the events.
        // get the first event off of the event queue
        workevent=EventQue.getvalue(0);
        while(workevent.getEtype()!=8)
        //for(int ixx=1;ixx<=15;ixx++)
        {//this is a valid event. Get ready to update the time
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
            switch (workevent.getEtype())
            {case 1://customer arrives at the facility.
                // generate the object for the customer
                //if servers busy put the customer generate a customer enters line event
                //if servers not busy, generate a customer object and put the the customer in the server
                if((busy1==false)&&(numinque<=0))
                {//server 1 is not busy and there is no one in the customer que, put the customer in the server 1
                    // create the customer object

                    if(x<=30) {
                        newcust=new Patient(-9, 1);
                        deltimeserv=TimetoArriveorServe(0.03);
                        //totalheart_2++;
                    } else if(x<=50){  // this is for the gastro patient
                        newcust=new Patient(-9, 2);
                        deltimeserv=TimetoArriveorServe(0.06);
                        //totalbleeding_2++;
                    } else {  // this is for the bleeding patient
                        newcust=new Patient(-9, 3);
                        deltimeserv=TimetoArriveorServe(0.1);
                        //totalgastro_2++;
                    }

                    //set the arrival time for this customer
                    newcust.SetArrive(Bigtime);
                    //put this customer in server 1
                    busy1=true;
                    served1=newcust;
                    // generate the finished server event for this customer

                    Eventtime=deltimeserv+Bigtime;
                    workevent=new Event2(5,Eventtime,-9);
                    //put this event in the event queue
                    numinevent=EventQue.addinorder(workevent);
                }

                else
                if((busy1==true)&&(busy2==false)&&(numinque<=0))
                {//server 2 is open and there is no one in the customer que, send the customer to server 2

                    if(x<=30) {
                        newcust=new Patient(-9, 1);
                        deltimeserv=TimetoArriveorServe(0.03);
                    } else if(x<=50){  // this is for the gastro patient
                        newcust=new Patient(-9, 2);
                        deltimeserv=TimetoArriveorServe(0.06);
                    } else {  // this is for the bleeding patient
                        newcust=new Patient(-9, 3);
                        deltimeserv=TimetoArriveorServe(0.1);
                    }

                    // set the arrival time for this customer
                    newcust.SetArrive(Bigtime);
                    //put this customer in server 2
                    busy2=true;
                    served2=newcust;
                    // generate the finished server event for this customer

                    Eventtime=deltimeserv+Bigtime;
                    workevent=new Event2(6,Eventtime,-9);
                    //put this event in the event queue
                    numinevent=EventQue.addinorder(workevent);
                }//done in server 2
                else
                if((busy1==true)&&(busy2==true))
                {//both servers are busy put the customer in line
                    //first generate the customer, note this customer must have a unique ID
                    //if((Bigtime2>=500.00)&&(Bigtime2<=600.00))System.out.println("customer goes into the line");
                    MyBalkid++;
                    if(x<=30) {
                        newcust=new Patient(MyBalkid, 1);
                        balktime=Bigtime + Balk(1);

                    } else if(x<=50){  // this is for the gastro patient
                        newcust=new Patient(MyBalkid, 2);
                        balktime=Bigtime + Balk(2);

                    } else {  // this is for the bleeding patient
                        newcust=new Patient(MyBalkid, 3);
                        balktime=Bigtime + Balk(3);

                    }

                    // set the arrival time for this customer
                    newcust.SetArrive(Bigtime);
                    // now put this customer in line at end
                    MyQueue.addatend(newcust);

                    // now create the event
                    workevent=new Event2(7,balktime,MyBalkid);
                    // add the event to the event queue
                    numinevent=EventQue.addinorder(workevent);
                }//the customer is in the line
                //generate the event for the next customer to arrive
                deltimearv=TimetoArriveorServe(0.05);
                //the event time is current time plus the delta time
                Eventtime=Bigtime+deltimearv;
                //if((Bigtime2>=500.00)&&(Bigtime2<=600.00))System.out.println("the next customer arrives at "+Eventtime2);
                //Creater the event for the first customer to arrive.
                workevent=new Event2(1,Eventtime,0);
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

                        if (served1.GetType() == 1) {
                            totalheart++;
                        } else if (served1.GetType() == 2) {
                            totalgastro++;
                        } else if (served1.GetType() == 3) {
                            totalbleeding++;
                        }

                        if(x<=30) {
                            //totalheart_2++;
                            deltimeserv=TimetoArriveorServe(0.03);

                        } else if(x<=50){  // this is for the gastro patient
                            //totalgastro_2++;
                            deltimeserv=TimetoArriveorServe(0.06);

                        } else {  // this is for the bleeding patient
                            deltimeserv=TimetoArriveorServe(0.1);
                            //totalbleeding_2++;
                        }

                        Eventtime=deltimeserv+Bigtime;
                        workevent=new Event2(5,Eventtime,-9);
                        //put this event in the event queue
                        numinevent=EventQue.addinorder(workevent);
                    }//end of enter service bay 1
                    else
                    {// either we are busy and have had an event collision or there is noone in the line
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
                            totalheart++;
                        } else if (served1.GetType() == 2) {
                            totalgastro++;
                        } else if (served1.GetType() == 3) {
                            totalbleeding++;
                        }


                        if(x<=30) {
                            deltimeserv=TimetoArriveorServe(0.03);
                        } else if(x<=50){  // this is for the gastro patient

                            deltimeserv=TimetoArriveorServe(0.06);
                        } else {  // this is for the bleeding patient

                            deltimeserv=TimetoArriveorServe(0.1);
                        }

                        Eventtime=deltimeserv+Bigtime;
                        workevent=new Event2(6,Eventtime,-9);
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
                        totalthrusystem_heart++;
                    } else if (served1.GetType() == 2) {
                        totalthrusystem_gastro++;
                    } else if (served1.GetType() == 3) {
                        totalthrusystem_bleeding++;
                    }
                    //if((Bigtime2>=500.00)&&(Bigtime2<=600.00))System.out.println("customer leaves server1 numinque2"+numinque2);
                    if(numinque>0)
                    {//there are customers in the line, generate a customer enter service bay 1 now at Bigtime2
                        //NOTE PROBLEMS WITH COLLISION EVENTS
                        workevent=new Event2(3,Bigtime+0.01,-9);
                        //put this event in the event queue
                        numinevent=EventQue.addinorder(workevent);
                    }
                    break;
                case 6://customer leaves service bay 2
                    //set the server to not busy
                    //if there are people in line generate an enter service bay 2 event
                    busy2=true;
                    totalthrusystem++;
                    if (served1.GetType() == 1) {
                        totalthrusystem_heart++;
                    } else if (served1.GetType() == 2) {
                        totalthrusystem_gastro++;
                    } else if (served1.GetType() == 3) {
                        totalthrusystem_bleeding++;
                    }
                    numinque=MyQueue.getmcount();
                    //if((Bigtime2>=500.00)&&(Bigtime2<=600.00))System.out.println("customer leaves servere2");
                    if(numinque>0)
                    {//there are customers in the line, generate a customer enter service bay 2 now at Bigtime2
                        workevent=new Event2(4,Bigtime+.01,-9);
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
                        heartbalked++;
                        totalheart++;
                    } else if (served1.GetType() == 2) {
                        gastrobalked++;
                        totalgastro++;
                    } else if (served1.GetType() == 3) {
                        bleedingbalked++;
                        totalbleeding++;
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
        System.out.println("\n\n");
        System.out.println("Printing the Statistics for this Run");
        System.out.println("There were a total of "+totalthruline+" customers going through the line");
        System.out.println("These customers spent a average time in line of "+totaltimeinline/totalthruline+" ");
        System.out.println("There were a total of "+totalbalked+" patients who died");
        System.out.println("customers spent an average time of "+totaltimeinservers/totalthrusystem+" in the servers");
        System.out.println("\n");
        System.out.println("Total Heart Patients: " + totalheart);
        System.out.println("Total Gastro Patients: " + totalgastro);
        System.out.println("Total Bleeding Patients: " +totalbleeding);
        System.out.println("My Total: " +(totalheart+totalgastro+totalbleeding));
        System.out.println("\n");
        System.out.println("Total Serviced of Heart Patients: " +(totalheart-heartbalked));
        System.out.println("Total Serviced of Gastro Patients: " +(totalgastro-gastrobalked));
        System.out.println("Total Serviced of Bleeding Patients: " + (totalbleeding-bleedingbalked));
        System.out.println("Total Services: " +((totalheart+totalgastro+totalbleeding) -(heartbalked+gastrobalked+bleedingbalked)));
        System.out.println("\n");
        System.out.println("Losses of Heart Patients: " +heartbalked);
        System.out.println("Losses of Gastro Patients: " +gastrobalked);
        System.out.println("Losses of Bleeding Patients: " +bleedingbalked);
        System.out.println("Total Losses: " +(heartbalked+gastrobalked+bleedingbalked));
        System.out.println("\n");
        System.out.println("Average Time of Heart Patients in Line: " +totaltimeinline/totalthrusystem_heart);
        System.out.println("Average Time of Gastro Patients in Line: " +totaltimeinline/totalthrusystem_gastro);
        System.out.println("Average Time of Bleeding Patients in Line: " +totaltimeinline/totalthrusystem_bleeding);
        System.out.println("\n");
        System.out.println("End of Run 1");
        System.out.println("\n");
        System.out.println("Start of Run 2");
        System.out.println("\n");
        double  Bigtime2=0.0; // this is the simulation main clock in seconds
        double  Endtime2=100.0;// we will test the queue structues for 100 seconds.
        double  Eventtime2;// this is the event time
        double deltime2;
        double balktime2=5.0;
        //Now create an event manager EventQue2 and a Que Simulation Manager QueMgr
        GenericManagerPatient<Event2> EventQue2=new GenericManagerPatient<Event2>();
        //Now create an a queue of folks waiting for service.
        GenericManagerPatient<Patient> MyQueue2=new GenericManagerPatient<Patient>();
        int MyBalkid2=0;//this is the unique balk id
        int numinque2;
        int numinevent2;// number of events in the event queue
        int totalthrusystem_2=0, totalthruline_2=0, totalthrufac_2=0, totalthrusystem_heart_2 = 0,totalthrusystem_gastro_2 = 0, totalthrusystem_bleeding_2 = 0 ;
        double totaltimeinline_4=0.0,totaltimeinsystem_4=0.0,totaltimeinservers_4=0.0,totaltimeinservers2_4=0.0,ttil_2, ttis_2;
        double totaltimeinline2_2=0.0,totaltimeinsystem2_2=0.0;
        int MyBCust_2;//this is the customer id from a balk event
        int totalbalked_2=0, heartbalked_2=0, gastrobalked_2=0, bleedingbalked_2=0;//the number of customers that balked
        int totalheart_2=0, totalgastro_2=0, totalbleeding_2=0;
        boolean busy1_2=false;//this is server 1 it is empty
        boolean busy2_2=false;// this is server 2 it is empty
        Patient served1_2;
        Patient served2_2;
        Patient newcust_2;
        Patient workcust_2;

        served1_2=new Patient(-9, 0);//this is the customer in server1
        served2_2=new Patient(-9, 0);//this is the customer in server2
        newcust_2=new Patient(-9, 0);//this is a work customer object for those entereing the queue
        workcust_2=new Patient(-9, 0);//this is a work customer object for those coming from the queue

        // this is for the heart patient

        //Patient served1_2=new Patient(-9,1);//this is the customer in server1
        //Patient served2_2=new Patient(-9);//this is the customer in server2
        double deltimeserv_2;//this is the service time
        double timearrive_2, deltimearv_2;//this is the timearrival

        // now create the last event of the simulation
        Event2 workevent_2=new Event2(8,6000.0,0);
        //add the event in the queue
        numinevent2=EventQue2.addinorder(workevent_2);
        //now add the arrival for the first customer
        deltimearv_2=TimetoArriveorServe(0.05);//customers arrive at 3 per hour 3/60
        //the event time is current time plus the delta time
        Eventtime2=Bigtime2+deltimearv_2;
        //System.out.println("the first customer arrives at "+Eventtime2);
        //Creater the event for the first customer to arrive.
        workevent_2=new Event2(1,Eventtime2,0);
        //Store this event on the queue
        numinevent2=EventQue2.addinorder(workevent_2);
        //now start processing the events.
        // get the first event off of the event queue
        workevent_2=EventQue2.getvalue(0);
        while(workevent_2.getEtype()!=8)
        //for(int ixx=1;ixx<=15;ixx++)
        {//this is a valid event. Get ready to update the time
            deltime2=workevent_2.getTime()-Bigtime2;
            // now update everybody with this deltime2
            ttil_2= UpdateCustomer(MyQueue2,deltime2);
            totaltimeinline_4+=ttil_2;
            totaltimeinline2_2+=ttil_2*ttil_2;
            // now update everybody in the servers
            ttis_2= UpdateServers(served1_2,busy1_2,served2_2,busy2_2,deltime2);
            // now get the event type and process it. First update the time
            totaltimeinservers_4+=ttis_2;
            totaltimeinservers2_4+=ttis_2*ttis_2;
            Bigtime2=workevent_2.getTime();
            //if((Bigtime2>=500.00)&&(Bigtime2<=600.00))System.out.println("++++++++++++++++++ THE TIME IS NOW++++++++++++++++ "+Bigtime2);
            // get the number in the Customer Queue at this time
            numinque2=MyQueue2.getmcount();
            int x= (int)(Math.random()*100);
            switch (workevent_2.getEtype())
            {case 1://customer arrives at the facility.
                // generate the object for the customer
                //if servers busy put the customer generate a customer enters line event
                //if servers not busy, generate a customer object and put the the customer in the server
                if((busy1_2==false)&&(numinque2<=0))
                {//server 1 is not busy and there is no one in the customer que, put the customer in the server 1
                    // create the customer object
                    //if((500.00<=Bigtime2)&&(Bigtime2<=600.00)) System.out.println("customer goes into server1");

                    if(x<=30) {
                        newcust_2=new Patient(-9, 1);
                        deltimeserv_2=TimetoArriveorServe(0.06);
                        //totalheart_2++;
                    } else if(x<=50){  // this is for the gastro patient
                        newcust_2=new Patient(-9, 2);
                        deltimeserv_2=TimetoArriveorServe(0.13);
                        //totalbleeding_2++;
                    } else {  // this is for the bleeding patient
                        newcust_2=new Patient(-9, 3);
                        deltimeserv_2=TimetoArriveorServe(0.2);
                        //totalgastro_2++;
                    }


                    //set the arrival time for this customer
                    newcust_2.SetArrive(Bigtime2);
                    //put this customer in server 1
                    busy1_2=true;
                    served1_2=newcust_2;
                    // generate the finished server event for this customer

                    Eventtime2=deltimeserv_2+Bigtime2;
                    workevent_2=new Event2(5,Eventtime2,-9);
                    //put this event in the event queue
                    numinevent2=EventQue2.addinorder(workevent_2);
                }


                if((busy2_2==false)&&(numinque2<=0))
                {//server 1 is not busy and there is no one in the customer que, put the customer in the server 1
                    // create the customer object
                    //if((500.00<=Bigtime2)&&(Bigtime2<=600.00)) System.out.println("customer goes into server1");

                    if(x<=30) {
                        newcust_2=new Patient(-9, 1);
                        deltimeserv_2=TimetoArriveorServe(0.06);//customers are served at the rate of 8 per 20 min
                        //totalheart_2++;
                    } else if(x<=50){  // this is for the gastro patient
                        newcust_2=new Patient(-9, 2);
                        deltimeserv_2=TimetoArriveorServe(0.13);//customers are served at the rate of 8 per 20 min
                        //totalbleeding_2++;
                    } else {  // this is for the bleeding patient
                        newcust_2=new Patient(-9, 3);
                        deltimeserv_2=TimetoArriveorServe(0.2);//customers are served at the rate of 8 per 20 min
                        //totalgastro_2++;
                    }


                    //set the arrival time for this customer
                    newcust_2.SetArrive(Bigtime2);
                    //put this customer in server 1
                    busy2_2=true;
                    served2_2=newcust_2;
                    // generate the finished server event for this customer

                    Eventtime2=deltimeserv_2+Bigtime2;
                    workevent_2=new Event2(5,Eventtime2,-9);
                    //put this event in the event queue
                    numinevent2=EventQue2.addinorder(workevent_2);
                }
                else
                if((busy1_2==true)&&(busy2_2==false)&&(numinque2<=0))
                {//server 2 is open and there is no one in the customer que, send the customer to server 2
                    // create the customer object

                    /*
                    This does not work on purpose
                     */
                    //if((Bigtime2>=500.00)&&(Bigtime2<=600.00))System.out.println("customer goes in server2");

                    if(x<=30) {
                        newcust_2=new Patient(-9, 1);
                        deltimeserv_2=TimetoArriveorServe(0.06);//customers are served at the rate of 8 per 20 min
                    } else if(x<=50){  // this is for the gastro patient
                        newcust_2=new Patient(-9, 2);
                        deltimeserv_2=TimetoArriveorServe(0.13);//customers are served at the rate of 8 per 20 min
                    } else {  // this is for the bleeding patient
                        newcust_2=new Patient(-9, 3);
                        deltimeserv_2=TimetoArriveorServe(0.2);//customers are served at the rate of 8 per 20 min
                    }

                    // set the arrival time for this customer
                    newcust_2.SetArrive(Bigtime2);
                    //put this customer in server 2
                    busy2_2=true;
                    served2_2=newcust_2;
                    // generate the finished server event for this customer

                    Eventtime2=deltimeserv_2+Bigtime2;
                    workevent_2=new Event2(6,Eventtime2,-9);
                    //put this event in the event queue
                    numinevent2=EventQue2.addinorder(workevent_2);
                }//done in server 2
                else
                if((busy1_2==true)&&(busy2_2==true))
                {//both servers are busy put the customer in line
                    //first generate the customer, note this customer must have a unique ID
                    //if((Bigtime2>=500.00)&&(Bigtime2<=600.00))System.out.println("customer goes into the line");
                    MyBalkid2++;
                    //System.out.println("Both Servers are Busy");

                    if(x<=30) {
                        newcust_2=new Patient(MyBalkid2, 1);
                        balktime2= Bigtime2 + Balk(1);

                    } else if(x<=50){  // this is for the gastro patient
                        newcust_2=new Patient(MyBalkid2, 2);
                        balktime2=Bigtime2 + Balk(2);

                    } else {  // this is for the bleeding patient
                        newcust_2=new Patient(MyBalkid2, 3);
                        balktime2=Bigtime2 + Balk(3);

                    }



                    // set the arrival time for this customer
                    newcust_2.SetArrive(Bigtime2);
                    // now put this customer in line at end
                    MyQueue2.addatend(newcust_2);
                    //now create the customer Balk event right now all customers will balk in 10 minutes

                    // now create the event
                    workevent_2=new Event2(7,balktime2,MyBalkid2);
                    // add the event to the event queue
                    numinevent2=EventQue2.addinorder(workevent_2);
                }//the customer is in the line
                //generate the event for the next customer to arrive
                deltimearv_2=TimetoArriveorServe(0.05);//customers arrive at the rate of 5/20 min
                //the event time is current time plus the delta time
                Eventtime2=Bigtime2+deltimearv_2;
                //if((Bigtime2>=500.00)&&(Bigtime2<=600.00))System.out.println("the next customer arrives at "+Eventtime2);
                //Creater the event for the first customer to arrive.
                workevent_2=new Event2(1,Eventtime2,0);
                //Store this event on the queue
                numinevent2=EventQue2.addinorder(workevent_2);
                break;
                case 2://customer enters the line at the facility
                    //generate the balk event for this customer and put them in line
                    //System.out.println("this is event 2, we have incorporated it in the arrival event if we are here we are in trouble");
                    break;
                case 3:// customer enters service bay 1
                    // decrement the number in line
                    //generate completion time and departure event for this customer
                    // set this server to busy
                    numinque2=MyQueue2.getmcount();
                    if((!busy1_2)&&(numinque2>0))
                    {// the customer can enter bay 1 get the customer from the front line
                        //if((Bigtime2>=500.00)&&(Bigtime2<=600.00)) System.out.println("first customer in line enters server 1");
                        workcust_2=MyQueue2.getvalue(0);
                        MyBCust_2=workcust_2.GetMyBalk();//get the customer balk event
                        PurgeEvent(EventQue2,MyBCust_2);//purge the event from the queue
                        totalthruline_2++;//this customer just came out of line
                        // delete this customer from the queue and put them in the server.
                        MyQueue2.removem(0);
                        //put this customer in server 1
                        busy1_2=true;
                        served1_2=workcust_2;
                        // generate the finished server event for this customer

                        if (served1_2.GetType() == 1) {
                            totalheart_2++;
                        } else if (served1_2.GetType() == 2) {
                            totalgastro_2++;
                        } else if (served1_2.GetType() == 3) {
                            totalbleeding_2++;
                        } else if (served2_2.GetType() == 1) {
                            totalheart_2++;
                        } else if (served2_2.GetType() == 2) {
                            totalgastro_2++;
                        } else if (served2_2.GetType() == 3) {
                            totalbleeding_2++;
                        }

                        if(x<=30) {
                            //totalheart_2++;
                            deltimeserv_2=TimetoArriveorServe(0.06);//customers are served at the rate of 8 per 20 min

                        } else if(x<=50){  // this is for the gastro patient
                            //totalgastro_2++;
                            deltimeserv_2=TimetoArriveorServe(0.13);//customers are served at the rate of 8 per 20 min

                        } else {  // this is for the bleeding patient
                            deltimeserv_2=TimetoArriveorServe(0.2);//customers are served at the rate of 8 per 20 min
                            //totalbleeding_2++;
                        }

                        Eventtime2=deltimeserv_2+Bigtime2;
                        workevent_2=new Event2(5,Eventtime2,-9);
                        //put this event in the event queue
                        numinevent2=EventQue2.addinorder(workevent_2);
                    }//end of enter service bay 1
                    else
                    {// either we are busy and have had an event collision or there is noone in the line
                        System.out.println("in event 3 customer enters service bay 1 unable to process event");
                    }
                    break;
                case 4: // customer enters service bay 2
                    //decrement the number in line
                    //generate completion time and departure event for this customr
                    // set this server to busy
                    numinque2=MyQueue2.getmcount();
                    if((!busy2_2)&&(numinque2>0))
                    {// the customer can enter bay 1 get the customer from the front line
                        //if((Bigtime2>=500.00)&&(Bigtime2<=600.00))System.out.println("customer enters service2 from line");
                        workcust_2=MyQueue2.getvalue(0);
                        MyBCust_2=workcust_2.GetMyBalk();//get the customer balk event
                        PurgeEvent(EventQue2,MyBCust_2);//purge the event from the queue
                        totalthruline_2++;//this customer just came out of line
                        // delete this customer from the queue and put them in the server.
                        MyQueue2.removem(0);
                        //put this customer in server 2
                        busy2_2=true;
                        served2_2=workcust_2;
                        // generate the finished server event for this customer

                        if (served1_2.GetType() == 1) {
                            totalheart_2++;
                        } else if (served1_2.GetType() == 2) {
                            totalgastro_2++;
                        } else if (served1_2.GetType() == 3) {
                            totalbleeding_2++;
                        } else if (served2_2.GetType() == 1) {
                            totalheart_2++;
                        } else if (served2_2.GetType() == 2) {
                            totalgastro_2++;
                        } else if (served2_2.GetType() == 3) {
                            totalbleeding_2++;
                        }


                        if(x<=30) {
                            deltimeserv_2=TimetoArriveorServe(0.06);//customers are served at the rate of 8 per 20 min
                        } else if(x<=50){  // this is for the gastro patient

                            deltimeserv_2=TimetoArriveorServe(0.13);//customers are served at the rate of 8 per 20 min
                        } else {  // this is for the bleeding patient

                            deltimeserv_2=TimetoArriveorServe(0.2);//customers are served at the rate of 8 per 20 min
                        }

                        Eventtime2=deltimeserv_2+Bigtime2;
                        workevent_2=new Event2(6,Eventtime2,-9);
                        //put this event in the event queue
                        numinevent2=EventQue2.addinorder(workevent_2);
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
                    busy1_2=false;
                    totalthrusystem_2++;
                    numinque2=MyQueue2.getmcount();

                    if (served1_2.GetType() == 1) {
                        totalthrusystem_heart_2++;
                    } else if (served1_2.GetType() == 2) {
                        totalthrusystem_gastro_2++;
                    } else if (served1_2.GetType() == 3) {
                        totalthrusystem_bleeding_2++;
                    } else if (served2_2.GetType() == 1) {
                        totalthrusystem_heart_2++;
                    } else if (served2_2.GetType() == 2) {
                        totalthrusystem_gastro_2++;
                    } else if (served2_2.GetType() == 3) {
                        totalthrusystem_bleeding_2++;
                    }
                    //if((Bigtime2>=500.00)&&(Bigtime2<=600.00))System.out.println("customer leaves server1 numinque2"+numinque2);
                    if(numinque2>0)
                    {//there are customers in the line, generate a customer enter service bay 1 now at Bigtime2
                        //NOTE PROBLEMS WITH COLLISION EVENTS
                        workevent_2=new Event2(3,Bigtime2+0.01,-9);
                        //put this event in the event queue
                        numinevent2=EventQue2.addinorder(workevent_2);
                    }
                    break;
                case 6://customer leaves service bay 2
                    //set the server to not busy
                    //if there are people in line generate an enter service bay 2 event
                    busy2_2=false;
                    totalthrusystem_2++;
                    if (served1_2.GetType() == 1) {
                        totalthrusystem_heart_2++;
                    } else if (served1_2.GetType() == 2) {
                        totalthrusystem_gastro_2++;
                    } else if (served1_2.GetType() == 3) {
                        totalthrusystem_bleeding_2++;
                    } else if (served2_2.GetType() == 1) {
                        totalthrusystem_heart_2++;
                    } else if (served2_2.GetType() == 2) {
                        totalthrusystem_gastro_2++;
                    } else if (served2_2.GetType() == 3) {
                        totalthrusystem_bleeding_2++;
                    }

                    numinque2=MyQueue2.getmcount();
                    //if((Bigtime2>=500.00)&&(Bigtime2<=600.00))System.out.println("customer leaves servere2");
                    if(numinque2>0)
                    {//there are customers in the line, generate a customer enter service bay 2 now at Bigtime2
                        workevent_2=new Event2(4,Bigtime2+.01,-9);
                        //put this event in the event queue
                        numinevent2=EventQue2.addinorder(workevent_2);
                    }
                    break;
                case 7:// customer balks and leaves the waiting line
                    //delete the customer from the line
                    // delete the customer from the queue
                    MyBCust_2=workevent_2.getMyCust();
                    //get this customer out of line
                    OutaHere(MyQueue2,MyBCust_2);
                    //add this to the customers that have gone through the system
                    totalbalked_2++;
                    totalthruline_2++;
                    if (served1_2.GetType() == 1) {
                        heartbalked_2++;
                        totalheart_2++;
                    } else if (served1_2.GetType() == 2) {
                        gastrobalked_2++;
                        totalgastro_2++;
                    } else if (served1_2.GetType() == 3) {
                        bleedingbalked_2++;
                        totalbleeding_2++;
                    } else if(served2_2.GetType() == 1) {
                        heartbalked_2++;
                        totalheart_2++;
                    } else if (served2_2.GetType() == 2) {
                        gastrobalked_2++;
                        totalgastro_2++;
                    } else if (served2_2.GetType() == 3) {
                        bleedingbalked_2++;
                        totalbleeding_2++;
                    }
                    break;
                case 8://this is the shutdown event
                    System.out.println(" this event is type 8 and we are in the switch statement TROUBLE!");
                    continue;
                default:
                    System.out.println("this is a bad event type"+workevent_2.getEtype()+" at time "+workevent_2.getTime());
            }//this is the end of the switch statement
            // this event is processed delete it from the eventqueue
            EventQue2.removem(0);
            // now get the next event
            //System.out.println(served1_2.GetType());
            //if((Bigtime2>=500.00)&&(Bigtime2<=600.00))System.out.println("*************************the time is "+Bigtime2+"****************************");
            workevent_2=EventQue2.getvalue(0);
            //  System.out.println("The current customer queue is");
            //PrintCustQue(MyQueue2);
            //System.out.println("The current event queue is");
            //PrintEventQue(EventQue2);
        }//end of simulation loop
        //Now for the Statics
        int total = (totalheart_2+totalgastro_2+totalbleeding_2);
        System.out.println("Printing the Statistics for this Run");
        System.out.println("There were a total of "+total+" customers going through the line");
        System.out.println("These customers spent a average time in line of "+totaltimeinline_4/total+" ");
        System.out.println("There were a total of "+totalbalked_2+" patients who died");
        System.out.println("customers spent an average time of "+totaltimeinservers_4/totalthrusystem_2+" in the servers");
        System.out.println("\n");
        System.out.println("Total Heart Patients: " + totalheart_2);
        System.out.println("Total Gastro Patients: " + totalgastro_2);
        System.out.println("Total Bleeding Patients: " +totalbleeding_2);
        System.out.println("My Total: " +(totalheart_2+totalgastro_2+totalbleeding_2));
        System.out.println("\n");
        System.out.println("Total Serviced of Heart Patients: " +(totalheart_2-heartbalked_2));
        System.out.println("Total Serviced of Gastro Patients: " +(totalgastro_2-gastrobalked_2));
        System.out.println("Total Serviced of Bleeding Patients: " + (totalbleeding_2-bleedingbalked_2));
        System.out.println("Total Services: " +((totalheart_2+totalgastro_2+totalbleeding_2) -(heartbalked_2+gastrobalked_2+bleedingbalked_2)));
        System.out.println("\n");
        System.out.println("Losses of Heart Patients: " +heartbalked_2);
        System.out.println("Losses of Gastro Patients: " +gastrobalked_2);
        System.out.println("Losses of Bleeding Patients: " +bleedingbalked_2);
        System.out.println("Total Losses: " +(heartbalked_2+gastrobalked_2+bleedingbalked_2));
        System.out.println("\n");
        System.out.println("Average Time of Heart Patients in Line: " +totaltimeinline_4/totalthrusystem_heart_2);
        System.out.println("Average Time of Gastro Patients in Line: " +totaltimeinline_4/totalthrusystem_gastro_2);
        System.out.println("Average Time of Bleeding Patients in Line: " +totaltimeinline_4/totalthrusystem_bleeding_2);
        System.out.println("\n\n");

    }// end of main

    public static double Balk(int ptype){
        double x;
        double b = 0;
        x = Math.random();

        if (ptype==1){
            b = x * 10+35;
        }
        if (ptype==2){
            b = x * 20+60;
        }
        if (ptype==3){
            b = x*30+80;
        }
        return b;
    }
    public static void OutaHere(GenericManagerPatient<Patient> CustLine, int Balkid)
    {// this function removes a balking customer from the Queue line CustLine.  It traverces the line, finds the customer with balkid
        // and removes them
        int i,numinline,CBalkid;

        Patient workcust=new Patient(-9, 0);
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
    public static void PurgeEvent(GenericManagerPatient<Event2> EventQue, int Balkid)
    {// this function removes a balking event from the Event Queue  It traverces the Event Que, finds the event with Balkid
        // and removes it
        int i,numinqueue,EBalkid;
        Event2 workevent=new Event2(1,1.0,1);
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
    public static void PrintCustQue(GenericManagerPatient<Patient> MyQueue)
    {int numinqueue;
        /*
        This is wrong but this method is never called!!!!!!!!
         */
        Patient workcust=new Patient(123, 1);
        numinqueue=MyQueue.getmcount()-1;
        for(int i=0;i<=numinqueue;i++)
        { workcust=MyQueue.getvalue(i);
            // System.out.println("this is the "+i+"person in line");
            // System.out.println("balk id is "+workcust.GetMyBalk());
            //System.out.println("timeArrive is"+workcust.GettimeArrive());
        }//end of for write loop
        return;
    }//End of PrintCustQue
    public static void PrintEventQue(GenericManagerPatient<Event2> EventQue)
    {int numinevent;
        Event2 workevent=new Event2(1,1.0,1);
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
    public static double UpdateCustomer(GenericManagerPatient<Patient> custline, double deltime)
    {// this function adds up all the time spent for a customer in line for this deltime
        double linetime=0.0;
        int custinline;
        custinline=custline.getmcount();
        if(custinline==0)
            return linetime;
        else
            return linetime=deltime*custinline;
    }//end of UpdateCustomer
    public static double UpdateServers(Patient s1, boolean b1, Patient s2, boolean b2, double deltime)
    {// this function updates the time to customers in the servers
        double servetime=0.0;
        if(b1&&b2)return servetime=2*deltime;
        else
        if(b1||b2)servetime=deltime;
        return servetime;
    }//end of UpdateServers
}//end of Multiserverqueingwitharraylist
class Patient implements Comparable{
    /* This is Customer class.  It stores the time the customer gets Nline, time gets Nserver and time Nsystem;  It also keeps an id to the
    Balk event associated with this customer.*/
    protected double timeNline;
    protected double timeNserver;
    protected double timeNsystem;
    protected double timeArrive;
    protected int mynum;
    protected int type;
    protected int MyBalk;//this is the unique identifier of my balking event
    public Patient(int x, int y)
    { // create the customer object.
        timeNline=timeNserver=timeNsystem=0;
        mynum=x;
        MyBalk=x;
        type=y;
    }
    public int compareTo(Object o){//the Customer class must have a comparable if we are to use in the queue manager
        //return Integer.compare(((Patient) o).GetType(), GetType());
        if(GetType()>((Patient)o).GetType())return 1;
        else
        if(GetType()<((Patient)o).GetType())return -1;
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
class Event2 implements Comparable {
    /* This is the event class.  Events hold an event type, an event time and in the case of a balk event, a pointer to customer
     when the event is a balk event.*/
    protected int x;// event type
    protected double time;// this is the time of the event
    protected int MyCust;//it this a balk event, this a unique identifier of the balking customer;
    protected int etype;//this is the event type
    public Event2(int etype, double etime, int balkcust)
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
        if(getTime()>((Event2)o).getTime())return 1;// if time a > time b return 1
        else
        if(getTime()<((Event2)o).getTime())return -1;//if time a < time b return -1
        else return 0;
    }//end of compareTo
    public double getTime(){return time;}
    public int getEtype(){return x;}
    public int getMyCust(){return MyCust;}
}//end of class event
class GenericManagerPatient<T extends Comparable>{// NOTE THAT YOU MUST ADD THIS COMPARABLE TO USE
    //                                       COMPARETO FUNCTON THAT COMES ALONG WITH T
    protected ArrayList<T> mylist= new ArrayList<T>();
    protected int mcount;
    public GenericManagerPatient()
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


