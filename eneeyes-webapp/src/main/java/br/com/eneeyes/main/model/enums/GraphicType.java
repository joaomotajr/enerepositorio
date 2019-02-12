package br.com.eneeyes.main.model.enums;

/**
 *  
 */

public enum GraphicType {
	
	BULBINDICATOR("Bulbo"), CYLINDERFILL("Cilindro"), LINEARSCALE("Linear"), QUARTERGAUGE("Gauge"), RATINGMETER("Rating"), SPEEDOMETER("Velocimetro"), THERMOMETERDISPLAY("Termometro"), VERTICALLEDS("Vertical"), VBULLET("Bala-Vertical"), HBULLET("Bala-Horizontal");
	
	private String model;

	private GraphicType(String model) {
		this.model = model;
	}

	public String GetModel() {
		return model;
	}	    
}
