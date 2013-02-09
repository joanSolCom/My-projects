from earley import Earley

if __name__ == '__main__':
  import sys
  import json
  
  if len(sys.argv) < 3:
    print 'Input needs to be of this form:'
    print 'earleyParser.py grammar.json "sentence to parse"'
    sys.exit()

  grammar_file = open(sys.argv[1])
  words = sys.argv[2].split(' ')
  grammar = json.load(grammar_file)
  
  e = Earley()
  debug = 0
  chart = e.earleyParse(words, grammar,debug)

