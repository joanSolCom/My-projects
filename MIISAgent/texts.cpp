#include "texts.h"

    /*Definition of the keywords*/

    string texts::keywords[15]={"content","enroll","requirements","subjects",
                            "master thesis","professors","general options"
                            ,"scholarships","prizing","enrollment","goodbye",
                            "greeting","opthow","opcont","not valid"};

    texts::texts() {

        this->init();
    }

    void texts::init(){

        /*Definition of the mandatory transitions of the system*/

        this->mandatoryTransitions["greeting"] = "general options";
        this->mandatoryTransitions["content"] = "opcont";
        this->mandatoryTransitions["enroll"] = "opthow";
        this->mandatoryTransitions["subjects"] = "opcont";
        this->mandatoryTransitions["master thesis"] = "opcont";
        this->mandatoryTransitions["professors"] = "opcont";
        this->mandatoryTransitions["scholarships"] = "opthow";
        this->mandatoryTransitions["prizing"] = "opthow";
        this->mandatoryTransitions["enter"] = "opthow";
        this->mandatoryTransitions["requirements"] = "general options";

        /*Text Definition of each state*/

        this->textMap["greeting"] = "Hi, I'm the virtual guide for the MIIS Master of the UPF";

        this->textMap["general options"] = "What do you want me to explain: The CONTENT of the master? The REQUIREMENTS to enter the master? How to ENROLL the master? If you have enough information, just say the word GOODBYE \n\n";

        this->textMap["content"] = "Future emerging technologies will provide both interactivity and intelligence. This Master's degree addresses these trends, focusing on the design, principles, and use of agents that can interact intelligently with humans and other agents: agents that can perceive, act, learn, plan,and communicate. The lecturers are world-renowned scientists in Artificial Intelligence, Machine Learning, Natural Language, Robotics, and the Internet. This is a 1 year, 60 ECTS credits program. It is structured in 3 terms in which there are different lectures, and at the end of which you have to turn in a master thesis\n\n";

        this->textMap["requirements"] = "In order to be admitted to the Intelligent Interactive Systems - MSc candidates must submit the following documents:\nOfficial undergraduate degree or diploma and the academic transcript of the accredited official training with the average grade at the university of origin. Degrees in any academic field of study will be accepted.\nCurriculum Vitae in English.\nA letter of motivation in English, stating the candidate's interest in following the master's program. \nAt least one letter of recommendation.\nIdentity Card or Passport.\n\n";

        this->textMap["enroll"] = "Here you'll learn how to enroll the master, and all the administrative parts\n\n";

        this->textMap["goodbye"] = "Goodbye, have a nice day \n\n";

        this->textMap["subjects"] = "MACHINE LEARNING by Gabor Lugosi \nThe course covers a number of machine learning formulations and algorithms: from supervised methods, where information provided by a teacher in the form of samples needs to be generalized to unseen situations, to unsupervised methods that learn from experience.\n\nAUTONOMOUS SYSTEMS by Hector Geffner \nThe focus of this course is autonomous behavior, and more precisely, the different methods for developing agents capable of making their own decisions in real or simulated environments. This includes characters in video-games, robots, softbots in the web, etc. \n\nMOBILE ROBOTICS by Vladimir Estivil Castro \nIntroduction to mobile robotics covering practical and theoretical aspects. The course will involve basic notions of robot locomotion, perception, localization, and action; robot architectures, and projects on real robots. \n\nWEB INTELLIGENCE by Ricardo Baeza \nStudy how to gather, process, search and mine data in the Web and its applications to search engines. Understand the basic concepts behind information retrieval and data mining. \n\nNATURAL LANGUAGE PROCESSING by Leo Wanner \nThe course covers the central themes involved in the interaction with intelligent agents through the use of natural language, with emphasis on dialogue and language generation.\n\n";

        this->textMap["master thesis"] = "The student will have to carry out a research project and write a thesis under the supervision of a teacher. Includes a weekly class to present and discuss relevant topics to help decide, develop and present the thesis work.\n\n";

        this->textMap["professors"] = "Ricardo Baeza-Yates (Ph.D., University of Waterloo, 1989). \nPart time Professor at DTIC and VP of Yahoo! Research for Europe. His main research areas are information retrieval, Web data mining and data visualization. He is author of the reference text Modern Information Retrieval. \n\nVladimir Estivill-Castro (Ph.D., University of Waterloo, 1992). Professor.\nCo-editor in Chief of CRPIT. A member of the editorial board of JRPIT and IJDWM. Team leader of MI-PAL and participant in Robocup (2001-2007, 2011-2012). Best known for his work on distance-based clustering and adaptive algorithms. \n\nHector Geffner (Ph.D., UCLA 1989). ICREA Research Professor.\nAssociate Editor of Artificial Intelligence (AIJ) and Journal of Artificial Research (JAIR). Best-known for his work on model-based approaches to autonomous behavior; planning. \n\nGabor Lugosi (Ph.D., Hungarian Academy of Sciences, 1991). ICREA Research Professor. Acting editor of the Journal of Machine Learning Research and member of the editorial board of Machine Learning Journal. Author of three books on statistical learning theory. \n\nLeo Wanner (Ph.D., Saarland University, 1997). ICREA Research Professor. Author of numerous publications in the field of Computational Linguistics and Natural Language Processing. Best known for his work on Natural Language Generation and Computational Lexicology.\n\n";

        this->textMap["prizing"] = "EU students: 2.837,82 € , Non EU students: 7.817,82 €\n\n";

        this->textMap["scholarships"] = "There are available some scholarships that are for full time students. These students will have a monthly salary of 800€ and will be required to teach for 30 hours. Being a full time student means to not be working, and to consider the master as your main work. To get more information about this, please send a mail to vanessa.jimenez@upf.edu\n\n";

        this->textMap["opthow"] = "What do you want to know? Do you want to know about the PRIZING? About the SCHOLARSHIPS? About the HOW to enter process? Or do you want to get back to the GENERAL OPTIONS?\n\n";

        this->textMap["opcont"] = "Do you want to know about the core SUBJECTS? About the MASTER THESIS? About the PROFESSORS? Or do you want to go back to the GENERAL OPTIONS?\n\n";

        this->textMap["not valid"] = "I'm sorry, that is not a valid option; please pick one of the following options.\n\n";

        this->textMap["enter"] = "You can send the documentation until 28/june/2013.\nThe steps to do the pre inscription are:\n\nFill in the on-line application form in the web. Make the application fee payment (€ 30 non-refundable).\nSend photocopies of the required application documents by courier or regular mail to the following address:\n\nPompeu Fabra University Postgraduate and Doctoral Studies Office - Admissions Jaume I Ramon Trias Fargas, 25-27 08005 Barcelona Spain \n\nThe documents are explained in the requirements option of this system. More in depth information please consult the web page.\n\n";


    }

    string texts::searchText(string keyword){

        string cleanKeyword = this->extractKeyword(keyword);

        return this->textMap[cleanKeyword];
    }

    string texts::extractKeyword(string phrase){

        unsigned found;

        for(int i=0;i<KEYWORDS-1;i++){

              found = phrase.find(this->keywords[i]);
              if (found!=std::string::npos){
                  return keywords[i];
              }

        }
        return "not valid";

    }

