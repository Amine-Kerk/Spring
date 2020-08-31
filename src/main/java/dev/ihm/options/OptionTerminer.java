package dev.ihm.options;

import org.springframework.stereotype.Controller;

import dev.exception.PlatException;

@Controller
public class OptionTerminer implements IOptionMenu {

	@Override
	public int getPoids() {
		return 99999;
	}

	@Override
	public String getTitre() {
		return "Terminer";
	}

	@Override
	public void executer() {
		throw new PlatException("Aurevoir");
	}
}
