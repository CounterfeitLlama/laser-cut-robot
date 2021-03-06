import com.neuronrobotics.bowlerstudio.vitamins.Vitamins;
import eu.mihosoft.vrl.v3d.parametrics.*;

CSG bottom = new Cube(50, 120, 5).toCSG().movez(12.5)
CSG top = new Cube(50, 120, 30).toCSG().movez(30)
CSG horn = Vitamins.get("hobbyServoHorn", "standard4").movey(-30).movez(15)//new Cube(45, 45, 5).toCSG().movey(-30).movez(15)

CSG screw = Vitamins.get("capScrew", "M3").movez(42)
CSG screw1 = screw.movex(20).movey(50)
CSG screw2 = screw.movex(-20).movey(50)
CSG screw3 = screw.movex(-20).movey(-50)
CSG screw4 = screw.movex(20).movey(-50)

CSG servo = Vitamins.get("hobbyServo", "towerProMG91").movey(-30)

ArrayList<CSG> allCad = []

top = top.difference(screw1).difference(screw2).difference(screw3).difference(screw4).difference(horn)
bottom = bottom.difference(screw1).difference(screw2).difference(screw3).difference(screw4).difference(horn).difference(servo)

allCad.add(top.movez(50))
allCad.add(bottom)
allCad.add(screw1.movez(120))
allCad.add(screw2.movez(120))
allCad.add(screw3.movez(120))
allCad.add(screw4.movez(120))
allCad.add(servo)

return allCad