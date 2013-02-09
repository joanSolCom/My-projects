from earley import Earley

if __name__ == '__main__':
  import sys
  import json
  
  if len(sys.argv) < 3:
    print 'Program needs to be invoked like this:\n'
    print 'python earleyParser.py grammar.json "sentence to parse"\n'
    print 'you can turn on the debug mode changing the debug variable to 1'
    print 'to see the whole process'
    print '\nExamples of phrases the program recognises:'
    print '--->I want to know about the subjects'
    print '--->tell me about the content'
    print '--->about prizing'
    print '--->enrollment'
    print '--->tell me about subjects'
    sys.exit()

  grammar_file = open(sys.argv[1])
  words = sys.argv[2].split(' ')
  grammar = json.load(grammar_file)
  
  e = Earley()
  debug = 0
  chart = e.earleyParse(words, grammar,debug)

