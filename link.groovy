import com.neuronrobotics.bowlerstudio.vitamins.Vitamins;
import eu.mihosoft.vrl.v3d.parametrics.*;

class allViataminMaker implements IParameterChanged{
	ArrayList <CSG> examples = null
	ArrayList <CSG> getParts(){
		if(examples!=null)
			return examples
		examples = new ArrayList<>()
		int numVits = 0;
		for(String type: Vitamins.listVitaminTypes()){
			String script = Vitamins.getMeta(type).get("scriptGit")
			println type+" from "+script
			for(String size:Vitamins.listVitaminSizes(type) ){
		
				HashMap<String, Object>  vitaminData = Vitamins.getConfiguration( type,size)
				println "\tSize = "+size+" "+vitaminData
			}
			
			if(script!=null){
				// Grab the first vitamin from the list and load that
				ArrayList<String> options = Vitamins.listVitaminSizes(type);
				StringParameter typParam = new StringParameter(	type+" Default",
														options.get(0),
														options)
				CSG vitaminFromScript = Vitamins.get("capScrew","6#32")
									.movey(40*numVits++)
				CSGDatabase.addParameterListener(typParam.getName(),this);
				vitaminFromScript.setRegenerate({getParts()})					
				examples.add(vitaminFromScript)
			}else
				println "ERROR no script for "+type
		}
		for(int i=0;i<examples.size();i++){
			int index = i
			examples.get(i)
				.setRegenerate({getParts().get(index)})
		}
		return examples
	}
	/**
	 * This is a listener for a parameter changing
	 * @param name
	 * @param p
	 */
	 
	public void parameterChanged(String name, Parameter p){
		println name+" changed "+p.getStrValue()
		examples=null
	}
	
}

new allViataminMaker().getParts()
/*
CSG bottom = new Cube(50, 120, 30).toCSG()
CSG top = new Cube(50, 120, 30).toCSG().movez(30)
CSG horn = new Cube(45, 45, 5).toCSG().movey(-30).movez(15)

top = top.difference(horn).movez(30)
bottom = bottom.difference(horn)
return [bottom, top]*/