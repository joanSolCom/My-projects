#include "texts.h"
#include <iostream>

string lowerCase(string input){

    for (int i=0;i<input.length();i++){
        input[i]=tolower(input[i]);
    }
    return input;
}

void interaction(){

    texts *agent = new texts();
    string input="";
    string text = "";
    string mandatory = "";
    string lastText = "";

    cout<<agent->searchText("greeting")<<"\n"<<endl;
    cout<<agent->searchText("general options")<<"\n"<<endl;
    lastText = agent->searchText("general options");
    cout<<"--->";
    getline(cin,input);
    input = lowerCase(input);

    while (1){

        text = agent->searchText(input);


        if(text == "Goodbye, have a nice day"){
            break;
        }
        else if(text=="I'm sorry, that is not a valid option; please pick one of the following options.\n\n"){

            cout<<text<<endl;
            cout<<lastText<<endl;
        }
        else{
            cout<<text<<endl;

            lastText = text;
        }

        mandatory = agent->mandatoryTransitions[input];

        if(mandatory != ""){
            text = agent->searchText(mandatory);

            cout<<text<<endl;
            lastText = text;
        }

        cout<<"--->";
        getline(cin,input);
        input = lowerCase(input);


    }



}




int main(int argc, char *argv[])
{

    interaction();

    return 1;
}


