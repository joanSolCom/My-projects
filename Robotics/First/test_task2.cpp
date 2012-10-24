// NXT++ test.cpp : Defines the entry point for the console application.
//
#include "NXT++.h"
#include <iostream>
#include <signal.h>

using namespace std;

void stop(){
    NXT::Motor::Stop(OUT_B,false);
    NXT::Motor::Stop(OUT_C,false);
}

void signal_callback_handler(int signum)
{
    stop();
    cout << "Stopping Motors" << endl;
    sleep(1);
    exit(signum);
}

//This function sets one motor forward and the other backward to make a right spin (motors set at 50% power).
void setLeftSpin(){
    stop();
    NXT::Motor::SetReverse(OUT_C, 50);
    NXT::Motor::SetForward(OUT_B, 50);
    usleep(300000);
    stop();
}

void setRightSpin(){
    stop();
    NXT::Motor::SetReverse(OUT_B, 50);
    NXT::Motor::SetForward(OUT_C, 50);
    usleep(300000);
    stop();
}

//This function sets the both motors back at 50% power.
void setBothBack(){
    stop();
    NXT::Motor::SetReverse(OUT_B, 50);
    NXT::Motor::SetReverse(OUT_C, 50);
    usleep(300000);
    stop();
}

//This function sets the both motors forwards at 50% power.
void setBothForward(){
    stop();
    NXT::Motor::SetForward(OUT_B, 50);
    NXT::Motor::SetForward(OUT_C, 50);
    //sleep(1);
    //stop();
}


int main()
{
    cout << "NXT++ test project" << endl;
    int turnCount =0;
    if(NXT::Open()) //initialize the NXT and continue if it succeds
    {
	int oldCount = NXT::Motor::GetRotationCount(OUT_A ); // obtain rotation
	NXT::Sensor::SetTouch(IN_1); 
	NXT::Sensor::SetTouch(IN_2);
	NXT::Sensor::SetSonar(IN_3);
	signal(SIGINT, signal_callback_handler);
	
	while(1) //main loop
	{
	    //Assuming IN_1 Left
	    if(NXT::Sensor::GetValue(IN_1) == true){
		
		setBothBack();
		setRightSpin();
		cout<<"IN 1"<<endl;
	    }
	    
	    if(NXT::Sensor::GetValue(IN_2) == true){
		setBothBack();
		setLeftSpin();
		cout<<"IN 2"<<endl;
		//stop();
	    }
	  
	    if(NXT::Sensor::GetSonarValue(IN_3) < 50){
		cout<<"SONAR"<<endl;
		int v1 = rand() % 2;
		
		if(v1 == 0){
		    setLeftSpin();
		}
		else{
		    setRightSpin();
		}
		
	    }
	    cout<<NXT::Sensor::GetSonarValue(IN_3)<<endl;
	    setBothForward();
	  
	    sleep(1);
	}
    }
    else
	cout << "NXT++ No connection" << endl;
	return 0;
}
