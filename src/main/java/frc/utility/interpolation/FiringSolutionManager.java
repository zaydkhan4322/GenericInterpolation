package frc.utility.interpolation;

import java.util.ArrayList;

public class FiringSolutionManager implements GenericFiringSolutionManager<FiringSolution> {
  private final ArrayList<FiringSolution> solutions;
  private final GenericCalculator<FiringSolution> calculator;

  FiringSolutionManager(GenericCalculator<FiringSolution> calculator) {
    solutions = new ArrayList<>();
    this.calculator = calculator;
  }

  public void addSolution(FiringSolution solution) {
    solutions.add(solution);
    calculator.whenAdded();
  }

  public FiringSolution calcSolution(double currentMag, double currentDeg) {
    FiringSolution inputsToFind = new FiringSolution(currentMag, currentDeg);
    ArrayList<FiringSolution> selectedSolutions = calculator.find(inputsToFind);
    ArrayList<Double> calculatedComponents = calculator.calculate(currentMag, currentDeg, selectedSolutions);
    return new FiringSolution(currentMag, currentDeg, calculatedComponents);
  }
}
