# ObjParser
Simple .obj to .c arrays parser

Simply parses .obj files and putting it into c arrays grouping by materials.
#Usage:
Curent directory where parser is launching should contain at least directory named "source" with .obj files in it. 
Launch it with "fileName" paramener, it will search for source/fileName.obj file and will create target/fileName.c file and target/fileName.h file
#Known issues
-If target file names contains some characters, that is forbidden for variable name characters in C - code just creates C code with error, i.e. just doing nothing
-If .obj file name contains faces with more than 4 vertices - code can't parse it
-Code now parses just one file at a time in a specified directories. The code should work
-Code won't parse faces with no normals right
-Code won't pase faces with less than 3 vertives right
-Every vector (color, normal, v) should contain 3 dimentions, vt should contain 2
-.obj should have material lib. all the faces should be assigned to some material.
