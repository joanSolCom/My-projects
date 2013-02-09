class State:

  #variable definition
  dot = 0
  i = 0
  j = 0
  left, right = '', []
  completed_by = []

  #initialization of the state object
  def __init__(self, left, right, dot, i, j, completed_by = []):
    self.left = left
    self.right = right
    self.dot = dot
    self.i = i
    self.j = j
    self.completed_by = completed_by
  
  #is a state complete?
  def isComplete(self):
    return not self.dot < len(self.right) 
  
  #redefinition of the equality for states
  def __eq__(self, other):
    return (self.left == other.left and self.right == other.right and self.dot == other.dot and self.i == other.i and self.j == other.j)
  
  ##### Definitions useful to print the information in a relatively nice format ######

  def __repr__(self):
    return str(self)
  
  def __str__(self):
    return unicode(self).encode('unicode-escape')
  
  def __unicode__(self):
    right = self.right[:]
    right.insert(self.dot, '.')
    completedBy = ''

    if self.completed_by:
      completedBy = ', %s' % self.completed_by

    t = (self.left, ' '.join(right), self.i, self.j, completedBy)

    return ('(%s -> %s, [%i, %i]%s)' % t)