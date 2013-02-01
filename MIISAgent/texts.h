#include <map>
#include <string>

#ifndef TEXTS_H
#define TEXTS_H
#define KEYWORDS 15

using namespace std;

class texts{

    public:

        map<string, string> textMap;
        map<string, string> mandatoryTransitions;
        static string keywords [KEYWORDS];


        texts();
        void init();
        string searchText(string keyword,string array);
        string extractKeyword(string phrase);
};

#endif // TEXTS_H
