#include "texts.h"
#include <iostream>

/*Simple function that transforms the input string into lower case*/

string lowerCase(string input){

    for (unsigned int i=0;i<input.length();i++){
        input[i]=tolower(input[i]);
    }
    return input;
}

/*The interaction*/

void interaction(){

    /*Definition of some auxiliar variables*/

    string input="";
    string text = "";
    string mandatory = "";
    string lastText = "";

    /*We create an instance of our agent*/

    texts *agent = new texts();

    /*We get the goodbye and notvalid texts*/

    string goodbyeText = agent->searchText("goodbye","textMap");
    string notValid = agent->searchText("not valid","textMap");

    /*First Intervention of the agent*/

    cout<<endl;
    cout<<agent->searchText("greeting","textMap")<<"\n"<<endl;
    cout<<agent->searchText("general options","textMap")<<"\n"<<endl;
    lastText = agent->searchText("general options","textMap");
    cout<<"--->";

    getline(cin,input);

    /*Everything the user says will be transformed into lower case*/

    input = lowerCase(input);

    while (1){

        text = agent->searchText(input,"textMap");

        /*If the user's phrase contains the word goodbye, we exit*/

        if(text == goodbyeText){
            break;
        }
        /*If the user introduced not valid text, we repeat the last text shown*/

        else if(text == notValid){

            cout<<text<<endl;
            cout<<lastText<<endl;
        }
        else{
            cout<<text<<endl;

            lastText = text;
        }

        /*
            If there is an unconditional transition, we show the text of the new state
            without having the user to do anything.
        */

        mandatory = agent->searchText(input,"mandatory");

        if(mandatory != ""){
            text = agent->searchText(mandatory,"textMap");
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


