from sys import argv
import os
import sys
classes = list(argv[1:])
cant = len(classes)
capital = lambda x:x[0].upper()+x[1:].lower()
filename = lambda x: 'Scripts\\'+capital(x)+".java"

texto1 = lambda x : str(open('_partscreator\\part1.part','r').read()).format(x)
texto2 = lambda x : str(open('_partscreator\\part2.no','r').read())
texto3 = lambda x : str(open('_partscreator\\part3.part','r').read()).format(x)
texto4 = lambda x : str(open('_partscreator\\part4.no','r').read())
texto5 = lambda x : str(open('_partscreator\\part5.part','r').read()).format(x)
texto6 = lambda x : str(open('_partscreator\\part6.no','r').read())
texto7 = lambda x : str(open('_partscreator\\part7.part','r').read()).format(x)
texto8 = lambda x : str(open('_partscreator\\part8.no','r').read())


def create(classname):
	classname = capital(classname)
	contenido = texto1(classname)+texto2(classname)+texto3(classname)+texto4(classname)+texto5(classname)+texto6(classname)+texto7(classname)+texto8(classname)
	f = open(filename(classname),"w+")
	f.write(contenido)
	f.close()

for cl in classes:
	create(cl)
