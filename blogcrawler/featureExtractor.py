import re
import MySQLdb
import sys
import pprint
from nltk import bigrams


def get_words_from_string(s):
    return set(re.findall(re.compile('\w+'), s.lower()))

def get_words_from_file(fname):
    with open(fname, 'rb') as inf:
        return inf.read().split()

def all_words(needle, haystack):
    return set(needle).issubset(set(haystack))

def any_words(needle, haystack):
    return set(needle).intersection(set(haystack))

def get_posts_men():
	
	# Open database connection
	db = MySQLdb.connect("localhost","admin","LxmANqhA6aA=","blogcrawler" )

	# prepare a cursor object using cursor() method
	cursor = db.cursor()
	
	postsMen = []
	
	cursor.execute("SELECT id FROM post WHERE crawled = 1 AND genre = 1")
	data = cursor.fetchall()

	for row in data:
			postsMen.append(int(row[0]))
			
	# disconnect from server
	db.close()
	return postsMen
	
def get_posts_women():
	
	# Open database connection
	db = MySQLdb.connect("localhost","admin","LxmANqhA6aA=","blogcrawler" )

	# prepare a cursor object using cursor() method
	cursor = db.cursor()
	
	postsWomen = []
	
	cursor.execute("SELECT id FROM post WHERE crawled = 1 AND genre = 0")
	data = cursor.fetchall()

	for row in data:
			postsWomen.append(int(row[0]))
			
	# disconnect from server
	db.close()
	return postsWomen
	

def get_women_words():

	filesW = get_posts_women()

	wordFreqW = {}

	for file in filesW:
	
		words = get_words_from_file("/var/www/vhosts/default/htdocs/dataset/"+str(file)+".txt")
	
		for word in words:
			word = word.lower()
			key = wordFreqW.get(word)
			
			if key is None:
				wordFreqW[word] = 1
			else:
				wordFreqW[word] = wordFreqW[word] + 1
	
	wordFreqW = sorted(wordFreqW.items(), key=lambda x: x[1],reverse=True)
	return wordFreqW
	
def get_men_words():

	filesM = get_posts_men()
	wordFreqM = {}

	for file in filesM:

        	words = get_words_from_file("/var/www/vhosts/default/htdocs/dataset/"+str(file)+".txt")

        	for word in words:

			word = word.lower()
                	key = wordFreqM.get(word)

                	if key is None:
                        	wordFreqM[word] = 1
                	else:
        	                wordFreqM[word] = wordFreqM[word] + 1
	


	wordFreqM = sorted(wordFreqM.items(), key=lambda x: x[1],reverse=True)
	return wordFreqM


def get_men_totalwordmean():

	filesM = get_posts_men()
	meanM = 0
	nfiles = len(filesM)

	for file in filesM:
		words = get_words_from_file("/var/www/vhosts/default/htdocs/dataset/"+str(file)+".txt")
		nwords = len(words)
		meanM = meanM + nwords
		
	meanM = float(meanM) / nfiles

	return meanM

def get_women_totalwordmean():

        filesW = get_posts_women()
        meanW = 0
        nfiles = len(filesW)

        for file in filesW:
                words = get_words_from_file("/var/www/vhosts/default/htdocs/dataset/"+str(file)+".txt")
                nwords = len(words)
                meanW = meanW + nwords

        meanW = float(meanW) / nfiles

        return meanW

def get_charsperword(sex):

	if sex == "m":
		files = get_posts_men()

	else:
		files = get_posts_women()

	meancharsword = 0
	nfiles = len(files)

	for file in files:
		words = get_words_from_file("/var/www/vhosts/default/htdocs/dataset/"+str(file)+".txt")
		nwords = len(words)
		ncharsword = 0

		for word in words:
			nchars = len(word)
			ncharsword = ncharsword + nchars
			
		ncharsword = float(ncharsword) / nwords
		meancharsword = meancharsword + ncharsword
		
	meancharsword = float(meancharsword) / nfiles
	return meancharsword

def get_totalnumchars(sex):

	if sex == "m":
                files = get_posts_men()

        else:
                files = get_posts_women()

        meanchars = 0
        nfiles = len(files)

	for file in files:
		infile = open("/var/www/vhosts/default/htdocs/dataset/"+str(file)+".txt",'r')
		nchars = 0
		while infile.read(1):
    		      nchars = nchars + 1
		meanchars = meanchars + nchars

	
	print meanchars
	meanchars = float(meanchars) / nfiles 
	return meanchars


def get_totalnumupper(sex):

        if sex == "m":
                files = get_posts_men()

        else:
                files = get_posts_women()

        meanuppers = 0
        nfiles = len(files)
	meanuppersfile = 0

        for file in files:
		words = get_words_from_file("/var/www/vhosts/default/htdocs/dataset/"+str(file)+".txt")
                nwords = len(words)
		nuppers = 0
		nchars = 0
		for word in words:
			for char in word:
				if char.isupper():
					nuppers = nuppers + 1
				nchars = nchars + 1
	
        	meanuppersfile = float(nuppers) / nchars
		meanuppers = meanuppers + meanuppersfile        
	
	meanuppers = float(meanuppers) / nfiles
	return meanuppers


def get_bigrams(sex):

	if sex == "m":
                files = get_posts_men()

        else:
                files = get_posts_women()

	bigramarray = {}
	bigramss = []	
	for file in files:

		words = get_words_from_file("/var/www/vhosts/default/htdocs/dataset/"+str(file)+".txt")
		#bigramss =  bigrams(words)[x.lower() for x in words]
		bigramss =  bigrams(words)
		for bigram in bigramss:
			
			key = bigramarray.get(bigram)

                        if key is None:
                                bigramarray[bigram] = 1
                        else:
                                bigramarray[bigram] = bigramarray[bigram] + 1

	bigramarray = sorted(bigramarray.items(), key=lambda x: x[1],reverse=True)
	return bigramarray

pprint.pprint(get_bigrams("w"))
	
#print str(get_totalnumchars("m"))
#print str(get_totalnumchars("w"))
#print str(get_totalnumupper("w"))
#print str(get_totalnumupper("m"))
#pprint.pprint(get_women_words())
#print str(get_women_totalwordmean())
#print "Mean chars per word men"
#print str(get_charsperword("m"))
#print "Mean chars per word women"
#print str(get_charsperword("w"))

