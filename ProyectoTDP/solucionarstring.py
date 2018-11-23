a = "only coin;point;rewardfire;rewardforce;rewardhealth;rewardshield;mouseAim;life;levintro3;levintro2;levintro1;wooky;bigbar;bossbar;shipplayer;commontie1;commontie2;hybridtie;shield;whitetie;soloship;vadership_a;vadership_b;vadership_c;bforcefield;forcefield;fexplo;bfexplo;exploA;exploB;bg_space;bg_water;bg_sand;black;"

stringjava = lambda s : "\"{0}\"".format(s)

x = "{"
for s in a.split(";"):
	x+= stringjava(s)+","
x+="}"
print(x)


