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
        static string keywords [15];


        texts();
        void init();
        string searchText(string keyword);
        string extractKeyword(string phrase);
};

#endif // TEXTS_H
