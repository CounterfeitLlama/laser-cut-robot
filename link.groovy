CSG bottom = new Cube(40, 120, 30).toCSG()
CSG top = new Cube(40, 120, 30).toCSG().movez(30)
return [bottom, top]