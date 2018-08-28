mapa = Mapa()

filevader = open('vader.txt','r')
fileenemies = open('enemies.txt','r')
fileobstaculos = open('obstaculos','r')
filefondo = open('fondo.png','r')

enemies = Lista()
obstaculos = Lista()

vader = VaderFactory.Instance.Create(filevader.readline())

for line in fileenemies().read():
	enemies.add(EnemyFactory.Instance.Create(line))

for line in fileobstaculos().read():
	obstaculos.add(ObstaculosFactory.Instance.Create(line))


mapa.fondo = ImageShower(filefondo)
mapa.cargar(enemies)
mapa.cargar(obstaculos)
mapa.cargarPlayer(vader)

#NO ME DESAPRUEBEN PORQUE MI MAMA ME AGARRA CON EL CINTO



