#get singleton

import os
import sys

args = sys.argv[1:]
nombre = args[0]
clase = open(nombre+'.java','w+')


contenido = "public final class "+nombre+" {\n\n"
contenido += "\tprivate static "+nombre+" instance;\n"
contenido += "\tpublic static "+nombre+" getInstance(){\n"
contenido += "\t\tinstance = (instance==null) ? new "+nombre+"():instance;\n"
contenido += "\t\treturn instance;\n\t}\n\n"
contenido += "\tprivate "+nombre+"(){\n\n\t}\n\n}"

clase.write(contenido)
clase.close()
