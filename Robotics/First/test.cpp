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
    sleep(1);
    exit(signum);
}

//This function sets one motor forward and the other backward to make a right spin (motors set at 50% power).
void setLeftSpin(){
    stop();
    NXT::Motor::SetReverse(OUT_C, 50);
    NXT::Motor::SetForward(OUT_B, 50);
    usleep(200000);
    stop();
}

void setRightSpin(){
    stop();
    NXT::Motor::SetReverse(OUT_B, 50);
    NXT::Motor::SetForward(OUT_C, 50);
    usleep(200000);
    stop();
}

//This function sets the both motors back at 50% power.
void setBothBack(){
    stop();
    NXT::Motor::SetReverse(OUT_B, 50);
    NXT::Motor::SetReverse(OUT_C, 50);
    usleep(200000);
    stop();
}

//This function sets the both motors forwards at 50% power.
void setBothForward(){
    stop();
    NXT::Motor::SetForward(OUT_B, 50);
    NXT::Motor::SetForward(OUT_C, 50);
}

int main()
{
    if(NXT::Open()) //initialize the NXT and continue if it succeds
    {
      
	cout<<"hola"<<endl;
		NXT::Sensor::SetTouch(IN_1); 
		NXT::Sensor::SetSonar(IN_2);
		NXT::Sensor::SetSonar(IN_3);
		signal(SIGINT, signal_callback_handler);
		while(1) //main loop
		{
		    int left = NXT::Sensor::GetSonarValue(IN_2);
		    int right = NXT::Sensor::GetSonarValue(IN_3);
		    if(left < right && left < 30){
			setBothBack();
						//setRightSpin();
						setLeftSpin();

		    }
		    else if(right < left && right < 30){
			setBothBack();
						//setLeftSpin();
						setRightSpin();

			
		    }
		
		    
		  /*  if(right){
		      setBothBack();
		    	setRightSpin();
		    }*/

		    setBothForward();
		    sleep(1);
		}
    }
    else
		cout << "NXT++ No connection" << endl;
	
	return 0;
}
