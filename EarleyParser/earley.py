from state import State
import sys

class Earley:

  grammar = []
  words = []
  chart = []
  word_pos = {}

  def earleyParse(self, words, grammar,debug = 0):

    #set the grammar and the words in the structure of the class 
    self.grammar = grammar
    self.words = words
 
    #check if all introduced words are valid, if not, we'll exit and show a message
    self.checkWords(words);

    #initializes the chart
    self.chart = [[] for i in range(len(self.words) + 1)]

    #insert the first state
    initialState = State(u'', [u'S'], 0, 0, 0)
    self.enqueue(initialState, 0)
    
    #var to know if we've successfully parsed the input or not
    nCompleted = 0

    #foreach word
    for i in range(len(self.words) + 1):
      #foreach state in the chart
      for state in self.chart[i]:
        #if this state is not complete
        if not state.isComplete():
          #we look if the next element is not part of the parts of speech
          if not self.next_cat_is_pos(state):
            #if not, we predict
            self.predictor(state)
            if debug == 1:
              print "predict: ",state
          #if it is, we scan, and we "eat" a token
          else:
            self.scanner(state)
            if debug == 1:
              print "scan: ",state
        #if the state is complete, we run the completer      
        else:
          self.completer(state)
          
          if debug == 1:
            print "complete: ",state

          #the input has been correctly parsed
          if state.j == len(self.words) and state.i == 0 and state.left == '':
            print "CORRECT!!!: ",state
            nCompleted = nCompleted + 1

    #the input wasn't correct
    if nCompleted == 0:
      print "Not Correct"

    return self.chart

  #definition of the predictor
  def predictor(self, state):
    B = state.right[state.dot]
    j = state.j

    if B in self.grammar:
      #we insert a new state for each alternative expansion of
      #this non-terminal, provided by the grammar
      for rule in self.grammar[B]:
        s = State(B, rule, 0, j, j)
        self.enqueue(s, j)

  #definition of the scanner
  def scanner(self, state):
    B = state.right[state.dot]
    j = state.j

    if j < len(self.words):
      word_i = self.words[j]

      if B in self.parts_of_speech(word_i):
        s = State(B, [word_i], 1, j, (j + 1))
        self.enqueue(s, (j + 1))

  #definition of the completer
  def completer(self, state):
    B = state.left
    j, k = state.i, state.j

    for old_state in self.chart[j]:
      dot = old_state.dot

      #we advance the states that were looking for this grammatical 
      #category at this position in the input
      #we copy the older state, advancing the dot
      if not old_state.isComplete() and old_state.right[dot] == B:
        i = old_state.i
        A = old_state.left
        cb = old_state.completed_by[:]
        s = State(A, old_state.right, (dot + 1), i, k, cb)
        self.enqueue(s, k, state)

  #inserts a state in the chart
  def enqueue(self, state, chart_entry, completed_by = None):

    if not state in self.chart[chart_entry]:
      self.chart[chart_entry].append(state)

    if not completed_by is None and not completed_by in state.completed_by:
      state.completed_by.append(completed_by)

  
  def next_cat_is_pos(self, state):
    next_word = state.right[state.dot]
    
    if (not next_word in self.grammar and not next_word.lower() in self.grammar):
      return False
    
    else:
      return not self.grammar[next_word][0][0] in self.grammar

  def parts_of_speech(self, word):
    
    if not self.word_pos:
      
      for l in self.grammar.keys():
        rules = self.grammar[l]
      
        for alternatives in rules:
      
          for w in alternatives:
      
            if not w in self.grammar:
      
              if not w in self.word_pos:
                self.word_pos[w.lower()] = []

              self.word_pos[w.lower()].append(l)
    
    #We notify that the word is not in the parts of speech
    if not word.lower() in self.word_pos:
      print "Unknown word",word
      sys.exit()

    return self.word_pos[word.lower()]

  #to check if every word in the input is a part of speech
  def checkWords(self,words):

    for w in words:
      self.parts_of_speech(w)

