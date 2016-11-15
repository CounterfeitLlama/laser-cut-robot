CSG bottom = new Cube(50, 120, 30).toCSG()
CSG top = new Cube(50, 120, 30).toCSG().movez(30)
CSG horn = new Cube(45, 45, 5).toCSG().movey(-30).movez(15)

top = top.difference(horn).movez(30)
bottom = bottom.difference(horn)
return [bottom, top]